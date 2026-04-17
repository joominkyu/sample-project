package org.example.adminbackend.util;

/**
 * Enum 관련 유틸 클래스
 *
 * - 문자열 → Enum 변환
 * - 기본값 처리
 */
public final class EnumUtils {

    private EnumUtils() {
    }

    /**
     * 문자열을 Enum으로 변환
     *
     * 예:
     * EnumUtils.fromString(MemberGrade.class, "VIP")
     *
     * @param enumClass Enum 클래스
     * @param value 문자열 값
     * @return Enum
     */
    public static <T extends Enum<T>> T fromString(
            Class<T> enumClass,
            String value
    ) {

        if (StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 문자열을 Enum으로 변환 (기본값 포함)
     *
     * @param enumClass Enum 클래스
     * @param value 문자열 값
     * @param defaultValue 기본값
     */
    public static <T extends Enum<T>> T fromString(
            Class<T> enumClass,
            String value,
            T defaultValue
    ) {

        T result = fromString(enumClass, value);

        return result != null
                ? result
                : defaultValue;

    }

}