package ru.job4j.asynch;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] result = new Sums[n];
        for (int i = 0; i < n; i++) {
            result[i] = new Sums();
            for (int j = 0; j < n; j++) {
                result[i].rowSum += matrix[i][j];
                result[i].colSum += matrix[j][i];
            }
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix)
            throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] result = new Sums[n];
        CompletableFuture<Sums>[] futures = new CompletableFuture[n];
        for (int i = 0; i < n; i++) {
            futures[i] = getTask(matrix, i);
        }
        for (int i = 0; i < n; i++) {
            result[i] = futures[i].get();
        }
        return result;
    }

    private static CompletableFuture<Sums> getTask(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
            int n = matrix.length;
            Sums sums = new Sums();
            for (int j = 0; j < n; j++) {
                sums.rowSum += matrix[index][j];
                sums.colSum += matrix[j][index];
            }
            return sums;
        });
    }

    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums() {
        }

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

}
