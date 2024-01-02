package com.example.sharedprefrence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "Name";
    public static final String PASS = "PASS";
    public static final String Flag = "FLAG";
    private CheckBox checkbox;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private EditText Name;
    private EditText password;
    private Button save;
    private boolean flag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpCompenants();
        checkBefore();
    }

    public void setUpCompenants() {
        Name = findViewById(R.id.name);
        password = findViewById(R.id.pass);
        checkbox = findViewById(R.id.checkBox);
        save = findViewById(R.id.button);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    public void checkBefore() {
            flag = prefs.getBoolean(Flag, false);
            if (flag) {
                String name = prefs.getString(NAME, "");
                String Password = prefs.getString(PASS, "");
                Name.setText(name);
                password.setText(Password);
                checkbox.setChecked(true);
            }
        }


    public void btnSetOnClick(View view) {
        String name = Name.getText().toString();
        String pass = password.getText().toString();
        if (checkbox.isChecked()) {
            if (flag) {
                editor.putString(NAME, name);
                editor.putString(PASS, pass);
                editor.putBoolean(Flag, true);
                editor.commit();
            }
        }
    }
}