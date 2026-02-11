import { useSearchParams } from "react-router-dom"
import { useQuery } from "@tanstack/react-query";
import { getAllGenres, getMoviesFilter } from "../../modules/category/service/CategoryService";
import MovieGrid from "../../common/components/MovieGrid";
import SideBar from "../../modules/category/components/SideBar";
import { useState } from "react";

export default function Category() {
    const [searchParams, setSearchParams] = useSearchParams();
    const genreId = searchParams.get('genre') || '';
    const sortByParam = searchParams.get('sortBy') || 'POPULARITY';

    const genresQuery = useQuery({
        queryKey: ['genres'],
        queryFn: getAllGenres,
        staleTime: Infinity,
    });
    const movieQuery = useQuery({
        queryKey: ['movies', sortByParam, genreId],
        queryFn: () => getMoviesFilter({ sortBy: sortByParam, genreId }),
        placeholderData: (pre) => pre,
    });

    const handleSortCHange = (newSortBy: string) => {
        setSearchParams({ sortBy: newSortBy, genre: genreId });
    }

    const handleGenreChange = (newGenreId: string) => {
        setSearchParams({ sortBy: sortByParam, genre: newGenreId });
    }

    const [page, setPage] = useState(0);

    const banner_category_image = "https://images.unsplash.com/photo-1485846234645-a62644f84728?q=80&w=1280";
    return (
        <>
            <div className="flex flex-col w-full bg-black min-h-screen">
                <div className="banner w-full h-[60vh] relative overflow-hidden border-4 border-black mb-8">
                    <img className="w-full h-full object-cover opacity-60 rounded-lg" src={banner_category_image} alt="Banner" />
                    <div className="absolute bottom-10 left-12">
                    </div>
                </div>
                <div className="flex w-full">
                    <SideBar
                        genres={genresQuery.data || []}
                        activeSort={sortByParam}
                        activeGenre={genreId}
                        onSortChange={handleSortCHange}
                        onGenreChange={handleGenreChange}
                    />
                    <MovieGrid data={movieQuery.data || []} loading={movieQuery.isLoading} onPageChange={setPage} />
                </div>

            </div>
        </>
    )
}