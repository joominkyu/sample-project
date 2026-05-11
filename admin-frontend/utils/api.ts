const BASE_URL = process.env.NEXT_PUBLIC_ADMIN_API_BASE_URL;

if (!BASE_URL) {
    throw new Error('NEXT_PUBLIC_ADMIN_API_BASE_URL 환경변수가 설정되지 않았습니다.');
}

export async function apiFetch<T>(
    path: string,
    options?: RequestInit
): Promise<T> {
    const res = await fetch(`${BASE_URL}${path}`, {
        ...options,
        headers: {
            'Content-Type': 'application/json',
            ...(options?.headers || {}),
        },
        cache: 'no-store',
    });

    if (!res.ok) {
        const text = await res.text();

        try {
            const json = JSON.parse(text);
            throw new Error(json.message || `API 요청 실패: ${res.status}`);
        } catch {
            throw new Error(text || `API 요청 실패: ${res.status}`);
        }
    }

    if (res.status === 204) {
        return undefined as T;
    }

    const text = await res.text();

    if (!text) {
        return undefined as T;
    }

    return JSON.parse(text) as T;
}