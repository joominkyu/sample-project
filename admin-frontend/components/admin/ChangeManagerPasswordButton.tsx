'use client';

import { changeManagerPassword } from '@/services/adminManager';

export default function ChangeManagerPasswordButton({ id }: { id: number }) {
    const handleChangePassword = async () => {
        const newPassword = window.prompt('새 비밀번호를 입력해주세요.');

        if (!newPassword || !newPassword.trim()) {
            alert('새 비밀번호를 입력해주세요.');
            return;
        }

        try {
            await changeManagerPassword(id, newPassword);
            alert('비밀번호가 변경되었습니다.');
        } catch (error) {
            alert(error instanceof Error ? error.message : '비밀번호 변경에 실패했습니다.');
        }
    };

    return (
        <button
            type="button"
            className="btn btn-sm btn-outline-secondary"
            onClick={handleChangePassword}
        >
            비밀번호 변경
        </button>
    );
}