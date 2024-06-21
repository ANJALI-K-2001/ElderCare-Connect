package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class Viewdisease extends AppCompatActivity implements  JsonResponse {
    ListView l1;
    String[] user_id,fname,lname,place,phone,email,gender,age,value;

    SharedPreferences sh;
    public  static String userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdisease);

        l1=(ListView) findViewById(R.id.viewdiseases);


        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userid=user_id[position];
                final  CharSequence[] items ={"Add  Medicine","Cancel"};
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(Viewdisease.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Add  Medicine")){
                            Intent il=new Intent(getApplicationContext(), Addmedicine.class);
                            startActivity(il);

                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }
        });


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());




        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewdisease.this;
        String q = "/viewuser?caretaker_id=" +  sh.getString("login_id", "");
        q = q.replace(" ", "%20");
        JR.execute(q);



    }


    @Override
    public void response(JSONObject jo) {

        try {

            String method = jo.getString("method") ;
            String status = jo.getString("status");


            Log.d("method",method);

            if (method.equalsIgnoreCase("viewuser")){

                if (status.equalsIgnoreCase("success")){

                    JSONArray ja=(JSONArray) jo.getJSONArray("data");

                    user_id=new String[ja.length()];
                    fname=new String[ja.length()];
                    lname=new String[ja.length()];
                    place=new String[ja.length()];
                    phone=new String[ja.length()];
                    email=new String[ja.length()];
                    gender=new String[ja.length()];
                    age=new String[ja.length()];

                    value=new String[ja.length()];


                    for (int i=0;i<ja.length();i++){

                        user_id[i]=ja.getJSONObject(i).getString("user_id");
                        fname[i]=ja.getJSONObject(i).getString("fname");
                        lname[i]=ja.getJSONObject(i).getString("lname");
                        place[i]=ja.getJSONObject(i).getString("place");
                        phone[i]=ja.getJSONObject(i).getString("phone");
                        email[i]=ja.getJSONObject(i).getString("email");
                        gender[i]=ja.getJSONObject(i).getString("gender");
                        age[i]=ja.getJSONObject(i).getString("age");


                        value[i]="First Name :" + fname[i]  + "\nLast Name: " + lname[i] + "\nPlace: " + place[i] +  "\nPhone: " +  phone[i] + "\nEmail: " + email[i]  + "\nGender: " + gender[i] + "\nAge :" + age[i];

                    }

                    ArrayAdapter<String> ar=new ArrayAdapter<>(getApplication(),R.layout.cust_list,value);
                    l1.setAdapter(ar);

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
        Intent b=new Intent(getApplicationContext(), Home.class);
        startActivity(b);

    }



}