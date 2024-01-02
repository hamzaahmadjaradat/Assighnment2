package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button add;
    private Button change;
    private ListView tasksView;
    private ArrayList<Task> tasks = new ArrayList<>();
    private TextView f;
    String task = "";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponents();
        Intent getData = getIntent();
        if (getData != null) {
            String data = getData.getStringExtra("item");
            task = "";
            task = data;
        }
        if (task != null) {
            saveInSharedPrefrence(task);
            saveInSharedPrefrence(task);
            loadIN();
        }
    }

    public void setComponents() {
        add = findViewById(R.id.ADD);
        change = findViewById(R.id.change);
        tasksView = findViewById(R.id.TASKSVIEW);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        f = findViewById(R.id.WELCOME);
        loadIN();

    }


    public void btnAddOnClick(View view) {
        Intent intent = new Intent(this, addActivity.class);
        startActivity(intent);
    }

    public void saveInSharedPrefrence(String task) {

        Gson gson = new Gson();
        Task x = new Task(task, "due");
        tasks.add(x);
        String json = gson.toJson(tasks);
        editor.putString("DATA", json);
        editor.commit();
    }

    public void loadIN() {
        Gson gson = new Gson();
        try {
            String json = prefs.getString("DATA", null);
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            tasks = gson.fromJson(json, type);

        } catch (Exception e) {
            f.setText("no no");
        }
    }

    public void btnChangeOnClick(View view) {
        String str = "";
        for (int i = 0; i < tasks.size(); i++) {
            str += tasks.get(i).getTitle() + "\n";
        }
        f.setText(str);
    }

}