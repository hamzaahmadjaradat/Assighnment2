package com.example.assignment1;

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
    public void setCompenets(){
        add=findViewById(R.id.btnADD);
        taskEntry=findViewById(R.id.taskEntry);
        list=findViewById(R.id.LIST);
    }
   public void btnADDOnClick(View view){
       String item= taskEntry.getText().toString();
        Intent intnet =new Intent(this,MainActivity.class);
        intnet.putExtra("item",item);
        startActivity(intnet);
   }


}