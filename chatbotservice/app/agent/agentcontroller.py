from app.agent.intent import embedding_intent
from app.agent.ruleintent import rule_based_intent
from app.tools.search_tool import search_movies_tool
from app.tools.rec_tool import recommend_movies_tool
from app.tools.chat_tool import knowledge_chat_tool

class AgentController:
    def __init__(self):
        print("--> Agent Controller Initialized")

    def _extract_keywords(self, message: str, intent: str):
        """
        Hàm phụ trợ để lọc bỏ các từ thừa, lấy từ khóa chính.
        Ví dụ: "Tìm phim Batman" -> "Batman"
        """
        msg_lower = message.lower()
        
        if intent == "SEARCH":
            # Xóa các từ stopword tiếng Việt thường gặp khi tìm kiếm
            stopwords = ["tìm", "kiếm", "phim", "về", "thông tin", "nội dung", "cho", "tôi", "biết", "là", "gì"]
            for word in stopwords:
                msg_lower = msg_lower.replace(word, "")
            return msg_lower.strip()
            
        return message # Các intent khác có thể không cần lọc kỹ

    async def handle_request(self, user_id: int, message: str, history: list):
        print(f"--> [USER REQUEST] {message}")

        intent = rule_based_intent(message)
        source = "rule"

        if not intent:
            intent = embedding_intent(message)
            source = "model"

        keywords = self._extract_keywords(message, intent)
        print(f"--> [KEYWORDS] {keywords}")

        response_data = {}

        if intent == "SEARCH":
            results = search_movies_tool(keywords)
            response_data = {
                "type": "search_results",
                "message": f"Dưới đây là kết quả tìm kiếm cho '{keywords}':",
                "data": results,
                "intent": intent
            }

        elif intent == "RECOMMEND":
            results = recommend_movies_tool(user_id)
            response_data = {
                "type": "recommendation",
                "message": "Dựa trên sở thích của bạn, AI đề xuất các phim sau:",
                "data": results,
                "intent": intent
            }

        else: 
            bot_reply = knowledge_chat_tool(message, history)
            response_data = {
                "type": "chat",
                "message": bot_reply,
                "data": None,
                "intent": intent
            }
            
        return response_data