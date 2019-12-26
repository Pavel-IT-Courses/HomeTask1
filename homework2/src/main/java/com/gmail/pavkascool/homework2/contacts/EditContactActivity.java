package com.gmail.pavkascool.homework2.contacts;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.gmail.pavkascool.homework2.R;

import androidx.appcompat.widget.Toolbar;

public class EditContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Person startPerson;
    private Person endPerson;

    private ImageButton backButton;
    private ImageButton saveButton;
    private TextView toolText;
    private Toolbar toolbar;
    private EditText editName, editPhone;
    private Button removeButton;
    private TextView contact;

    PersonsHolder holder = MyApp.getPersonsHolder();

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
        toolText.setText("Edit Contact");
        editName = findViewById(R.id.edit_name);
        editPhone = findViewById(R.id.edit_phone);
        removeButton = findViewById(R.id.remove);
        removeButton.setOnClickListener(this);
        contact = findViewById(R.id.contact);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        if (index >= 0) {
            startPerson = holder.get(index);
            endPerson = new Person();
            endPerson.setName((startPerson.getName()));
            endPerson.setPhone((startPerson.getPhone()));
            endPerson.setEmail((startPerson.getEmail()));
        }
        editName.setText(endPerson.getName());
        if(startPerson.getPhone() != null && !startPerson.getName().isEmpty()) {
            contact.setText(R.string.phone);
            editPhone.setText(startPerson.getPhone());
        }
        else {
            contact.setText(R.string.email);
            editPhone.setText(startPerson.getEmail());
        }

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.save:
                String name = editName.getText().toString();
                endPerson.setName(name);
                String info = editPhone.getText().toString();
                if(contact.getText().toString() == getString(R.string.phone)) {
                    endPerson.setPhone(info);
                }
                else {
                    endPerson.setEmail(info);
                }
                holder.remove(startPerson);
                holder.add(endPerson);
                finish();
                break;
            case R.id.remove:
                holder.remove(startPerson);
                finish();
                break;
        }
    }
}
