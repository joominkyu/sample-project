// @ts-ignore
export type MemberGrade = 'BASIC' | 'SILVER' | 'GOLD' | 'VIP';

export interface MemberItem {
    id: number;
    name: string;
    loginId: string;
    grade: MemberGrade;
}