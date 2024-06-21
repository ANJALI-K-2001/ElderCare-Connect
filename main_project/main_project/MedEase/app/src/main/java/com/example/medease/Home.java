package com.example.medease;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Home extends AppCompatActivity implements JsonResponse {
    ImageView b1, b2, b3, b4, b5, b6, b7;

    SharedPreferences sh;
    private Handler handler = new Handler();

    public static String place = "", address = "", lati = "", logi = "", rem, time,sh_name,sh_image;

    public static String[] start_date, end_date, time_mrg, time_aftn, time_nyt,mname,mimage;


    private static final long LOCATION_SERVICE_INTERVAL = 30000; // 30 seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Start the LocationService immediately
//        startService(new Intent(getApplicationContext(), LocationService.class));

        // Schedule the LocationService to be called every 10 seconds
        handler.postDelayed(locationServiceRunnable, LOCATION_SERVICE_INTERVAL);


        b1 = (ImageView) findViewById(R.id.viemergencyalert);
        b2 = (ImageView) findViewById(R.id.button8);
        b3 = (ImageView) findViewById(R.id.viewusers);
//        b4 = (ImageView) findViewById(R.id.button5);
//        b5 = (ImageView) findViewById(R.id.button6);
        b6 = (ImageView) findViewById(R.id.button11);
        b7 = (ImageView) findViewById(R.id.meddet);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), View_emergency_alert.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(),Login.class));
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("MedEase");
                builder.setMessage("Are you sure you want to Logout........");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), Login.class));
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
//                AlertDialog dialog= builder.create();
//                dialog.show();
                builder.show();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Viewdisease.class));
            }
        });
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Viewdoctors.class));
//            }
//        });
//        b5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Upload_images.class));
//            }
//        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Userregistration.class));
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Viewsymptoms.class));
            }
        });


    }

    private Runnable locationServiceRunnable = new Runnable() {
        @Override
        public void run() {
//            startService(new Intent(getApplicationContext(), LocationService.class));

            JsonReq JR = new JsonReq();
            JR.json_response = (JsonResponse) Home.this;
            String q = "/notification?log_id=" + sh.getString("login_id", "");
            q = q.replace(" ", "%20");
            JR.execute(q);

            // Schedule the next execution after the specified interval
            handler.postDelayed(this, LOCATION_SERVICE_INTERVAL);
        }
    };


    @Override
    protected void onDestroy() {
        // Remove any callbacks to prevent memory leaks
        handler.removeCallbacks(locationServiceRunnable);
        super.onDestroy();
    }


    private String getCurrentTime() {
        // Get current date and time
        Calendar calendar = Calendar.getInstance();
        Date today = calendar.getTime();

        // Format the time as needed
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = timeFormat.format(today);

        return formattedTime;
    }


    public void response(JSONObject jo) {
        try {
            String status = jo.getString("status");
            Log.d("method", status);

            if (status.equalsIgnoreCase("success")) {

                String currenttime = getCurrentTime();

//                Toast.makeText(getApplicationContext(), "currenttime : " + currenttime, Toast.LENGTH_SHORT).show();


                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");


                start_date = new String[ja1.length()];
                end_date = new String[ja1.length()];
                time_mrg = new String[ja1.length()];
                time_aftn = new String[ja1.length()];
                time_nyt = new String[ja1.length()];
                mname = new String[ja1.length()];
                mimage = new String[ja1.length()];

//				Toast.makeText(getApplicationContext(),"fff : ",Toast.LENGTH_SHORT).show();


                for (int i = 0; i < ja1.length(); i++) {

                    start_date[i] = ja1.getJSONObject(i).getString("start_date");
                    end_date[i] = ja1.getJSONObject(i).getString("end_date");
                    time_mrg[i] = ja1.getJSONObject(i).getString("time_mrg");
                    time_aftn[i] = ja1.getJSONObject(i).getString("time_aftn");
                    time_nyt[i] = ja1.getJSONObject(i).getString("time_nyt");
                    mname[i] = ja1.getJSONObject(i).getString("name");
                    mimage[i] = ja1.getJSONObject(i).getString("image");


                    if (time_mrg[i].equalsIgnoreCase(currenttime) || time_aftn[i].equalsIgnoreCase(currenttime) || time_nyt[i].equalsIgnoreCase(currenttime)) {


                        SharedPreferences.Editor e = sh.edit();
                        e.putString("mname", mname[i]);
                        e.putString("mimage", mimage[i]);
                        e.commit();


                        Toast.makeText(getApplicationContext(), "It's time to take your medicine. Please remember to do so now.", Toast.LENGTH_SHORT).show();
                        startService(new Intent(getApplicationContext(), SocialDistanceAlert.class));
//                        showFullScreenNotification();

                    }


                }

            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }


    }


    public void onBackPressed ()
    {
        // TODO Auto-generated method stub
        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Home.class);
        startActivity(b);
        Toast.makeText(this, "please Logout........", Toast.LENGTH_SHORT).show();
    }




}