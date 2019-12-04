package com.gmail.pavkascool.homework2;

import android.app.Service;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    private com.google.android.material.floatingactionbutton.FloatingActionButton floatingActionButton;
    private TextView initText;
    private GridLayout gridLayout;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_book);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.requestFocus();

        initText = findViewById(R.id.initial_text);

        searchView = findViewById(R.id.search);

        persons = new ArrayList<>();
        Person p = new Person("Michael Zawacki", "66666666");
        persons.add(p);
        Person p1 = new Person("John LaSalle", "1111111111111");
        persons.add(p1);


        linearLayout = findViewById(R.id.inner_layout);
        gridLayout = findViewById(R.id.grid);

        LayoutInflater li = getLayoutInflater();

        if(persons.size() > 0) {
            linearLayout.removeView(initText);
        }

        for(int i = 0; i < persons.size(); i++) {
            View item = li.inflate(R.layout.item_person, gridLayout, false);
            TextView name = item.findViewById(R.id.name);
            TextView phone = item.findViewById(R.id.phone);
            name.setText(persons.get(i).getName());
            phone.setText(persons.get(i).getPhone());
            gridLayout.addView(item, i);

        }

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
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
