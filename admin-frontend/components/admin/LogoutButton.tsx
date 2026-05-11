'use client';

import { useRouter } from 'next/navigation';
import { clearAdminLogin } from '@/utils/auth';

export default function LogoutButton() {
    const router = useRouter();

    const handleLogout = () => {
        clearAdminLogin();
        router.push('/login');
    };

    return (
        <button
            type="button"
            className="nav-link text-white text-start border-0 bg-transparent p-0"
            onClick={handleLogout}
        >
            로그아웃
        </button>
    );
}