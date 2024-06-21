package com.example.medease;

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

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Viewtransportation extends AppCompatActivity implements  JsonResponse{
    ListView l1;

    String []  fullname,transportation_id,value;

    public  static String transportationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewtransportation);

        l1=(ListView) findViewById(R.id.viewtransportations);



        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                transportationid=transportation_id[position];

                final CharSequence[] items = {"Send Request","Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(  Viewtransportation.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Send Request")) {
                            Intent il = new Intent(getApplicationContext(), User_send_request_transportation.class);
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
        JR.json_response = (JsonResponse) Viewtransportation.this;
        String q = "/viewtransportation?";
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

                    fullname=new String[ja.length()];
                    transportation_id=new String[ja.length()];
                    value=new String[ja.length()];


                    for (int i=0;i<ja.length();i++){

                        transportation_id[i]=ja.getJSONObject(i).getString("transportation_id");
                        fullname[i]=ja.getJSONObject(i).getString("fullname");

                        value[i]="* " + fullname[i];

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