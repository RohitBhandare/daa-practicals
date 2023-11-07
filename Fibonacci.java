package DAA_Practicals;

public class Fibonacci {

    public static long fibonacciNonRecursive(int n) {
        if (n <= 1)
            return n;

        long fib = 0;
        long prev1 = 1;
        long prev2 = 0;

        for (int i = 2; i <= n; i++) {
            fib = prev1 + prev2;
            prev2 = prev1;
            prev1 = fib;
        }

        return fib;
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 1)
            return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciRecursiveDP(int n, int dp[]) {
        if (n <= 1)
            return n;
        
        if(dp[n]!=0)
            return dp[n];
        return dp[n]=fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static void main(String[] args) {
        int n = 10;

        new Thread(() -> {
            Thread.currentThread().setName("Fibonacci Recursive Thread");
            System.out.println(Thread.currentThread().getName() + " is computing...");
            int result1 = fibonacciRecursive(n);  // TC: O(2^n), SC: O(n)
            System.out.println("Fibonacci Recursive (" + n + ") = " + result1);
            System.out.println(Thread.currentThread().getName() + " done.");
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Fibonacci Non-Recursive Thread");
            System.out.println(Thread.currentThread().getName() + " is computing...");
            long result2 = fibonacciNonRecursive(n);  // TC: O(n), SC: O(1)
            System.out.println("Fibonacci Non-Recursive (" + n + ") = " + result2);
            System.out.println(Thread.currentThread().getName() + " done.");
        }).start();

        new Thread(() -> {
            Thread.currentThread().setName("Fibonacci Recursive DP Thread");
            System.out.println(Thread.currentThread().getName() + " is computing...");
            long result2 = fibonacciRecursiveDP(n,new int[n+1]);  // TC: O(n), SC: O(n)
            System.out.println("Fibonacci Recursive DP (" + n + ") = " + result2);
            System.out.println(Thread.currentThread().getName() + " done.");
        }).start();
       
    }
}
// Output
// Fibonacci Non-Recursive Thread is computing...
// Fibonacci Recursive DP Thread is computing...
// Fibonacci Recursive Thread is computing...
// Fibonacci Non-Recursive (10) = 55
// Fibonacci Recursive (10) = 55
// Fibonacci Non-Recursive Thread done.
// Fibonacci Recursive DP (10) = 55
// Fibonacci Recursive DP Thread done.
// Fibonacci Recursive Thread done.