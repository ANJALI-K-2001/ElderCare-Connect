package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Send_Feedback extends AppCompatActivity implements JsonResponse{

    EditText e1;

    Button b1;

    String feedback;

    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        e1=(EditText) findViewById(R.id.editTextText13);
        b1=(Button) findViewById(R.id.button14);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback=e1.getText().toString();


                if (feedback.equalsIgnoreCase("")){
                    e1.setFocusable(true);
                    e1.setError("enter the date");
                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Send_Feedback.this;
                    String q = "/user_send_feedback?user_id=" + sh.getString("login_id","")  + "&feedback=" + feedback +  "&receiver_id=" + Viewmedicine.homecareid;
                    q = q.replace(" ", "%20");
                    JR.execute(q);

                }

            }


        });



    }

    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");

            if (status.equalsIgnoreCase("success")){
                Toast.makeText(this, "Add successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),User_Home.class));
            }

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }


    }
}