package com.nth.demo;

/**
 * Created by NguyenTienHoa on 12/17/2020
 */

public class JavaLibrary {

    public int[] startBubbleSort(int[] arr){
        int temp;
        boolean swapped = false;
        for (int i = 0; i < arr.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return arr;
    }

    public int fibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    public int[] quickSort (int[] numbers, int low, int high) {
        int i = low;
        int j = high;
        int temp;
        int middle = numbers[(low+high)/2];
        while (i < j) {
            while (numbers[i] < middle) {
                i++;
            }
            while (numbers[j] > middle) {
                j--;
            }

            if (i<=j) {
                temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
                j--;
            }
        }

        if (low < j) {
            quickSort(numbers, low, j);
        }

        if (i < high) {
            quickSort(numbers, i, high);
        }
        return numbers;
    }

}
