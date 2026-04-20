package org.example.util;

/**
 * 문자열 관련 유틸 클래스
 *
 * - null / empty 체크
 * - 기본값 처리
 * - trim 처리
 *
 * 사용 예:
 * StringUtils.isEmpty(str)
 * StringUtils.nvl(str, "default")
 */
public final class StringUtils {

    /** 생성자 호출 방지 */
    private StringUtils() {
    }

    /**
     * 문자열이 null 또는 빈 문자열인지 확인
     *
     * @param str 문자열
     * @return true: null 또는 empty
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 문자열이 null 또는 빈 문자열이 아닌지 확인
     *
     * @param str 문자열
     * @return true: 값 있음
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * null 값을 빈 문자열로 변환
     *
     * @param str 문자열
     * @return null이면 ""
     */
    public static String nvl(String str) {
        return str == null ? "" : str;
    }

    /**
     * null 값을 기본값으로 변환
     *
     * @param str 문자열
     * @param defaultValue 기본값
     * @return 변환된 문자열
     */
    public static String nvl(String str, String defaultValue) {
        return str == null ? defaultValue : str;
    }

    /**
     * 문자열 trim 처리
     *
     * @param str 문자열
     * @return trim 결과
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

}