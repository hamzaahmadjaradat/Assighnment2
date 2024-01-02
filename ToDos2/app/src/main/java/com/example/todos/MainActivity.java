package com.example.todos;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private ListView Todos;
     private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue= Volley.newRequestQueue(this);
        Todos=findViewById(R.id.TODO);
        btn=findViewById(R.id.GET);

    }

    public void btnGetOnClick(View view){

        String url="https://jsonplaceholder.typicode.com/todos";
        JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> todos =new ArrayList<>();
                for(int i=0;i<20;i++){
                    try{
                        JSONObject obj = response.getJSONObject(i);
                        todos.add(obj.getString("title"));
                    }
                    catch (JSONException e){
                        Log.d("Volley error",e.toString());

                    }

                }
                ArrayAdapter<String> adapter=new ArrayAdapter<String> (MainActivity.this
                , android.R.layout.simple_list_item_1,todos);
                Todos.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);




    }
}