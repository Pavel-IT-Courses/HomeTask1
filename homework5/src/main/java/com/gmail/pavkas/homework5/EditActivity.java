package com.gmail.pavkas.homework5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    private Person startPerson;
    private Person endPerson;

    private ImageButton backButton;
    private ImageButton saveButton;
    private TextView toolText;
    private Toolbar toolbar;
    private EditText editName, editPhone;
    private Button removeButton;
    private TextView contact;
    PersonDatabase db;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(this);
        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(this);
        toolText = findViewById(R.id.action);
        toolbar = findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolText.setText("Edit Contact");
        editName = findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        removeButton = findViewById(R.id.remove);
        removeButton.setOnClickListener(this);
        contact = findViewById(R.id.contact);
        db = App.getInstance().getDatabase();
        Intent intent = getIntent();
        int index = intent.getIntExtra("id", 0);
        person = db.personDao().getById(index);
        editName.setText(person.getName());
        if(person.getPhone() == null || person.getPhone().isEmpty()) {
            editPhone.setText(person.getEmail());
            contact.setText("E-Mail: ");
        }
        else {
            editPhone.setText(person.getPhone());
            contact.setText("Phone: ");
        }
    }

    @Override
    public void onClick(View v) {
        int result = 0;
        Intent intent = new Intent();
        switch(v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.save:
                Person newPerson = new Person();
                newPerson.setId(person.getId());
                newPerson.setName(editName.getText().toString());
                if(person.getPhone() == null || person.getPhone().isEmpty()) {
                    newPerson.setEmail(editPhone.getText().toString());
                }
                else {
                    newPerson.setPhone(editPhone.getText().toString());
                }
                result = db.personDao().update(newPerson);
                break;
            case R.id.remove:
                result = db.personDao().delete(person);
                break;
        }
        setResult(result, intent);
        finish();
    }
}
