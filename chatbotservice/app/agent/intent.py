import json
from sentence_transformers import SentenceTransformer, util

model = SentenceTransformer('sentence-transformers/all-MiniLM-L6-v2')

intent_anchors = { "SEARCH": [ "Find movie Batman", "Who directed Inception?", "Search for action movies", "Movie about time travel", "Tim phim hanh dong", "Phim gi noi ve vu tru" ],
                  "RECOMMEND": [ "Recommend me a movie", "What should I watch?", "Movies like Toy Story", "Give me a suggestion", "Goi y phim hay", "Toi nen xem phim gi" ], 
                  "CHAT": [ "Hello", "How are you?", "Who are you?", "What can you do?", "Xin chao", "Ban la ai", "Chuc ngu ngon" ] }

anchor_embedding = {
    k: model.encode(v) for k,v in intent_anchors.items()
}

def embedding_intent(text: str):
    """
    Phân tích ý định người dùng thành 3 loại: SEARCH, RECOMMEND, CHAT.
    """
    
    system_prompt = """
    Bạn là một trợ lý phân loại ý định (Intent Classifier).
    Hãy phân tích câu nói của người dùng và trả về định dạng JSON:
    {
        "intent": "SEARCH" | "RECOMMEND" | "CHAT",
        "keywords": "từ khóa chính nếu có"
    }
    
    Quy tắc:
    - Nếu người dùng hỏi về phim cụ thể, diễn viên, nội dung -> SEARCH
    - Nếu người dùng nhờ gợi ý, "có gì hay không", "thích xem..." -> RECOMMEND
    - Nếu chào hỏi hoặc hỏi linh tinh -> CHAT
    """

    user_embedding = model.encode(text)
    
    best_intent = "UNKNOW"
    max_score = 0.0
    
    for intent, anchors in anchor_embedding.items():
        scores = util.cos_sim(user_embedding, anchors)
        score = float(scores.max())

        if score > max_score:
            max_score = score
            best_intent = intent
            
    if max_score < 0.45:
        return "UNKNOWN"

    return best_intent
    
    