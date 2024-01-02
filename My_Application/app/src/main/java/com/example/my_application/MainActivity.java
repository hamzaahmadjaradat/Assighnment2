package com.example.my_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView welcome;
    private Button btn;
    private TextView result;
    private Spinner spn;
    ArrayList<Book> array=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome=findViewById(R.id.text);
        btn=findViewById(R.id.button);
        spn=findViewById(R.id.spinner2);
        result=findViewById(R.id.textView);
        readingData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str1="";
                String str2="";

                str1=spn.getSelectedItem().toString();
                for(Book b:array){
                    if (b.getCategory().equals(str1)){
                        str2+=b.getName()+"\n";
                    }
                }
                result.setText(str2);
            }
        });


    }
    public void readingData(){
        Book book1=new Book("bookName","author","terror");
        Book book2=new Book("bookName","author","terror");
        Book book3=new Book("bookName","author","hello");
        Book book4=new Book("bookName","author","romantic");
        Book book5=new Book("bookName","author","romantic");
        Book book6=new Book("bookName","author","action");
        Book book7=new Book("bookName","author","action");
        Book book8=new Book("bookName","author","action");
        array.add(book1);
        array.add(book2);
        array.add(book3);
        array.add(book4);
        array.add(book5);
        array.add(book6);
        array.add(book7);
        array.add(book8);

    }
}