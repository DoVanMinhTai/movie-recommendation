import { Navigate, Outlet } from 'react-router-dom';
import { getAuthData } from '../AuthUtils';

interface Props {
    allowedRoles: string[];
}

const ProtectedRoute = ({ allowedRoles }: Props) => {
    const auth = getAuthData();
    const isNewUser = auth?.isNewUser;
    
    if (!auth) {
        return <Navigate to="/login" replace />;
    }

    if (!allowedRoles.includes(auth.role)) {
        console.log(1)
        return <Navigate to="/" replace />;
    }

    if (auth.role === 'USER' && isNewUser && window.location.pathname !== '/onboarding') {
        console.log(2)
        return <Navigate to="/onboarding" replace />;
    }

    return <Outlet />;
};

export default ProtectedRoute;