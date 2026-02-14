import { useRef, useState } from "react";
import MovieWrapper from "./MovieWrapper";
import type { PageAbleResponse } from "../services/ApiClientService";

type MovieGridProps = {
    data: PageAbleResponse;
    loading: boolean;
    onPageChange: (newPage: number) => void;
};

const MovieGrid = ({ data, loading, onPageChange }: MovieGridProps) => {
    const containerRef = useRef<HTMLDivElement>(null);
    const scrollAmount = 500; // Increased for better UX

    const handleScroll = (direction: 'left' | 'right') => {
        if (containerRef.current) {
            const currentScroll = containerRef.current.scrollLeft;
            const newPosition = direction === 'left'
                ? currentScroll - scrollAmount
                : currentScroll + scrollAmount;

            containerRef.current.scrollTo({
                left: newPosition,
                behavior: 'smooth',
            });
        }
    };

    return (
        <div className="flex flex-col gap-6 w-full px-4">
            {/* The Grid Container */}
            <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-6">
                {loading ? (
                    // Skeleton Loaders
                    [...Array(10)].map((_, i) => (
                        <div key={i} className="aspect-[2/3] bg-gray-800 animate-pulse rounded-lg" />
                    ))
                ) : (
                    data?.content?.map((movie) => (
                        <div key={movie.id} className="w-full">
                            <MovieWrapper key={movie.id} id={movie.id}>
                                <div className="relative aspect-[2/3] w-full rounded-xl shadow-lg hover:scale-105 transition-all overflow-hidden bg-gray-900">
                                    <img
                                        src={movie.backdropPath
                                            ? `https://image.tmdb.org/t/p/w500${movie.backdropPath}`
                                            : "https://via.placeholder.com/500x750?text=No+Poster"}
                                        alt={movie.title}
                                        className="w-full h-full object-cover"
                                    />
                                    <div className="absolute bottom-0 p-2 w-full bg-black/70">
                                        <p className="text-white text-xs truncate">{movie.title}</p>
                                    </div>
                                </div>
                            </MovieWrapper>
                        </div>
                    ))
                )}
            </div>

            {!loading && data && (
                <div className="flex justify-center items-center gap-4 mt-8 py-4 border-t border-gray-800">
                    <button
                        disabled={data.number === 0}
                        onClick={() => onPageChange(data.number - 1)}
                        className="px-4 py-2 bg-gray-800 rounded-md disabled:opacity-30 hover:bg-blue-600 transition"
                    >
                        Previous
                    </button>

                    <span className="text-gray-300 font-medium">
                        Page <span className="text-blue-500">{data.number + 1}</span> of {data.totalPages}
                    </span>

                    <button
                        disabled={data.number + 1 >= data.totalPages}
                        onClick={() => onPageChange(data.number + 1)}
                        className="px-4 py-2 bg-gray-800 rounded-md disabled:opacity-30 hover:bg-blue-600 transition"
                    >
                        Next
                    </button>
                </div>
            )}
        </div>
    );
}

export default MovieGrid;