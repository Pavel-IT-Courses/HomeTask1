package com.gmail.pavkascool.homework3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Person> persons = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListOfPersons();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, RecyclerView.VERTICAL));
        recyclerView.setAdapter(new PersonAdapter(persons));
    }

    private void initListOfPersons() {
        Person p = new Person("John Smith", "01 666 555 222", "john@email.by");
        persons.add(p);
        p = new Person("Maxim Isaev", "+375 17 2222222", "stirlitz@center.ru");
        persons.add(p);
        for (int i = 0; i < 10; i++) {
            p = new Person("Captain Nemo", "no phone", "no mail");
            persons.add(p);
        }

    }

    private class PersonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<Person> list;

        public PersonAdapter(List<Person> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item, parent, false);
            return new PersonViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            PersonViewHolder personViewHolder = (PersonViewHolder)holder;
            personViewHolder.nameView.setText(list.get(position).getName());
            personViewHolder.phoneView.setText(list.get(position).getPhone());
            personViewHolder.emailView.setText(list.get(position).getEmail());

        }

        @Override
        public int getItemCount() {
            if(list != null) return list.size();
            return 0;
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
