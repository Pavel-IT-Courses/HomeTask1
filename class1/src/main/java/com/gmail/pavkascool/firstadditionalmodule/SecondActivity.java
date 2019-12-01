package com.gmail.pavkascool.firstadditionalmodule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

   private static final String TAG = "Second";
   private TextView textView;
   public static String NUM = "Num";
   private int num;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String line = null;
        if(intent != null && intent.hasExtra(MainActivity.PARCEL)) {
            line = intent.getStringExtra(MainActivity.PARCEL);
            Log.d(TAG, line);
            num = intent.getIntExtra("Number", -1);
        }
        textView = findViewById(R.id.text_view);
        textView.setText(line);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    public void onResume() {
        super.onResume();
        Intent intent = new Intent();
        intent.putExtra(NUM, num * 2);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
