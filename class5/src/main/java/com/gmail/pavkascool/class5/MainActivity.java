package com.gmail.pavkascool.class5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Dialog dialog;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "YES!", Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                dialog.show();
            }
        });
        getPreferences(MODE_PRIVATE).edit().putString("string", "My String").apply();
        System.out.println(getPreferences(MODE_PRIVATE).getString("string", "nothing"));
    }

    @Override
    protected void onStop() {
        super.onStop();

        dialog.dismiss();
    }
}
