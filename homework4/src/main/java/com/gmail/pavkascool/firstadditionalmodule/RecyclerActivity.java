package com.gmail.pavkascool.firstadditionalmodule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RecyclerActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private RecyclerView recyclerView;
    private SwitchCompat switchCompat;
    private EditText editText;
    private Button button;
    private TextAdapter textAdapter;

    //private List<String> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        editText = findViewById(R.id.edit);
        switchCompat = findViewById(R.id.switchcompat);
        switchCompat.setOnCheckedChangeListener(this);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textAdapter = new TextAdapter();
        recyclerView.setAdapter(textAdapter);


    }

    private class TextAdapter extends RecyclerView.Adapter<RecyclerActivity.TextHolder> {

        public TextAdapter() {
            data = new ArrayList<String>();
        }

        private List<String> data;

        public void addItem(String s) {
            data.add(s);
            notifyDataSetChanged();
        }

        public void deleteItem(String s) {
            data.remove(s);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new TextHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TextHolder holder, int position) {
            holder.textView.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class TextHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public TextHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onClick(View v) {
        String line = editText.getText().toString();
        textAdapter.addItem(line);
        editText.setText("");
    }
}
