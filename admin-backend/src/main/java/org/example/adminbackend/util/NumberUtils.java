package org.example.adminbackend.util;

/**
 * 숫자 관련 유틸 클래스
 *
 * 문자열 → 숫자 변환 시 예외 방지
 */
public final class NumberUtils {

    private NumberUtils() {
    }

    /**
     * 문자열을 int 로 변환
     *
     * @param value 문자열
     * @param defaultValue 기본값
     * @return int 값
     */
    public static int toInt(String value, int defaultValue) {

        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return defaultValue;
        }

    }

    /**
     * 문자열을 long 로 변환
     */
    public static long toLong(String value, long defaultValue) {

        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return defaultValue;
        }

    }

}