import apiClientService from "../../../common/services/ApiClientService";

export const adminService = {
    getStats: () => apiClientService.get('/admin/statistics').then(res => res.data),
    getMovies: () => apiClientService.get('/admin/movies').then(res => res.data),
    deleteMovie: (id: number) => apiClientService.delete(`/admin/${id}`),
    getUsers: () => apiClientService.get('/admin/users').then(res => res.data),
    deleteUser: (id: number) => apiClientService.delete(`/admin/users/${id}`),
};