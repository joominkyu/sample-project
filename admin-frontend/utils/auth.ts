const ADMIN_LOGIN_KEY = 'admin_logged_in_user';

export function setAdminLogin(loginId: string) {
    if (typeof window === 'undefined') return;
    localStorage.setItem(ADMIN_LOGIN_KEY, loginId);
}

export function getAdminLogin() {
    if (typeof window === 'undefined') return null;
    return localStorage.getItem(ADMIN_LOGIN_KEY);
}

export function clearAdminLogin() {
    if (typeof window === 'undefined') return;
    localStorage.removeItem(ADMIN_LOGIN_KEY);
}

export function isAdminLoggedIn() {
    return !!getAdminLogin();
}