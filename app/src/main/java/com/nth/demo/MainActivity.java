package com.nth.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nth.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    static {
        System.loadLibrary("native-lib");
    }
    private NativeLibrary nativeLibrary;
    private JavaLibrary javaLibrary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        nativeLibrary = new NativeLibrary();
        javaLibrary = new JavaLibrary();
        binding.sampleText.setText(nativeLibrary.stringFromJNI());

        binding.btnClick.setOnClickListener(v -> {
            int[] input = {1,3,5,10,9,3,22,4,6,1,5};
            int[] result = javaLibrary.quickSort(input,0,input.length-1);
            for (int i = 0 ; i < result.length ; i++){
                Log.i("TestArr","KQ: "+result[i]);
            }
//            int number = javaLibrary.fibonacci(40);
//            Log.i("TestArr","KQ: "+number);

        });
    }


}