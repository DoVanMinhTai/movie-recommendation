import apiClientService, { type PageAbleResponse } from "../../../common/services/ApiClientService";
import type { MovieThumbnailVm } from "../model/MovieThumbnailVm";

const baseUrl = "http://localhost:8080";

export async function getMovieDetailById(movieId: number) : Promise<PageAbleResponse> {
    const response = await apiClientService.get(`${baseUrl}/movie/${Number(movieId)}`);
    return response.data;
}

export async function getMovieSimilarById(movieId: number) : Promise<MovieThumbnailVm[]> {
    const response = await apiClientService.get(`${baseUrl}/recommendation/similar/${movieId}`);
    return response.data;
}