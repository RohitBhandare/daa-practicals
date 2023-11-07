package DAA_Practicals;

import java.util.Arrays;
import java.util.Random;

public class QuickSortRandom {
    public static void deterministicQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            deterministicQuickSort(arr, low, pivot - 1);
            deterministicQuickSort(arr, pivot + 1, high);
        }
    }

    public static void randomizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = randomizedPartition(arr, low, high);
            randomizedQuickSort(arr, low, pivotIndex - 1);
            randomizedQuickSort(arr, pivotIndex + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static int randomizedPartition(int[] arr, int low, int high) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(high - low + 1) + low;
        swap(arr, randomIndex, high);
        return partition(arr, low, high);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5,6};
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

        System.out.println("Deterministic Quick Sort:");
        long startTime = System.nanoTime();
        deterministicQuickSort(arr1, 0, arr1.length - 1);
        long endTime = System.nanoTime();
        System.out.println("Sorted Array: " + Arrays.toString(arr1));
        System.out.println("Time taken: " + (endTime - startTime) + " ns");

        System.out.println("\nRandomized Quick Sort:");
        startTime = System.nanoTime();
        randomizedQuickSort(arr2, 0, arr2.length - 1);
        endTime = System.nanoTime();
        System.out.println("Sorted Array: " + Arrays.toString(arr2));
        System.out.println("Time taken: " + (endTime - startTime) + " ns");
    }
}
