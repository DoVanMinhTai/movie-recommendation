import { useEffect } from "react";
import type { Movie } from "../model/MovieVm";

export default function VideoOverlay({ movie, episode, onClose }: { movie: Movie | null, episode: any, onClose: () => void }) {

    useEffect(() => {
        document.body.style.overflow = 'hidden';
        return () => {
            document.body.style.overflow = 'auto';
        };
    }, []);

    return (
        <div className="fixed inset-0 z-[9999] bg-black flex flex-center">
            {/* 1. Nút Back để quay lại trang Detail */}
            <button onClick={onClose} className="absolute top-10 left-10 z-50">
                {/* <ArrowBackIcon /> */}
            </button>

            {/* 2. Lớp Video thuần túy */}
            <video className="w-full h-full" src={episode.video_url} autoPlay />

            {/* 3. Lớp UI Controls (Chứa các Pattern bạn đã gửi) */}
            <div className="absolute inset-0 flex flex-col justify-between p-8 opacity-0 hover:opacity-100 transition-opacity">
                <div className="top-controls"> {/* Tên phim/tập đang phát */} </div>

                <div className="center-controls">
                    {/* Nút Play/Pause, tua 10s (giống ảnh Patterns) */}
                </div>

                <div className="bottom-controls w-full space-y-4">
                    {/* <ProgressBar /> Thanh đỏ trong ảnh Patterns */}
                    <div className="flex justify-between items-center">
                        <div className="flex gap-4 items-center">
                            {/* <PlayButton /> */}
                            {/* <VolumeControl /> Volume Slider dọc trong ảnh Video Player */}
                        </div>
                        <div className="flex gap-4">
                            {/* <SubtitleSettings /> Menu chọn Sub/Audio trong ảnh Subtitles */}
                            {/* <PlaybackSpeed />   Thanh gạt 0.5x - 1.5x trong ảnh Patterns */}
                            {/* <FullScreenButton /> */}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}