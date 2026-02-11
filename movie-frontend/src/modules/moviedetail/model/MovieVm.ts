import type { Episode } from "./Episode";

export interface Movie {
    id: number;
    title: string;
    backdropUrl: string;
    trailerKey?: string;
    episodes?: Episode[];
    year: number;
    duration: number;
    genres: string[];
    description: string;
    cast: string[];
    director: string;
    rating: number; 
    similarMovies?: Movie[];
}