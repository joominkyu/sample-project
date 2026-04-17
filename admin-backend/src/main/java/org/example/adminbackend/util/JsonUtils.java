package org.example.adminbackend.util;

import tools.jackson.databind.ObjectMapper;

/**
 * JSON 변환 유틸 클래스
 *
 * - Object → JSON
 * - JSON → Object
 */
public final class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private JsonUtils() {
    }

    /**
     * Object를 JSON 문자열로 변환
     *
     * @param obj 변환할 객체
     * @return JSON 문자열
     */
    public static String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("JSON 변환 실패", e);
        }
    }

    /**
     * JSON 문자열을 객체로 변환
     *
     * @param json JSON 문자열
     * @param clazz 대상 클래스
     * @return 변환된 객체
     * @param <T> 타입
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }
}