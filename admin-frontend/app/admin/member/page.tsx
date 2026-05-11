import MemberGradeTable from '@/components/admin/MemberGradeTable';
import { getMemberList } from '@/services/adminMember';

export default async function MemberPage() {
    const members = await getMemberList();

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h3 className="mb-0">회원 관리</h3>
            </div>

            <MemberGradeTable members={members} />
        </div>
    );
}