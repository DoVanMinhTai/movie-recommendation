import { jwtDecode } from "jwt-decode";

interface JwtPayload {
    sub: string;
    role: string; 
    iat: number;
    exp: number;
    isNewUser: boolean;
}

export const getAuthData = () => {
    const token = localStorage.getItem("token");
    if (!token) return null;

    try {
        const decoded = jwtDecode<JwtPayload>(token);
        if (decoded.exp * 1000 < Date.now()) {
            localStorage.removeItem("token");
            return null;
        }
        return decoded;
    } catch (error) {
        return null;
    }
};

export const checkOnboarding = (): boolean => {
    const auth = getAuthData();
    if (auth?.role === 'ADMIN') return false;
    return auth?.isNewUser === true;
};