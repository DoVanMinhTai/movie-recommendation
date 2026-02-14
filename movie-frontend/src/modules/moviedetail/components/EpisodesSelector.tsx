import { useState } from "react";
import type { Movie } from "../model/MovieVm";

export const EpisodesSelector = ({ movie }: { movie: Movie | null }) => {
    const [selectedSeasonIdx, setSelectedSeasonIdx] = useState(0);

    if (!movie) return null;

    const isSeries = movie.dtype === 'SERIES';
    const currentSeason = movie.seasons?.[selectedSeasonIdx];

    return (
        <div className="bg-[#141414] text-white p-8 rounded-b-lg">
            {/* 1. Header & Trailer Section */}
            <div className="flex flex-col md:flex-row justify-between items-end mb-8 border-b border-gray-700 pb-6">
                <div>
                    <h2 className="text-3xl font-bold mb-2">Tập phim</h2>
                    {isSeries && (
                        <select 
                            className="bg-nfGrey-800 border border-gray-600 p-2 rounded text-lg font-bold outline-none focus:border-white"
                            onChange={(e) => setSelectedSeasonIdx(Number(e.target.value))}
                        >
                            {movie.seasons?.map((s, idx) => (
                                <option key={s.id} value={idx}>Mùa {s.seasonNumber}</option>
                            ))}
                        </select>
                    )}
                </div>
                
                {movie.trailerKey && (
                    <div className="mt-4 md:mt-0">
                        <p className="text-sm text-gray-400 mb-2">Trailer chính thức</p>
                        <iframe
                            className="rounded-lg shadow-2xl"
                            width="350"
                            height="197"
                            src={`https://www.youtube.com/embed/${movie.trailerKey}`}
                            title="Trailer"
                            allowFullScreen
                        ></iframe>
                    </div>
                )}
            </div>

            {/* 2. Episodes List */}
            <div className="space-y-4">
                {isSeries ? (
                    currentSeason?.episodes.map((episode, index) => (
                        <div 
                            key={episode.id} 
                            className="flex items-center gap-4 p-4 rounded-md hover:bg-nfGrey-800 transition cursor-pointer border-b border-gray-800 last:border-none"
                        >
                            <span className="text-2xl text-gray-500 font-bold w-8">{index + 1}</span>
                            <div className="flex-1">
                                <div className="flex justify-between mb-1">
                                    <h4 className="font-bold">{episode.title}</h4>
                                    <span className="text-gray-400">{episode.duration} phút</span>
                                </div>
                                <p className="text-sm text-gray-400 line-clamp-2 italic">
                                    {episode.description || "Không có mô tả cho tập này."}
                                </p>
                            </div>
                        </div>
                    ))
                ) : (
                    <div className="text-gray-400 italic">
                        Đây là phim lẻ, không chia theo tập.
                    </div>
                )}
            </div>
        </div>
    );
};