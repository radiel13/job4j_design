package ru.job4j.io.duplicates;

import ru.job4j.io.SearchFiles;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        Map<Path, FileProperty> fp = duplicate(Path.of("./"), new DuplicatesVisitor());
        for (Map.Entry<Path, FileProperty> file : fp.entrySet()) {
            System.out.println(file.getKey() + " - " + file.getValue().toString());
        }
    }

    public static Map<Path, FileProperty> duplicate(Path root, DuplicatesVisitor dv) throws IOException {
        Files.walkFileTree(root, dv);
        return dv.getDuplicates();
    }
}