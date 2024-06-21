package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Userregistration extends AppCompatActivity  implements  JsonResponse{
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    Button b1;

    RadioGroup r1;

    SharedPreferences sh;


    String fname,lname,place,phone,email,selectedOption,age,uname,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregistration);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        r1=(RadioGroup) findViewById(R.id.radio_group);


        r1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                selectedOption = radioButton.getText().toString();

            }
        });

        e1=(EditText) findViewById(R.id.editTextText5);
        e2=(EditText) findViewById(R.id.editTextText6);
        e3=(EditText) findViewById(R.id.editTextText7);
        e4=(EditText) findViewById(R.id.editTextText8);
        e5=(EditText) findViewById(R.id.editTextText9);
        e6=(EditText) findViewById(R.id.editTextTextphone);
        e7=(EditText) findViewById(R.id.editTextTextemail);
        e8=(EditText) findViewById(R.id.editTextTextage);
        b1=(Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname=e1.getText().toString();
                lname=e2.getText().toString();
                place=e3.getText().toString();
                uname=e4.getText().toString();
                password=e5.getText().toString();
                phone=e6.getText().toString();
                email=e7.getText().toString();
                age=e8.getText().toString();

                if (fname.equalsIgnoreCase("")){
                    e1.setFocusable(true);
                    e1.setError("enter the first name");
                } else if (lname.equalsIgnoreCase("")) {
                    e2.setError("enter the last name");
                    e2.setFocusable(true);

                } else if (place.equalsIgnoreCase("")) {
                    e3.setFocusable(true);
                    e3.setError("enter the place");

                } else if (uname.equalsIgnoreCase("")) {
                    e4.setError("enter the user name");
                    e4.setFocusable(true);

                } else if (password.equalsIgnoreCase("")) {
                    e5.setFocusable(true);
                    e5.setError("enter the password");

                }else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Userregistration.this;
                    String q = "/userreg?fname=" + fname + "&lname="  + lname + "&place=" + place  + "&username=" + uname + "&pass=" + password + "&phone=" + phone + "&email=" +email + "&age=" + age + "&care_id=" + sh.getString("login_id","") + "&gender=" + selectedOption ;
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
            Log.d("method","status");

            if (status.equalsIgnoreCase("success")){
                Toast.makeText(this, "Registration success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),Home.class));
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    public void onBackPressed ()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), Login.class);
        startActivity(b);
    }

}