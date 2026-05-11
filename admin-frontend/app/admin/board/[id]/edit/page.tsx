import EditBoardForm from '@/components/admin/EditBoardForm';
import { getBoard } from '@/services/adminBoard';

export default async function EditBoardPage({
                                                params,
                                            }: {
    params: Promise<{ id: string }>;
}) {
    const { id } = await params;
    const board = await getBoard(Number(id));

    return <EditBoardForm board={board} />;
}