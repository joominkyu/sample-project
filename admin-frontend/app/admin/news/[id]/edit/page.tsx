import EditNewsForm from '@/components/admin/EditNewsForm';
import { getNews } from '@/services/adminNews';

export default async function EditNewsPage({
                                               params,
                                           }: {
    params: Promise<{ id: string }>;
}) {
    const { id } = await params;
    const news = await getNews(Number(id));

    return <EditNewsForm news={news} />;
}