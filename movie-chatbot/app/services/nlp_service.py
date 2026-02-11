import torch
from sentence_transformers import SentenceTransformer, util
from app.config.config import settings

class NLPService:
    def __init__(self):
        self.embed_model = SentenceTransformer(settings.embed_model_name, cache_folder=settings.model_path)
        self.anchor_embeddings = {
            intent: self.embed_model.encode(anchors, convert_to_tensor=True) 
            for intent, anchors in settings.intent_anchors.items()
        }

    def detect_intent(self, text: str) -> str:
        user_embedding = self.embed_model.encode(text)
        best_intent = "UNKNOWN"
        max_score = 0.0
        
        for intent, anchors in self.anchor_embeddings.items():
            scores = util.cos_sim(user_embedding, anchors)
            current_max = float(torch.max(scores))
            if current_max > max_score:
                max_score = current_max
                best_intent = intent
                
        return best_intent if max_score > 0.45 else "UNKNOWN"