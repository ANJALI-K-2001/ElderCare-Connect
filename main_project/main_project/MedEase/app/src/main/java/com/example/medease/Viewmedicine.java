package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class Viewmedicine extends AppCompatActivity implements  JsonResponse{
    ListView l1;

    String []  log_id,name,lname,homecare_id,value;

    public  static String lid,homecareid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmedicine);

        l1=(ListView) findViewById(R.id.viewmedicine);



        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lid=log_id[position];
                homecareid=homecare_id[position];

                final CharSequence[] items = {"Chat","Send Request","Send Feedback","Cancel"};
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(  Viewmedicine.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Chat"))
                        {
                            Intent il=new Intent(getApplicationContext(), User_chat.class);
                            startActivity(il);
                        }
                        else if (items[item].equals("Send Request"))
                        {
                            Intent il=new Intent(getApplicationContext(),User_send_request.class);
                            startActivity(il);
                        } else if (items[item].equals("Send Feedback"))
                        {
                            Intent il=new Intent(getApplicationContext(),Send_Feedback.class);
                            startActivity(il);
                        }
                        else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Viewmedicine.this;
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
                    homecare_id=new String[ja.length()];
                    name=new String[ja.length()];
                    lname=new String[ja.length()];
                    value=new String[ja.length()];


                    for (int i=0;i<ja.length();i++){

                        log_id[i]=ja.getJSONObject(i).getString("login_id");
                        homecare_id[i]=ja.getJSONObject(i).getString("homecare_id");

                        name[i]=ja.getJSONObject(i).getString("fname");
                        lname[i]=ja.getJSONObject(i).getString("lname");


                        value[i]="* " + name[i] + " " + lname[i] ;

                    }

                    ArrayAdapter<String> ar=new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,value);
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
}