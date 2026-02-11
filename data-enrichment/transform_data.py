import pandas as pd
from typing import List,Dict, Any, Union
import re
import numpy as np 
import json
import os

def transfrom_data(path: str):
    '''
    1. Read File
    2. Get Type
    3. Convert Type
        3.1 If type is object example: Genres , seperate genres and save it or... you can do it depend your option
    4. Dump File again 
    '''
    
    column_mapping = {
        "movieId": "id",              # Long
        "tmdbId": "tmDBId",           # Long
        "title": "title",             # String
        "original_title": "originalTitle", # String
        "genres": "genres_string",    # TEXT (Intermediate)
        "overview": "overview",       # TEXT
        "release_date": "releaseDate",# LocalDateTime
        "poster_path": "posterPath",  # String
        "backdrop_path": "backdropPath", # String
        "runtime": "runtime",         # Integer
        "trailer_key": "trailerKey",  # String
        "tmdb_vote": "voteAverage",   # Double
        "vote_count": "vote_count",    # Integer
        "popularity": "popularity",   # Double
        # movieLensId is not in the CSV but could be added later if available
    }
   
    try:
        # 1. Read File
        df = pd.read_csv(path, sep=',',dtype=str,keep_default_na=False)
        
        df = df.rename(columns=column_mapping)
        
        df = df[list(column_mapping.values())]
        
        # 2 & 3. Get Type and Convert Type
        integer_cols = ["id","tmDBId","runtime","vote_count"]
        for col in integer_cols:
            df[col] = pd.to_numeric(df[col], errors="coerce").astype("Int64")
            
        double_cols = ["voteAverage","popularity"]
        for col in double_cols:
            df[col] = pd.to_numeric(df[col], errors="coerce").astype("double")

        df['releaseDate'] = pd.to_datetime(
            df['releaseDate'], 
            errors='coerce', 
            format='%Y-%m-%d'
        )
        
        GENRE_MAP = {
    "unknown": 0, "Action": 1, "Adventure": 2, "Animation": 3, 
    "Children": 4, "Comedy": 5, "Crime": 6, "Documentary": 7, 
    "Drama": 8, "Fantasy": 9, "Film-Noir": 10, "Horror": 11, 
    "Musical": 12, "Mystery": 13, "Romance": 14, "Sci-Fi": 15, 
    "Thriller": 16, "War": 17, "Western": 18
}
        df_genres = pd.DataFrame(list(GENRE_MAP.items()), columns=['name', 'genre_id'])
        df_genres = df_genres[['genre_id', 'name']].sort_values(by='genre_id')
        
        df_movies_genres = (df[['id',"genres_string"]]
                            .dropna(subset=["genres_string"])
                            .assign(genre_lists= lambda x : x['genres_string'].str.split("|"))
                            .explode('genre_lists')
                            .rename(columns={"id": "movie_id", "genre_lists" :"genre_name"})
                            )
        
        df_movies_genres["genre_id"] = df_movies_genres["genre_name"].map(GENRE_MAP)
        df_movies_genres = df_movies_genres[["movie_id","genre_id"]].dropna()
        
        df_movies_final = df.drop(columns=["genres_string"])
        output = "F:\Github\movie-recommendation\chatbotservice\data_enrichment\data_transform"

        # Dump Files
        output_movie_path = os.path.join(os.path.dirname(path), 'movies_transformed.csv')
        df_movies_final.to_csv(output_movie_path, index=False, date_format='%Y-%m-%d %H:%M:%S')

        # 4.2 Dump the Genre table data
        output_genre_path = os.path.join(os.path.dirname(path), 'genres_master.csv')
        df_genres.to_csv(output_genre_path, index=False)

        # 4.3 Dump the Movie-Genre mapping data
        output_mapping_path = os.path.join(os.path.dirname(path), 'movie_genres_mapping.csv')
        df_movies_genres.to_csv(output_mapping_path, index=False)
        
        print("Transformation successful.")
        print(f"Output files: {output_movie_path}, {output_genre_path}, {output_mapping_path}")
        return "Success"
        
    except FileNotFoundError:
        return FileNotFoundError("Input file not found at the specified path.")
    except Exception as e:
        print(f"An error occurred: {e}")
        return e

# Create function script auto insert into file in Postgresql Database
    

path = "F:\Github\movie-recommendation\chatbotservice\data_enrichment\data\movies_final.csv"
transfrom_data(path)