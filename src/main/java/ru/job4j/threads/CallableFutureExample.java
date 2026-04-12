package ru.job4j.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("This is a Runnable task");
            }
        };
        Callable<String> task = new Callable<>() {
            @Override
            public String call() throws Exception {
                return "Future task";
            }
        };
        FutureTask<String> future = new FutureTask<>(task);
        new Thread(future).start();
        System.out.println(future.isDone());
        System.out.println(future.get());
    }

}