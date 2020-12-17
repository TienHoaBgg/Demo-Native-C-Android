#include <jni.h>
#include <string>
#include <math.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_nth_demo_NativeLibrary_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

void swap(int &a, int &b){
    int temp = a;
    a = b;
    b = temp;
}

extern "C" JNIEXPORT jintArray JNICALL
Java_com_nth_demo_NativeLibrary_startBubbleSort(JNIEnv *env, jobject obj, jintArray jArr) {

    jint *arr = env -> GetIntArrayElements(jArr, 0);
    int len = env -> GetArrayLength(jArr);
    for(int i = 0; i < len; i++){
        for(int j = 0; j < len - i -1; j++){
            if(arr[j] > arr[j + 1]){
                swap(arr[j], arr[j + 1]);
            }
        }
    }
    env -> ReleaseIntArrayElements(jArr, arr, JNI_COMMIT);
    return jArr;
}

int Fibonacci(int n)
{
    if (n == 1 || n == 2)
        return 1;
    return Fibonacci(n - 1) + Fibonacci(n - 2);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_nth_demo_NativeLibrary_fibonacci(JNIEnv *env,jobject instance,jint number) {
    int ret = Fibonacci(number);
    return ret;
}

int partition (int arr[], int low, int high)
{
    int pivot = arr[high];
    int left = low;
    int right = high - 1;
    while(true){
        while(left <= right && arr[left] < pivot) left++;
        while(right >= left && arr[right] > pivot) right--;
        if (left >= right) break;
        swap(arr[left], arr[right]);
        left++;
        right--;
    }
    swap(arr[left], arr[high]);
    return left;
}

void quickSort(int arr[], int low, int high)
{
    if (low < high)
    {
        int pi = partition(arr, low, high);
        quickSort(arr, low, pi - 1);
        quickSort(arr, pi + 1, high);
    }
}

extern "C"
JNIEXPORT jintArray JNICALL
Java_com_nth_demo_NativeLibrary_quickSort(JNIEnv *env,jobject instance,jintArray jArr,jint low , jint high) {
    jint *arr = env -> GetIntArrayElements(jArr, 0);
    quickSort(arr,low,high);
    env -> ReleaseIntArrayElements(jArr, arr, JNI_COMMIT);
    return jArr;
}