package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        String name = "Paul";
        int age = 29;
        byte legs = 2;
        short height = 176;
        long salary = 10000L;
        float weight = 68.2f;
        double tripple = 123.8D;
        boolean sex = true;
        char firstLetter = 'P';
        LOG.debug("User info name : {}, " +
                "age : {}, " +
                "legs : {}, " +
                "height : {}, " +
                "salary : {}, weight : {},  " +
                "tripple : {}, " +
                "sex : {}, " +
                "firstLetter : {}" ,
                name, age, legs,height, salary, weight, tripple, sex, firstLetter);
    }
}