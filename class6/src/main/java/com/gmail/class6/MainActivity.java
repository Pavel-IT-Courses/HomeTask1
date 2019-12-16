package com.gmail.class6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button save;
    EditText editText;
    TextView textView;
    SharedPreferences preferences;
    private final String KEY_NAME = "key_name";
    private final String KEY_TIME = "key_time";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        save = findViewById(R.id.button);
        save.setOnClickListener(this);
        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.last_session);
        preferences = getPreferences(MODE_PRIVATE);
        String s = getString(R.string.last_visit);
        long current = Calendar.getInstance().getTimeInMillis();
        long millis = preferences.getLong(KEY_TIME, 0);
        System.out.println("Current = " + current + " last = " + millis);
        String visit = s.format(s, (current - millis) / 60000);
        textView.setText((preferences.getString(KEY_NAME, "Hello Android") + " " + visit));
        readFile();
    }

    @Override
    public void onClick(View v) {
        /*String name = editText.getText().toString();
        preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
        editText.setText("");
        textView.setText(name);*/

        writeFile();

        File INTERNAL = getFilesDir();
        File EXT_DIR = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File dataDir = Environment.getExternalStorageDirectory();

        Log.d("FILE", "INTERNAL: " + INTERNAL.getAbsolutePath());
        Log.d("FILE", "EXTERNAL: " + EXT_DIR.getAbsolutePath());
        Log.d("FILE", "DATA: " + dataDir.getAbsolutePath());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        long millis = Calendar.getInstance().getTimeInMillis();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(KEY_TIME, millis);
        editor.apply();


    }

    private void writeFile() {
        String text = editText.getText().toString();
        String fileName = "NAMES.txt";

        File file = new File(getFilesDir(), fileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readFile() {
        String fileName = "NAMES.txt";
        File file = new File(getFilesDir(), fileName);
        if(file.exists()) {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = null;
            FileReader fileReader = null;

            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line = null;
                while((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                editText.setText(stringBuilder.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            finally {
                if(bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


        }
        else {
            Toast.makeText(this, "File doesn't exist", Toast.LENGTH_SHORT);
        }
    }
}
