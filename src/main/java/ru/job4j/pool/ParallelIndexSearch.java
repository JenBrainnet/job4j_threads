package ru.job4j.pool;

import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T target;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T target, int from, int to) {
        this.array = array;
        this.target = target;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (from > to) {
            return -1;
        }
        if (to - from <= 10) {
            return linearSearch();
        }
        int middle = (to + from) / 2;
        ParallelIndexSearch<T> leftSearch = new ParallelIndexSearch<>(array, target, from, middle);
        ParallelIndexSearch<T> rightSearch = new ParallelIndexSearch<>(array, target, middle + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        int leftIndex = leftSearch.join();
        int rightIndex = rightSearch.join();
        return leftIndex != -1 ? leftIndex : rightIndex;
    }

    private Integer linearSearch() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (Objects.equals(array[i], target)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static <T> int search(T[] array, T target) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelIndexSearch<>(array, target, 0, array.length - 1));
    }

}
