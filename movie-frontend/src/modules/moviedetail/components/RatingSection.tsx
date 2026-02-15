import { useState } from "react";
import { toast } from "react-hot-toast";

export const RatingSection = ({ mediaId }: { mediaId: number }) => {
    const [hover, setHover] = useState(0);
    const [rating, setRating] = useState(0);
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleRate = async (score: number) => {
        setIsSubmitting(true);
        try {
            const response = await fetch("http://localhost:8080/user/api/rateMovie", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem("token")}`
                },
                body: JSON.stringify({
                    movieId: mediaId,
                    score: score,
                    comment: ""
                })
            });

            if (response.ok) {
                setRating(score);
                toast.success(`Cảm ơn! Bạn đã đánh giá ${score} sao.`);
            } else {
                toast.error("Không thể gửi đánh giá.");
            }
        } catch (error) {
            console.error("Rating error:", error);
            toast.error("Đã xảy ra lỗi khi kết nối server.");
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="container mx-auto">
            <div className={`container flex flex-col mt-10 p-6 bg-black/40 border border-gray-800 rounded-lg transition-opacity ${isSubmitting ? 'opacity-50 pointer-events-none' : ''}`}>
                <h3 className="text-xl font-bold mb-4">Đánh giá phim để AI gợi ý tốt hơn</h3>
                <div className="flex gap-2">
                    {[1, 2, 3, 4, 5].map((star) => (
                        <button
                            key={star}
                            className={`text-4xl transition-all hover:scale-125 ${star <= (hover || rating) ? "text-yellow-400 drop-shadow-[0_0_8px_rgba(250,204,21,0.6)]" : "text-gray-600"
                                }`}
                            onMouseEnter={() => setHover(star)}
                            onMouseLeave={() => setHover(0)}
                            onClick={() => handleRate(star)}
                        >
                            ★
                        </button>
                    ))}
                </div>
                <p className="mt-4 text-sm font-medium text-gray-400">
                    {rating > 0 ? `Cảm ơn bạn đã đóng góp!` : "Phim này như thế nào với bạn?"}
                </p>
            </div>
        </div>
    );
};