import pandas as pd
import requests
import time
import os
import sys
import csv

# --- CẤU HÌNH ---
BASE_DIR = os.path.dirname(os.path.abspath(__file__))
DATA_DIR = os.path.join(BASE_DIR, "data")
MOVIE_LENS_DIR = os.path.join(DATA_DIR, "movielens")    
SERIES = os.path.join(DATA_DIR, "series")

TMDB_API_KEY = '09a7faea89755a7cc2ab38967e530f1f'

print("1. Đang đọc các file CSV...")
try:
    df_links = pd.read_csv(os.path.join(DATA_DIR, "links.csv"))
    df_movies = pd.read_csv(os.path.join(DATA_DIR, "movies.csv"))
    df_ratings = pd.read_csv(os.path.join(DATA_DIR, "ratings.csv"))
except FileNotFoundError as e:
    print(f"Lỗi: Không tìm thấy file tại {DATA_DIR}")
    sys.exit(1)

# --- MERGE DỮ LIỆU ---
print("2. Đang xử lý dữ liệu...")
df_merged = pd.merge(df_movies, df_links, on='movieId', how='left')
df_merged = df_merged.dropna(subset=['tmdbId'])
df_merged['tmdbId'] = df_merged['tmdbId'].astype(int)

df_merged = df_merged.head(20)
print(f"3. Bắt đầu lấy dữ liệu từ TMDB cho {len(df_merged)} bộ phim...")

enrich_data = []
session = requests.Session()
start_time = time.time()

for index, row in df_merged.iterrows():
    tmdb_id = row['tmdbId']
    
    # 1. API chi tiết phim
    url_details = f"https://api.themoviedb.org/3/movie/{tmdb_id}?api_key={TMDB_API_KEY}&language=en-US"
    
    # 2. API Trailer (Video)
    url_videos = f"https://api.themoviedb.org/3/movie/{tmdb_id}/videos?api_key={TMDB_API_KEY}"


    series_film = f"https://api.themoviedb.org/3/tv/{tmdb_id}?api_key={TMDB_API_KEY}&language=en-US"    
    
    movie_data = {
        'overview': None,
        'poster_path': None,
        'backdrop_path': None,
        'release_date': None,
        'runtime': None,
        'tmdb_vote': 0.0,
        'vote_count': 0,
        'popularity': 0.0,
        'original_title': None,
        'trailer_key': None,
        'originalLanguage': None
    }

    try:
        # --- CALL API DETAILS ---
        resp_det = session.get(url_details, timeout=3)
        if resp_det.status_code == 200:
            data = resp_det.json()
            movie_data['overview'] = data.get('overview', '').replace('\n', ' ').replace('\r', '')
            movie_data['poster_path'] = data.get('poster_path')
            movie_data['backdrop_path'] = data.get('backdrop_path')
            movie_data['release_date'] = data.get('release_date') 
            movie_data['runtime'] = data.get('runtime')
            movie_data['tmdb_vote'] = data.get('vote_average')
            movie_data['vote_count'] = data.get('vote_count')
            movie_data['popularity'] = data.get('popularity')
            movie_data['original_title'] = data.get('original_title')
            movie_data['originalLanguage'] = data.get('original_language')

        # --- CALL API VIDEOS (TRAILER) ---
        resp_vid = session.get(url_videos, timeout=3)
        if resp_vid.status_code == 200:
            vid_data = resp_vid.json()
            results = vid_data.get('results', [])
            # Tìm video là Trailer và ở Youtube
            for vid in results:
                if vid.get('site') == 'YouTube' and vid.get('type') == 'Trailer':
                    movie_data['trailer_key'] = vid.get('key')
                    break # Lấy cái đầu tiên tìm thấy
                    
    except Exception as e:
        print(f"Lỗi ID {tmdb_id}: {e}")

    enrich_data.append(movie_data)

    # In tiến độ
    if (index + 1) % 50 == 0:
        elapsed = time.time() - start_time
        print(f"   -> Đã xử lý: {index + 1}/{len(df_merged)} phim ({elapsed:.1f}s)")
    
    # Rate limit nhẹ
    time.sleep(0.05)

# --- GỘP DATA VÀO DATAFRAME ---
df_enrich = pd.DataFrame(enrich_data)
# Reset index để đảm bảo concat đúng dòng
df_final = pd.concat([df_merged.reset_index(drop=True), df_enrich], axis=1)

# Chọn các cột cần thiết để lưu DB
# Lưu ý: genres lấy từ file gốc (df_movies) vì nó đã có định dạng "Action|Adventure" 
# phù hợp với StringListConverter của Java
cols_to_save = [
    'movieId', 'title', 'genres', 'tmdbId', 
    'overview', 'poster_path', 'backdrop_path', 'release_date', 
    'runtime', 'tmdb_vote', 'vote_count', 'popularity', 
    'original_title', 'trailer_key'
]

# Chỉ lấy những cột tồn tại (đề phòng lỗi)
final_cols = [c for c in cols_to_save if c in df_final.columns]
df_final = df_final[final_cols]

# --- LƯU FILE CSV ---
print("4. Đang lưu ra file CSV...")
out_path = os.path.join(DATA_DIR, "movies_final.csv")

# quoting=csv.QUOTE_ALL: Quan trọng để xử lý các dấu phẩy, xuống dòng trong Overview/Title
df_final.to_csv(out_path, index=False, encoding='utf-8', quoting=csv.QUOTE_ALL)

print(f"XONG! File đã lưu tại: {out_path}")
print("Bây giờ bạn có thể dùng lệnh COPY trong PostgreSQL.")

def fetch_series(): 
    df_series = pd.read_csv(os.path.join(SERIES, "series_final.csv"))
    return df_series