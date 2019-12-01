package com.gmail.pavkascool.secondauxiliarymodule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonFrame, buttonLinear, buttonTable, buttonConstraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonFrame = findViewById(R.id.button_frame);
        buttonLinear = findViewById(R.id.button_linear);
        buttonTable = findViewById(R.id.button_table);
        buttonConstraint = findViewById(R.id.button_constraint);
        buttonFrame.setOnClickListener(this);
        buttonLinear.setOnClickListener(this);
        buttonTable.setOnClickListener(this);
        buttonConstraint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.button_frame:
                startActivity(new Intent(this, FrameLayoutActivity.class));
                break;
            case R.id.button_linear:
                startActivity(new Intent(this, LinearLayoutActivity.class));
                break;
            case R.id.button_table:
                startActivity(new Intent(this, TableLayoutActivity.class));
                break;
            case R.id.button_constraint:
                startActivity(new Intent(this, ConstraintLayoutActivity.class));
                break;
        }
    }
}
