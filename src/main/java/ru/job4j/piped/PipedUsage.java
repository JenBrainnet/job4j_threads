package ru.job4j.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedUsage {

    public static void main(String[] args) throws IOException {
        final PipedInputStream input = new PipedInputStream();
        final PipedOutputStream output = new PipedOutputStream();

        Thread firstThread = new Thread(() -> {
            try {
                output.write("Job4j".getBytes());
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread secondThread = new Thread(() -> {
            try {
                int symbol;
                while ((symbol = input.read()) != -1) {
                    System.out.print((char) symbol);
                }
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        input.connect(output);
        firstThread.start();
        secondThread.start();
    }

}
