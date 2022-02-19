package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        boolean isAnswering = true;
        String word = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!word.equals(OUT)) {
                word = reader.readLine();
                if (word.equals(OUT)) {
                    log.add(word);
                    break;
                }

                if (word.equals(STOP)) {
                    isAnswering = false;
                    log.add(word);
                    continue;
                }
                if (word.equals(CONTINUE)) {
                    isAnswering = true;
                    log.add(word);
                    continue;
                }

                if (isAnswering) {
                    int a = (int) (Math.random() * answers.size());
                    log.add(word);
                    log.add(answers.get(a));
                    System.out.println(answers.get(a));
                } else {
                    log.add(word);
                }
            }

            saveLog(log);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            br.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/log.txt", "./data/phrases.txt");
        cc.run();
    }
}
