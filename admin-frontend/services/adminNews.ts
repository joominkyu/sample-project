import { apiFetch } from '@/utils/api';
import { NewsItem } from '@/types/news';

export async function getNewsList(): Promise<NewsItem[]> {
    return apiFetch<NewsItem[]>('/api/admin/news');
}

export async function getNews(id: number): Promise<NewsItem> {
    return apiFetch<NewsItem>(`/api/admin/news/${id}`);
}

export async function createNews(data: {
    title: string;
    content: string;
}): Promise<NewsItem> {
    return apiFetch<NewsItem>('/api/admin/news', {
        method: 'POST',
        body: JSON.stringify(data),
    });
}

export async function updateNews(
    id: number,
    data: { title: string; content: string }
): Promise<NewsItem> {
    return apiFetch<NewsItem>(`/api/admin/news/${id}`, {
        method: 'PUT',
        body: JSON.stringify(data),
    });
}

export async function deleteNews(id: number): Promise<void> {
    return apiFetch<void>(`/api/admin/news/${id}`, {
        method: 'DELETE',
    });
}