import React, { useEffect, useState } from 'react';
import { getAuthData } from '../../common/auth/AuthUtils';
import { getMyProfile } from '../../modules/profile/service/ProfileService';

// Định nghĩa interface để tránh lỗi type 'any'
interface UserData {
    fullName: string;
    email: string;
    role: string;
    joinedDate: string;
    preferences: string[];
}

export default function Profile() {
    const auth = getAuthData();
    const [loading, setLoading] = useState(true);
    const [userData, setUserData] = useState<UserData>({
        fullName: "",
        email: auth?.sub || "",
        role: auth?.role || "USER",
        joinedDate: "",
        preferences: []
    });

    useEffect(() => {
        getMyProfile()
            .then((data) => {
                // data ở đây chính là ProfileVm từ Backend
                setUserData({
                    fullName: data.fullName || "N/A",
                    email: data.email || auth?.sub,
                    role: data.role || "USER",
                    joinedDate: data.joinedDate || "Vừa mới đây",
                    // Khớp đúng với field 'preferences' trong ProfileVm
                    preferences: data.preferences || [] 
                });
            })
            .catch(err => console.error("Lỗi:", err))
            .finally(() => setLoading(false));
    }, []);

    if (loading) return (
        <div className="min-h-screen bg-[#141414] flex items-center justify-center">
            <div className="animate-spin rounded-full h-12 w-12 border-t-2 border-b-2 border-red-600"></div>
        </div>
    );

    return (
        <div className="min-h-screen bg-[#141414] text-white pt-24 pb-12 px-4 md:px-20 bg-gradient-to-b from-black/60 to-[#141414]">
            <div className="max-w-4xl mx-auto animate-fadeIn">
                <header className="mb-10">
                    <h1 className="text-5xl font-extrabold tracking-tight mb-2">Tài khoản</h1>
                    <div className="h-1 w-20 bg-red-600 rounded-full"></div>
                </header>

                <div className="grid gap-8">
                    {/* Section 1: Thông tin cơ bản */}
                    <section className="bg-zinc-900/50 p-8 rounded-xl border border-zinc-800 hover:border-zinc-700 transition-all duration-300 shadow-2xl">
                        <div className="flex flex-col md:flex-row items-center gap-8">
                            <div className="relative group">
                                <img 
                                    src="https://upload.wikimedia.org/wikipedia/commons/0/0b/Netflix-avatar.png" 
                                    alt="Avatar" 
                                    className="w-32 h-32 rounded-lg shadow-lg group-hover:scale-105 transition-transform duration-300"
                                />
                                <div className="absolute inset-0 bg-black/40 opacity-0 group-hover:opacity-100 flex items-center justify-center rounded-lg transition-opacity cursor-pointer">
                                    <span className="text-xs font-bold">THAY ĐỔI</span>
                                </div>
                            </div>
                            
                            <div className="text-center md:text-left flex-1">
                                <div className="flex items-center justify-center md:justify-start gap-3 mb-2">
                                    <h2 className="text-3xl font-bold">{userData.fullName}</h2>
                                    <span className="px-2 py-0.5 bg-red-600 text-[10px] font-black rounded shadow-sm">
                                        {userData.role}
                                    </span>
                                </div>
                                <p className="text-zinc-400 font-medium">Email: <span className="text-white">{userData.email}</span></p>
                                <p className="text-zinc-500 text-sm mt-1">Gia nhập từ: {userData.joinedDate}</p>
                            </div>

                            <button className="px-5 py-2.5 bg-zinc-800 hover:bg-zinc-700 border border-zinc-700 rounded font-semibold text-sm transition-colors">
                                CHỈNH SỬA
                            </button>
                        </div>
                    </section>

                    <section className="bg-zinc-900/50 p-8 rounded-xl border border-zinc-800 shadow-2xl">
                        <div className="flex items-center gap-3 mb-6">
                            <div className="p-2 bg-red-600/10 rounded-lg">
                                <span className="text-xl">🎬</span>
                            </div>
                            <div>
                                <h3 className="text-xl font-bold">Thể loại yêu thích</h3>
                                <p className="text-zinc-500 text-xs">Chúng tôi dựa vào đây để gợi ý phim cho bạn</p>
                            </div>
                        </div>
                        
                        <div className="flex flex-wrap gap-3">
                            {userData.preferences.length > 0 ? (
                                userData.preferences.map((genre, index) => (
                                    <div 
                                        key={index} 
                                        className="px-4 py-2 bg-zinc-800 hover:bg-red-600 border border-zinc-700 hover:border-red-500 rounded-md text-sm font-medium transition-all duration-200 cursor-default"
                                    >
                                        {genre}
                                    </div>
                                ))
                            ) : (
                                <div className="w-full py-10 text-center border-2 border-dashed border-zinc-800 rounded-xl">
                                    <p className="text-zinc-500">Bạn chưa chọn sở thích nào.</p>
                                    <button className="mt-3 text-red-500 font-bold hover:underline">Khám phá ngay</button>
                                </div>
                            )}
                        </div>
                    </section>

                    <footer className="flex justify-between items-center px-2">
                        <button className="text-zinc-500 hover:text-red-500 transition text-sm font-medium">
                            Xóa tài khoản
                        </button>
                        <button className="text-zinc-500 hover:text-white transition text-sm font-medium">
                            Đăng xuất khỏi tất cả thiết bị
                        </button>
                    </footer>
                </div>
            </div>
        </div>
    );
}