import os
from pathlib import Path
from typing import Dict, List
from pydantic_settings import BaseSettings, SettingsConfigDict
BASE_DIR = Path(__file__).resolve().parent.parent.parent.parent
class Settings(BaseSettings):
    # Use '...' to tell Pylance: "This is required, but don't expect it in __init__"
    model_path: str = "..."
    es_host: str = "..."
    recommendation_service_url: str = "..."
    embed_model_name: str = "..."
    llm_model_name: str = "..."
    
    intent_anchors: Dict[str, List[str]] = ...
    genre_map: Dict[str, str] = ...

    model_config = SettingsConfigDict(
        env_file=os.path.join(BASE_DIR, ".env"),
        env_file_encoding="utf-8",
        extra="ignore"
    )

settings = Settings()