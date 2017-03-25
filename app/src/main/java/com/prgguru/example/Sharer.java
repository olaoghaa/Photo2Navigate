package com.prgguru.example;

import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Sharer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharer);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
             //   Toast.makeText(this,"share"+type,Toast.LENGTH_LONG).show(); // Handle single image being sent
                handleSendImage(intent); // Handle single image being sent

            }
        }
    }

    void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            String c=getRealPathFromURI(imageUri);
            Location imagePicked = readGeoTagImage(c);
           // Toast.makeText(this,"don't be zero= "+imagePicked.getLongitude(),Toast.LENGTH_LONG).show(); // Handle single image being sent
            ((Application)this.getApplication()).setGPS(imagePicked);
        }
    }


    public void sendToMap2(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        //EditText editText = (EditText) findViewById(R.id.edit_message);
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        // intent.putExtra("Location of image", c);
        startActivity(intent);
    }


    public String getRealPathFromURI(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        @SuppressWarnings("deprecation")
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public Location readGeoTagImage(String imagePath)
    {
        Location loc = new Location("");
        try {
            ExifInterface exif = new ExifInterface(imagePath);
            float [] latlong = new float[2] ;
            if(exif.getLatLong(latlong)){
                loc.setLatitude(latlong[0]);
                loc.setLongitude(latlong[1]);
            }
            String date = exif.getAttribute(ExifInterface.TAG_DATETIME);
            SimpleDateFormat fmt_Exif = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            loc.setTime(fmt_Exif.parse(date).getTime());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return loc;
    }
}
