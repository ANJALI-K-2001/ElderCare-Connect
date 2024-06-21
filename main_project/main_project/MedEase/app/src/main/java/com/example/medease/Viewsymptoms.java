package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewsymptoms extends AppCompatActivity implements JsonResponse {
    ListView l1;

    String []  log_id,name,value;

    public  static String lid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsymptoms);

        l1=(ListView) findViewById(R.id.viewsymptoms);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewsymptoms.this;
        String q = "/viewhomecare?";
        q = q.replace(" ", "%20");
        JR.execute(q);


    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method = jo.getString("method") ;
            String status = jo.getString("status");


            Log.d("method",method);

            if (method.equalsIgnoreCase("view")){

                if (status.equalsIgnoreCase("success")){

                    JSONArray ja=(JSONArray) jo.getJSONArray("data");

                    log_id=new String[ja.length()];
                    name=new String[ja.length()];
                    value=new String[ja.length()];


                    for (int i=0;i<ja.length();i++){

                        log_id[i]=ja.getJSONObject(i).getString("login_id");
                        name[i]=ja.getJSONObject(i).getString("fname");

                        value[i]="* " + name[i];

                    }

                    ArrayAdapter<String> ar=new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,value);
                    l1.setAdapter(ar);

                    l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            lid=log_id[position];

                            startActivity(new Intent(getApplicationContext(),Chat.class));
                        }
                    });

                }

            }
            else {

                Toast.makeText(this, "No messages", Toast.LENGTH_SHORT).show();

            }


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public void onBackPressed ()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), Viewdisease.class);
        startActivity(b);

    }
}