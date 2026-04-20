package org.example.util;

/**
 * XSS 방어용 문자열 유틸 클래스
 *
 * 사용자 입력값에서 특수문자를 치환하여
 * 스크립트 실행을 방지한다.
 */
public final class XssUtils {

    private XssUtils() {
    }

    /**
     * 문자열 escape 처리
     *
     * @param value 원본 문자열
     * @return escape 된 문자열
     */
    public static String escape(String value) {
        if (value == null) {
            return null;
        }

        return value
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;")
                .replace("/", "&#x2F;");
    }
}