import type { Movie } from "../model/MovieVm";

export const MovieInfo = ({ movie }: { movie: Movie | null }) => {
    return (
        <div className="container mx-auto my-8 p-4 bg-gray-800 text-white rounded-lg">
            <div className="flex">
                <div>
                    {movie ? (
                        <>
                            <h1 className="text-3xl font-bold mb-4">{movie.title} ({movie.year})</h1>
                            <p className="mb-2"><strong>Duration:</strong> {movie.duration} minutes</p>
                            {/* <p className="mb-2"><strong>Genres:</strong> {movie.genres.join(", ")}</p> */}
                            <p className="mb-4"><strong>Description:</strong> {movie.description}</p>
                            {/* <p className="mb-2"><strong>Cast:</strong> {movie.cast.join(", ")}</p> */}
                            <p className="mb-2"><strong>Director:</strong> {movie.director}</p>
                            <p className="mb-2"><strong>Rating:</strong> {movie.rating}/10</p>
                        </>
                    ) : (
                        <p>Loading movie information...</p>
                    )}
                </div>
                <div>

                </div>
            </div>
        </div>
    );
}