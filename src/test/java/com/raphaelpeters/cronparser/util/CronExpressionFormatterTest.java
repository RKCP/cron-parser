package com.raphaelpeters.cronparser.util;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CronExpressionFormatterTest {

    @Test
    void testPrintFormatted_withList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        CronExpressionFormatter.printFormatted("minute", List.of(0, 15, 30, 45));

        assertEquals("minute        0 15 30 45\n", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void testPrintFormatted_withEmptyList() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        CronExpressionFormatter.printFormatted("hour", List.of());

        assertEquals("hour          \n", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void testPrintFormatted_withSingleString() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        CronExpressionFormatter.printFormatted("command", "/usr/bin/find");

        assertEquals("command       /usr/bin/find\n", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void testPrintFormatted_withEmptyString() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        CronExpressionFormatter.printFormatted("command", "");

        assertEquals("command       \n", outContent.toString());

        System.setOut(originalOut);
    }
}
