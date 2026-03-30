package ru.job4j.synchronization;

public class ShowRoomLock {

    public void lockOfInstance() {
        synchronized (this) {
            System.out.println("Lock on instance");
        }
    }

    public static void lockOfClass() {
        synchronized (ShowRoomLock.class) {
            System.out.println("Lock on class");
        }
    }

}
