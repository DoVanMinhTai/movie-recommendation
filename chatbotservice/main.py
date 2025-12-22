from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List, Optional
from app.agent.agentcontroller import AgentController

app = FastAPI(title="Movie Agent Service", version="3.0")

# Khởi tạo Agent
agent = AgentController()

class ChatRequest(BaseModel):
    user_id: int
    message: str
    history: Optional[List[dict]] = []

@app.post("/api/v1/chat")
async def chat_endpoint(request: ChatRequest):
    try:
        # Gọi Agent để xử lý
        response = await agent.handle_request(
            user_id=request.user_id,
            message=request.message,
            history=request.history
        )
        return response
    
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)