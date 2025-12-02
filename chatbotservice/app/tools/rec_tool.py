def recommend_movies_tool(user_id: int):
    """
    Công cụ gợi ý phim cá nhân hóa.
    Kết nối thực tế: Load model Matrix Factorization hoặc gọi API khác.
    """
    print(f"[TOOL] Đang gợi ý phim cho User ID: {user_id}")
    # Code thực tế: model.predict(user_id)
    return [
        {"title": "Inception", "score": 0.98},
        {"title": "Interstellar", "score": 0.95}
    ]