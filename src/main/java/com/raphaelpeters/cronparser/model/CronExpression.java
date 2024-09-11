package com.raphaelpeters.cronparser.model;

import java.util.List;

public record CronExpression(
        List<Integer> minutes,
        List<Integer> hours,
        List<Integer> daysOfMonth,
        List<Integer> months,
        List<Integer> daysOfWeek,
        String command
) {}
