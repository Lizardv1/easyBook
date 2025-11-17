package org.easybook.booking.utils;

public class PageUtils {

    public static int countPage(int limit, int offset) {
        return offset / limit + 1;
    }

    public static int countTotalPages(int totalRows, int limit) {
        return Math.ceilDiv(totalRows, limit);
    }

    public static int countOffset(int page, int size) {
        return (page - 1) * size;
    }
}
