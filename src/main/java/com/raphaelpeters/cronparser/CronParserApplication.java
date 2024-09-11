package com.raphaelpeters.cronparser;

import com.raphaelpeters.cronparser.model.CronExpression;
import com.raphaelpeters.cronparser.service.CronService;
import com.raphaelpeters.cronparser.service.impl.CronServiceImpl;
import com.raphaelpeters.cronparser.util.CronExpressionFormatter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CronParserApplication {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java CronParser <cron-expression>");
			System.exit(1);
		}

		String cronExpression = args[0];
		CronService cronParserService = new CronServiceImpl();
		CronExpression parsedExpression = cronParserService.parseCronExpression(cronExpression);

		// Use utility class to print formatted results
		CronExpressionFormatter.printFormatted("minute", parsedExpression.minutes());
		CronExpressionFormatter.printFormatted("hour", parsedExpression.hours());
		CronExpressionFormatter.printFormatted("day of month", parsedExpression.daysOfMonth());
		CronExpressionFormatter.printFormatted("month", parsedExpression.months());
		CronExpressionFormatter.printFormatted("day of week", parsedExpression.daysOfWeek());
		CronExpressionFormatter.printFormatted("command", parsedExpression.command());
	}

}
