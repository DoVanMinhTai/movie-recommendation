import type { Movie } from "../model/MovieVm";

export const EpisodesSelector = ({ movie }: { movie: Movie | null }) => {
    return (
        <div className="relative h-[500px]">
            {movie && movie.backdropUrl && (
                <img
                    src={movie.backdropUrl}
                    alt={movie.title}
                    className="w-full h-full object-cover"
                />
            )}
            <div className="absolute bottom-5 left-5 text-white">
                <h1 className="text-4xl font-bold">{movie?.title}</h1>
                {movie?.trailerUrl && (
                    <a
                        href={movie.trailerUrl}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="mt-3 inline-block bg-red-600 px-4 py-2 rounded"
                    >Watch Trailer</a>
                )}
            </div>
        </div>
    );
}