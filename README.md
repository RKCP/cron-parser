# Cron Expression Parser

This project is a simple command-line application that parses cron expressions and expands them into their component 
fields (minutes, hours, day of month, months, day of week, and the command to be executed). 
The application is built using Java and Maven.

### Features
- Basic Cron Field Parsing: Handles *, comma-separated values, ranges, and step intervals.
- Named Days and Months: Parses named days (SUN, MON, etc.) and months (JAN, FEB, etc.).
- Ignore Field: Supports the ? character to ignore day of month or day of week fields.

### Requirements
- Java 8 or later
- Maven

### Getting Started

#### 1. Clone the Repository

`git clone <repository-url>`

`cd cronparser`

#### 2. Build the Project

Use Maven to clean and compile the project:

`mvn clean compile`

#### 3. Run the Application

You can run the application using Maven’s exec:java plugin. 
The main class is CronParserApplication, and you need to pass a cron expression as an argument.

Here’s how to run the application, feel free to replace the string in exec.args with your own cron expression:

    mvn exec:java -Dexec.mainClass="com.raphaelpeters.cronparser.CronParserApplication" -Dexec.args="\"*/15 0 1,15 * 1-5 /usr/bin/find\""

*Example Explanation*

For the above example:

    Minute: Every 15 minutes (*/15).
    Hour: At 00:00 (midnight).
    Day of Month: On the 1st and 15th of the month (1,15).
    Month: Every month (*).
    Day of Week: Monday to Friday (1-5).
    Command: /usr/bin/find.

*No Maven?*

If you don't want to use Maven, you can manually compile and run the project using the javac (Java compiler) 
and java commands.

Navigate to the root directory of the project (where the src folder is located) and run the following command to compile the code. 
You'll need to specify the source files and the output directory for the compiled class files.

    javac -d out/ src/main/java/com/raphaelpeters/cronparser/*.java src/main/java/com/raphaelpeters/cronparser/util/*.java src/main/java/com/raphaelpeters/cronparser/service/*.java src/main/java/com/raphaelpeters/cronparser/model/*.java

This command will:
- Use javac to compile all the .java files in your project.
- Place the compiled .class files in the out/ directory.

Once the Java code has been compiled, you can run the application using the java command. You'll need to specify the classpath 
(-cp) and the fully qualified name of the main class. You can replace the cron expression here with your own.

    java -cp out/ com.raphaelpeters.cronparser.CronParserApplication "*/15 0 1,15 * 1-5 /usr/bin/find"


#### 4. Running Tests

To run unit tests for the application, use the following Maven command:

`mvn test`

This will run the CronFieldParserTest and validate that the cron parsing logic works as expected.

## Good to Know
#### Cron Expression Format

A cron expression consists of five or six fields representing different time units and a command. Here’s a breakdown:

    *  *  *  *  *  command
    -  -  -  -  -
    |  |  |  |  |
    |  |  |  |  +----- Day of the week (0 - 7) (Sunday to Saturday, 7 is Sunday again)
    |  |  |  +------- Month (1 - 12) or JAN, FEB, etc.
    |  |  +--------- Day of the month (1 - 31)
    |  +----------- Hour (0 - 23)
    +------------- Minute (0 - 59)

#### Special Characters:

    *: Matches any value.
    /: Step value (e.g., */15 means every 15 units).
    ,: Separate multiple values (e.g., 1,15 means the 1st and 15th).
    -: Defines a range of values (e.g., 1-5).
    ?: No specific value (used in day of month or day of week).
    L: Last day of the month or last weekday (e.g., L).
    W: Nearest weekday (e.g., 15W).
    #: nth weekday of the month (e.g., 2#3 means the third Monday).

#### Example Cron Expressions

Run every day at 12:00 PM:

    0 0 12 * * ?

Run at 10:15 AM every Monday through Friday:

    0 15 10 ? * MON-FRI

Run at 10:15 AM on the last day of every month:

    0 15 10 L * ?

Run every 5 minutes between 2:00 PM and 2:55 PM every day:

    0 0/5 14 * * ?

## Need to add:
- Special Characters: Supports L (last day), W (nearest weekday), and # (nth weekday of the month).