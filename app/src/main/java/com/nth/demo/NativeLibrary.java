package com.nth.demo;

/**
 * Created by NguyenTienHoa on 12/17/2020
 */

public class NativeLibrary {

    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();

    public native int[] startBubbleSort(int[] arr);

    public native int fibonacci(int number);

    public native int[] quickSort(int[] arr,int low, int high);
}
