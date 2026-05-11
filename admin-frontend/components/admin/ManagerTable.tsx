'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { getAdminLogin } from '@/utils/auth';
import { ManagerGrade, ManagerItem } from '@/types/manager';
import {
    changeManagerPassword,
    deleteManager,
    updateManagerGrade,
} from '@/services/adminManager';

function formatDate(dateString: string) {
    return new Date(dateString).toLocaleString('ko-KR');
}

const gradeOptions: ManagerGrade[] = ['SUPER_ADMIN', 'ADMIN'];

export default function ManagerTable({
                                         managers,
                                     }: {
    managers: ManagerItem[];
}) {
    const router = useRouter();
    const currentLoginId = getAdminLogin();

    const [gradeMap, setGradeMap] = useState<Record<number, ManagerGrade>>(
        Object.fromEntries(managers.map((manager) => [manager.id, manager.grade]))
    );
    const [loadingId, setLoadingId] = useState<number | null>(null);

    const handleChangeGrade = async (manager: ManagerItem) => {
        try {
            setLoadingId(manager.id);
            await updateManagerGrade(manager.id, gradeMap[manager.id]);
            router.refresh();
        } catch (error) {
            alert(error instanceof Error ? error.message : '등급 변경에 실패했습니다.');
        } finally {
            setLoadingId(null);
        }
    };

    const handleChangePassword = async (managerId: number) => {
        const newPassword = window.prompt('새 비밀번호를 입력해주세요.');

        if (!newPassword || !newPassword.trim()) {
            return;
        }

        try {
            await changeManagerPassword(managerId, newPassword);
            alert('비밀번호가 변경되었습니다.');
        } catch (error) {
            alert(error instanceof Error ? error.message : '비밀번호 변경에 실패했습니다.');
        }
    };

    const handleDelete = async (managerId: number) => {
        const confirmed = window.confirm('정말 삭제하시겠습니까?');
        if (!confirmed) return;

        try {
            await deleteManager(managerId);
            router.refresh();
        } catch (error) {
            alert(error instanceof Error ? error.message : '관리자 삭제에 실패했습니다.');
        }
    };

    return (
        <div className="card border-0 shadow-sm">
            <div className="card-body">
                <table className="table align-middle mb-0">
                    <thead>
                    <tr>
                        <th style={{ width: '80px' }}>번호</th>
                        <th>아이디</th>
                        <th>이름</th>
                        <th style={{ width: '180px' }}>등급</th>
                        <th style={{ width: '220px' }}>가입일자</th>
                        <th style={{ width: '240px' }}>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    {managers.map((manager) => {
                        const isSelf = currentLoginId === manager.loginId;
                        const isSuperAdmin = manager.grade === 'SUPER_ADMIN';
                        const canChangeGrade = !isSelf && !isSuperAdmin;
                        const canDelete = !isSelf && !isSuperAdmin;

                        return (
                            <tr key={manager.id}>
                                <td>{manager.id}</td>
                                <td>{manager.loginId}</td>
                                <td>{manager.name}</td>
                                <td>
                                    {canChangeGrade ? (
                                        <div className="d-flex gap-2">
                                            <select
                                                className="form-select form-select-sm"
                                                value={gradeMap[manager.id]}
                                                onChange={(e) =>
                                                    setGradeMap((prev) => ({
                                                        ...prev,
                                                        [manager.id]: e.target.value as ManagerGrade,
                                                    }))
                                                }
                                            >
                                                {gradeOptions.map((grade) => (
                                                    <option key={grade} value={grade}>
                                                        {grade}
                                                    </option>
                                                ))}
                                            </select>
                                            <button
                                                type="button"
                                                className="btn btn-sm btn-outline-primary"
                                                onClick={() => handleChangeGrade(manager)}
                                                disabled={loadingId === manager.id}
                                            >
                                                변경
                                            </button>
                                        </div>
                                    ) : (
                                        <span>{manager.grade}</span>
                                    )}
                                </td>
                                <td>{formatDate(manager.createdAt)}</td>
                                <td>
                                    <div className="d-flex gap-2">
                                        <button
                                            type="button"
                                            className="btn btn-sm btn-outline-secondary"
                                            onClick={() => handleChangePassword(manager.id)}
                                        >
                                            비밀번호 변경
                                        </button>

                                        {canDelete && (
                                            <button
                                                type="button"
                                                className="btn btn-sm btn-outline-danger"
                                                onClick={() => handleDelete(manager.id)}
                                            >
                                                삭제
                                            </button>
                                        )}
                                    </div>
                                </td>
                            </tr>
                        );
                    })}
                    </tbody>
                </table>
            </div>
        </div>
    );
}