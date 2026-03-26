package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        var progress = new char[] {'-', '\\', '|', '/'};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500);
                System.out.print("\rLoading..." + progress[i % progress.length]);
                i++;
            } catch (InterruptedException e) {
                System.out.println("\nLoading interrupted");
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }

}
