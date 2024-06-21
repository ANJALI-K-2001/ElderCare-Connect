package com.example.medease;

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

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

public class User_send_emergency_alert extends AppCompatActivity implements JsonResponse{
    EditText e1;
    Button b1;


    String  title;


    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_emergency_request);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());



        e1=(EditText) findViewById(R.id.editTett12);


        b1=(Button) findViewById(R.id.button6send);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=e1.getText().toString();
                if (title.equalsIgnoreCase("")){
                    e1.setError("Enter the Enquiry");
                    e1.setFocusable(true);
                }else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) User_send_emergency_alert.this;
                    String q = "/user_send_emergency_request?title=" + title + "&user_id=" + sh.getString("login_id","") ;
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

            }else {
                Toast.makeText(this, "No Messages", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}