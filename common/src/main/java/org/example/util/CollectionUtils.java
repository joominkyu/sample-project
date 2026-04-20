package org.example.util;

import java.util.Collection;

/**
 * Collection 관련 유틸 클래스
 *
 * - List / Set empty 체크
 */
public final class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * Collection empty 여부 확인
     *
     * @param collection 컬렉션
     * @return true: empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Collection empty 여부 확인
     *
     * @param collection 컬렉션
     * @return true: 값 있음
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}