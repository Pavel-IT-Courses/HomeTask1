package com.gmail.pavkascool.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.os.Bundle;

public class ContactBookActivity extends AppCompatActivity {

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_book);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.first);
        setSupportActionBar(toolbar);


    }
}
