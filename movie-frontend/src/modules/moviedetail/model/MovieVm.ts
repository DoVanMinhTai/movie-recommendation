import type { Session } from "./Session";

export interface Movie {
    id: number;
    title: string;
    backdropUrl: string;
    trailerKey?: string;
    dtype: 'SERIES' | 'MOVIE';
    seasons?: Session[];
    year: number;
    duration: number;
    genres: string[];
    description: string;
    cast: string[];
    director: string;
    rating: number; 
    similarMovies?: Movie[];
}