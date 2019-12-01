package com.gmail.pavkascool.firstadditionalmodule;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Callback";
    public static final String PARCEL = "Parcel";
    public static final String NUMBER = "Number";
    private Timer timer;
    private long delay = 5000;
    public static final int REQUEST_CODE = 1;
    private int num;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");


        textView = findViewById(R.id.text_view);
        if(savedInstanceState != null) {
            num = savedInstanceState.getInt("state");
            textView.setText("Already exists " + num);
        }
        else {
            textView.setText("New Launch");
        }
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(PARCEL, "This is a parcel");
                intent.putExtra(NUMBER, 8);
                startActivityForResult(intent, REQUEST_CODE);
            }
        }, delay);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            num = data.getIntExtra(SecondActivity.NUM, -1);
            Log.d(TAG, "I have got " + num);
            textView.setText("" + num);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("state", num);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
