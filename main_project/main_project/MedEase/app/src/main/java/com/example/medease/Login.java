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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements  JsonResponse{
    EditText e1,e2;

    Button b1,b2;

    SharedPreferences sh;

    public static String username,password,loginid,usertype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        startService(new Intent(getApplicationContext(),LocationService.class));

        e1=(EditText) findViewById(R.id.editTextText3);
        e2=(EditText) findViewById(R.id.editTextText4);
        b1=(Button) findViewById(R.id.button3);
        b2=(Button) findViewById(R.id.singup);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=e1.getText().toString();
                password=e2.getText().toString();

                if (username.equalsIgnoreCase("")){
                    e1.setError("Enter the username");
                    e1.setFocusable(true);
                } else if (password.equalsIgnoreCase("")) {
                    e2.setFocusable(true);
                    e2.setError("Enter the password");
                }else {

                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Login.this;
                    String q = "/user_login?uname=" + username + "&pwd=" + password;
                    q = q.replace(" ", "%20");
                    JR.execute(q);
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Userregistration.class));
            }
        });

    }


    @Override
    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("method",status);

            if (status.equalsIgnoreCase("success")) {

                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");

                loginid = ja1.getJSONObject(0).getString("login_id");

                usertype = ja1.getJSONObject(0).getString("usertype");


                SharedPreferences.Editor e = sh.edit();
                e.putString("login_id", loginid);
                e.commit();


                if (usertype.equals("user")) {
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), User_Home.class));

                } else if (usertype.equals("caretaker")) {
                    Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Home.class));

                    
                } else {
                    Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Login.class));

                }
            }

            else
            {
                Toast.makeText(getApplicationContext(), "Login failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed ()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(), IpSetting.class);
        startActivity(b);

    }
}