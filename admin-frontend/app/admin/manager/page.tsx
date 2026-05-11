import Link from 'next/link';
import { getManagerList } from '@/services/adminManager';
import ManagerTable from '@/components/admin/ManagerTable';

export default async function ManagerPage() {
    const managers = await getManagerList();

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h3 className="mb-0">관리자 관리</h3>
                <Link href="/admin/manager/create" className="btn btn-primary">
                    관리자 추가
                </Link>
            </div>

            <ManagerTable managers={managers} />
        </div>
    );
}