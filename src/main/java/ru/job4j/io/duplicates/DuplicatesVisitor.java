package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.Files.size;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<Path, FileProperty> paths = new LinkedHashMap<>();
    Map<Path, FileProperty> duplicates = new LinkedHashMap<>();

    public Map<Path, FileProperty> getPaths() {
        return this.paths;
    }

    public Map<Path, FileProperty> getDuplicates() {
        for (Map.Entry<Path, FileProperty> pair : paths.entrySet()) {
            FileProperty temp = pair.getValue();
            for (Map.Entry<Path, FileProperty> file : paths.entrySet()) {
                if (temp.equals(file.getValue()) && !(pair.getKey().equals(file.getKey()))) {
                    duplicates.put(file.getKey(), file.getValue());
                }
            }
        }
        return this.duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        paths.put(file.toAbsolutePath(), new FileProperty(size(file), file.getFileName().toString()));
        return FileVisitResult.CONTINUE;
    }
}