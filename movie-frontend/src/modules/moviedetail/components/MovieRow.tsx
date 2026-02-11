import { useRef, useState } from "react";
import MovieWrapper from "../../../common/components/MovieWrapper"
import type { MovieThumbnailVm } from "../model/MovieThumbnailVm"

type Props = {
    title: string,
    movies: MovieThumbnailVm[],
    isLarge?: boolean
}

export default function MovieRow({ title, movies, isLarge }: Props) {
    const [sliderIndex, setSliderIndex] = useState(0);
    const sliderRef = useRef<HTMLDivElement>(null);

    const handleArrowClick = (direction: "left" | "right") => {
        if (direction === "left") setSliderIndex((prev => Math.max(prev - 1, 0)));
        else setSliderIndex((prev => prev + 1));
    }


    return <>
        <div className="container mx-auto space-y-2 ">
            <h2 className="text-xl font-semibold text-white">{title}</h2>
            <div className="relative">
                {sliderIndex > 0 && (
                    <button
                        className="absolute left-0 top-0 bottom-0 z-40 w-12 bg-black/50 opacity-0 group-hover:opacity-100 transition-opacity"
                        onClick={() => handleArrowClick("left")}>
                        ◀
                    </button>
                )}
            </div>
            <div className="flex gap-3 overflow-x-auto scrollbar-hide py-4">
                {movies.map((movie) => (
                    <MovieWrapper key={movie.id} id={movie.id}>
                        <div
                            key={movie.id}
                            className={`flex-none transition-transform duration-300 hover:scale-105 cursor-pointer ${isLarge ? "h-80 w-56" : "h-32 w-56"
                                }`}
                        >
                            <img
                                src={movie.backdropPath ? `https://image.tmdb.org/t/p/w500${movie.backdropPath}` : "https://via.placeholder.com/500x750?text=No+Poster"}
                                alt={movie.title}
                                className="h-full w-full rounded-md object-cover"
                            />
                        </div>
                        <div className="text-white text-sm mt-2 truncate">{movie.title}</div>
                    </MovieWrapper>
                ))}
            </div>
            <div className="relative">
                <button
                    onClick={() => handleArrowClick('right')}
                    className="absolute right-0 top-0 bottom-0 z-40 w-12 bg-black/50 opacity-0 group-hover:opacity-100 transition-opacity"
                > ▶ </button>
            </div>
        </div>
    </>
}
