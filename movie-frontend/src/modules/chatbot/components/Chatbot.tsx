import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Chatbot() {
    const [isOpen, setIsOpen] = React.useState(false);
    const navigate = useNavigate();

    const [messages] = useState([
        { id: 1, role: 'bot', text: 'Xin chào! Bạn muốn tìm phim gì hôm nay?' },
    ]);

    return (
        <div className="fixed bottom-5 right-5 z-[1000] font-sans">
            {!isOpen ? (
                <button
                    onClick={() => setIsOpen(true)}
                    className="bg-nfRed text-white p-4 rounded-full shadow-lg hover:bg-red-700 transition"
                    title="Chatbot"
                >
                    💬
                </button>
            ) : (
                <div className="w-[350px] h-[500px] bg-nfGrey-800 border border-nfGrey-400 rounded-2xl shadow-2xl flex flex-col overflow-hidden animate-in fade-in slide-in-from-bottom-4 duration-300">
                    {/* Header */}
                    <div className="p-4 bg-black flex justify-between items-center border-b border-nfGrey-400">
                        <div className="flex items-center gap-2">
                            <div className="w-2 h-2 bg-green-500 rounded-full animate-pulse"></div>
                            <span className="text-white font-medium">Netflix AI Assistant</span>
                        </div>
                        <button onClick={() => setIsOpen(false)} className="text-nfGrey-10 hover:text-white">✕</button>
                    </div>

                    {/* Message Area */}
                    <div className="flex-1 overflow-y-auto p-4 space-y-4 custom-scrollbar">
                        {messages.map((msg) => (
                            <div key={msg.id} className={`flex ${msg.role === 'user' ? 'justify-end' : 'justify-start'}`}>
                                <div className={`max-w-[80%] p-3 rounded-2xl text-[14px] ${msg.role === 'user' ? 'bg-nfRed text-white' : 'bg-black text-nfGrey-10'
                                    }`}>
                                    {msg.text}
                                </div>
                            </div>
                        ))}

                        {/* 3. Card hiển thị phim ngay trong Chat */}
                        <div className="bg-black rounded-lg overflow-hidden border border-nfGrey-400 w-[200px]">
                            <img src="https://example.com/movie-poster.jpg" className="w-full aspect-video object-cover" />
                            <div className="p-2">
                                <p className="text-white text-xs font-bold truncate">Stranger Things</p>
                                <button
                                    onClick={() => navigate('/movie/123')}
                                    className="mt-2 w-full py-1 bg-white text-black text-[10px] font-bold rounded hover:bg-nfGrey-10"
                                >
                                    XEM NGAY
                                </button>
                            </div>
                        </div>
                    </div>

                    {/* 4. Quick Replies & Input */}
                    <div className="p-3 bg-nfGrey-800 space-y-3">
                        <div className="flex gap-2 overflow-x-auto pb-1 no-scrollbar">
                            {['Phim hành động', 'Top 10 hôm nay', 'Phim mới'].map((txt) => (
                                <button key={txt} className="whitespace-nowrap px-3 py-1 bg-black border border-nfGrey-400 rounded-full text-[11px] text-nfGrey-10 hover:border-white transition">
                                    {txt}
                                </button>
                            ))}
                        </div>
                        <div className="flex gap-2">
                            <input
                                type="text"
                                placeholder="Hỏi AI về phim..."
                                className="flex-1 bg-black border border-nfGrey-400 rounded-full px-4 py-2 text-sm text-white outline-none focus:border-nfRed"
                            />
                            <button className="text-nfRed font-bold px-2">Gửi</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
}