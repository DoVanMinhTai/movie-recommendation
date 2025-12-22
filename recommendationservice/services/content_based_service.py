import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import linear_kernel
from sqlalchemy.orm import Session
from ..model.model_registry import Model_Registry
import os
from .colleborating_service import save_model, insert_model_to_db
class ContentBasedService:
    
    def __init__(self, db: Session, model_dir: str):
        self.db = db
        self.model_dir = model_dir
        
    def find_similar_movies(self, movie_id: int, top_n: int = 10):
        model_path_sim = os.path.join(self.model_dir, "cosine_sim.pkl")
        model_path_df = os.path.join(self.model_dir, "content_df.pkl")
        
        if not os.path.exists(model_path_sim) or not os.path.exists(model_path_df):
            return {"error": "Model artifacts not found. Please train the model first."}
        
        import pickle
        with open(model_path_sim, "rb") as f:
            cosine_sim = pickle.load(f)
        with open(model_path_df, "rb") as f:
            df = pickle.load(f)
            
        if movie_id not in df["movie_id"].values:
            return {"error": "Movie ID not found in the dataset."}
        
        idx = df.index[df["movie_id"] == movie_id][0]
        sim_scores = list(enumerate(cosine_sim[idx]))
        sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
        
        sim_scores = sim_scores[1:top_n+1]
        movie_indices = [i[0] for i in sim_scores]
        similar_movies = df.iloc[movie_indices][["movie_id", "title"]]
        return similar_movies.to_dict(orient="records")
    
    def content_based_filtering_reTraining(db: Session, mode_dir: str):
    
        query = "Select movies.movie_id,genres.name,movies.title, movies.overview  from movies Inner join movie_genres on movies.movie_id = movie_genres.movie_id INNER join genres on movie_genres.genre_id = genres.id"
        with db.bind.connect() as connection:
            df = pd.read_sql(query,db.bind)
        
        if df.empty:
            print("No data to train on")
            return False
        
        '''Preprocessing DataFrame'''
        df["content"] = df["title"] + " " + df["overview"] + " " + df["name"]
        df["content"] = df["content"].fillna("").astype("str")
        
        '''Create TF-IDF Matrix'''
        tfidf = TfidfVectorizer(stop_words="english")
        tfidf_matrix = tfidf.fit_transform(df["content"])
        
        '''Compute Cosine Similarity'''
        cosine_sim = linear_kernel(tfidf_matrix, tfidf_matrix)
        
        file_path_sim = f"{mode_dir}/cosine_sim.pkl"
        file_path_df = f"{mode_dir}/content_df.pkl"
        
        '''Save Model Artifacts'''
        with open(file_path_sim, "wb") as f:
            import pickle
            pickle.dump(cosine_sim, f)
        with open(file_path_df, "wb") as f:
            import pickle
            pickle.dump(df, f)
            
        '''Insert to Model Registry'''
        
        model_registry = Model_Registry(
            model_name="content_based_filtering",
            version=1.0,
            model_path=mode_dir,
            rmse=0.0,
            mae=0.0,
            precision=0.0,
            recall=0.0,
            f1_score=0.0,
            is_active=True
        )
        
        insert_model_to_db(db, model_registry)
        
        return {"status": "Content-based filtering model trained and saved successfully."}
        