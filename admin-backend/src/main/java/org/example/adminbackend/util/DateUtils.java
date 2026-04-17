package org.example.adminbackend.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 날짜 관련 공통 유틸 클래스
 *
 * - 현재 날짜/시간 조회
 * - 날짜 포맷 문자열 변환
 * - null 안전 처리
 *
 * 사용 예:
 * DateUtils.getToday();
 * DateUtils.nowDateTimeString();
 */
public final class DateUtils {

    /** 날짜 포맷 (yyyy-MM-dd) */
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** 날짜시간 포맷 (yyyy-MM-dd HH:mm:ss) */
    private static final DateTimeFormatter DATETIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /** 생성자 호출 방지 */
    private DateUtils() {
    }

    /**
     * 현재 날짜 반환
     *
     * @return Date 현재 날짜
     */
    public static Date getToday() {
        return new Date();
    }

    /**
     * 현재 LocalDate 반환
     *
     * @return LocalDate 오늘 날짜
     */
    public static LocalDate nowDate() {
        return LocalDate.now();
    }

    /**
     * 현재 LocalDateTime 반환
     *
     * @return LocalDateTime 현재 날짜시간
     */
    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 오늘 날짜를 문자열로 반환
     *
     * @return yyyy-MM-dd 형식 문자열
     */
    public static String todayString() {
        return LocalDate.now().format(DATE_FORMAT);
    }

    /**
     * 현재 날짜시간을 문자열로 반환
     *
     * @return yyyy-MM-dd HH:mm:ss 형식 문자열
     */
    public static String nowDateTimeString() {
        return LocalDateTime.now().format(DATETIME_FORMAT);
    }

    /**
     * LocalDate → 문자열 변환
     *
     * @param date LocalDate
     * @return yyyy-MM-dd 문자열
     */
    public static String format(LocalDate date) {

        if (date == null) {
            return "";
        }

        return date.format(DATE_FORMAT);
    }

    /**
     * LocalDateTime → 문자열 변환
     *
     * @param dateTime LocalDateTime
     * @return yyyy-MM-dd HH:mm:ss 문자열
     */
    public static String format(LocalDateTime dateTime) {

        if (dateTime == null) {
            return "";
        }

        return dateTime.format(DATETIME_FORMAT);
    }

}