package org.example.adminbackend.util;

import java.util.Collection;
import java.util.Map;

/**
 * 객체 관련 유틸 클래스
 *
 * - null 체크
 * - empty 체크 (String / Collection / Map)
 */
public final class ObjectUtils {

    /** 생성자 호출 방지 */
    private ObjectUtils() {
    }

    /**
     * null 여부 확인
     *
     * @param obj 객체
     * @return true: null
     */
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    /**
     * null 여부 확인
     *
     * @param obj 객체
     * @return true: not null
     */
    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    /**
     * 객체가 null 또는 empty 인지 확인
     *
     * 지원 타입:
     * - String
     * - Collection
     * - Map
     *
     * @param obj 객체
     * @return true: empty
     */
    public static boolean isEmpty(Object obj) {

        if (obj == null) {
            return true;
        }

        if (obj instanceof String str) {
            return str.trim().isEmpty();
        }

        if (obj instanceof Collection<?> col) {
            return col.isEmpty();
        }

        if (obj instanceof Map<?, ?> map) {
            return map.isEmpty();
        }

        return false;
    }

}