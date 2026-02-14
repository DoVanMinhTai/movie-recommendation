import React, { useState } from 'react';
import MovieManage from './MovieManage';      
import Dashboard from './Dashboard';

type TabType = 'statistics' | 'movies' | 'users';

export default function AdminDashboard() {
    const [activeTab, setActiveTab] = useState<TabType>('statistics');

    return (
        <div className="min-h-screen bg-[#141414] text-white">
            {/* Header của trang Admin */}
            <div className="p-6 border-b border-zinc-800 bg-zinc-900/50 sticky top-0 z-20 backdrop-blur-md">
                <div className="flex flex-col md:flex-row justify-between items-start md:items-center gap-4">
                    <h1 className="text-2xl font-black tracking-wider text-red-600">ADMIN CONSOLE</h1>
                    
                    {/* Thanh điều hướng Tab */}
                    <nav className="flex bg-black rounded-lg p-1 border border-zinc-800">
                        <button
                            onClick={() => setActiveTab('statistics')}
                            className={`px-4 py-2 rounded-md text-sm font-bold transition-all ${
                                activeTab === 'statistics' ? 'bg-zinc-800 text-white shadow-lg' : 'text-zinc-500 hover:text-zinc-300'
                            }`}
                        >
                            📊 Thống kê
                        </button>
                        <button
                            onClick={() => setActiveTab('movies')}
                            className={`px-4 py-2 rounded-md text-sm font-bold transition-all ${
                                activeTab === 'movies' ? 'bg-zinc-800 text-white shadow-lg' : 'text-zinc-500 hover:text-zinc-300'
                            }`}
                        >
                            🎬 Quản lý phim
                        </button>
                        <button
                            onClick={() => setActiveTab('users')}
                            className={`px-4 py-2 rounded-md text-sm font-bold transition-all ${
                                activeTab === 'users' ? 'bg-zinc-800 text-white shadow-lg' : 'text-zinc-500 hover:text-zinc-300'
                            }`}
                        >
                            👥 Người dùng
                        </button>
                    </nav>
                </div>
            </div>

            {/* Nội dung thay đổi dựa trên Tab */}
            <main className="p-6 animate-fadeIn">
                {activeTab === 'statistics' && <Dashboard />}
                {activeTab === 'movies' && <MovieManage />}
                {activeTab === 'users' && (
                    <div className="p-20 text-center text-zinc-500 border-2 border-dashed border-zinc-800 rounded-xl">
                        Chức năng quản lý người dùng đang được phát triển...
                    </div>
                )}
            </main>
        </div>
    );
}