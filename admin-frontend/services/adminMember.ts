import { apiFetch } from '@/utils/api';
import { MemberGrade, MemberItem } from '@/types/member';

export async function getMemberList(): Promise<MemberItem[]> {
    return apiFetch<MemberItem[]>('/api/admin/members');
}

export async function updateMemberGrade(
    memberId: number,
    grade: MemberGrade
): Promise<MemberItem> {
    return apiFetch<MemberItem>(`/api/admin/members/${memberId}/grade`, {
        method: 'PATCH',
        body: JSON.stringify({ grade }),
    });
}