package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelIndexSearchTest {

    @Test
    void whenIntegerArrayIsBigThenRecursiveSearch() {
        Integer[] array = {
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12
        };
        int result = ParallelIndexSearch.search(array, 11);
        assertThat(result).isEqualTo(10);
    }

    @Test
    void whenIntegerArrayIsSmallThenLinearSearch() {
        Integer[] array = {1, 2, 3, 4};
        int result = ParallelIndexSearch.search(array, 3);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void whenStringArrayThenFindIndex() {
        String[] array = {"aaa", "bbb", "ccc", "ddd"};
        int result = ParallelIndexSearch.search(array, "bbb");
        assertThat(result).isEqualTo(1);
    }

    @Test
    void whenElementNotFoundThenReturnMinusOne() {
        Integer[] array = {1, 2, 3, 4};
        int result = ParallelIndexSearch.search(array, 5);
        assertThat(result).isEqualTo(-1);
    }

    @Test
    void whenArrayIsEmptyThenReturnMinusOne() {
        Integer[] array = {};
        int result = ParallelIndexSearch.search(array, 5);
        assertThat(result).isEqualTo(-1);
    }

}