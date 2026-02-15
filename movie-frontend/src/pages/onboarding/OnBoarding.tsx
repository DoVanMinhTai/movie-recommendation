import { useMutation } from "@tanstack/react-query";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import type { Genre } from "../../modules/category/model/Genre";
import { getAllGenres } from "../../modules/category/service/CategoryService";
import { getAuthData } from "../../common/auth/AuthUtils";

const GENRES = [
    { id: 1, name: 'Hành động', emoji: '💥' },
    { id: 2, name: 'Kinh dị', emoji: '👻' },
    { id: 3, name: 'Hài hước', emoji: '😂' },
    { id: 4, name: 'Lãng mạn', emoji: '❤️' },
    { id: 5, name: 'Khoa học viễn tưởng', emoji: '🚀' },
    { id: 6, name: 'Hoạt hình', emoji: '🦄' },
    { id: 7, name: 'Tâm lý', emoji: '🧠' },
    { id: 8, name: 'Tài liệu', emoji: '📹' },
];

export default function OnBoarding() {
    const [selectedGenres, setSelectedGenres] = useState<number[]>([]);
    const [genres, setGenres] = useState<Genre[]>();
    const navigate = useNavigate();

    useEffect(() => {
        getAllGenres().then((data) => {
            setGenres(data);
        })
    }, [])

    const mutation = useMutation({
        mutationFn: async (data: { genres: number[] }) => {
            const response = await fetch('http://localhost:8080/user/onboarding', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(data),
            });
            return response.json();
        },
        onSuccess: (data) => {
            localStorage.setItem('token', data.token);
            navigate('/');
        }
    });

    const toggleGenre = (id: number) => {
        setSelectedGenres(prev =>
            prev.includes(id) ? prev.filter(g => g !== id) : [...prev, id]
        );
    };

    const handleComplete = () => {
        if (selectedGenres.length < 3) {
            alert("Vui lòng chọn ít nhất 3 thể loại để AI gợi ý chuẩn hơn nhé!");
            return;
        }
        mutation.mutate({ genres: selectedGenres });
    };

    const handleSkip = () => {
        mutation.mutate({ genres: [] });
    };

    const auth = getAuthData();
    useEffect(() => {
        if (auth && !auth.isNewUser) {
            navigate('/');
        }
    }, [auth]);

    return (
        <div className="min-h-screen bg-black text-white flex flex-col items-center justify-center px-4">
            <div className="max-w-4xl w-full text-center space-y-8">
                <header className="space-y-2">
                    <h1 className="text-4xl md:text-5xl font-bold">Bạn thích xem gì?</h1>
                    <p className="text-nfGrey-10 text-lg">Chọn ít nhất 3 thể loại để chúng tôi gợi ý phim cho riêng bạn.</p>
                </header>

                <div className="grid grid-cols-2 md:grid-cols-4 gap-4">
                    {genres && genres.map((genre) => (
                        <div
                            key={genre.id}
                            onClick={() => toggleGenre(genre.id)}
                            className={`
                                cursor-pointer p-6 rounded-xl border-2 transition-all duration-300 transform hover:scale-105
                                ${selectedGenres.includes(genre.id)
                                    ? 'border-nfRed bg-nfRed/20 shadow-[0_0_15px_rgba(229,9,20,0.5)]'
                                    : 'border-nfGrey-700 bg-nfGrey-800 hover:border-nfGrey-400'}
                            `}
                        >
                            {/* <span className="text-3xl mb-2 block">{genre.emoji}</span> */}
                            <span className="font-medium">{genre.name}</span>
                        </div>
                    ))}
                </div>

                <div className="flex flex-col md:flex-row items-center justify-center gap-4 pt-8">
                    <button
                        onClick={handleComplete}
                        disabled={mutation.isPending}
                        className="w-full md:w-64 py-3 bg-nfRed text-white font-bold rounded hover:bg-red-700 transition disabled:opacity-50"
                    >
                        {mutation.isPending ? 'ĐANG LƯU...' : 'HOÀN TẤT'}
                    </button>

                    <button
                        onClick={handleSkip}
                        className="text-nfGrey-10 hover:text-white transition font-medium underline underline-offset-4"
                    >
                        Bỏ qua bước này
                    </button>
                </div>
            </div>

            <div className="fixed top-0 left-0 w-full h-full -z-10 opacity-20 pointer-events-none">
                <div className="absolute inset-0 bg-gradient-to-t from-black via-transparent to-black" />
            </div>
        </div>
    );
}