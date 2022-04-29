package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        if (argsName.get("path").length() == 0) {
            throw new IllegalArgumentException("Path is missing.");
        } else if (argsName.get("delimiter").length() == 0) {
            throw new IllegalArgumentException("Delimiter is missing.");
        } else if (argsName.get("out").length() == 0) {
            throw new IllegalArgumentException("Destination is missing.");
        }else if (argsName.get("filter").length() == 0) {
            throw new IllegalArgumentException("Filters are missing.");
        }

        List<List<String>> columns = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(argsName.get("path")))) {
            while (scanner.hasNextLine()) {
                columns.add(Arrays.asList(scanner.nextLine().split(argsName.get("delimiter"))));
            }
        }

        List<String> filterNames = columns.get(0);
        List<String> userFilterNames = Arrays.asList(argsName.get("filter").split(","));
        List<Integer> index = new ArrayList<>();
        for (String s : userFilterNames) {
            if (filterNames.contains(s)) {
                index.add(filterNames.indexOf(s));
            }
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(argsName.get("out"))))) {
            List<String> temp = new ArrayList<>();
            for (List<String> list : columns) {
                for (int i = 0; i < index.size(); i++) {
                    temp.add(list.get(i));
                }
                out.write(String.join(";", temp.toArray(new String[0])));
                out.write("\r\n");
                temp.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws Exception {

        handle(ArgsName.of(args));
    }
}