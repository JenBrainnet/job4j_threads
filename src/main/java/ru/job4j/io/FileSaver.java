package ru.job4j.io;

import java.io.*;

public class FileSaver {

    private final File file;

    public FileSaver(File file) {
        this.file = file;
    }

    public void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i++) {
                o.write(content.charAt(i));
            }
        }
    }

}
