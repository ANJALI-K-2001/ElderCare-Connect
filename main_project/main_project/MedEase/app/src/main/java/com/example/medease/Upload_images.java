package com.example.medease;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Upload_images extends AppCompatActivity implements JsonResponse{
    ImageButton img1;

    Button b1;
    SharedPreferences sh;

    public static String encodedImage = "", path = "";
    byte[] byteArray = null;

    private Uri mImageCaptureUri;

    final int CAMERA_PIC_REQUEST = 0, GALLERY_CODE = 201;

    String [] uploads_id,user_id,image,out,date_time,value;

    ListView l1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_images);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        img1 = (ImageButton) findViewById(R.id.imageButton);

        l1=(ListView) findViewById(R.id.viewtumorimages);

        b1=(Button) findViewById(R.id.button9);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) Upload_images.this;
        String q = "/viewuploadsimages?user_id=" + sh.getString("login_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectImageOption();}


        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAttach();
            }
        });


    }




    private void sendAttach() {

        try {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//	            String uid = sh.getString("uid", "");


//            Toast.makeText(getApplicationContext(), "BBB : "+byteArray, Toast.LENGTH_LONG).show();


//                String q = "http://" + IpSetting.ip + "/smart city/apis.php";
            String q = "http://" +sh.getString("ip","")+"/user_uploads_images";
            Log.d("qq",q);
//            Toast.makeText(getApplicationContext(), "qwerty"+q, Toast.LENGTH_LONG).show();





            Map<String, byte[]> aa = new HashMap<>();

            aa.put("image", byteArray);
            aa.put("user_id",sh.getString("login_id","").getBytes());

//            aa.put("post",post.getBytes());
//            aa.put("house",house.getBytes());

            FileUploadAsync fua = new FileUploadAsync(q);
            fua.json_response = (JsonResponse) Upload_images.this;
            fua.execute(aa);
        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Exception upload : " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method= jo.getString("method");

            Log.d("method",method);

            if (method.equalsIgnoreCase("uploadssuccess")){
                String status = jo.getString("status");
                if (status.equalsIgnoreCase("success")) {

                    Toast.makeText(this, "Upload Successfully....", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), Upload_images.class));
                }
            }
            else if (method.equalsIgnoreCase("viewuploads")){
                String status = jo.getString("status");
                Log.d("method", status);
                if (status.equalsIgnoreCase("success")){

                    JSONArray ja=(JSONArray) jo.getJSONArray("data");

                    uploads_id=new String[ja.length()];
                    user_id=new String[ja.length()];
                    image=new  String[ja.length()];
                    out=new String[ja.length()];
                    date_time=new String[ja.length()];
                    value=new String[ja.length()];

                    for (int i=0;i<ja.length();i++){
                        uploads_id[i]=ja.getJSONObject(i).getString("uploads_id");
                        user_id[i]=ja.getJSONObject(i).getString("user_id");
                        image[i]=ja.getJSONObject(i).getString("file");
                        out[i]=ja.getJSONObject(i).getString("out");
                        date_time[i]=ja.getJSONObject(i).getString("date_time");


                        value[i]=" OUT: "+ out[i]+ "\n IMAGE: " + image[i] + "\n DATE AND TIME: " +date_time[i] ;

                    }
//
//                    ArrayAdapter<String> ar =new ArrayAdapter<>(getApplication(), android.R.layout.simple_list_item_1,value);
//                    l1.setAdapter(ar);
                    Custimagethree ar = new Custimagethree(Upload_images.this,image,out,date_time);
                    l1.setAdapter(ar);

                }
            }else {
                Toast.makeText(this, "No Messages", Toast.LENGTH_SHORT).show();
            }


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


    private void selectImageOption() {
        /*Android 10+ gallery code
        android:requestLegacyExternalStorage="true"*/

        final CharSequence[] items = {"Capture Photo", "Choose from Gallery", "Cancel"};

        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(Upload_images.this);
        builder.setTitle("Take Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                    startActivityForResult(intent, CAMERA_PIC_REQUEST);

                } else if (items[item].equals("Choose from Gallery")) {
                    Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : " + mImageCaptureUri);
            //   CropingIMG();

            Uri uri = data.getData();
            Log.d("File Uri", "File Uri: " + uri.toString());
            // Get the path
            //String path = null;

            try {
//                path = FileUtils.getPath(this, uri);
                path=FileUtils.getPath(this,uri);
                Toast.makeText(getApplicationContext(), "path : " + path, Toast.LENGTH_LONG).show();
                Log.d("path",path);

                File fl = new File(path);
                int ln = (int) fl.length();

                InputStream inputStream = new FileInputStream(fl);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] b = new byte[ln];
                int bytesRead = 0;

                while ((bytesRead = inputStream.read(b)) != -1) {
                    bos.write(b, 0, bytesRead);
                }
                inputStream.close();
                byteArray = bos.toByteArray();
                Toast.makeText(getApplicationContext(), "byteArray : " + byteArray, Toast.LENGTH_LONG).show();


                Bitmap bit = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                img1.setImageBitmap(bit);

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == CAMERA_PIC_REQUEST && resultCode == Activity.RESULT_OK) {

            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                img1.setImageBitmap(thumbnail);
                byteArray = baos.toByteArray();

                String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
                encodedImage = str;
//                sendAttach1();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}