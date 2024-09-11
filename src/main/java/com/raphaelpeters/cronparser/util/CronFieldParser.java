package com.raphaelpeters.cronparser.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CronFieldParser {

    private static final Map<String, Integer> MONTHS_MAP = Map.ofEntries(
            Map.entry("JAN", 1), Map.entry("FEB", 2), Map.entry("MAR", 3),
            Map.entry("APR", 4), Map.entry("MAY", 5), Map.entry("JUN", 6),
            Map.entry("JUL", 7), Map.entry("AUG", 8), Map.entry("SEP", 9),
            Map.entry("OCT", 10), Map.entry("NOV", 11), Map.entry("DEC", 12)
    );

    private static final Map<String, Integer> DAYS_MAP = Map.ofEntries(
            Map.entry("SUN", 0), Map.entry("MON", 1), Map.entry("TUE", 2),
            Map.entry("WED", 3), Map.entry("THU", 4), Map.entry("FRI", 5),
            Map.entry("SAT", 6)
    );

    public static List<Integer> parseField(String field, int min, int max) {

        List<Integer> values = new ArrayList<>();

        if (field.equals("*")) {
            addAllInGivenRange(values, min, max);
        } else if (field.contains(",")) { // Handle the ',' case: process each part independently.
            String[] parts = field.split(",");
            for (String part : parts) {
                values.addAll(parseSingleField(part, min, max));
            }
        } else { // Handle all other cases (ranges, steps, and single values).
            values.addAll(parseSingleField(field, min, max));
        }

        return values;
    }


    //--- Helper Methods ---

    private static List<Integer> parseSingleField(String field, int min, int max) {

        List<Integer> values = new ArrayList<>();

        if (MONTHS_MAP.containsKey(field.toUpperCase())) {
            values.add(MONTHS_MAP.get(field.toUpperCase()));
        } else if (DAYS_MAP.containsKey(field.toUpperCase())) {
            values.add(DAYS_MAP.get(field.toUpperCase()));
        } else if (field.contains("-")) {
            addSpecificRange(values, field);
        } else if (field.contains("/")) {
            addStepValues(values, field, min, max);
        } else if (!field.equals("?")) { // single value, ignore any '?' as that means any day/month
            int value = Integer.parseInt(field);
            if (value >= min && value <= max) {
                values.add(value);
            }
        }

        return values;
    }

    private static void addAllInGivenRange(List<Integer> values, int min, int max) {
        for (int i = min; i <= max; i++) {
            values.add(i);
        }
    }

    private static void addSpecificRange(List<Integer> values, String field) {
        String[] range = field.split("-");
        int start = Integer.parseInt(range[0]);
        int end = Integer.parseInt(range[1]);

        for (int i = start; i <= end; i++) {
            values.add(i); // will add numbers in range, e.g. 1-10, will add all nums from 1 to 10 to list.
        }
    }

    private static void addStepValues(List<Integer> values, String field, int min, int max) {
        // run every y mins starting from x time or between x time range etc
        String[] stepIntervalParts = field.split("/");
        int base = stepIntervalParts[0].equals("*") ? min : Integer.parseInt(stepIntervalParts[0]); // start adding from min or given num
        int stepInterval = Integer.parseInt(stepIntervalParts[1]);
        for (int i = base; i <= max; i += stepInterval) { // += step interval, since we need to jump by the given interval
            values.add(i);
        }
    }
}
