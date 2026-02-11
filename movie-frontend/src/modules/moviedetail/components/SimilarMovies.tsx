import type { MovieThumbnailVm } from "../model/MovieThumbnailVm";
import MovieRow from "./MovieRow";

export const SimilarMovies = ({ similarMovies }: { similarMovies: MovieThumbnailVm[] }) => {
    return (
       <MovieRow title="Similar Movies" movies={similarMovies} />
    );
}