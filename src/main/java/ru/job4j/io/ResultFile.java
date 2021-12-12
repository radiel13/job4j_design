package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        ResultFile rf = new ResultFile();
        rf.multipleFile(9, "lesson.txt");
    }

    public int[][] multipleFile(int size, String fileName) {

        int[][] table = new int[size][size];
        try (FileOutputStream out = new FileOutputStream(fileName)) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    out.write(((i + 1) * (j + 1) + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return table;
    }
}