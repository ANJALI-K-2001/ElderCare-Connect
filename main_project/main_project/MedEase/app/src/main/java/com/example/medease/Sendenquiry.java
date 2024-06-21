package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Stack;

import kotlinx.coroutines.Job;

public class Sendenquiry extends AppCompatActivity implements  JsonResponse{
    EditText e1,e2;
    Button b1;

    ListView l1;

    String titles,descriptions;
    String []  title,description,reply,date,value;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendenquiry);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1=(ListView) findViewById(R.id.viewenquiry);

        e1=(EditText) findViewById(R.id.editTextText);
        e2=(EditText) findViewById(R.id.description);

        b1=(Button) findViewById(R.id.button2);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Sendenquiry.this;
        String q = "/viewenquiry?user_id=" + sh.getString("login_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titles=e1.getText().toString();
                descriptions=e2.getText().toString();
                if (titles.equalsIgnoreCase("")){
                    e1.setError("Enter the Enquiry");
                    e1.setFocusable(true);
                }else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Sendenquiry.this;
                    String q = "/sendenquiry?title=" + titles + "&user_id=" + sh.getString("login_id","") + "&description=" + descriptions;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }

            }
        });
    }

    @Override
    public void response(JSONObject jo) {
        try {


            String method= jo.getString("method");

            Log.d("method",method);

            if (method.equalsIgnoreCase("sendenquiry")){
                 String status = jo.getString("status");
                if (status.equalsIgnoreCase("success")) {

                    Toast.makeText(this, "Send Successfully....", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), User_Home.class));
                }
            }
            else if (method.equalsIgnoreCase("viewenquiry")){
                    String status = jo.getString("status");
                    Log.d("method", status);
                    if (status.equalsIgnoreCase("success")){

                        JSONArray ja=(JSONArray) jo.getJSONArray("data");

                        title=new String[ja.length()];
                        description=new String[ja.length()];
                        reply=new  String[ja.length()];
                        date=new String[ja.length()];
                        value=new String[ja.length()];

                        for (int i=0;i<ja.length();i++){
                            title[i]=ja.getJSONObject(i).getString("title");
                            description[i]=ja.getJSONObject(i).getString("description");
                            reply[i]=ja.getJSONObject(i).getString("reply");
                            date[i]=ja.getJSONObject(i).getString("date");


                            value[i]=" Titles: "+ title[i]  + "\nDescription: "+ description[i]    + "\n Reply: " + reply[i] + "\n Date: " + date[i];


                        }

                        ArrayAdapter<String> ar =new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,value);
                        l1.setAdapter(ar);

                    }
                }else {
                    Toast.makeText(this, "No Messages", Toast.LENGTH_SHORT).show();
                }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public void onBackPressed ()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), Home.class);
        startActivity(b);
    }


}