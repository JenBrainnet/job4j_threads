package ru.job4j.threads.threadlocal;

public class FirstThread extends Thread {

    @Override
    public void run() {
        ThreadLocalDemo.threadLocal.set("This is thread 1.");
        System.out.println(ThreadLocalDemo.threadLocal.get());
    }

}
