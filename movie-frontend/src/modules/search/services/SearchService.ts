import apiClientService from "../../../common/services/ApiClientService";

const baseUrl = "http://localhost:8080";

export async function getMovieSuggestionByTitle(query: string) {
    const response = await apiClientService.get(`${baseUrl}/search/suggest`, {
        params: { q: query }
    });
    return response.data;
}

export async function getAllMovieByTitle(query: string) {
    const response = await apiClientService.get(`${baseUrl}/search/all`, {
        params: { q: query, pageAblesize: 20 },
    });
    return response.data;
}


