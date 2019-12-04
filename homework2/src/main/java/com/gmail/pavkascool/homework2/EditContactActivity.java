package com.gmail.pavkascool.homework2;

import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class EditContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Person startPerson;
    private Person endPerson;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
