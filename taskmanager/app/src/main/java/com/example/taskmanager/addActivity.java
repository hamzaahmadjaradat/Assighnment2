package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addActivity extends AppCompatActivity {
    private Button add;
    private EditText taskEntry;
    private TextView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setCompenets();
    }

    public void setCompenets() {
        add = findViewById(R.id.btnADD);
        taskEntry = findViewById(R.id.taskEntry);
    }

    String item = "";

    public void btnADDOnClick(View view) {
        item = taskEntry.getText().toString();
        MainActivity.getter=item;
        finishAddActivity(view);
    }


    public void finishAddActivity(View view) {
        Intent finalIntent = new Intent(this,MainActivity.class);
        startActivity(finalIntent);
        finish();
    }


}