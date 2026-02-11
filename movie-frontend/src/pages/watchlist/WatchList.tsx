import React, { useState, useEffect } from 'react';
import { NETFLIX_DATA } from '../../data/NETFLIX_DATA';

export default function WatchList() {
    // Trong thực tế, bạn sẽ lấy data này từ LocalStorage, Redux hoặc API
    const [myList, setMyList] = useState(NETFLIX_DATA.trending);

    const removeFromList = (id: number) => {
        setMyList(prev => prev.filter(movie => movie.id !== id));
    };

    return (
        <div className="bg-nfBlack min-h-screen pt-32 px-4 md:px-12">
            <header className="mb-8">
                <h1 className="text-white text-[48px] font-bold">Danh sách của tôi</h1>
            </header>

            {myList.length > 0 ? (
                <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-x-2 gap-y-12">
                    {myList.map((movie) => (
                        <div key={movie.id} className="relative group">
                            <div className="relative transition-transform duration-300 hover:scale-110 hover:z-20 cursor-pointer">
                                <img 
                                    src={movie.image} 
                                    alt={movie.title}
                                    className="rounded-md aspect-video object-cover shadow-lg"
                                />
                                
                                <button 
                                    onClick={() => removeFromList(movie.id)}
                                    className="absolute top-2 right-2 bg-nfGrey-800/80 p-1.5 rounded-full opacity-0 group-hover:opacity-100 hover:bg-nfRed transition-all"
                                    title="Xóa khỏi danh sách"
                                >
                                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                                    </svg>
                                </button>

                                <div className="hidden group-hover:block absolute bottom-0 w-full p-2 bg-nfGrey-800 rounded-b-md">
                                    <p className="text-white text-sm font-medium truncate">{movie.title}</p>
                                    <p className="text-nfGrey-10 text-xs">{movie.year} • {movie.quality}</p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <div className="flex flex-col items-center justify-center mt-20 text-nfGrey-400">
                    <p className="text-lg mb-4">Bạn chưa thêm bộ phim nào vào danh sách của mình.</p>
                    <a 
                        href="/" 
                        className="border border-nfGrey-400 px-6 py-2 hover:bg-white hover:text-black transition"
                    >
                        Khám phá ngay
                    </a>
                </div>
            )}
        </div>
    );
}