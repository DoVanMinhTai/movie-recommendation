from gpt4all import GPT4All
import os

class LocalLLMChat:
    _instance = None
    _model = None

    def __new__(cls):
        # Pattern Singleton: Đảm bảo chỉ load model 1 lần duy nhất
        if cls._instance is None:
            cls._instance = super(LocalLLMChat, cls).__new__(cls)
            print("--> [LOADING LLM] Đang tải model GPT4All (Qwen2-1.5B)...")
            
            # allow_download=False nếu bạn đã tải file về máy để chạy offline hoàn toàn
            # device='cpu' để chạy trên CPU
            # Model Qwen2 1.5B rất nhẹ (~1GB RAM)
            cls._model = GPT4All("qwen2-1_5b-instruct-q4_0.gguf", device='cpu') 
            
            print("--> [LOADING LLM] Đã tải xong Model!")
        return cls._instance

    def generate_response(self, user_message: str, history: list = []):
        """
        Hàm sinh câu trả lời
        """
        # Tạo System Prompt để định hình tính cách cho Bot
        system_prompt = "Bạn là một trợ lý ảo am hiểu về điện ảnh. Hãy trả lời ngắn gọn, thân thiện bằng tiếng Việt."
        
        # Xây dựng ngữ cảnh hội thoại (Prompt Template)
        # GPT4All quản lý context khá đơn giản
        full_prompt = f"System: {system_prompt}\n"
        
        # Thêm lịch sử chat ngắn (nếu có)
        for msg in history[-3:]: # Chỉ lấy 3 câu gần nhất để đỡ tốn RAM
            role = "User" if msg.get('role') == 'user' else "Bot"
            full_prompt += f"{role}: {msg.get('content')}\n"
            
        full_prompt += f"User: {user_message}\nBot:"

        # Gọi Model sinh text
        # max_tokens=150: Trả lời ngắn gọn thôi
        with self._model.chat_session():
             response = self._model.generate(full_prompt, max_tokens=200, temp=0.7)
        
        return response

# Khởi tạo instance toàn cục để dùng
llm_instance = LocalLLMChat()

def knowledge_chat_tool(message: str, history: list):
    """
    Hàm wrapper để AgentController gọi
    """
    try:
        reply = llm_instance.generate_response(message, history)
        return reply
    except Exception as e:
        print(f"Lỗi LLM: {e}")
        return "Xin lỗi, hiện tại bộ não AI của tôi đang quá tải. Bạn hãy thử lại sau nhé!"