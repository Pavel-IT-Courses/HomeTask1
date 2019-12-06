package com.gmail.pavkascool.homework2.view;

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

public class CustomizedViewActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Toolbar toolbar;
    private TextView toolText, mode;
    private ImageButton backButton;
    private SwitchCompat switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_view);
        toolbar = findViewById(R.id.tb);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mode = findViewById(R.id.mode);
        toolText = findViewById(R.id.tb_action);
        toolText.setText("Customized View");
        backButton = findViewById(R.id.tb_back);
        backButton.setOnClickListener(this);
        switchButton = findViewById(R.id.switch_button);
        switchButton.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.tb_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Toast.makeText(this, "Отслеживание переключения: " + (isChecked ? "on" : "off"),
                Toast.LENGTH_SHORT).show();
        String s = isChecked? getString(R.string.toast) : getString(R.string.snack);
        mode.setText(s);
    }
}
