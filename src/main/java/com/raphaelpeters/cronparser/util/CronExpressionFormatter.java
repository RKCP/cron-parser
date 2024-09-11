package com.raphaelpeters.cronparser.util;

import java.util.List;
import java.util.stream.Collectors;

public class CronExpressionFormatter {

    // Utility method to format and print a list of values (e.g., minutes, hours, etc.)
    public static void printFormatted(String fieldName, List<Integer> values) {
        System.out.printf("%-14s%s%n", fieldName, formatValues(values));
    }

    // Utility method to format and print the command (or any single string)
    public static void printFormatted(String fieldName, String command) {
        System.out.printf("%-14s%s%n", fieldName, command);
    }

    // Helper method to format a list of integers into a space-separated string for printing
    private static String formatValues(List<Integer> values) {
        return values.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }
}
