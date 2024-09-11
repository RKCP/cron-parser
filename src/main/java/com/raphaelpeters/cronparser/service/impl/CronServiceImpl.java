package com.raphaelpeters.cronparser.service.impl;

import com.raphaelpeters.cronparser.model.CronExpression;
import com.raphaelpeters.cronparser.service.CronService;
import com.raphaelpeters.cronparser.util.CronFieldParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronServiceImpl implements CronService {

    @Override
    public CronExpression parseCronExpression(String cronExpression) {

        String[] fields = cronExpression.split(" ");

        if (fields.length != 6 && fields.length != 7) {
            throw new IllegalArgumentException("Invalid cron expression format. A cron expression must have 6 or 7 fields.");
        }

        List<Integer> minutes = CronFieldParser.parseField(fields[0], 0, 59);
        List<Integer> hours = CronFieldParser.parseField(fields[1], 0, 23);
        List<Integer> daysOfMonth = CronFieldParser.parseField(fields[2], 1, 31);
        List<Integer> months = CronFieldParser.parseField(fields[3], 1, 12);
        List<Integer> daysOfWeek = CronFieldParser.parseField(fields[4], 0, 7); // cron accepts 7 for sunday too
        String command = fields[5];

        return new CronExpression(minutes, hours, daysOfMonth, months, daysOfWeek, command);
    }


}
