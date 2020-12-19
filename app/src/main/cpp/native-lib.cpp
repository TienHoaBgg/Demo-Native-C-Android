#include <jni.h>
#include <string>
#include <cmath>

void swap(int* a, int* b)
{
    int t = *a;
    *a = *b;
    *b = t;
}

int fibonacci(int n) {
    if (n == 1 || n == 2)
        return 1;
    return fibonacci(n - 1) + fibonacci(n - 2);
}

int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int left = low;
    int right = high - 1;
    while (true) {
        while (left <= right && arr[left] < pivot) left++;
        while (right >= left && arr[right] > pivot) right--;
        if (left >= right) break;
        swap(&arr[left], &arr[right]);
        left++;
        right--;
    }
    swap(&arr[left], &arr[high]);
    return left;
}

void quickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

void bubbleSort(int arr[], int len){
    for (int i = 0; i < len; i++) {
        for (int j = 0; j < len - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                swap(&arr[j], &arr[j + 1]);
            }
        }
    }
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_nth_demo_NativeLibrary_getTimeFibonacci(JNIEnv *env, jobject thiz, jint number) {
    clock_t t_start, t_end;
    t_start = clock() / (CLOCKS_PER_SEC / 1000);
    fibonacci(number);
    t_end = clock() / (CLOCKS_PER_SEC / 1000);
    return (t_end - t_start);
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_nth_demo_NativeLibrary_quickSort(JNIEnv *env, jobject thiz, jintArray jArr,
                                                  jint low, jint high) {
    jint *arr = env->GetIntArrayElements(jArr, nullptr);
    quickSort(arr, low, high);
    env->ReleaseIntArrayElements(jArr, arr, JNI_COMMIT);
    return jArr;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_nth_demo_NativeLibrary_getTimeQuickSort(JNIEnv *env, jobject thiz, jintArray jArr,
                                          jint low, jint high) {
    clock_t t_start, t_end;
    t_start = clock() / (CLOCKS_PER_SEC / 1000);
    jint *arr = env->GetIntArrayElements(jArr, nullptr);
    quickSort(arr, low, high);
    t_end = clock() / (CLOCKS_PER_SEC / 1000);
    return (t_end - t_start);
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_nth_demo_NativeLibrary_startBubbleSort(JNIEnv *env, jobject thiz, jintArray jArr) {
    jint *arr = env->GetIntArrayElements(jArr, nullptr);
    int len = env->GetArrayLength(jArr);
    bubbleSort(arr,len);
    env->ReleaseIntArrayElements(jArr, arr, JNI_COMMIT);
    return jArr;
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_nth_demo_NativeLibrary_getTimeBubbleSort(JNIEnv *env, jobject thiz, jintArray jArr) {
    clock_t t_start, t_end;
    t_start = clock() / (CLOCKS_PER_SEC / 1000);
    jint *arr = env->GetIntArrayElements(jArr, nullptr);
    int len = env->GetArrayLength(jArr);
    bubbleSort(arr,len);
    t_end = clock() / (CLOCKS_PER_SEC / 1000);
    return (t_end - t_start);
}