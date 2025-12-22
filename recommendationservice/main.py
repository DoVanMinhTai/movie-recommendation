from fastapi import FastAPI, Depends, BackgroundTasks
from fastapi.concurrency import asynccontextmanager
from pydantic import BaseModel
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, Session
from typing import ClassVar, List, Optional
from .services import content_based_service
from pydantic_settings import BaseSettings
import glob, os, joblib, uvicorn
from pathlib import Path

DATABASE_URL = "postgresql://postgres:admin@localhost/MovieRecommendation"

engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

def load_model_latest():
    search_Path = os.path.join(settings.model_base,"*.pkl")
    list_of_files = glob.glob(search_Path)
    
    if not list_of_files:
        print(f"WARNING: No model files found matching pattern: {search_Path}")
        return None
    
    '''How to get model trained of week, i think i will saved model by Month, day, year'''
    latest_file = max(list_of_files, key=os.path.getctime)
    return joblib.load(latest_file)
    
@asynccontextmanager
async def lifespan(app: FastAPI):
    models = load_model_latest()
    if(models): 
        ml_models['recommender'] = models
    else:
        ml_models['recommender'] = None
        print("WARNING: Application starting without a trained ML model.")
    yield
    ml_models.clear()
    
    
app = FastAPI(lifespan=lifespan)

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


class Settings(BaseSettings):
    database_url: str = "postgresql://postgres:admin@localhost/MovieRecommendation"
    model_base : ClassVar[Path] = (Path(__file__).parent / ".." / "model_store").resolve()
    
    class Config:
        env_file = ".env"
        
settings = Settings()

class RecommendationItem(BaseModel):
    movie_id: int
    score: float


class RecommendationResponse(BaseModel):
    strategy: str
    data: List[RecommendationItem]
    
class UserContext(BaseModel):
    user_Id: Optional[int] = None
    selected_Genres: Optional[List[str]] = None
    is_new_user: bool = True


ml_models = {}
    
@app.get("/recommend/item/{movie_id}")
def get_similar_movies(movie_id: int):
    return content_based_service.find_similar_movies(movie_id)

@app.post("/recommend/user")
def get_user_feed(ctx : UserContext, db: Session = Depends(get_db)):
    if ctx.selected_Genres:
        return query_db_by_genre(ctx.selected_Genres,db)
    elif not ctx.is_new_user and ctx.user_Id:
        return collaborative_filtering(ctx.user_Id)
    return get_popular_movies(db)

def query_db_by_genre(listStr: List[str]):
    return 
    
def collaborative_filtering(user_id: int):
    return

def get_popular_movies():
    return
    


@app.post("/admin/reload-Model")
def refresh_model():
    new_model = load_model_latest()
    if new_model:
        ml_models["recommer"] = load_model_latest()
        return {"status": "Loaded model succesfully"}
    return {"status": "Failed to load model"}


@app.get("/")
def read_root(db: Session = Depends(get_db)):
    return {"message": "Connected to PostgreSQL!"}

# Background Tasks
@app.post("/retrainingmodel")
async def retraining_model(background_tasks: BackgroundTasks, db: Session = Depends(get_db)):
    '''When api send it to recommendation service, recommendaton service receive request and run backgrounds task 
            check status when run model 
            return "response receive request"
            but How to send I response when model is runned if model have error , how should I save and return response status with background tasks
    '''
    import uuid
    job_id = str(uuid.uuid4())
    
    background_tasks.add_task(train_model,job_id)
    return {"message": "Model retraining started", "job_id": job_id}
    
def train_model(job_id: str):
    from .services.training_service import train_and_import as train_model_service
    model_path = os.path.join(settings.model_base, "model_"+ str(len(os.listdir(settings.model_base))+1) +".pkl")
    print(f"Starting model training with job ID: {job_id}")
    db = SessionLocal()
    try:
        success = train_model_service(db, model_path)
    
        if success:
            print("Model retraining and import completed successfully.")
        else:
            print("Model retraining and import failed.")  
    except Exception as e:
        print(f"Error during model retraining with job ID {job_id}: {e}")
if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)