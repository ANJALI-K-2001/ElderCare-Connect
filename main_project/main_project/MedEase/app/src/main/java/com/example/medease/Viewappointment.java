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

public class Viewappointment extends AppCompatActivity implements  JsonResponse{

    ListView l1;
    SharedPreferences sh;

     String[] stts,date,appid,amount,reason,time,value;

    public  static String  app_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewappointment);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1=(ListView) findViewById(R.id.viewappointment);

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                app_id=appid[position];

                final CharSequence[] items = {"Make Payment","View Prescription","Cancel"};
                android.app.AlertDialog.Builder builder = new AlertDialog.Builder(Viewappointment.this);
                builder.setTitle("Select Option!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Make Payment"))
                        {
                            Intent il=new Intent(getApplicationContext(), Payment.class);
                            startActivity(il);
                        }
                        else if (items[item].equals("View Prescription"))
                        {
                            Intent il=new Intent(getApplicationContext(),View_prescription.class);
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
        JR.json_response = (JsonResponse) Viewappointment.this;
        String q = "/viewappointment?user_id=" + sh.getString("login_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            String method= jo.getString("method");

            Log.d("method",method);

            if (method.equalsIgnoreCase("viewappointment")){

                if (status.equalsIgnoreCase("success")){

                    JSONArray ja=(JSONArray) jo.getJSONArray("data");

                    appid=new String[ja.length()];
                    reason=new String[ja.length()];
                    amount=new String[ja.length()];
                    time=new String[ja.length()];
                    stts=new String[ja.length()];
                    date=new String[ja.length()];
                    value=new String[ja.length()];



                    for (int i=0;i< ja.length();i++){
                        appid[i]=ja.getJSONObject(i).getString("appointment_id");
                        reason[i]=ja.getJSONObject(i).getString("reason");
                        time[i]=ja.getJSONObject(i).getString("time");
                        amount[i]=ja.getJSONObject(i).getString("amount");
                        stts[i]=ja.getJSONObject(i).getString("status");
                        date[i]=ja.getJSONObject(i).getString("date");

                        value[i]="Reason" +  reason[i] +  "\nSTATUS: " + stts[i] + "\n  DATE: " + date[i];
                    }
                    ArrayAdapter<String> ar =new ArrayAdapter<>(getApplication(), R.layout.custlistviewapp,value);
                    l1.setAdapter(ar);

                }else {
                    Toast.makeText(this, "no messages", Toast.LENGTH_SHORT).show();
                }
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