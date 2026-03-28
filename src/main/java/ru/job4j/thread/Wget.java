package ru.job4j.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class Wget implements Runnable {

    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        String fileName = "data" + url.substring(url.lastIndexOf("/"));
        var file = new File(fileName);
        var dataBuffer = new byte[1024];
        int downloaded = 0;
        long startTime = System.currentTimeMillis();
        try (var input = new URL(url).openStream();
             var output = new FileOutputStream(file)) {
            int bytesRead;
            while ((bytesRead = input.read(dataBuffer, 0, dataBuffer.length)) != -1) {
                output.write(dataBuffer, 0, bytesRead);
                downloaded += bytesRead;
                if (downloaded >= speed) {
                    long timePassed = System.currentTimeMillis() - startTime;
                    if (timePassed < 1000) {
                        Thread.sleep(1000 - timePassed);
                    }
                    downloaded -= speed;
                    startTime = System.currentTimeMillis();
                }
            }
            System.out.println("Downloaded: " + Files.size(file.toPath()) + " bytes.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Expected 2 arguments: URL and speed");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        if (speed <= 0) {
            throw new IllegalArgumentException("Speed must be > 0");
        }
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }

}
