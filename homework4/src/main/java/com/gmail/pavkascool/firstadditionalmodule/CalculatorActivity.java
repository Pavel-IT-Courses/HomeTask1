package com.gmail.pavkascool.firstadditionalmodule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private double operand1;
    private double operand2;
    private String operation;

    private TextView display;
    private String text = new String();

    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
    private Button plus, minus, divide, multiply, equal, back, clear, point;

    private boolean newFigure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);

        b0 = findViewById(R.id.b0);
        b0.setOnClickListener(this);
        b1 = findViewById(R.id.b1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.b3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.b4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.b5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.b6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.b7);
        b7.setOnClickListener(this);
        b8 = findViewById(R.id.b8);
        b8.setOnClickListener(this);
        b9 = findViewById(R.id.b9);
        b9.setOnClickListener(this);
        minus = findViewById(R.id.minus);
        minus.setOnClickListener(this);
        plus = findViewById(R.id.plus);
        plus.setOnClickListener(this);
        divide = findViewById(R.id.divide);
        divide.setOnClickListener(this);
        multiply = findViewById(R.id.multiply);
        multiply.setOnClickListener(this);
        equal = findViewById(R.id.equal);
        equal.setOnClickListener(this);
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        clear = findViewById(R.id.clear);
        clear.setOnClickListener(this);
        point = findViewById(R.id.point);
        point.setOnClickListener(this);

        if (savedInstanceState != null) {
            operation = savedInstanceState.getString("operation", null);
            operand1 = savedInstanceState.getDouble("operand1", 0);
            text = savedInstanceState.getString("text");
            newFigure = savedInstanceState.getBoolean("newFigure");
            display.setText(text);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("operation", operation);
        outState.putDouble("operand1", operand1);
        outState.putString("text", text);
        outState.putBoolean("newFigure", newFigure);
    }

    @Override
    public void onClick(View v) {


        switch(v.getId()) {

            case R.id.b0:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                if(text.startsWith("0") && !text.contains(".")) break;
                text += "0";
                break;

            case R.id.b1:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "1";
                break;

            case R.id.b2:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "2";
                break;

            case R.id.b3:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "3";
                break;

            case R.id.b4:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "4";
                break;

            case R.id.b5:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "5";
                break;

            case R.id.b6:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "6";
                break;

            case R.id.b7:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "7";
                break;

            case R.id.b8:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "8";
                break;

            case R.id.b9:
                if(newFigure) {
                    text = new String();
                    newFigure = false;
                }
                text += "9";
                break;

            case R.id.point:
                if(!text.contains(".")) text += ".";
                break;

            case R.id.back:
                text = text.substring(0, text.length() - 1);
                break;

            case R.id.clear:
                text = new String();
                break;

            case R.id.equal:
                if(operation != null) {
                    text = doOperation();
                }
                break;

            case R.id.plus:
                if(operation != null)  {
                    text = doOperation();
                    Toast.makeText(this, "NULL!!!  OPERAND1 = " + operand1, Toast.LENGTH_SHORT).show();
                }
                setOperation("+");
                break;

            case R.id.minus:
                if(operation != null)  {
                    text = doOperation();
                }
                setOperation("-");
                break;

            case R.id.multiply:
                if(operation != null)  {
                    text = doOperation();
                }
                setOperation("*");
                break;

            case R.id.divide:
                if(operation != null)  {
                    text = doOperation();
                }
                setOperation("/");
                break;
        }
        display.setText(text);
    }

    private void setOperation (String s) {
        newFigure = true;
        if(text.isEmpty()) return;
        try {
            operand1 = Double.parseDouble(text);
            Toast.makeText(this, "OPERAND1 = " + operand1, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(this, "Wrong argument", Toast.LENGTH_SHORT).show();
        }
        operation = s;

    }

    private String doOperation() {
        try {
            operand2 = Double.parseDouble(text);
        } catch (Exception e) {
            Toast.makeText(this, "Wrong argument", Toast.LENGTH_SHORT).show();
        }
        switch(operation) {
            case "+":
                operand1 += operand2;
                break;
            case "-":
                operand1 -= operand2;
                break;
            case "*":
                operand1 *= operand2;
                break;
            case "/":
                try {
                    operand1 /= operand2;
                }
                catch (Exception e) {
                    Toast.makeText(this, "Illegal Operation", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        operation = null;
        newFigure = true;
        text = "" + operand1;
        return "" + operand1;
    }
}
