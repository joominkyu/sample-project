import { apiFetch } from '@/utils/api';
import { BoardItem } from '@/types/board';

export async function getBoardList(): Promise<BoardItem[]> {
    return apiFetch<BoardItem[]>('/api/admin/board');
}

export async function getBoard(id: number): Promise<BoardItem> {
    return apiFetch<BoardItem>(`/api/admin/board/${id}`);
}

export async function createBoard(data: {
    name: string;
    content: string;
}): Promise<BoardItem> {
    return apiFetch<BoardItem>('/api/admin/board', {
        method: 'POST',
        body: JSON.stringify(data),
    });
}

export async function updateBoard(
    id: number,
    data: { name: string; content: string }
): Promise<BoardItem> {
    return apiFetch<BoardItem>(`/api/admin/board/${id}`, {
        method: 'PUT',
        body: JSON.stringify(data),
    });
}

export async function deleteBoard(id: number): Promise<void> {
    return apiFetch<void>(`/api/admin/board/${id}`, {
        method: 'DELETE',
    });
}