package ru.job4j.synchronization;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MutableCache {

    private final ConcurrentHashMap<Integer, String> dictionary = new ConcurrentHashMap<>();
    private final AtomicInteger ids = new AtomicInteger();

    public MutableCache() {
        dictionary.put(ids.getAndIncrement(), "Petr Arsentev");
        dictionary.put(ids.getAndIncrement(), "Ivan Ivanov");
    }

    public void add(String name) {
        dictionary.put(ids.getAndIncrement(), name);
    }

    public boolean contains(String name) {
        return dictionary.contains(name);
    }

}
