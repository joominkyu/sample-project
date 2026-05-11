import Link from 'next/link';
import LogoutButton from '@/components/admin/LogoutButton';

export default function AdminSidebar() {
    return (
        <aside
            className="bg-dark text-white p-3"
            style={{ width: '240px', minHeight: '100vh' }}
        >
            <h4 className="mb-4">Admin</h4>

            <nav className="nav flex-column gap-2">
                <Link href="/admin/news" className="nav-link text-white">
                    뉴스 관리
                </Link>
                <Link href="/admin/board" className="nav-link text-white">
                    게시판 관리
                </Link>
                <Link href="/admin/member" className="nav-link text-white">
                    회원 관리
                </Link>
                <Link href="/admin/manager" className="nav-link text-white">
                    관리자 관리
                </Link>
                <LogoutButton />
            </nav>
        </aside>
    );
}