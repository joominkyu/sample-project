'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { NewsItem } from '@/types/news';
import TiptapEditor from '@/components/admin/TiptapEditor';
import { updateNews } from '@/services/adminNews';

export default function EditNewsForm({ news }: { news: NewsItem }) {
    const router = useRouter();

    const [title, setTitle] = useState(news.title);
    const [content, setContent] = useState(news.content);
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setErrorMessage('');

        try {
            await updateNews(news.id, { title, content });
            router.push('/admin/news');
            router.refresh();
        } catch (error) {
            setErrorMessage(
                error instanceof Error ? error.message : '뉴스 수정에 실패했습니다.'
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card border-0 shadow-sm">
            <div className="card-body">
                <h3 className="mb-4">뉴스 수정</h3>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">제목</label>
                        <input
                            type="text"
                            className="form-control"
                            value={title}
                            onChange={(e) => setTitle(e.target.value)}
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">내용</label>
                        <TiptapEditor value={content} onChange={setContent} />
                    </div>

                    {errorMessage && (
                        <div className="alert alert-danger py-2">{errorMessage}</div>
                    )}

                    <div className="d-flex gap-2">
                        <button type="submit" className="btn btn-primary" disabled={loading}>
                            {loading ? '수정 중...' : '수정'}
                        </button>
                        <button
                            type="button"
                            className="btn btn-outline-secondary"
                            onClick={() => router.push('/admin/news')}
                        >
                            취소
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}