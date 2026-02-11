import axios, { type AxiosRequestConfig, type AxiosInstance } from "axios";
import { HandleError } from "./error/HandleError";

const axiosInstance: AxiosInstance = axios.create({
    baseURL: "",
    headers: {
        'Content-Type': 'application/json; charset=UTF-8'
    },
    timeout: 10000
});

axiosInstance.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`
    }

    return config;
}, (error) => {
    if (error.response) {
        throw new HandleError(error.response.data);
    }
    throw error;
});

const apiClientService = {
    get: (endpoint: string, config?: AxiosRequestConfig) => axiosInstance.get(endpoint, config),
    post: (endpoint: string, data?: any, config?: AxiosRequestConfig) => axiosInstance.post(endpoint, data, config),
    put: (endpoint: string, data?: any, config?: AxiosRequestConfig) => axiosInstance.put(endpoint, data, config),
    delete: (endpoint: string, config?: AxiosRequestConfig) => axiosInstance.delete(endpoint, config),
}

export default apiClientService;