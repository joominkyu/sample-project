import Link from 'next/link';
import { getBoardList } from '@/services/adminBoard';
import DeleteBoardButton from '@/components/admin/DeleteBoardButton';
import { summarizeHtml } from '@/utils/content';

function formatDate(dateString: string) {
    return new Date(dateString).toLocaleString('ko-KR');
}

export default async function BoardPage() {
    const boards = await getBoardList();

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h3 className="mb-0">게시판 관리</h3>
                <Link href="/admin/board/create" className="btn btn-primary">
                    게시판 등록
                </Link>
            </div>

            <div className="card border-0 shadow-sm">
                <div className="card-body">
                    <table className="table align-middle mb-0">
                        <thead>
                        <tr>
                            <th style={{ width: '80px' }}>번호</th>
                            <th style={{ width: '220px' }}>게시판명</th>
                            <th>내용 요약</th>
                            <th style={{ width: '180px' }}>등록일</th>
                            <th style={{ width: '180px' }}>수정일</th>
                            <th style={{ width: '140px' }}>관리</th>
                        </tr>
                        </thead>
                        <tbody>
                        {boards.map((board) => (
                            <tr key={board.id}>
                                <td>{board.id}</td>
                                <td>{board.name}</td>
                                <td>{summarizeHtml(board.content, 80)}</td>
                                <td>{formatDate(board.createdAt)}</td>
                                <td>{formatDate(board.updatedAt)}</td>
                                <td>
                                    <div className="d-flex gap-2">
                                        <Link
                                            href={`/admin/board/${board.id}/edit`}
                                            className="btn btn-sm btn-outline-secondary"
                                        >
                                            수정
                                        </Link>
                                        <DeleteBoardButton id={board.id} />
                                    </div>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}