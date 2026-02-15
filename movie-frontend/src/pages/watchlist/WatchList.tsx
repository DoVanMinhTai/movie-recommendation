import React from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { Link } from 'react-router-dom';

interface Movie {
    id: number;
    title: string;
    backdropPath: string;
    releaseDate: string;
}

export default function WatchList() {
    const queryClient = useQueryClient();

    const { data: myList = [], isLoading, isError } = useQuery<Movie[]>({
        queryKey: ['watchlist'],
        queryFn: async () => {
            const response = await fetch("http://localhost:8080/user/api/getAllFavorites", {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                }
            });
            if (!response.ok) throw new Error("Không thể tải danh sách");
            return response.json();
        }
    });

    const deleteMutation = useMutation({
        mutationFn: async (movieId: number) => {
            const response = await fetch(`http://localhost:8080/user/api/favorites/${movieId}`, {
                method: 'DELETE',
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                }
            });
            if (!response.ok) throw new Error("Không thể xóa phim");
        },
        onSuccess: () => {
            queryClient.invalidateQueries({ queryKey: ['watchlist'] });
        }
    });

    const removeFromList = (id: number) => {
        if (window.confirm("Bạn có muốn xóa phim này khỏi danh sách?")) {
            deleteMutation.mutate(id);
        }
    };

    if (isLoading) return <div className="bg-black min-h-screen pt-32 text-center text-white">Đang tải danh sách...</div>;
    if (isError) return <div className="bg-black min-h-screen pt-32 text-center text-red-500">Có lỗi xảy ra khi tải dữ liệu.</div>;

    return (
        <div className="bg-[#141414] min-h-screen pt-32 px-4 md:px-12">
            <header className="mb-8">
                <h1 className="text-white text-4xl md:text-5xl font-bold">Danh sách của tôi</h1>
            </header>

            {myList.length > 0 ? (
                <div className="grid grid-cols-2 md:grid-cols-4 lg:grid-cols-6 gap-x-4 gap-y-12">
                    {myList.map((movie) => (
                        <div key={movie.id} className="relative group">
                            <div className="relative transition-transform duration-300 hover:scale-110 hover:z-20 cursor-pointer">
                                <Link to={`/movie/${movie.id}`}>
                                    <img 
                                        src={`https://image.tmdb.org/t/p/w500${movie.backdropPath}`} 
                                        alt={movie.title}
                                        className="rounded-md aspect-video object-cover shadow-lg border border-gray-800"
                                    />
                                </Link>
                                
                                <button 
                                    onClick={(e) => {
                                        e.preventDefault(); 
                                        removeFromList(movie.id);
                                    }}
                                    disabled={deleteMutation.isPending}
                                    className="absolute top-2 right-2 bg-black/60 p-2 rounded-full opacity-0 group-hover:opacity-100 hover:bg-red-600 transition-all z-30"
                                    title="Xóa khỏi danh sách"
                                >
                                    <svg xmlns="http://www.w3.org/2000/svg" className="h-4 w-4 text-white" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={3} d="M6 18L18 6M6 6l12 12" />
                                    </svg>
                                </button>

                                <div className="hidden group-hover:block absolute bottom-0 w-full p-2 bg-[#181818] rounded-b-md shadow-xl border-t border-gray-700">
                                    <p className="text-white text-xs font-bold truncate">{movie.title}</p>
                                    <p className="text-green-400 text-[10px] font-bold mt-1">
                                        {movie.releaseDate?.split('-')[0] || 'N/A'}
                                    </p>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <div className="flex flex-col items-center justify-center mt-20 text-gray-400">
                    <p className="text-lg mb-6">Danh sách của bạn đang trống.</p>
                    <Link 
                        to="/" 
                        className="border border-gray-400 px-8 py-2 text-white font-bold hover:bg-white hover:text-black transition duration-300"
                    >
                        KHÁM PHÁ NGAY
                    </Link>
                </div>
            )}
        </div>
    );
}