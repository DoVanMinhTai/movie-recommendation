from fastapi import FastAPI
from contextlib import asynccontextmanager
from app.controller import chat_controller as chat_controller
from app.dependencies import chatbot_container as chatbot_container 
from app.services.chatbot_service import ChatBotService

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Initialize the instance inside the container
    chatbot_container.chatbot_instance = ChatBotService()
    yield
    chatbot_container.chatbot_instance = None

app = FastAPI(lifespan=lifespan, title="Movie Recommendation Chatbot")

# Now this will work because chat_controller will import from dependencies.py, not main.py
app.include_router(chat_controller.router)

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000)