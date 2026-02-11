from pydantic import BaseModel, Field

class ChatRequest(BaseModel):
    user_id: int = Field()
    message: str = Field()
    
    