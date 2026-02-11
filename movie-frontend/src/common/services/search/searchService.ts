import apiClientService from "../ApiClientService"

const baseURL = ""

export function searchMovie(movieName: string) {
    const url = baseURL + movieName;
    const response = apiClientService.get(url);
    return response;
}