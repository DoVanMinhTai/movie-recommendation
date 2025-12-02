def search_movies_tool(query: str):
    """
    Công cụ tìm kiếm phim theo ngữ nghĩa hoặc từ khóa.
    Kết nối thực tế: Elasticsearch Vector Search.
    """
    print(f"[TOOL] Đang tìm kiếm phim với từ khóa: {query}")
    # Code thực tế: es_client.search(...)
    return [
        {"title": "The Dark Knight", "year": 2008, "reason": "Khớp từ khóa Batman"},
        {"title": "Batman Begins", "year": 2005, "reason": "Khớp từ khóa Batman"}
    ]