package com.gmail.class32;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    androidx.recyclerview.widget.RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<100; i++) {
            list.add(i);
        }

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new NumbersAdapter(list));
    }

    private class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder> {
        private List<Integer> items;

        public NumbersAdapter(List<Integer> items) {
            super();
            this.items = items;
        }

        @NonNull
        @Override
        public NumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            return new NumbersViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NumbersViewHolder holder, int position) {
            holder.textView.setText(String.valueOf(position));
        }

        @Override
        public int getItemCount() {
            if (items != null) return items.size();
            return 0;
        }

        private class NumbersViewHolder extends RecyclerView.ViewHolder {

            private LinearLayout layout;
            private ImageView imageView;
            private TextView textView;

            public NumbersViewHolder(@NonNull View itemView) {
                super(itemView);
                layout = itemView.findViewById(R.id.layout);
                imageView = layout.findViewById(R.id.image);
                textView = layout.findViewById(R.id.text);
            }
        }
    }

}
