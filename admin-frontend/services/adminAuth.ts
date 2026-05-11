import { apiFetch } from '@/utils/api';

export interface LoginRequest {
    loginId: string;
    password: string;
}

export interface LoginResponse {
    message: string;
    loginId: string;
    name: string;
}

export async function login(data: LoginRequest): Promise<LoginResponse> {
    return apiFetch<LoginResponse>('/api/admin/login', {
        method: 'POST',
        body: JSON.stringify(data),
    });
}