package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("404\\s\\d");
        try (BufferedReader in = new BufferedReader((new FileReader(file)))) {
            result = in.lines().filter(s -> pattern.matcher(s).find()).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

      /*  List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }

            String[] lines = text.toString().split(System.lineSeparator());

            Pattern pattern = Pattern.compile("404\\s\\d");
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    result.add(line + "\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;*/

    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}
