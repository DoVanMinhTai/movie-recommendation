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

export async function getMoviesFilter({ sortBy, genreId }: { sortBy: string; genreId: string }) {
    const params: any = { sortBy: sortBy };
    if (genreId) {
        params.genre = genreId;
    }
    const response = await apiClientService.get(baseURL + "movies/", {
        params: params
    });
    return response.data;
}