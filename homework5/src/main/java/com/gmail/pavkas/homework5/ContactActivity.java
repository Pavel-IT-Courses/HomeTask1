package com.gmail.pavkas.homework5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private SearchView searchView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private TextView initText;
    private GridLayout gridLayout;
    LinearLayout linearLayout;
    PersonDatabase db;

    private final int REQ_CODE_ADD = 1;
    private final int REQ_CODE_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.requestFocus();

        initText = findViewById(R.id.initial_text);

        searchView = findViewById(R.id.search);

        linearLayout = findViewById(R.id.inner_layout);
        recyclerView = findViewById(R.id.recycler);

        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        db = App.getInstance().getDatabase();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        int reqCode = 0;
        if(v.getId() == R.id.fab) {
            intent = new Intent(this, AddActivity.class);
            reqCode = REQ_CODE_ADD;
        }
        else {

        }
        startActivityForResult(intent, reqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
