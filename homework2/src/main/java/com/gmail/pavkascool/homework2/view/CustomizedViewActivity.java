package com.gmail.pavkascool.homework2.view;

import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import com.gmail.pavkascool.homework2.R;
import com.google.android.material.snackbar.Snackbar;

public class CustomizedViewActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, CustomListener {

    private Toolbar toolbar;
    private TextView toolText, mode;
    private ImageButton backButton;
    private SwitchCompat switchButton;
    private CustomView customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_view);
        toolbar = findViewById(R.id.tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mode = findViewById(R.id.mode);
        toolText = findViewById(R.id.tb_action);
        toolText.setText("Custom View");
        backButton = findViewById(R.id.tb_back);
        backButton.setOnClickListener(this);
        switchButton = findViewById(R.id.switch_button);
        switchButton.setOnCheckedChangeListener(this);

        customView = findViewById(R.id.my_view);

        customView.setCustomListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String s = isChecked? getString(R.string.toast) : getString(R.string.snack);
        mode.setText(s);
    }

    @Override
    public void onCustomTouchDown(CustomView cv, int event, float x, float y) {
        if(!switchButton.isChecked()) {
            Snackbar snackbar = Snackbar.make(cv, "x = " + x + ", y = " + y, Snackbar.LENGTH_SHORT);
            View snackbarView = snackbar.getView();
            TextView tv = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
            tv.setTextColor(cv.colors[event]);
            snackbar.show();
        }
        else {
            Toast.makeText(this, "x = " + x + ", y = " + y, Toast.LENGTH_SHORT).show();
        }
    }
}
