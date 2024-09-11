package com.raphaelpeters.cronparser.service;

import com.raphaelpeters.cronparser.model.CronExpression;

public interface CronService {

    /**
     * Parses a given cron expression string and returns a CronExpression object
     * containing the expanded fields.
     *
     * @param cronExpression the cron expression string to parse
     * @return a CronExpression object containing the parsed fields
     * @throws IllegalArgumentException if the cron expression is invalid
     */
    CronExpression parseCronExpression(String cronExpression);
}
