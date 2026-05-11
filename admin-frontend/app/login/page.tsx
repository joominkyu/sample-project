'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { login } from '@/services/adminAuth';
import { setAdminLogin } from '@/utils/auth';

export default function LoginPage() {
    const router = useRouter();

    const [loginId, setLoginId] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setErrorMessage('');

        try {
            const result = await login({ loginId, password });
            setAdminLogin(result.loginId);
            router.push('/admin/news');
        } catch (error) {
            if (error instanceof Error) {
                setErrorMessage(error.message || '로그인에 실패했습니다.');
            } else {
                setErrorMessage('알 수 없는 오류가 발생했습니다.');
            }
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="container" style={{ maxWidth: '420px', marginTop: '100px' }}>
            <div className="card shadow-sm border-0">
                <div className="card-body p-4">
                    <h2 className="text-center mb-4">관리자 로그인</h2>

                    <form onSubmit={handleLogin}>
                        <div className="mb-3">
                            <label htmlFor="loginId" className="form-label">아이디</label>
                            <input
                                id="loginId"
                                type="text"
                                className="form-control"
                                value={loginId}
                                onChange={(e) => setLoginId(e.target.value)}
                                placeholder="아이디를 입력하세요"
                            />
                        </div>

                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">비밀번호</label>
                            <input
                                id="password"
                                type="password"
                                className="form-control"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                placeholder="비밀번호를 입력하세요"
                            />
                        </div>

                        {errorMessage && (
                            <div className="alert alert-danger py-2" role="alert">
                                {errorMessage}
                            </div>
                        )}

                        <button type="submit" className="btn btn-dark w-100" disabled={loading}>
                            {loading ? '로그인 중...' : '로그인'}
                        </button>
                    </form>
                </div>
            </div>
        </div>
    );
}