package com.nth.demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.nth.demo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    static {
        System.loadLibrary("native-lib");
    }

    private NativeLibrary nativeLibrary;
    private JavaLibrary javaLibrary;
    private int select = 0;
    private int[] numbers;
    private int loop;
    private int element;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        nativeLibrary = new NativeLibrary();
        javaLibrary = new JavaLibrary();

        List<String> list = new ArrayList<>();
        list.add("Quick Sort");
        list.add("Bubble Sort");
        list.add("Fibonacci");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.item_spinner, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(arrayAdapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select = position;
                if (numbers != null) {
                    numbers = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        binding.btnJava.setOnClickListener(v -> {
            init();
            if (checkInput()) {
                switch (select) {
                    case 0:
                        quickSort(true);
                        break;
                    case 1:
                        bubbleSort(true);
                        break;
                    case 2:
                        fibonacci(true);
                        break;
                    default:
                        break;
                }
            }
        });

        binding.btnNative.setOnClickListener(v -> {
            init();
            if (checkInput()) {
                switch (select) {
                    case 0:
                        quickSort(false);
                        break;
                    case 1:
                        bubbleSort(false);
                        break;
                    case 2:
                        fibonacci(false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void quickSort(boolean isJava) {
        new Thread(() -> {
            StringBuilder results = new StringBuilder();
            int countTime = 0;
            runOnUiThread(() -> {
                process(true);
            });
            if (isJava) {
                for (int i = 0; i < loop; i++) {
                    long time = javaLibrary.getTimeQuickSort(numbers, 0, numbers.length - 1);
                    results.append(time);
                    results.append(", ");
                    countTime += time;
                }
                long timeTb = (countTime / loop);
                runOnUiThread(() -> {
                    process(false);
                    setResult("Quick Sort (Java)", results.toString(), timeTb);
                });
            } else {
                for (int i = 0; i < loop; i++) {
                    long time = nativeLibrary.getTimeQuickSort(numbers, 0, numbers.length - 1);
                    results.append(time);
                    results.append(", ");
                    countTime += time;
                }
                long timeTb = (countTime / loop);
                runOnUiThread(() -> {
                    process(false);
                    setResult("Quick Sort (Native)", results.toString(), timeTb);
                });

            }
        }).start();
    }

    private void bubbleSort(boolean isJava) {
        new Thread(() -> {
            StringBuilder results = new StringBuilder();
            int countTime = 0;
            runOnUiThread(() -> {
                process(true);
            });
            if (isJava) {
                for (int i = 0; i < loop; i++) {
                    long time = javaLibrary.getTimeBubbleSort(numbers);
                    results.append(time);
                    results.append(", ");
                    countTime += time;
                }
                long timeTb = (countTime / loop);
                runOnUiThread(() -> {
                    process(false);
                    setResult("Bubble Sort (Java)", results.toString(), timeTb);
                });
            } else {
                for (int i = 0; i < loop; i++) {
                    long time = nativeLibrary.getTimeBubbleSort(numbers);
                    results.append(time);
                    results.append(", ");
                    countTime += time;
                }
                long timeTb = (countTime / loop);
                runOnUiThread(() -> {
                    process(false);
                    setResult("Bubble Sort (Native)", results.toString(), timeTb);
                });
            }
        }).start();
    }

    private void fibonacci(boolean isJava) {
        new Thread(() -> {
            StringBuilder results = new StringBuilder();
            int countTime = 0;
            runOnUiThread(() -> {
                process(true);
            });
            if (isJava) {
                for (int i = 0; i < loop; i++) {
                    long time = javaLibrary.getTimeFibonacci(element);
                    results.append(time);
                    results.append(", ");
                    countTime += time;
                }
                long timeTb = (countTime / loop);
                runOnUiThread(() -> {
                    process(false);
                    setResult("Fibonacci (Java)", results.toString(), timeTb);
                });
            } else {
                for (int i = 0; i < loop; i++) {
                    long time = nativeLibrary.getTimeFibonacci(element);
                    results.append(time);
                    results.append(", ");
                    countTime += time;
                }
                long timeTb = (countTime / loop);
                runOnUiThread(() -> {
                    process(false);
                    setResult("Fibonacci (Native)", results.toString(), timeTb);
                });
            }
        }).start();
    }

    private boolean checkInput() {
        if (binding.txtInput.getText().toString().equals("") || binding.txtLoop.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this,"Cần nhập đủ thông tin",Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private void process(boolean isStart) {
        if (isStart) {
            binding.btnNative.setEnabled(false);
            binding.btnJava.setEnabled(false);
            binding.process.setVisibility(View.VISIBLE);
            binding.showResult.setVisibility(View.GONE);
        } else {
            binding.btnNative.setEnabled(true);
            binding.btnJava.setEnabled(true);
            binding.process.setVisibility(View.GONE);
            binding.showResult.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setResult(String memory, String results, long timeTb) {
        binding.memory.setText(memory);
        binding.result.setText(results);
        binding.element.setText("" + element);
        binding.iteration.setText("" + loop);
        binding.mean.setText(timeTb + " ms");
    }

    private void init() {
        element = Integer.parseInt(binding.txtInput.getText().toString());
        loop = Integer.parseInt(binding.txtLoop.getText().toString());
        if (select != 2 && (numbers == null || numbers.length == 0)) {
            numbers = getNumbersRandom(element);
        }
    }

    private int[] getNumbersRandom(int size) {
        int[] numbers = new int[size];
        int i = 0;
        while (i < size) {
            numbers[i] = new Random().nextInt(1000);
            i++;
        }
        return numbers;
    }

}


