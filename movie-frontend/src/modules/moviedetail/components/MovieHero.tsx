import type { Movie } from "../model/MovieVm";

export const MovieHero = ({ movie, onPlayClick }: { movie: Movie | null, onPlayClick: () => void }) => {
    const TMDB_IMAGE_BASE = "https://image.tmdb.org/t/p/original";
    const fullImageUrl = `${TMDB_IMAGE_BASE}${movie?.backdropUrl}`;
    const handleAddFavorite = async () => {
        if (!movie) return;

        try {
            const response = await fetch("http://localhost:8080/user/api/favorites/add", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                },
                body: JSON.stringify(movie.id) 
            });

            if (response.ok) {
                alert("Đã thêm vào danh sách yêu thích!");
            } else {
                alert("Không thể thêm vào danh sách.");
            }
        } catch (error) {
            console.error("Error adding favorite:", error);
        }
    };
    return (
        <div className="container mx-auto relative h-[500px]">
            {movie && movie.backdropUrl && (
                <img
                    src={fullImageUrl}
                    alt={movie.title}
                    className="w-full h-full object-cover"
                />
            )}
            <div className="absolute  bottom-30 left-30 w-[500px] h-[250px] text-white">
                <h1 className="text-4xl font-bold mb-4">{movie?.title}</h1>
                <div className="flex-1 flex-col items-center gap-4 mt-4">

                    {movie?.trailerKey && (
                        <iframe
                            src={`https://www.youtube.com/embed/${movie.trailerKey}?autoplay=0&mute=1`}
                            allow="autoplay; encrypted-media"
                            allowFullScreen
                            className="w-full h-[220px] rounded-lg"
                        />

                    )}
                    <div className="items-center w-10/12 flex-row gap-4 flex mt-4">
                        <button className="flex-1 w-1/3 gap-2 bg-red-600 text-white px-4 py-2 rounded-md" onClick={onPlayClick}>
                            <span>Play</span>
                        </button>
                        <button className=" border border-white text-white px-4 py-2 rounded-lg"
                            onClick={handleAddFavorite}
                            title="Thêm vào danh sách của tôi"
                        >
                            <span>+</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}