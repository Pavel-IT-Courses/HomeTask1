package com.gmail.class5new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    SwitchCompat switchButton;
    RecyclerView recyclerView;
    EditText editText;

    ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        switchButton = findViewById(R.id.switch_button);
        editText = findViewById(R.id.edit);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new TextAdapter(strings));

        button.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String t = editText.getText().toString();
        TextAdapter adapter = (TextAdapter)recyclerView.getAdapter();
        adapter.addItem(t);
    }

    private class TextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        ArrayList<String> texts;

        private void addItem(String s) {
            strings.add(s);
            notifyDataSetChanged();
        }

        public TextAdapter(ArrayList<String> texts) {
            this.texts = texts;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new TextViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((TextViewHolder)holder).tv.setText(texts.get(position));
        }

        @Override
        public int getItemCount() {
            return texts.size();
        }
    }

    private class TextViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
        }
    }
}
