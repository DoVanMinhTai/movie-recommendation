from sqlalchemy import Column, Integer, String, Float, Boolean, DateTime
from sqlalchemy.orm import declarative_base, sessionmaker
from datetime import datetime
from sqlalchemy import create_engine
import os

DATABASE_URL = os.getenv(
    "DATABASE_URL",
    "postgresql+psycopg2://postgres:admin@localhost:5432/MovieRecommendation"
) 

engine = create_engine(DATABASE_URL, echo=True)
SessionLocal = sessionmaker(autocommit= False, bind=engine, autoflush=False)
Base = declarative_base()

class Model_Registry(Base):
    __tablename__ = "model_registry"

    id = Column(Integer, primary_key=True, index=True)
    model_name = Column(String)
    version = Column(Float)
    model_path = Column(String)     
    rmse = Column(Float)           
    mae = Column(Float)
    precision = Column(Float)
    recall = Column(Float)
    f1_score = Column(Float)
    is_active = Column(Boolean, default=False)
    created_at = Column(DateTime, default=datetime.utcnow)