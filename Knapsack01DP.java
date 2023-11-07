package DAA_Practicals;

import java.util.Arrays;

public class Knapsack01DP {
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        for (int[] row : dp) {
            System.out.println(Arrays.toString(row));
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weights = {2,1,3,2};
        int[] values = {12,10,20,15};
        int capacity = 5;

        int maxValue = knapsack(weights, values, capacity);
       
        System.out.println("Knapsack capacity: " + capacity);
        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Values: " + Arrays.toString(values));
        System.out.println("Maximum value in the knapsack: " + maxValue);
    }
}
// Output
// Knapsack capacity: 50
// Weights: [10, 20, 30]
// Values: [60, 100, 120]
// Maximum value in the knapsack: 220