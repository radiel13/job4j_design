package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            int num;
            for (String line : lines) {
                num = Integer.parseInt(line);
                System.out.println(num%2==0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
