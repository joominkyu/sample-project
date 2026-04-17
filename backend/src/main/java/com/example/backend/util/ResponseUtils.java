package com.example.backend.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * API 응답 통일 유틸 클래스
 *
 * - 성공 응답
 * - 메시지 응답
 * - 데이터 응답
 */
public final class ResponseUtils {

    private ResponseUtils() {
    }

    /**
     * 성공 메시지 응답
     *
     * @param message 메시지
     * @return ResponseEntity
     */
    public static ResponseEntity<Map<String, Object>> success(String message) {

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", message);

        return ResponseEntity.ok(result);
    }

    /**
     * 데이터 포함 응답
     *
     * @param data 데이터
     */
    public static ResponseEntity<Map<String, Object>> success(Object data) {

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("data", data);

        return ResponseEntity.ok(result);
    }

    /**
     * 상태 코드 포함 응답
     */
    public static ResponseEntity<Map<String, Object>> success(
            String message,
            Object data
    ) {

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", message);
        result.put("data", data);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}