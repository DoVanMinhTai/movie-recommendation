import { Link } from "react-router-dom";

type props = {
    id: any;
    children: React.ReactNode
};

export default function MovieWrapper({ id, children }: props) {
    return (
        <Link to={`/movie/${id}`} className="">
            {children}
        </Link>
    );
}