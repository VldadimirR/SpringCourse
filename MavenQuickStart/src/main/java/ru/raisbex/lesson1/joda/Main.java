package ru.raisbex.lesson1.joda;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println("Current date and time: " + dateTime);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = formatter.print(dateTime);
        System.out.println("Formatted date and time: " + formattedDateTime);
    }
}
