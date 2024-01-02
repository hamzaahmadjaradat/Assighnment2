package com.example.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;


import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String DATA = "DATA";
    private static final String FLAG = "FLAG";
    private static final String TrueSign = new String(Character.toChars(0x2705));
    private static final String FalseSign = new String(Character.toChars(0x274E));
    private Button delete;
    private Button add;
    private Button clear;
    private ListView tasksView;
    private List<Task> tasksMain = new ArrayList<>();
    public static List<Task> tasks = new ArrayList<>();
    public static List<Task> tasksDelete = new ArrayList<>();
    public static String getter = "";
    private TextView note;
    private static TextView welcome;
    static String task = "";

    private ArrayAdapter<String> adapter;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static int IndexDelete=0;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setComponents();
        checkBefore();
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position,
                                    long id) {
                tasks = tasksMain;
                Intent intent = new Intent(MainActivity.this, changeActivity.class);
                String str = "" + parent.getItemAtPosition(position);
                intent.putExtra(DATA, str + "");
                startActivity(intent);
                finish();
            }
        };
        tasksView.setOnItemClickListener(itemClickListener);

        if (!tasks.isEmpty()) {
            getter = "";
            tasksMain.clear();
            saveInSharedPrefrence(tasks);
            tasksMain = loadIN();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < tasksMain.size(); i++) {
                if (tasksMain.get(i).getStatus().equals("done")) {

                    list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + TrueSign);
                    continue;
                }
                list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + FalseSign);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            tasksView.setAdapter(adapter);
            tasks.clear();
        }
        if (tasksDelete.size()!=0) {
            tasksDelete.remove(IndexDelete);
            getter = "";
            tasksMain.clear();
            saveInSharedPrefrence(tasksDelete);
            tasksMain = loadIN();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < tasksMain.size(); i++) {
                if (tasksMain.get(i).getStatus().equals("done")) {

                    list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + TrueSign);
                    continue;
                }
                list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + FalseSign);
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            tasksView.setAdapter(adapter);
            tasksDelete.clear();
        }
        if (getter != null || !getter.equals("")) {
            note.setText("");
            List<String> list = new ArrayList<>();
            Task x = new Task(getter.trim(), "due");
            boolean repetedTask = true;
            if (!tasksMain.isEmpty()) {
                for (int i = 0; i < tasksMain.size(); i++) {
                    if (tasksMain.get(i).getTask().equals(getter)) {
                        repetedTask = false;
                        for (int j = 0; j < tasksMain.size(); j++) {
                            if (tasksMain.get(i).getStatus().equals("done")) {
                                list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + TrueSign);
                                continue;
                            }
                            list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + FalseSign);
                        }
                        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                        tasksView.setAdapter(adapter);
                        note.setText("you added this before");
                        break;
                    }
                }
            }
            if (repetedTask) {
                note.setText("");
                if (!getter.equals("")) {
                    list.clear();
                    tasksMain.add(x);
                    saveInSharedPrefrence(tasksMain);
                    tasksMain = loadIN();
                    for (int i = 0; i < tasksMain.size(); i++) {
                        if (tasksMain.get(i).getStatus().equals("done")) {
                            list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + TrueSign);
                            continue;
                        }
                        list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + FalseSign);

                    }
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                    tasksView.setAdapter(adapter);
                    if (tasksMain == null) {
                        tasksMain = new ArrayList<>();
                    }
                }
            }
            getter = "";
        }
    }
    public void btnDeleteOnClick(View view){
        tasksDelete=tasksMain;
        Intent intent=new Intent(this,deleteActivity.class);
        startActivity(intent);
    }

    public void btnCLEARListOnClick(View view) {
        if (tasks != null) {
            tasks.clear();
        }
        if (tasksMain != null) {
            tasksMain.clear();
        }
        if (adapter != null) {
            adapter.clear();
        }
        if (editor != null) {
            editor.clear();
            editor.apply();
        }
        getter = "";
    }

    public void checkBefore() {
        flag = prefs.getBoolean(FLAG, false);
        if (flag) {
            if (loadIN() != null) {
                tasksMain = loadIN();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < tasksMain.size(); i++) {
                    if (tasksMain.get(i).getStatus().equals("done")) {
                        list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + TrueSign);
                        continue;
                    }
                    list.add(tasksMain.get(i).getTask() + "             " + tasksMain.get(i).getStatus() + "  " + FalseSign);
                }
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
                tasksView.setAdapter(adapter);
            }
        }
    }

    private List<Task> loadIN() {
        String jsonStr = prefs.getString(DATA, "");
        if (jsonStr != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Task>>() {
            }.getType();
            List<Task> list = gson.fromJson(jsonStr, type);
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    private void saveInSharedPrefrence(List list) {
        if (editor != null) {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(list);
            if (jsonStr != null) {
                editor.putString(DATA, jsonStr);
                editor.putBoolean(FLAG, true);
                editor.commit();
            }
        }
    }


    public void setComponents() {
        add = findViewById(R.id.ADD);
        tasksView = findViewById(R.id.TASKSVIEW);
        welcome = findViewById(R.id.WELCOME);
        note = findViewById(R.id.NOTE);
        clear = findViewById(R.id.CLEAR);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        delete=findViewById(R.id.DELETE);
    }


    public void btnAddOnClick(View view) {
        note.setText("");
        Intent intent = new Intent(this, addActivity.class);
        startActivity(intent);
    }
}