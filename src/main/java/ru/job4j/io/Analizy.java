package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Analizy {
    public void unavailable(String source, String target) {
        ArrayList<List<String>> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines().forEach(s -> result.add(Arrays.asList(s.split(" "))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            StringBuilder sb = new StringBuilder();
            boolean isWorking;
            if (result.get(0).get(0).startsWith("2") || result.get(0).get(0).startsWith("3")) {
                isWorking = true;
            } else {
                isWorking = false;
                sb.append(result.get(0).get(1));
            }

            for (List<String> list : result) {
                if (isWorking && (list.get(0).startsWith("4") || list.get(0).startsWith("5"))) {
                    sb.append(list.get(1));
                    isWorking = false;
                } else if (!isWorking && (list.get(0).startsWith("2") || list.get(0).startsWith("3"))) {
                    isWorking = true;
                    sb.append(";").append(list.get(1));
                    out.println(sb);
                    sb.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy a = new Analizy();
        a.unavailable("Analizy2.txt", "unavailable.txt");
    }
}