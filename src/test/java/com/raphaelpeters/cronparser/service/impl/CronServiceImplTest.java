package com.raphaelpeters.cronparser.service.impl;

import com.raphaelpeters.cronparser.model.CronExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CronServiceImplTest {

    private CronServiceImpl cronService;

    @BeforeEach
    void setUp() {
        cronService = new CronServiceImpl();
    }

    @Test
    void testParseCronExpression_validInput() {
        // Example with all fields valid
        CronExpression expression = cronService.parseCronExpression("*/15 0 1,15 * 1-5 /usr/bin/find");

        assertEquals(List.of(0, 15, 30, 45), expression.minutes());
        assertEquals(List.of(0), expression.hours());
        assertEquals(List.of(1, 15), expression.daysOfMonth());
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12), expression.months());
        assertEquals(List.of(1, 2, 3, 4, 5), expression.daysOfWeek());
        assertEquals("/usr/bin/find", expression.command());
    }

    @Test
    void testParseCronExpression_invalidInput_throwsException() {
        // Example with invalid number of fields
        assertThrows(IllegalArgumentException.class, () -> {
            cronService.parseCronExpression("*/15 0 1,15 * /usr/bin/find"); // Missing a field
        });
    }

    @Test
    void testParseCronExpression_edgeCase_minValues() {
        CronExpression expression = cronService.parseCronExpression("0 0 1 1 0 /usr/bin/find");

        assertEquals(List.of(0), expression.minutes());
        assertEquals(List.of(0), expression.hours());
        assertEquals(List.of(1), expression.daysOfMonth());
        assertEquals(List.of(1), expression.months());
        assertEquals(List.of(0), expression.daysOfWeek());
        assertEquals("/usr/bin/find", expression.command());
    }

    @Test
    void testParseCronExpression_edgeCase_maxValues() {
        CronExpression expression = cronService.parseCronExpression("59 23 31 12 7 /usr/bin/find");

        assertEquals(List.of(59), expression.minutes());
        assertEquals(List.of(23), expression.hours());
        assertEquals(List.of(31), expression.daysOfMonth());
        assertEquals(List.of(12), expression.months());
        assertEquals(List.of(7), expression.daysOfWeek());
        assertEquals("/usr/bin/find", expression.command());
    }
}
