import Link from 'next/link';
import { getNewsList } from '@/services/adminNews';
import DeleteNewsButton from '@/components/admin/DeleteNewsButton';
import { summarizeHtml } from '@/utils/content';

function formatDate(dateString: string) {
    return new Date(dateString).toLocaleString('ko-KR');
}

export default async function NewsPage() {
    const newsList = await getNewsList();

    return (
        <div>
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h3 className="mb-0">뉴스 관리</h3>
                <Link href="/admin/news/create" className="btn btn-primary">
                    뉴스 등록
                </Link>
            </div>

            <div className="card border-0 shadow-sm">
                <div className="card-body">
                    <table className="table align-middle mb-0">
                        <thead>
                        <tr>
                            <th style={{ width: '80px' }}>번호</th>
                            <th style={{ width: '220px' }}>제목</th>
                            <th>내용 요약</th>
                            <th style={{ width: '180px' }}>등록일</th>
                            <th style={{ width: '180px' }}>수정일</th>
                            <th style={{ width: '140px' }}>관리</th>
                        </tr>
                        </thead>
                        <tbody>
                        {newsList.map((item) => (
                            <tr key={item.id}>
                                <td>{item.id}</td>
                                <td>{item.title}</td>
                                <td>{summarizeHtml(item.content, 80)}</td>
                                <td>{formatDate(item.createdAt)}</td>
                                <td>{formatDate(item.updatedAt)}</td>
                                <td>
                                    <div className="d-flex gap-2">
                                        <Link
                                            href={`/admin/news/${item.id}/edit`}
                                            className="btn btn-sm btn-outline-secondary"
                                        >
                                            수정
                                        </Link>
                                        <DeleteNewsButton id={item.id} />
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