import { Outlet } from "react-router-dom";
import Header from "./components/Header";
import Footer from "./components/Footer";

const AuthLayout = () => {
    return (<>
        <div className="bg-[#141414] min-h-screen text-white overflow-x-hidden">
            <Header />
            <main>
                <Outlet />
            </main>
            <Footer />
        </div>
    </>)
}
export default AuthLayout;