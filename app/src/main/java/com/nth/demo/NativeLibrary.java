package com.nth.demo;

/**
 * Created by NguyenTienHoa on 12/17/2020
 */

public class NativeLibrary {

    static {
        System.loadLibrary("native-lib");
    }

    public native int[] startBubbleSort(int[] arr);

    public native int getTimeBubbleSort(int[] arr);

    public native int getTimeFibonacci(int number);

    private native int[] quickSort(int[] arr,int low, int high);

    public native int getTimeQuickSort(int[] arr,int low, int high);

//    public long getTimeFibonacci(int n){
//        long startTime = System.currentTimeMillis();
//        fibonacci(n);
//        long endTime = System.currentTimeMillis();
//        return (endTime - startTime);
//    }

//    public long getTimeQuickSort(int[] arr,int low, int high){
//        long startTime = System.currentTimeMillis();
//        quickSort(arr,low,high);
//        long endTime = System.currentTimeMillis();
//        return (endTime - startTime);
//    }

//    public long getTimeBubbleSort(int[] arr){
//        long startTime = System.currentTimeMillis();
//        startBubbleSort(arr);
//        long endTime = System.currentTimeMillis();
//        return (endTime - startTime);
//    }

}
