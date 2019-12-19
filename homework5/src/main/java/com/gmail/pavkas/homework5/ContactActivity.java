package com.gmail.pavkas.homework5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private SearchView searchView;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerView;
    private PersonAdapter personAdapter;
    private TextView initText;
    PersonDatabase db;
    private List<Person> persons;

    private final int REQ_CODE = 1;

    private AlertDialog dialog;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.requestFocus();

        initText = findViewById(R.id.initial_text);

        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                personAdapter.filter(newText);
                return true;
            }
        });


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
        personAdapter = new PersonAdapter(persons);
        recyclerView.setAdapter(personAdapter);


        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);


    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.fab) {
            Intent intent = new Intent(this, AddActivity.class);
            startActivityForResult(intent, REQ_CODE);
        }
        else {
            int pos = recyclerView.getChildLayoutPosition(v);
            final int id = (int) (personAdapter.items.get(pos).getId());
            final String communicate, toWhom;
            if(personAdapter.items.get(pos).isHasEmail()) {
                communicate = "Mail";
                toWhom = "mailto:" + personAdapter.items.get(pos).getContact();
            }
            else {
                communicate = "Call";
                toWhom = "tel:" + personAdapter.items.get(pos).getContact();
            }
            dialog = builder.setTitle("Chose Action").setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent data = new Intent(ContactActivity.this, EditActivity.class);
                    data.putExtra("id", id);
                    startActivityForResult(data, REQ_CODE);
                }
            }).setNegativeButton(communicate, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent data = new Intent(Intent.ACTION_VIEW, Uri.parse(toWhom));
                    startActivity(data);
                }
            }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).create();
            dialog.show();


        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode > 0) {
            persons = db.personDao().getAll();
            personAdapter.items = new ArrayList<Person>(persons);
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

        List<Person> items;

        PersonAdapter(List<Person> persons) {
            items = new ArrayList<Person>(persons);
        }

        public void filter(String text) {
            items.clear();
            if(text.isEmpty()) {
                items.addAll(persons);
            }
            else {
                text = text.toLowerCase();
                for(Person p: persons) {
                    if(p.getName().toLowerCase().contains(text)) {
                        items.add(p);
                    }
                }
            }
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false);
            v.setOnClickListener(ContactActivity.this);
            return new PersonViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            PersonViewHolder personViewHolder = (PersonViewHolder)holder;
            Person p = items.get(position);
            personViewHolder.nameView.setText(p.getName());
            String contactType;
            int image;
            int color;
            if(p.isHasEmail()) {
                contactType = "e-mail: ";
                image = R.drawable.contact_mail;
                color = Color.RED;
            }
            else {
                contactType = "tel: ";
                image = R.drawable.contact_phone;
                color = Color.BLUE;
            }
            personViewHolder.contactView.setText(contactType + p.getContact());
            personViewHolder.imageView.setImageResource(image);
            personViewHolder.imageView.setColorFilter(color);

        }

        @Override
        public int getItemViewType(int position) {
            return position;

        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    private class PersonViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, contactView;
        ImageView imageView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name);
            contactView = itemView.findViewById(R.id.contact);
            imageView = itemView.findViewById(R.id.image);
        }
    }

}
