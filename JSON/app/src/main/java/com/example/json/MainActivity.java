package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    public static final String DATA="DATA";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Button save;
    private Button load;
    private TextView data;
    private ArrayList<BOOK> x;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCompenants();
    }

    public void setCompenants(){
        load=findViewById(R.id.LOAD);
        save=findViewById(R.id.SAVE);
        data=findViewById(R.id.DATA);
        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        editor=prefs.edit();
    }
    public void btnSAVEOnClick(View view){
        BOOK[] books=new BOOK[4];
        books[0]=new BOOK("health","hamza jaradat");
        books[1]=new BOOK("art","muath jaradat");
        books[2]=new BOOK("computer","waad jaradat");
        books[3]=new BOOK("civil engeniring","bashar jaradat");
        Gson gson =new Gson();
        String booksData=gson.toJson(books);
        editor.putString(DATA,booksData);
        editor.commit();
        data.setText(booksData);

    }
    public void btnLOADOnClick(View view){
        Gson gson=new Gson();
        String str=prefs.getString(DATA,"");
        if(!str.isEmpty()){
            BOOK[] book=gson.fromJson(str,BOOK[].class);
            data.setText("number of books -------> "+book.length);

        }else{data.setText("not found");}

    }



}