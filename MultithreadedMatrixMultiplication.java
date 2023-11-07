package DAA_Practicals;


import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MultithreadedMatrixMultiplication {

    public static int[][] multiplySequential(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;
        int[][] C = new int[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                C[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static int[][] multiplyWithOneThreadPerRow(int[][] A, int[][] B, int numThreads) {
        int m = A.length;
        int p = B[0].length;
        int[][] C = new int[m][p];
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < m; i++) {
            final int row = i;
            executor.execute(() -> {
                for (int j = 0; j < p; j++) {
                    C[row][j] = 0;
                    for (int k = 0; k < B.length; k++) {
                        C[row][j] += A[row][k] * B[k][j];
                    }
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return C;
    }

    public static int[][] multiplyWithOneThreadPerCell(int[][] A, int[][] B, int numThreads) {
        int m = A.length;
        int p = B[0].length;
        int[][] C = new int[m][p];
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                final int row = i;
                final int col = j;
                executor.execute(() -> {
                    C[row][col] = 0;
                    for (int k = 0; k < B.length; k++) {
                        C[row][col] += A[row][k] * B[k][col];
                    }
                });
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return C;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 2, 3}, {4, 5, 6}};
        int[][] B = {{7, 8}, {9, 10}, {11, 12}};

        long startTime, endTime;

        // Sequential multiplication
        startTime = System.nanoTime();
        int[][] CSequential = multiplySequential(A, B);
        endTime = System.nanoTime();
        System.out.println("Sequential multiplication took " + (endTime - startTime) + " ns");

        // Multithreaded multiplication with one thread per row
        int numThreadsRow = 2;
        startTime = System.nanoTime();
        int[][] CRow = multiplyWithOneThreadPerRow(A, B, numThreadsRow);
        endTime = System.nanoTime();
        System.out.println("Multithreaded (one thread per row) multiplication took " + (endTime - startTime) + " ns");

        // Multithreaded multiplication with one thread per cell
        int numThreadsCell = 4;
        startTime = System.nanoTime();
        int[][] CCell = multiplyWithOneThreadPerCell(A, B, numThreadsCell);
        endTime = System.nanoTime();
        System.out.println("Multithreaded (one thread per cell) multiplication took " + (endTime - startTime) + " ns");

        // Verify that the results are the same
        System.out.println("Results are equal: " + Arrays.deepEquals(CSequential, CRow) + " (Row)");
        System.out.println("Results are equal: " + Arrays.deepEquals(CSequential, CCell) + " (Cell)");
    }
}
