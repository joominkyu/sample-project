'use client';

import { useRouter } from 'next/navigation';
import { deleteManager } from '@/services/adminManager';
import { getAdminLogin } from '@/utils/auth';
import { ManagerGrade } from '@/types/manager';

export default function DeleteManagerButton({
                                                id,
                                                loginId,
                                                grade,
                                            }: {
    id: number;
    loginId: string;
    grade: ManagerGrade;
}) {
    const router = useRouter();

    const currentLoginId = getAdminLogin();
    const isSelf = currentLoginId === loginId;
    const isSuperAdmin = grade === 'SUPER_ADMIN';

    const handleDelete = async () => {
        if (isSelf) {
            alert('본인 계정은 삭제할 수 없습니다.');
            return;
        }

        if (isSuperAdmin) {
            alert('최초 관리자 계정은 삭제할 수 없습니다.');
            return;
        }

        const confirmed = window.confirm('정말 삭제하시겠습니까?');
        if (!confirmed) return;

        try {
            await deleteManager(id);
            router.refresh();
        } catch (error) {
            alert(error instanceof Error ? error.message : '관리자 삭제에 실패했습니다.');
        }
    };

    return (
        <button
            type="button"
            className="btn btn-sm btn-outline-danger"
            onClick={handleDelete}
            disabled={isSelf || isSuperAdmin}
        >
            삭제
        </button>
    );
}