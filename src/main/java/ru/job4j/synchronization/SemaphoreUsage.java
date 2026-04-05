package ru.job4j.synchronization;

import java.util.concurrent.Semaphore;

public class SemaphoreUsage {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);
        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println("The thread completed the task");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(task).start();
        Thread.sleep(3000);
        semaphore.release(1);
    }

}
