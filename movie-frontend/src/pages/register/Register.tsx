import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { register } from "../../modules/auth/service/AuthService";

export default function Register() {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  function handleRegister() {
    if (!username || !email || !password) {
      alert('Vui lòng điền tất cả các trường.');
      return;
    }
    register(username, email, password)
      .then(() => {
        alert('Đăng ký thành công!');
        navigate('/login');
      })
      .catch((error: { message: string; }) => {
        alert('Đăng ký thất bại: ' + error.message);
      });
  }

  return (
    <div className="min-h-screen bg-gradient-to-b from-black via-[#300000] to-black">
      <div className="min-h-screen z-40 overflow-hidden flex flex-col items-center justify-center px-4">
        <div className="max-w-[440px] text-center bg-black p-10 rounded-lg bg-opacity-75">
          <h1 className="text-3xl font-bold mb-4 text-[#333]">Chương trình truyền hình, phim không giới hạn và nhiều nội dung khác.</h1>
          <p className="text-lg mb-6 text-[#333]">Sẵn sàng xem chưa? Nhập email để tạo hoặc kích hoạt lại tư cách thành viên của bạn.</p>

          <div className="flex flex-col gap-2">
            <input
              type="text"
              placeholder="Tên người dùng"
              className="p-4 border text-white rounded-sm outline-none focus:border-white"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            <input
              type="email"
              placeholder="Địa chỉ email"
              className="p-4 border text-white rounded-sm outline-none focus:border-white"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
            />
            <input
              type="password"
              placeholder="Mật khẩu"
              className="p-4 border text-white rounded-sm outline-none focus:border-white"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <button 
            onClick={handleRegister}
            className=" text-white text-xl px-8 py-4 rounded-sm bg-red-700 flex items-center justify-center">
              Bắt đầu {'>'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}