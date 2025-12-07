from gpt4all import GPT4All
import os

class LocalLLM:
    _instance = None
    _model = None
    
    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(LocalLLM, cls).__new__(cls)
            
            print(f"Start Downloading Model")
            cls._model = GPT4All("qwen2-1_5b-instruct-q4_0.gguf", device='cpu')
            
            print(f"Downloaded Model")
        
        return cls._instance
    
    def responseModel(self,user_mess:str, history_list: list = []):
        system_prompt = "Bạn là một trợ lý ảo am hiểu về điện ảnh. Hãy trả lời ngắn gọn, thân thiện bằng tiếng Việt."
        
        full_prompt = f"System: {system_prompt}\n"
        
        #         # Thêm lịch sử chat ngắn (nếu có)
#         for msg in history[-3:]: # Chỉ lấy 3 câu gần nhất để đỡ tốn RAM
#             role = "User" if msg.get('role') == 'user' else "Bot"
#             full_prompt += f"{role}: {msg.get('content')}\n"

        full_prompt += f"User: {user_mess}\nBot:"

        
        with self._model.chat_session:
            response = self._model.generate(full_prompt,max_tokens=200, temp=0.7)
        
        return response
    
llm_instance = LocalLLM()

def knowledge_chat_tool(message, history):
    try:
        reply = llm_instance.responseModel(message,history)
        print(reply)
        return reply 
    except Exception as e:
        print(f"Lỗi LLM: {e}")
        return "Xin lỗi, hiện tại bộ não AI của tôi đang quá tải. Bạn hãy thử lại sau nhé!"