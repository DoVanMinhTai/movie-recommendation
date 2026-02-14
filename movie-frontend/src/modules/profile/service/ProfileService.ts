import apiClientService from "../../../common/services/ApiClientService";

const baseUrl = "http://localhost:8080"
export async function getMyProfile() {
    const response = await apiClientService.get(`${baseUrl}/auth/profile`);
    return response.data; 
}