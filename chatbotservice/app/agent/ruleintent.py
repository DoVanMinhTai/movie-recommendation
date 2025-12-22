import re

def rule_based_intent(message: str):
    text = message.lower()
    
    if re.search(r"(gợi ý|recommend|đề xuất|nên xem|suggest)",text):
        return "RECOMMEND"
    
    if re.search(r"(tìm|search|find|phim về|movie about)",text):
        return "SEARCH"
    
    if re.search(r"(ai đóng|đạo diễn|diễn viên|director|actor)", text):
        return "DETAIL"

    if re.search(r"(xin chào|hello|hi|chào bạn)", text):
        return "CHAT"

    return None