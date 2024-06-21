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
import org.json.JSONObject;

public class User_send_request extends AppCompatActivity implements JsonResponse{
    EditText e1;
    Button b1;

    ListView l1;

    String  descriptions;
    String []  description,date,stat,value;

    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_send_request);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1=(ListView) findViewById(R.id.viewrequesthomecare);

        e1=(EditText) findViewById(R.id.editTextText12);


        b1=(Button) findViewById(R.id.button6);

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) User_send_request.this;
        String q = "/viewrequest_homecare?user_id=" + sh.getString("login_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptions=e1.getText().toString();
                if (descriptions.equalsIgnoreCase("")){
                    e1.setError("Enter the Enquiry");
                    e1.setFocusable(true);
                }else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) User_send_request.this;
                    String q = "/sendrequest_homecare?description=" + descriptions + "&user_id=" + sh.getString("login_id","") + "&homecare_id=" +  Viewmedicine.homecareid;
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

                    description=new String[ja.length()];
                    stat=new  String[ja.length()];
                    date=new String[ja.length()];
                    value=new String[ja.length()];

                    for (int i=0;i<ja.length();i++){
                        date[i]=ja.getJSONObject(i).getString("date");
                        description[i]=ja.getJSONObject(i).getString("description");
                        stat[i]=ja.getJSONObject(i).getString("status");



                        value[i]=" Description: "+ description[i]  + "\nStatus: "+ stat[i]  + "\n Date: " + date[i];


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
}