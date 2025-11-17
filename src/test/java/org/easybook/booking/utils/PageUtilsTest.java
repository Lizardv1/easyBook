package org.easybook.booking.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
class PageUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "25, 0, 1",
            "25, 25, 2",
            "25, 50, 3",
            "50, 25, 1"
    })
    void countPageTest(int limit, int offset, int expectedResult) {
        int page = PageUtils.countPage(limit, offset);
        assertEquals(expectedResult, page);
    }

    @Test
    void countTotalPagesTest() {
        int pagesTotal = PageUtils.countTotalPages(90, 25);
        assertEquals(4, pagesTotal);
    }
}