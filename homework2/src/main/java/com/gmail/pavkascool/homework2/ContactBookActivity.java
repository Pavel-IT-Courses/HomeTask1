package com.gmail.pavkascool.homework2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactBookActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<Person> persons = new ArrayList<>();

    private Toolbar toolbar;
    private SearchView searchView;
    private FloatingActionButton floatingActionButton;
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

        gridLayout = findViewById(R.id.grid);

        linearLayout = findViewById(R.id.inner_layout);


        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        LayoutInflater li = getLayoutInflater();

        if(persons.size() > 0) {
            linearLayout.removeView(initText);
            gridLayout.removeAllViews();
            for (int i = 0; i < persons.size(); i++) {
                String name = persons.get(i).getName();
                String phone = persons.get(i).getPhone();
                String email = persons.get(i).getEmail();
                View item = null;
                if (phone != null && !phone.isEmpty()) {
                    item = li.inflate(R.layout.item_phone, gridLayout, false);
                    TextView phoneText = item.findViewById(R.id.phone);
                    phoneText.setText(phone);
                } else {
                    item = li.inflate(R.layout.item_email, gridLayout, false);
                    TextView emailText = item.findViewById(R.id.email);
                    emailText.setText(email);
                }

                TextView nameText = item.findViewById(R.id.name);
                nameText.setText(name);
                item.setId(i);
                item.setOnClickListener(this);
                gridLayout.addView(item);

            }
        }
        else {
            gridLayout.removeAllViews();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if(v.getId() == R.id.fab) {
            intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        }
        else {
            int index = v.getId();
            intent = new Intent(this, EditContactActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
