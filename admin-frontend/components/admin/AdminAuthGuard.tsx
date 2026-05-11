'use client';

import { useEffect, useState } from 'react';
import { useRouter } from 'next/navigation';
import { isAdminLoggedIn } from '@/utils/auth';

export default function AdminAuthGuard({
                                           children,
                                       }: {
    children: React.ReactNode;
}) {
    const router = useRouter();
    const [checked, setChecked] = useState(false);
    const [loggedIn, setLoggedIn] = useState(false);

    useEffect(() => {
        const result = isAdminLoggedIn();

        if (!result) {
            router.replace('/login');
            return;
        }

        setLoggedIn(true);
        setChecked(true);
    }, [router]);

    if (!checked) {
        return null;
    }

    if (!loggedIn) {
        return null;
    }

    return <>{children}</>;
}