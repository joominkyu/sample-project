import AdminSidebar from '@/components/admin/AdminSidebar';
import AdminHeader from '@/components/admin/AdminHeader';
import AdminAuthGuard from '@/components/admin/AdminAuthGuard';

export default function AdminLayout({
                                        children,
                                    }: {
    children: React.ReactNode;
}) {
    return (
        <AdminAuthGuard>
            <div className="d-flex" style={{ minHeight: '100vh', backgroundColor: '#f8f9fa' }}>
                <AdminSidebar />

                <div className="flex-grow-1">
                    <AdminHeader />
                    <main className="p-4">{children}</main>
                </div>
            </div>
        </AdminAuthGuard>
    );
}