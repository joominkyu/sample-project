package org.example.util;


/**
 * 개인정보 마스킹 유틸 클래스
 *
 * - 이름 마스킹
 * - 이메일 마스킹
 * - 전화번호 마스킹
 */
public final class MaskingUtils {

    private MaskingUtils() {
    }

    /**
     * 이름 마스킹
     *
     * 예:
     * 홍길동 -> 홍*동
     * 김철수 -> 김*수
     * 가 -> *
     *
     * @param name 이름
     * @return 마스킹된 이름
     */
    public static String maskName(String name) {

        if (StringUtils.isEmpty(name)) {
            return "";
        }

        if (name.length() == 1) {
            return "*";
        }

        if (name.length() == 2) {
            return name.charAt(0) + "*";
        }

        return name.charAt(0)
                + "*".repeat(name.length() - 2)
                + name.charAt(name.length() - 1);
    }

    /**
     * 이메일 마스킹
     *
     * 예:
     * test123@gmail.com -> te***@gmail.com
     *
     * @param email 이메일
     * @return 마스킹된 이메일
     */
    public static String maskEmail(String email) {

        if (StringUtils.isEmpty(email)) {
            return "";
        }

        int atIndex = email.indexOf("@");

        if (atIndex <= 1) {
            return email;
        }

        String idPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex);

        String visible = idPart.substring(0, Math.min(2, idPart.length()));
        String masked = "*".repeat(Math.max(0, idPart.length() - visible.length()));

        return visible + masked + domainPart;
    }

    /**
     * 전화번호 마스킹
     *
     * 예:
     * 01012345678 -> 010****5678
     * 010-1234-5678 -> 010****5678
     *
     * @param phone 전화번호
     * @return 마스킹된 전화번호
     */
    public static String maskPhone(String phone) {

        if (StringUtils.isEmpty(phone)) {
            return "";
        }

        String onlyNumber = phone.replaceAll("-", "");

        if (onlyNumber.length() < 8) {
            return phone;
        }

        String prefix = onlyNumber.substring(0, 3);
        String suffix = onlyNumber.substring(onlyNumber.length() - 4);

        return prefix + "****" + suffix;
    }
}