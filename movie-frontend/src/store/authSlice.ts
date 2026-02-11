import { createSlice, type PayloadAction } from '@reduxjs/toolkit';
import { jwtDecode } from 'jwt-decode';

interface AuthState {
  user: any | null;
  token: string | null;
  role: string | null;
  isAuthenticated: boolean;
}

// Hàm bổ trợ để parse token an toàn
const getInfoFromToken = (token: string | null) => {
  if (!token) return null;
  try {
    const decoded: any = jwtDecode(token);
    // Kiểm tra token hết hạn (exp)
    if (decoded.exp * 1000 < Date.now()) {
      localStorage.removeItem('token');
      return null;
    }
    return decoded;
  } catch (e) {
    return null;
  }
};

const token = localStorage.getItem('token');
const decoded = getInfoFromToken(token);

const initialState: AuthState = {
  user: decoded ? { email: decoded.sub } : null, 
  token: token,
  role: decoded ? decoded.role : null, 
  isAuthenticated: !!decoded,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setCredentials: (state, action: PayloadAction<{ token: string }>) => {
      const { token } = action.payload;
      const decoded: any = jwtDecode(token);

      state.token = token;
      state.user = { email: decoded.sub };
      state.role = decoded.role;
      state.isAuthenticated = true;

      localStorage.setItem('token', token);
    },
    logout: (state) => {
      state.user = null;
      state.token = null;
      state.role = null;
      state.isAuthenticated = false;
      localStorage.removeItem('token');
    },
  },
});