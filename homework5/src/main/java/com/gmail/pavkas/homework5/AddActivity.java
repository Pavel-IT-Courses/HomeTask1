package com.gmail.pavkas.homework5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton backButton;
    private ImageButton saveButton;
    private TextView toolText, telOrMail;
    private Toolbar toolbar;

    private EditText editName, editContact;
    private RadioButton tel, mail;

    PersonDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(this);
        saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(this);
        toolText = findViewById(R.id.action);
        toolbar = findViewById(R.id.toolbar_edit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolText.setText("Add Contact");
        editName = findViewById(R.id.edit_name);
        editContact = findViewById(R.id.edit_contact);
        telOrMail = findViewById(R.id.tel_mail);
        tel = findViewById(R.id.tel);
        tel.setOnClickListener(this);
        mail = findViewById(R.id.mail);
        mail.setOnClickListener(this);

        db = App.getInstance().getDatabase();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.save:
                Person person = new Person();
                person.setName(editName.getText().toString());
                person.setContact(editContact.getText().toString());
                if(mail.isChecked()) person.setHasEmail(true);
                int adds = (int)db.personDao().insert(person);
                Intent intent = new Intent();
                setResult(adds, intent);
                finish();
                break;
            case R.id.tel:
                telOrMail.setText(R.string.phone);
                break;
            case R.id.mail:
                telOrMail.setText(R.string.email);
                break;
        }
    }
}
