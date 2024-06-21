package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_emergency_alert extends AppCompatActivity implements JsonResponse{

    ListView l1;

    String[] emergency_alert_id,fname,lname,place,phone,title,date,time,stat,value;

    SharedPreferences sh;

    public  static String emergencyalertid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emergency_alert);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        l1=(ListView) findViewById(R.id.view_emergency);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                emergencyalertid=emergency_alert_id[position];
                final  CharSequence[] items ={"Update Status","Cancel"};
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(View_emergency_alert.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Update Status")){
                            JsonReq JR = new JsonReq();
                            JR.json_response = (JsonResponse) View_emergency_alert.this;
                            String q = "/update_viewemergency?emergency_alert_id=" +  View_emergency_alert.emergencyalertid;
                            q = q.replace(" ", "%20");
                            JR.execute(q);


                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();

            }
        });


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_emergency_alert.this;
        String q = "/viewemergency?caretaker_id=" +  sh.getString("login_id", "");
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

                    emergency_alert_id=new String[ja.length()];
                    fname=new String[ja.length()];
                    lname=new String[ja.length()];
                    place=new String[ja.length()];
                    phone=new String[ja.length()];
                    title=new String[ja.length()];
                    date=new String[ja.length()];
                    time=new String[ja.length()];
                    stat=new String[ja.length()];


                    value=new String[ja.length()];


                    for (int i=0;i<ja.length();i++){

                        emergency_alert_id[i]=ja.getJSONObject(i).getString("emergency_alert_id");
                        fname[i]=ja.getJSONObject(i).getString("fname");
                        lname[i]=ja.getJSONObject(i).getString("lname");
                        place[i]=ja.getJSONObject(i).getString("place");
                        phone[i]=ja.getJSONObject(i).getString("phone");
                        title[i]=ja.getJSONObject(i).getString("title");
                        date[i]=ja.getJSONObject(i).getString("date");
                        time[i]=ja.getJSONObject(i).getString("time");
                        stat[i]=ja.getJSONObject(i).getString("status");


                        value[i]="First Name :" + fname[i]  + "\nLast Name: " + lname[i] + "\nPlace: " + place[i] +  "\nPhone: " +  phone[i] + "\nTitle: " + title[i]  + "\nTime: " + time[i] + "\nStatus :" + stat[i];

                    }

                    ArrayAdapter<String> ar=new ArrayAdapter<>(getApplication(),R.layout.cust_list,value);
                    l1.setAdapter(ar);

                }

            } else if (method.equalsIgnoreCase("update")) {
                if (status.equalsIgnoreCase("success")){
                    Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),View_emergency_alert.class));

                }

                
            } else {

                Toast.makeText(this, "No messages", Toast.LENGTH_SHORT).show();

            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
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