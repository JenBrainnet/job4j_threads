package ru.job4j.concurrent;

public class ThreadDaemon {
    public final static int MIN_PRIORITY = 1;
    public final static int NORM_PRIORITY = 5;
    public final static int MAX_PRIORITY = 10;

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();
        System.out.println(thread.isDaemon());
        System.out.println(thread.getPriority());
    }

}
