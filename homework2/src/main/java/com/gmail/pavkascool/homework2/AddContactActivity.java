package com.gmail.pavkascool.homework2;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Person startPerson;
    private Person endPerson;

    private ImageButton backButton;
    private ImageButton saveButton;
    private TextView toolText;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(this);
        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(this);
        toolText = findViewById(R.id.action);
        toolbar = findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolText.setText("Add Contact");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.save:
                break;
        }
    }
}
