package com.gmail.pavkascool.homework2;

import android.app.Service;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ContactBookActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<Person> persons;

    private Toolbar toolbar;
    private SearchView searchView;
    private Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_book);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.requestFocus();

        searchView = findViewById(R.id.search);

        persons = new ArrayList<>();

        next = findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, EditContactActivity.class);
        startActivity(intent);
    }
}
