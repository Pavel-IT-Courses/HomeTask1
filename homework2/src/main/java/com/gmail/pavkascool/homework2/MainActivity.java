package com.gmail.pavkascool.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gmail.pavkascool.homework2.contacts.ContactBookActivity;
import com.gmail.pavkascool.homework2.view.CustomizedViewActivity;
import com.gmail.pavkascool.homework2.web.WebBrowserActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonTel, buttonView, buttonWeb;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        buttonTel = findViewById(R.id.button_tel);
        buttonView = findViewById(R.id.button_view);
        buttonWeb = findViewById(R.id.button_web);

        buttonTel.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        buttonWeb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch(v.getId()) {
            case R.id.button_tel:
                intent = new Intent(this, ContactBookActivity.class);
                break;
            case R.id.button_view:
                intent = new Intent(this, CustomizedViewActivity.class);
                break;
            case R.id.button_web:
                intent = new Intent(this, WebBrowserActivity.class);
                intent.setData(Uri.parse("http://www.google.com"));
                break;
        }
        startActivity(intent);

    }
}
