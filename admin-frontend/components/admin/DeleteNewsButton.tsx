'use client';

import { useRouter } from 'next/navigation';
import { deleteNews } from '@/services/adminNews';

export default function DeleteNewsButton({ id }: { id: number }) {
    const router = useRouter();

    const handleDelete = async () => {
        const confirmed = window.confirm('정말 삭제하시겠습니까?');
        if (!confirmed) return;

        try {
            await deleteNews(id);
            router.refresh();
        } catch (error) {
            alert(error instanceof Error ? error.message : '오류가 발생했습니다.');
        }
    };

    return (
        <button
            type="button"
            className="btn btn-sm btn-outline-danger"
            onClick={handleDelete}
        >
            삭제
        </button>
    );
}