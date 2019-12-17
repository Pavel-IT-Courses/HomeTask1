package com.gmail.pavkas.homework5;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private SearchView searchView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private TextView initText;
    private GridLayout gridLayout;
    private LinearLayout linearLayout;
    PersonDatabase db;
    private List<Person> persons;

    private final int REQ_CODE_ADD = 1;
    private final int REQ_CODE_EDIT = 2;
    private final int TYPE_PHONE = 0;
    private final int TYPE_MAIL = 1;

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

        db = App.getInstance().getDatabase();
        persons = db.personDao().getAll();
        if(persons.size() > 0) {
            initText.setText("");
        }
        else {
            initText.setText(getString(R.string.empty));
        }

        recyclerView = findViewById(R.id.recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(this, getResources().getConfiguration().orientation);
        recyclerView.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter();
        recyclerView.setAdapter(personAdapter);


        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

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
            intent = new Intent(this, EditActivity.class);
            intent.putExtra("id", v.getId());
        }
        startActivityForResult(intent, reqCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode > 0) {
            persons = db.personDao().getAll();
            if(persons.size() > 0) {
                initText.setText("");
            }
            else {
                initText.setText(getString(R.string.empty));
            }
            personAdapter.notifyDataSetChanged();
        }
    }

    private class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = null;
            if(TextUtils.isEmpty(persons.get(viewType).getPhone())) {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_email, parent, false);
            }
            else {
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone, parent, false);
            }
            v.setId(viewType);
            v.setOnClickListener(ContactActivity.this);
            return new PersonViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            PersonViewHolder personViewHolder = (PersonViewHolder)holder;
            personViewHolder.nameView.setText(persons.get(position).getName());
            if (personViewHolder.phoneView != null) personViewHolder.phoneView.setText(persons.get(position).getPhone());
            else personViewHolder.emailView.setText(persons.get(position).getEmail());
        }

        @Override
        public int getItemViewType(int position) {
            return position;
//            if(TextUtils.isEmpty(persons.get(position).getEmail())) return TYPE_PHONE;
//            else return TYPE_MAIL;
        }

        @Override
        public int getItemCount() {
            return persons.size();
        }
    }

    private class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, phoneView, emailView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            phoneView = itemView.findViewById(R.id.phone);
            emailView = itemView.findViewById(R.id.email);
        }
    }


}
