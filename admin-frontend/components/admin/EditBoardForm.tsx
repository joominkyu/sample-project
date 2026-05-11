'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { BoardItem } from '@/types/board';
import TiptapEditor from '@/components/admin/TiptapEditor';
import { updateBoard } from '@/services/adminBoard';

export default function EditBoardForm({ board }: { board: BoardItem }) {
    const router = useRouter();

    const [name, setName] = useState(board.name);
    const [content, setContent] = useState(board.content || '');
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setErrorMessage('');

        try {
            await updateBoard(board.id, { name, content });
            router.push('/admin/board');
            router.refresh();
        } catch (error) {
            setErrorMessage(
                error instanceof Error ? error.message : '게시판 수정에 실패했습니다.'
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card border-0 shadow-sm">
            <div className="card-body">
                <h3 className="mb-4">게시판 수정</h3>

                <form onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label className="form-label">게시판명</label>
                        <input
                            type="text"
                            className="form-control"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
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