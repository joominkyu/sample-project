import { apiFetch } from '@/utils/api';
import { getAdminLogin } from '@/utils/auth';
import { ManagerGrade, ManagerItem } from '@/types/manager';

export interface ManagerCreateRequest {
    loginId: string;
    password: string;
    name: string;
}

export interface ManagerLoginIdCheckResponse {
    available: boolean;
    message: string;
}

export async function getManagerList(): Promise<ManagerItem[]> {
    return apiFetch<ManagerItem[]>('/api/admin/managers');
}

export async function checkManagerLoginId(
    loginId: string
): Promise<ManagerLoginIdCheckResponse> {
    return apiFetch<ManagerLoginIdCheckResponse>(
        `/api/admin/managers/check-login-id?loginId=${encodeURIComponent(loginId)}`
    );
}

export async function createManager(
    data: ManagerCreateRequest
): Promise<ManagerItem> {
    return apiFetch<ManagerItem>('/api/admin/managers', {
        method: 'POST',
        body: JSON.stringify(data),
    });
}

export async function changeManagerPassword(
    managerId: number,
    newPassword: string
): Promise<void> {
    return apiFetch<void>(`/api/admin/managers/${managerId}/password`, {
        method: 'PATCH',
        body: JSON.stringify({ newPassword }),
    });
}

export async function updateManagerGrade(
    managerId: number,
    grade: ManagerGrade
): Promise<ManagerItem> {
    const loginId = getAdminLogin();

    return apiFetch<ManagerItem>(`/api/admin/managers/${managerId}/grade`, {
        method: 'PATCH',
        headers: {
            'X-Admin-LoginId': loginId ?? '',
        },
        body: JSON.stringify({ grade }),
    });
}

export async function deleteManager(managerId: number): Promise<void> {
    const loginId = getAdminLogin();

    return apiFetch<void>(`/api/admin/managers/${managerId}`, {
        method: 'DELETE',
        headers: {
            'X-Admin-LoginId': loginId ?? '',
        },
    });
}