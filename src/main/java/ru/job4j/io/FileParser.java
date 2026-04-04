package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class FileParser {

    private final File file;

    public FileParser(File file) {
        this.file = file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {
        try (InputStream input = new FileInputStream(file)) {
            StringBuilder output = new StringBuilder();
            int data;
            while ((data = input.read()) != -1) {
                char symbol = (char) data;
                if (filter.test(symbol)) {
                    output.append(symbol);
                }
            }
            return output.toString();
        }
    }

}
