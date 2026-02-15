import apiClientService from "../../../common/services/ApiClientService"

const baseURL = "http://localhost:8080/movie/"

export async function getMovieByType(type: string) {
    const response = await apiClientService.get(baseURL, {
        params: { type: type }
    })

    return response.data;
}

export async function getAllGenres() {
    const response = await apiClientService.get(baseURL + "movies/genres");
    console.log("Genres response:", response);
    return response.data;
}

export async function getMoviesFilter({ sortBy, genre, page }: { sortBy: string; genre: string; page: number }) {
    const params: any = { 
        sortBy: sortBy,
        page: page, 
        size: 10   
    };
    if (genre) {
        params.genre = genre;
    }
    const response = await apiClientService.get(baseURL + "movies/", {
        params: params
    });
    return response.data;
}