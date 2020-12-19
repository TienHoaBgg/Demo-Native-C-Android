package com.nth.demo;

import android.util.Log;

/**
 * Created by NguyenTienHoa on 12/17/2020
 */

public class JavaLibrary {


    public long getTimeBubbleSort(int[] arr) {
        long startTime = System.currentTimeMillis();
        bubbleSort(arr);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    public long getTimeFibonacci(int n) {
        long startTime = System.currentTimeMillis();
        fibonacci(n);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }


    public long getTimeQuickSort(int[] numbers, int low, int high) {
        long startTime = System.currentTimeMillis();
        quickSort(numbers, low, high);
        long endTime = System.currentTimeMillis();
        return (endTime - startTime);
    }

    private int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    private int fibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public void quickSort(int arr[], int left, int right) {
        if (right - left <= 0) {
            return;
        } else {
            int pivot = arr[right];
            int partitionPoint = partition(arr, left, right, pivot);
            quickSort(arr, left, partitionPoint - 1);
            quickSort(arr, partitionPoint + 1, right);
        }
    }

    public void swap(int arr[], int num1, int num2) {
        int temp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = temp;
    }

    public int partition(int arr[], int left, int right, int pivot) {
        int leftPointer = left - 1;
        int rightPointer = right;
        while (true) {
            while (arr[++leftPointer] < pivot) {
            }
            while (rightPointer > 0 && arr[--rightPointer] > pivot) {
            }
            if (leftPointer >= rightPointer) {
                break;
            } else {
                swap(arr, leftPointer, rightPointer);
            }
        }
        swap(arr, leftPointer, right);
        return leftPointer;
    }

}
