package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View_appointment_image extends AppCompatActivity implements  JsonResponse{

    String[]  image,uppod,out,value;

    ListView l1;

    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_appointment_image);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1=(ListView) findViewById(R.id.viewimages);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) View_appointment_image.this;
        String q = "/viewappointment_image?uppid=" ;
        q = q.replace(" ", "%20");
        JR.execute(q);


    }


    @Override
    public void response(JSONObject jo) {
        try {

            String status = jo.getString("status");
            String method= jo.getString("method");

            Log.d("method",method);

            if (method.equalsIgnoreCase("viewappointmentimages")){

                if (status.equalsIgnoreCase("success")){

                    JSONArray ja=(JSONArray) jo.getJSONArray("data");

                    uppod=new String[ja.length()];
                    image=new String[ja.length()];
                    out=new String[ja.length()];
                    value=new String[ja.length()];


                    for (int i=0;i< ja.length();i++){

                        uppod[i]=ja.getJSONObject(i).getString("uploads_id");
                        out[i]=ja.getJSONObject(i).getString("out");
                        image[i]=ja.getJSONObject(i).getString("file");

                        value[i]="IMAGE : " + out[i];

                    }
//                    ArrayAdapter<String> ar =new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,value);
//                    l1.setAdapter(ar);
                    Custimage ar=new Custimage(View_appointment_image.this,image);
                    l1.setAdapter(ar);

                }else {
                    Toast.makeText(this, "no messages", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}