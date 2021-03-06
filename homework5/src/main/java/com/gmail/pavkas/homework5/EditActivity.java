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


    private ImageButton backButton;
    private ImageButton saveButton;
    private TextView toolText;
    private Toolbar toolbar;
    private EditText editName, editContact;
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
        editContact = findViewById(R.id.edit_contact);
        removeButton = findViewById(R.id.remove);
        removeButton.setOnClickListener(this);
        contact = findViewById(R.id.contact);
        db = App.getInstance().getDatabase();
        Intent intent = getIntent();
        int index = intent.getIntExtra("id", 0);
        person = db.personDao().getById(index);
        System.out.println("person = " + person + " id = " + index);
        editName.setText(person.getName());
        editContact.setText(person.getContact());
        if(person.isHasEmail()) {
            contact.setText("E-Mail: ");
        }
        else {
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
                newPerson.setHasEmail(person.isHasEmail());
                newPerson.setName(editName.getText().toString());
                newPerson.setContact(editContact.getText().toString());
                result = db.personDao().update(newPerson);
                setResult(result, intent);
                finish();
                break;
            case R.id.remove:
                result = db.personDao().delete(person);
                setResult(result, intent);
                finish();
                break;
        }

    }
}
