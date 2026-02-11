from gpt4all import GPT4All
from app.config.config import settings
from app.config.prompts import Prompts
from app.services.helpers import clean_json_response, format_movie_summary

class LLMService:
    def __init__(self):
        try:
            self.model = GPT4All(
                model_name=settings.llm_model_name, 
                model_path=settings.model_path, 
                n_ctx=2048, 
                allow_download=False
            )
            print("LLM Loaded successfully.")
        except Exception as e:
            print(f"LLM Error: {e}")
            self.model = None

    def generate_natural_response(self, message: str, movies_data: list):
        if not self.model:
            return f"Tìm thấy {len(movies_data)} phim: " + ", ".join([m.get('title') for m in movies_data])
            
        summary = format_movie_summary(movies_data)
        prompt = Prompts.get_natural_answer_prompt(message, summary)
        
        try:
            return self.model.generate(prompt=prompt, max_tokens=200).strip()
        except:
            return f"Kết quả: {summary}"

    def extract_search_params(self, text: str):
        if not self.model: return {"title": text}
        
        prompt = Prompts.get_search_param_prompt(text)
        try:
            raw_output = self.model.generate(prompt=prompt, max_tokens=100).strip()
            return clean_json_response(raw_output) or {"title": text}
        except:
            return {"title": text}

    def extract_reference_movie(self, text: str):
        if not self.model: return None
        
        prompt = Prompts.get_recommend_extract_prompt(text)
        try:
            return self.model.generate(prompt=prompt, max_tokens=15).strip()
        except:
            return None

    def handle_generic_chat(self, message: str):
        if not self.model: return "Xin chào! Tôi có thể giúp gì cho bạn?"
        
        prompt = f"[INST] Bạn là trợ lý ảo về phim ảnh. Trả lời ngắn gọn, vui vẻ.\nUser: {message} [/INST]"
        return self.model.generate(prompt, max_tokens=100).strip()