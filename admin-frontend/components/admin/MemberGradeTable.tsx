'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { MemberGrade, MemberItem } from '@/types/member';
import { updateMemberGrade } from '@/services/adminMember';

const gradeOptions: MemberGrade[] = ['BASIC', 'SILVER', 'GOLD', 'VIP'];

export default function MemberGradeTable({
                                             members,
                                         }: {
    members: MemberItem[];
}) {
    const router = useRouter();
    const [gradeMap, setGradeMap] = useState<Record<number, MemberGrade>>(
        Object.fromEntries(members.map((member) => [member.id, member.grade]))
    );
    const [loadingId, setLoadingId] = useState<number | null>(null);

    const handleChange = (memberId: number, grade: MemberGrade) => {
        setGradeMap((prev) => ({
            ...prev,
            [memberId]: grade,
        }));
    };

    const handleSave = async (memberId: number) => {
        try {
            setLoadingId(memberId);
            await updateMemberGrade(memberId, gradeMap[memberId]);
            router.refresh();
        } catch (error) {
            alert(error instanceof Error ? error.message : '등급 변경에 실패했습니다.');
        } finally {
            setLoadingId(null);
        }
    };

    return (
        <div className="card border-0 shadow-sm">
            <div className="card-body">
                <table className="table align-middle mb-0">
                    <thead>
                    <tr>
                        <th style={{ width: '80px' }}>번호</th>
                        <th>이름</th>
                        <th>아이디</th>
                        <th style={{ width: '180px' }}>등급</th>
                        <th style={{ width: '140px' }}>관리</th>
                    </tr>
                    </thead>
                    <tbody>
                    {members.map((member) => (
                        <tr key={member.id}>
                            <td>{member.id}</td>
                            <td>{member.name}</td>
                            <td>{member.loginId}</td>
                            <td>
                                <select
                                    className="form-select"
                                    value={gradeMap[member.id]}
                                    onChange={(e) =>
                                        handleChange(member.id, e.target.value as MemberGrade)
                                    }
                                >
                                    {gradeOptions.map((grade) => (
                                        <option key={grade} value={grade}>
                                            {grade}
                                        </option>
                                    ))}
                                </select>
                            </td>
                            <td>
                                <button
                                    type="button"
                                    className="btn btn-sm btn-primary"
                                    onClick={() => handleSave(member.id)}
                                    disabled={loadingId === member.id}
                                >
                                    {loadingId === member.id ? '저장 중...' : '변경'}
                                </button>
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}