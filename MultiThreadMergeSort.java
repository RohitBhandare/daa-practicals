package DAA_Practicals;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MultiThreadMergeSort {
    private static ForkJoinPool pool = new ForkJoinPool();

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        int[] temp = new int[arr.length];
        MergeSortTask task = new MergeSortTask(arr, temp, 0, arr.length - 1);
        pool.invoke(task);
    }

    static class MergeSortTask extends RecursiveAction {
        private int[] arr;
        private int[] temp;
        private int left;
        private int right;

        MergeSortTask(int[] arr, int[] temp, int left, int right) {
            this.arr = arr;
            this.temp = temp;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left < right) {
                int mid = (left + right) / 2;
                System.out.println(Thread.currentThread());
                MergeSortTask leftTask = new MergeSortTask(arr, temp, left, mid);
                MergeSortTask rightTask = new MergeSortTask(arr, temp, mid + 1, right);
                invokeAll(leftTask, rightTask);
                merge(arr, temp, left, mid, right);
            }
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            arr[k] = temp[i];
            k++;
            i++;
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5,3,4,5,2,7,8,1,2,4,5}; // Adjust the array as needed
        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));
        int[] arrCopy = Arrays.copyOf(arr, arr.length); // Copy the array for multi-threaded version

        // Measure and compare execution times
        long startTime, endTime;

        // Regular Merge Sort
        startTime = System.currentTimeMillis();
        mergeSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Regular Merge Sort Time: " + (endTime - startTime) + " ms");

        // Multi-Threaded Merge Sort
        arr = Arrays.copyOf(arrCopy, arrCopy.length); // Reset the array
        startTime = System.currentTimeMillis();
        mergeSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Multi-Threaded Merge Sort Time: " + (endTime - startTime) + " ms");
        
        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    
}

/*

Output:

Original Array:
[10, 7, 8, 9, 1, 5, 3, 4, 5, 2, 7, 8, 1, 2, 4, 5]

Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#24,ForkJoinPool-1-worker-3,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#25,ForkJoinPool-1-worker-4,5,main]
Thread[#24,ForkJoinPool-1-worker-3,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#26,ForkJoinPool-1-worker-5,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#26,ForkJoinPool-1-worker-5,5,main]
Thread[#24,ForkJoinPool-1-worker-3,5,main]
Thread[#24,ForkJoinPool-1-worker-3,5,main]
Thread[#23,ForkJoinPool-1-worker-2,5,main]
Thread[#25,ForkJoinPool-1-worker-4,5,main]
Thread[#22,ForkJoinPool-1-worker-1,5,main]
Thread[#29,ForkJoinPool-1-worker-8,5,main]
Thread[#26,ForkJoinPool-1-worker-5,5,main]
Thread[#28,ForkJoinPool-1-worker-7,5,main]
Thread[#27,ForkJoinPool-1-worker-6,5,main]

Regular Merge Sort Time: 5 ms
Multi-Threaded Merge Sort Time: 2 ms

Sorted Array:
[1, 1, 2, 2, 3, 4, 4, 5, 5, 5, 7, 7, 8, 8, 9, 10]

*/