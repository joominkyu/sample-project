package org.example.adminbackend.util;

/**
 * 페이징 계산 유틸 클래스
 *
 * page / size 기반 offset 계산
 */
public final class PaginationUtils {

    private PaginationUtils() {
    }

    /**
     * offset 계산
     *
     * @param page 페이지 번호
     * @param size 페이지 사이즈
     * @return offset
     */
    public static int getOffset(int page, int size) {

        if (page <= 0) {
            page = 1;
        }

        return (page - 1) * size;

    }

    /**
     * 총 페이지 수 계산
     *
     * @param totalCount 전체 건수
     * @param size 페이지 사이즈
     * @return total pages
     */
    public static int getTotalPages(long totalCount, int size) {

        if (size == 0) {
            return 0;
        }

        return (int) Math.ceil((double) totalCount / size);

    }

}