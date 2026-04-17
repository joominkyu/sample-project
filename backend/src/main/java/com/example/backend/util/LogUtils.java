package com.example.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 로그 유틸 클래스
 *
 * - INFO 로그
 * - ERROR 로그
 */
public final class LogUtils {

    private static final Logger log =
            LoggerFactory.getLogger(LogUtils.class);

    private LogUtils() {
    }

    /**
     * 정보 로그 출력
     *
     * @param message 메시지
     */
    public static void info(String message) {
        log.info(message);
    }

    /**
     * 정보 로그 출력
     *
     * @param message 메시지
     * @param value 값
     */
    public static void info(
            String message,
            Object value
    ) {

        log.info("{} : {}", message, value);

    }

    /**
     * 에러 로그 출력
     *
     * @param message 메시지
     * @param e 예외
     */
    public static void error(
            String message,
            Exception e
    ) {

        log.error(message, e);

    }

}