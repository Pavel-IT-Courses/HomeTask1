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
        CompoundButton.OnCheckedChangeListener, View.OnTouchListener {

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

        customView.setOnTouchListener(this);
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
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            float x = event.getX();
            float y = event.getY();
            int w = ((CustomView)v).w;
            int h = ((CustomView)v).h;
            float radius = ((CustomView)v).radius;
            float cX = x - w/2;
            float cY = y - h/2;

            float r = (float)Math.sqrt(cX*cX + cY*cY);
            if(r > radius) {
                return false;
            }
            if(r < radius/3) {
                ((CustomView)v).shuffleColors();
                v.invalidate();
                return true;
            }

            if (!switchButton.isChecked()) {
                int index = 0;
                if(cX > 0 && cY > 0) index = 0;
                if(cX < 0 && cY > 0) index = 1;
                if(cX < 0 && cY < 0) index = 2;
                if(cX > 0 && cY < 0) index = 3;
                Snackbar snackbar = Snackbar.make(v, "x = " + x + ", y = " + y, Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView tv = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text);
                tv.setTextColor(((CustomView)v).colors[index]);
                snackbar.show();
            }
            else {
                Toast.makeText(this, "x = " + x + ", y = " + y, Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }
}
