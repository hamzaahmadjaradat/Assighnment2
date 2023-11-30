package com.example.taskmanager;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class changeActivity extends AppCompatActivity {
    ArrayList<String> list = new ArrayList<>();
    private Spinner spn;
    private Button finish;
    private TextView thankMessage;
    String changeTask = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        setComponent();

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (spn.getSelectedItem().equals("done")) {
                    thankMessage.setText("GREAT JOP, KEEP GOING "+" "+new String (Character.toChars(0x1F600)));
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }


    public void setComponent() {
        list.add("due");
        list.add("done");
        spn = findViewById(R.id.spinner);
        finish = findViewById(R.id.FINISH);
        thankMessage = findViewById(R.id.hello);
        Intent intent = getIntent();
        changeTask = intent.getStringExtra("DATA");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        spn.setAdapter(adapter);

    }


    public void btnFinishOnClick(View view) {
        String str[] = changeTask.split("             ");
        for (int i = 0; i < MainActivity.tasks.size(); i++) {
            if (MainActivity.tasks.get(i).getTask().trim().equals(str[0].trim())) {
                MainActivity.tasks.get(i).setStatus("" + spn.getSelectedItem());
                break;
            }
        }
        Intent finalIntent = new Intent(this, MainActivity.class);
        startActivity(finalIntent);
    }


}