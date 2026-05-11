'use client';

import { useState } from 'react';
import { useRouter } from 'next/navigation';
import { checkManagerLoginId, createManager } from '@/services/adminManager';

export default function ManagerCreatePage() {
    const router = useRouter();

    const [loginId, setLoginId] = useState('');
    const [password, setPassword] = useState('');
    const [name, setName] = useState('');
    const [loading, setLoading] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const [loginIdMessage, setLoginIdMessage] = useState('');
    const [loginIdChecked, setLoginIdChecked] = useState(false);

    const handleCheckLoginId = async () => {
        setLoginIdMessage('');
        setLoginIdChecked(false);

        if (!loginId.trim()) {
            setLoginIdMessage('아이디를 입력해주세요.');
            return;
        }

        try {
            const result = await checkManagerLoginId(loginId);
            setLoginIdMessage(result.message);
            setLoginIdChecked(result.available);
        } catch (error) {
            setLoginIdMessage(
                error instanceof Error ? error.message : '중복 확인에 실패했습니다.'
            );
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setErrorMessage('');

        if (!loginIdChecked) {
            setLoading(false);
            setErrorMessage('아이디 중복 확인을 먼저 진행해주세요.');
            return;
        }

        try {
            await createManager({ loginId, password, name });
            router.push('/admin/manager');
            router.refresh();
        } catch (error) {
            setErrorMessage(
                error instanceof Error ? error.message : '관리자 추가에 실패했습니다.'
            );
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card border-0 shadow-sm">
            <div className="card-body p-4 p-md-5">
                <div style={{ maxWidth: '640px' }}>
                    <h3 className="mb-4">관리자 추가</h3>

                    <form onSubmit={handleSubmit}>
                        <div className="mb-2">
                            <label className="form-label">아이디</label>
                            <div className="d-flex gap-2 align-items-start">
                                <input
                                    type="text"
                                    className="form-control"
                                    value={loginId}
                                    onChange={(e) => {
                                        setLoginId(e.target.value);
                                        setLoginIdChecked(false);
                                        setLoginIdMessage('');
                                    }}
                                />
                                <button
                                    type="button"
                                    className="btn btn-outline-dark"
                                    onClick={handleCheckLoginId}
                                    style={{ minWidth: '110px', whiteSpace: 'nowrap' }}
                                >
                                    중복 확인
                                </button>
                            </div>
                        </div>

                        {loginIdMessage && (
                            <div
                                className={`mb-3 small ${
                                    loginIdChecked ? 'text-success' : 'text-danger'
                                }`}
                            >
                                {loginIdMessage}
                            </div>
                        )}

                        <div className="mb-3">
                            <label className="form-label">비밀번호</label>
                            <input
                                type="password"
                                className="form-control"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </div>

                        <div className="mb-4">
                            <label className="form-label">이름</label>
                            <input
                                type="text"
                                className="form-control"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </div>

                        {errorMessage && (
                            <div className="alert alert-danger py-2">{errorMessage}</div>
                        )}

                        <div className="d-flex gap-2">
                            <button type="submit" className="btn btn-primary" disabled={loading}>
                                {loading ? '저장 중...' : '저장'}
                            </button>
                            <button
                                type="button"
                                className="btn btn-outline-secondary"
                                onClick={() => router.push('/admin/manager')}
                            >
                                취소
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}