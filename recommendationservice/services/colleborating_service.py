import os
import datetime
import pandas as pd
from ..model.model_registry import Model_Registry
import surprise as sp
from collections import defaultdict
from sqlalchemy.orm import Session
from sklearn.model_selection import train_test_split
from surprise import Reader, Dataset

def train_and_import(db: Session, model_path: str):
    
    query = "SELECT user_id, movie_id, score from ratings"
    with db.bind.connect() as connection:
        df = pd.read_sql(query,db.bind)
    
    if df.empty:
        print("No data to train on")
        return False
    
    '''Should I pre processing in the df?
            Depend the choose ML Library 
    '''
    reader = Reader(rating_scale=(1,5))

    data = sp.Dataset.load_from_df(df[['user_id','movie_id','rating']],reader)
    
    trainset, testset = train_test_split(data, test_size=0.2, random_state=42)
    
    '''Called model and train'''
    svd = sp.SVD()
    svd.fit(trainset)
    
    '''Validated Model, Should I saved model
            if model is failure or accuracy low,...
                Don't need save it'''
    '''Accuracy,...'''
    new_metrics = evaluate_performance(svd, trainset, testset)
    
    # get the current active model
    active_model = db.query(Model_Registry).filter(Model_Registry.is_active == True).first()
    
    should_activate_new_model = False
    
    if active_model:
        '''Compare new model with active model'''
        if new_metrics["RMSE"] < active_model.rmse:
            '''New model is better, deactivate old model'''
            active_model.is_active = False
            db.commit()
            should_activate_new_model = True
    else:
        '''No active model, activate new model'''
        should_activate_new_model = True
        
    '''Saved Model in path '''
    save_model(svd,model_path)
    
    '''Insert model_registry object to DB'''
    model_registry = Model_Registry(
        model_name = "SVD",
        version = 1.0,
        model_path = model_path,
        rmse = new_metrics["RMSE"],
        mae = new_metrics["MAE"],
        precision = new_metrics["Precision@10"],
        recall = new_metrics["Recall@10"],
        f1_score = new_metrics["F1-Score@10"],
        is_active = should_activate_new_model,
        created_at = datetime.utcnow()
    )
    '''Insert to Model Table'''
    insert_model_to_db(db, model_registry)
    
    return True

def insert_model_to_db(db: Session, model_registry: Model_Registry):

    db.add(model_registry)
    db.commit()
    db.refresh(model_registry)
    
    if model_registry.id:
        print(f"Model registered with ID: {model_registry.id}")
    else:
        print("Failed to register model.")
    
def get_top_n(predicsions,n = 10):
    
    top_n = defaultdict(list)
    for uid, iid, true_r, est, _ in predicsions:
        top_n[uid].append((iid,est,true_r))
        
    for uid, user_ratings in top_n.items():
        user_ratings.sort(key= lambda x: x[1],reverse=True)
        top_n[uid] = user_ratings[:n]

def precision_recall_f1(predictions,k ,threshold):
    
    top_n = get_top_n(predictions,n= k)
    precisions = defaultdict(float)
    recalls = defaultdict(float)
    f1_scores = defaultdict(float)
    
    for uid, user_ratings in top_n.items():
        all_user_ratings = [p for p in predictions if p.uid == uid]
        
        '''True positives + False negatives'''
        n_rel = sum((p.r_ui > threshold ) for p in predictions if p.uid == uid)
        
        '''True positives'''
        n_rec_rel = sum((p.true_r > threshold) for p in predictions if p.uid == uid)
        
        # Top N
        n_rec = len(user_ratings)
        
        precisions[uid] = n_rec_rel / n_rec if n_rec != 0 else 0
        
        recalls[uid] = n_rec_rel / n_rel if n_rel != 0 else 0
        
        if(precisions[uid] + recalls[uid] > 0):
            f1_scores[uid] = 2*(precisions[uid] * recalls[uid])/ (precisions[uid] + recalls[uid])
        else:
            0
    
    if len(precisions) == 0: return 0.0, 0.0, 0.0
    
    return (
        sum(precisions.values()) / len(precisions),
        sum(recalls.values()) / len(recalls),
        sum(f1_scores.values()) / len(f1_scores)
    )
    

def evaluate_performance(svd, testset):
    predictions = svd.test(testset)
    
    rmse = sp.accuracy.rmse(predictions, verbose=False)
    mae = sp.accuracy.mae(predictions, verbose=False)
    P, R, F1 = precision_recall_f1(predictions, k=10, threshold=3.5)

    print("\n### Model Evaluation Summary ###")
    print(f"| Metric | Value | Interpretation |")
    print(f"| :--- | :---: | :--- |")
    print(f"| **RMSE** | {rmse:.4f} | Average error in predicting the rating (lower is better) |")
    print(f"| **MAE** | {mae:.4f} | Average absolute error in rating prediction (lower is better) |")
    print(f"| **Precision@10** | {P:.4f} | Percentage of Top-10 recommendations that were relevant |")
    print(f"| **Recall@10** | {R:.4f} | Percentage of all relevant items that were found in the Top-10 list |")
    print(f"| **F1-Score@10** | {F1:.4f} | Harmonic Mean of Precision and Recall (best balance) |")
    print("--------------------------------------------------")
    
    # Return Json object if needed
    return {
        "RMSE": rmse,
        "MAE": mae,
        "Precision@10": P,
        "Recall@10": R,
        "F1-Score@10": F1
    }

def save_model(svd, path):
    if not path:
        raise ValueError("Model path must be provided to save the model.")
    
    fileName = f"model_{datetime.datetime.now().strftime('%Y%m%d_%H%M%S')}.pkl"
    full_path = os.path.join(path, fileName)
    sp.dump.dump(path, algorithm=svd)
    return full_path