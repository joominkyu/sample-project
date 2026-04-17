package com.example.backend.util;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 클라이언트 IP 조회 유틸
 */
public final class IpUtils {

    private IpUtils() {
    }

    /**
     * 실제 클라이언트 IP 조회
     *
     * @param request HttpServletRequest
     * @return IP 주소
     */
    public static String getClientIp(
            HttpServletRequest request
    ) {

        String ip =
                request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty()) {
            ip = request.getHeader(
                    "Proxy-Client-IP"
            );
        }

        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

}