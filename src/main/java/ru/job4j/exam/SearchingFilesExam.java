package ru.job4j.exam;

import ru.job4j.io.ArgsName;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class SearchingFilesExam {
    public static void main(String[] args) throws IOException {
        /** 0: -d - директория, в которой начинать поиск.
         1: -n - имя файла либо регулярное выражение (напр.- .*?xt).
         2: -t - тип поиска: name по полному совпадение имени, regex по регулярному выражению.
         3: -o - результат записать в файл.*/
        ArgsName jvm = ArgsName.of(args);
        searchFilesExam(jvm);
    }

    public static void searchFilesExam(ArgsName argsName) throws IOException {

        if (argsName.get("t").equals("name")) {
            SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().equals(argsName.get("n")));
            Files.walkFileTree(Paths.get(argsName.get("d")), searcher);
            List<Path> paths = searcher.getPaths(argsName.get("o"));

        } else if (argsName.get("t").equals("regex")) {
            SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().matches(argsName.get("n")));
            Files.walkFileTree(Paths.get(argsName.get("d")), searcher);
            List<Path> paths = searcher.getPaths(argsName.get("o"));
        }
    }
}
