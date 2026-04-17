package com.example.backend.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 로그인 사용자 정보 조회 유틸
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * 현재 로그인 사용자 ID 반환
     *
     * @return username
     */
    public static String getCurrentUsername() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        return authentication.getName();
    }

    /**
     * 로그인 여부 확인
     *
     * @return boolean
     */
    public static boolean isAuthenticated() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication != null
                && authentication.isAuthenticated();
    }

}