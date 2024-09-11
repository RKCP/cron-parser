package com.raphaelpeters.cronparser.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CronFieldParserTest {

    @Test
    void testParseField_wildcard() {
        List<Integer> result = CronFieldParser.parseField("*", 0, 59);
        assertEquals(60, result.size());
        assertTrue(result.containsAll(List.of(0, 15, 30, 45)));
    }

    @Test
    void testParseField_commaSeparated() {
        List<Integer> result = CronFieldParser.parseField("1,15,30", 0, 59);
        assertEquals(List.of(1, 15, 30), result);
    }

    @Test
    void testParseField_range() {
        List<Integer> result = CronFieldParser.parseField("10-15", 0, 59);
        assertEquals(List.of(10, 11, 12, 13, 14, 15), result);
    }

    @Test
    void testParseField_step() {
        List<Integer> result = CronFieldParser.parseField("0/15", 0, 59);
        assertEquals(List.of(0, 15, 30, 45), result);
    }

    @Test
    void testParseField_namedDays() {
        List<Integer> result = CronFieldParser.parseField("MON,WED,FRI", 0, 6);
        assertEquals(List.of(1, 3, 5), result);
    }

    @Test
    void testParseField_namedMonths() {
        List<Integer> result = CronFieldParser.parseField("JAN,MAR,MAY", 1, 12);
        assertEquals(List.of(1, 3, 5), result);
    }
}
