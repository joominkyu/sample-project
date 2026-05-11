'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import TiptapEditor from '@/components/admin/TiptapEditor';
import { createBoard } from '@/services/adminBoard';

export default function BoardCreatePage() {
    const router = useRouter();

    const [name, setName] = useState('');
    const [content, setContent] = useState('');
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setErrorMessage('');

        try {
            await createBoard({ name, content });
            router.push('/admin/board');
            router.refresh();
        } catch (error) {
            if (error instanceof Error) {
                setErrorMessage(error.message);
            } else {
                setErrorMessage('알 수 없는 오류가 발생했습니다.');
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card border-0 shadow-sm">
            <div className="card-body">
                <h3 className="mb-4">게시판 등록</h3>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">게시판명</label>
                        <input
                            type="text"
                            className="form-control"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="게시판명을 입력하세요"
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
                            {loading ? '등록 중...' : '등록'}
                        </button>
                        <button
                            type="button"
                            className="btn btn-outline-secondary"
                            onClick={() => router.push('/admin/board')}
                        >
                            취소
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}