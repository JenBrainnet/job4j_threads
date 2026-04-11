package ru.job4j.asynch;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {

    @Test
    void whenSum2x2MatrixThenReturnCorrectSums() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2},
                {3, 4}
        };
        RolColSum.Sums[] expected = {
                new RolColSum.Sums(3, 4),
                new RolColSum.Sums(7, 6)
        };
        assertThat(RolColSum.sum(matrix)).containsExactly(expected);
        assertThat(RolColSum.asyncSum(matrix)).containsExactly(expected);
    }

    @Test
    void whenSum1x1MatrixThenReturnCorrectSums() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1}
        };
        RolColSum.Sums[] expected = {
                new RolColSum.Sums(1, 1),
        };
        assertThat(RolColSum.sum(matrix)).containsExactly(expected);
        assertThat(RolColSum.asyncSum(matrix)).containsExactly(expected);
    }

}