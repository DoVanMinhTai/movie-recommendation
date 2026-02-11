import { useEffect } from "react";
import { useSearchParams } from "react-router-dom";

export default function SearchResult() {
    const [searchParams] = useSearchParams();
    const query = searchParams.get("s");

    useEffect(() => {
        if(query) {
            // call api to get Result 
        }
    }, [query]);

    return (<></>);
}