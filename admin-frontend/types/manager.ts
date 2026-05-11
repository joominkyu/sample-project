export type ManagerGrade = 'SUPER_ADMIN' | 'ADMIN';

export interface ManagerItem {
    id: number;
    loginId: string;
    name: string;
    grade: ManagerGrade;
    createdAt: string;
}