package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class deleteActivity extends AppCompatActivity {
    private Button delete;
    private Spinner spn;
    private TextView note;
    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        setComponents();

    }

    private void setComponents() {
        delete = findViewById(R.id.finishDelete);
        spn = findViewById(R.id.spinnerDelete);
        note=findViewById(R.id.note);
        for (int i = 0; i < MainActivity.tasksDelete.size(); i++) {
            list.add(MainActivity.tasksDelete.get(i).getTask());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        spn.setAdapter(adapter);
    }

    public void btnFinishOnClick(View view) {
        String item="";
        if (spn.getAdapter().getCount() > 0) {
             item = spn.getSelectedItem().toString();
        }

        if (!MainActivity.tasksDelete.isEmpty()) {
            for (int i = 0; i < MainActivity.tasksDelete.size(); i++) {
                if (MainActivity.tasksDelete.get(i).getTask().equals(item)) {
                    MainActivity.IndexDelete = i;
                }
            }
        }
        else{
            note.setText("there are no TASKS!! make sure to add");

        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}