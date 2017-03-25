package com.prgguru.example;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;




import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.location.LocationListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener,
        LocationSource.OnLocationChangedListener, com.google.android.gms.location.LocationListener, View.OnClickListener,
        GoogleMap.OnMyLocationButtonClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback{
    private GoogleMap mMap;
    ArrayList<LatLng> markerPoints;
    UiSettings uiSettings;
    ListView love;
    private ListAdapter lastHope;

   // com.google.android.gms.location.LocationListener locationListener;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Location hope = ((Application) this.getApplication()).getGPS();
        double lat = hope.getLatitude();
        double log = hope.getLongitude();
        if(lat==0){
            Toast.makeText(this, "Error matey boy. SET YOUR GEOTAGS", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_main);
        }


        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

/*        LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(final Location location) {
                //your code here
            }
        };


        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                LOCATION_REFRESH_DISTANCE, mLocationListener);


*/
        // Getting reference to SupportMapFragment of the activity_main
        //SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);

        // Getting Map for the SupportMapFragment
        //mMap = fm.getMapAsync(mMap.);

        // Initializing





    }
        private String getDirectionsUrl (LatLng origin, LatLng dest){

            // Origin of route
            String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

            // Destination of route
            String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

            // Sensor enabled
            String sensor = "sensor=false";

            // Building the parameters to the web service
            String parameters = str_origin + "&" + str_dest + "&" + sensor;

            // Output format
            String output = "json";

            // Building the url to the web service

            String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

            return url;
            }
        /** A method to download json data from url */
        private String downloadUrl (String strUrl)throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strUrl);

                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();

                // Connecting to url
                urlConnection.connect();

                // Reading data from url
                iStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    }

                data = sb.toString();

                br.close();

                } catch (Exception e) {
                Log.d("Ecept wil downladig url", e.toString());
                } finally {
                iStream.close();
                urlConnection.disconnect();
                }
            return data;
            }

   /* @Override
    public void onLocationChanged(Location location) {

    }*/

    @Override
    public void onMapClick(LatLng latLng) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }


    // Fetches data from url passed
        private class DownloadTask extends AsyncTask<String, Void, String> {

                    // Downloading data in non-ui thread

            @Override


            protected String doInBackground(String... url) {

                // For storing data from web service
                String data = "";

                try {
                    // Fetching the data from web service
                    data = downloadUrl(url[0]);
                    } catch (Exception e) {
                    Log.d("Background Task", e.toString());
                    }
                return data;
                }


            // Executes in UI thread, after the execution of
            // doInBackground()
            @Override


            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                ParserTask parserTask = new ParserTask();
                ParserTask2 parserTask2 = new ParserTask2();


                // Invokes the thread for parsing the JSON data
                parserTask.execute(result);
                parserTask2.execute(result);

            }


        }

        /** A class to parse the Google Places in JSON format */

        private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

                    // Parsing the data in non-ui thread

            @Override


            protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

                JSONObject jObject;
                List<List<HashMap<String, String>>> routes = null;

                try {
                    jObject = new JSONObject(jsonData[0]);
                    DirectionsJSONParser parser = new DirectionsJSONParser();

                    // Starts parsing data
                    routes = parser.parse(jObject);
                    } catch (Exception e) {
                    e.printStackTrace();
                    }
                return routes;
                }




            // Executes in UI thread, after the parsing process
            @Override
            protected void onPostExecute(List<List<HashMap<String, String>>> result) {
                ArrayList<LatLng> points = null;
                PolylineOptions lineOptions = null;
                ArrayList<String> route= null;
                MarkerOptions markerOptions = new MarkerOptions();
               //Result is all the routes
                // Traversing through all the routes
                for(int i=0;i<result.size();i++){
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route. One singular route.
                    List<HashMap<String, String>> path = result.get(i);

                    // Fetching all the points in i-th route
                    for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);

                        /*String [] tt=new String [path.size()];
                        tt[j]= (point.get("steps"));
                        Log.d("Heya shake it", ""+tt[j]);*/
                        if(j==4){
                            Log.d("sdfsdfes", ""+result);
                        }
                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                        }

                    // Adding all the points in the route to LineOptions
                    lineOptions.addAll(points);
                    lineOptions.width(2);
                    lineOptions.color(Color.MAGENTA);
                    }

                // Drawing polyline in the Google Map for the i-th route
                mMap.addPolyline(lineOptions);
                }
            }



    //second parser for directions

    private class ParserTask2 extends AsyncTask<String, Integer, List<String>> {

        // Parsing the data in non-ui thread

        @Override

        protected List <String> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<String> directions = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                InstructionsJSONParser parser = new InstructionsJSONParser();

                // Starts parsing data
                directions = parser.parse(jObject);
                if(directions==null){
                    Log.d("Ggggg", "you want to ride my bicycle");
                }
                else{
                    Log.d("ddddddd", "well this is bad"+directions.get(2));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return directions;
        }


        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<String> directions) {
           /* for(int i=0;i< directions.length;i++) {
                Log.d("Ggggg", directions[i]);
            }*/

            if(directions!=null&&directions.size()>=1) {
                  // love = (ListView) findViewById(R.id.listy);
                   //ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_maps, directions);
                     //RelativeLayout item = (RelativeLayout)findViewById(R.id.other);
                     //View lover = getLayoutInflater().inflate(R.layout.activity_maps, null);
                    //item.addView(lover);
                   //love.setAdapter(adapter);
                //Toast.makeText(MapsActivity.this, "", Toast.LENGTH_SHORT).show();
                //Toast toast = new Toast(getApplicationContext());
               // Toast.makeText(getApplicationContext(), "Hello"+directions.get(0)+"/n"+directions.get(1), Toast.LENGTH_SHORT).show();
                Log.d("Noot broke yote", "High quality gifs"+directions.size());
            /*RelativeLayout lView= (RelativeLayout)findViewById(R.id.other);
            ListFragment listFragment= new ListFragment();
            ListAdapter listAdapter;
            listFragment.getListView();
            ListView listView= new ListView(getApplicationContext());*/
               /* love.addView(findViewById(R.id.map));
            }*/
                // Toast.makeText(getApplicationContext(), "You haven't picked Image"+directions,
                //       Toast.LENGTH_LONG).show();
              /*  ArrayAdapter<String> itemsAdapter =
                        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, directions);
                Spanned tt=Html.fromHtml(directions.get(1));
                ListView listView = (ListView) findViewById(R.id.listy);
                listView.setAdapter(itemsAdapter);
                TextView johhny= (TextView) findViewById(R.id.html);
                johhny.setText(tt);*/
                ListView listView = (ListView) findViewById(R.id.listy);

                UsersAdapter foo = new UsersAdapter(getApplicationContext(), directions);
                listView.setAdapter(foo);

            }




        }
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

         /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        }*/


    @Override
    public void onLocationChanged(Location location) {
        //TextView locationTv = (TextView) findViewById(R.id.latlongLocation);
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title("A"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //locationTv.setText("Current Latitude:" + latitude + ", Longitude:" + longitude);

    }



        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady (GoogleMap googleMap){
            mMap = googleMap;
            LatLng newbie= enableMyLocation();
           /* LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
           // Location location = locationManager.getLastKnownLocation(bestProvider);
            /*if (location != null) {
                onLocationChanged(location);
            }*/
            //locationManager.requestLocationUpdates(bestProvider, 20000, 0, (android.location.LocationListener) this);
            //Log.d("heta ttud", ""+location.getLongitude());
             //   LatLng newbie = new LatLng(location.getLatitude(), location.getLongitude());

            //Location yyy=getLastLocation();
            //Location currentlocation= LocationServices.FusedLocationApi.getLastLocation();
            Location hope = ((Application) this.getApplication()).getGPS();
            // Add a marker in Sydney and move the camera
            double lat = hope.getLatitude();
            double log = hope.getLongitude();
            LatLng picture = new LatLng(lat, log);
            LatLng sydney = new LatLng(-34, 151);
            LatLng nearSyd= new LatLng(-34, 148);
            //Log.d("Hello is it me", "Lat and Lang"+newbie);
            mMap.addMarker(new MarkerOptions().position(picture).title("Marker of picture"));
          //  mMap.addMarker(new MarkerOptions().position(newbie).title("Marker of last known location"));
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.addMarker(new MarkerOptions().position(nearSyd).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(picture));
            String url = getDirectionsUrl(newbie, picture);

            DownloadTask downloadTask = new DownloadTask();
            Log.d("Regarding it", ""+url);
            // Start downloading json data from Google Directions API
            downloadTask.execute(url);
            //mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            //setUpMap();
       // Location gg=mMap.getMyLocation();
        /*double lat1= gg.getLatitude();
        double log1=gg.getLongitude();
        LatLng picture1= new LatLng(lat1, log1);
        mMap.addMarker(new MarkerOptions().position(picture1).title("Marker in not Sydney"));*/


            markerPoints = new ArrayList<LatLng>();
            if (mMap != null) {
                Log.d("Help", "Are we human or are we dancers my signs are vital. My hands are cold. And i'm on my knees looking for the answer");

                // Enable MyLocation Button in the Map
               // mMap.setMyLocationEnabled(true);

                // Setting onclick event listener for the map
                /*mMap.setOnMapClickListener(new OnMapClickListener() {

                    @Override


                    public void onMapClick(LatLng point) {

                        // Already two locations
                        if (markerPoints.size() > 1) {
                            markerPoints.clear();
                            mMap.clear();
                        }

                        // Adding new item to the ArrayList
                        markerPoints.add(point);

                        // Creating MarkerOptions
                        MarkerOptions options = new MarkerOptions();

                        // Setting the position of the marker
                        options.position(point);

                        /**
                                             * For the start location, the color of marker is GREEN and
                                             * for the end location, the color of marker is RED.
                                             */
                  /*      if (markerPoints.size() == 1) {

                            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        } else if (markerPoints.size() == 2) {

                            options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                        }

                        // Add new marker to the Google Map Android API V2
                        mMap.addMarker(options);

                        // Checks, whether start and end locations are captured
                        if (markerPoints.size() >= 2) {
                            LatLng origin = markerPoints.get(0);
                            LatLng dest = markerPoints.get(1);

                            // Getting URL to the Google Directions API
                            String url = getDirectionsUrl(origin, dest);

                            DownloadTask downloadTask = new DownloadTask();

                            // Start downloading json data from Google Directions API
                            downloadTask.execute(url);
                        }
                    }


                });*/
            }




        }


/*    private void setUpMap() {
        // mMap.addMarker(new MarkerOsptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);

      /* PolylineOptions rectOptions = new PolylineOptions()
               .add(new LatLng(37.35, -122.0))
               .add(new LatLng(37.45, -122.0))  // North of the previous point, but at the same longitude
               .add(new LatLng(37.45, -122.2))  // Same latitude, and 30km to the west
               .add(new LatLng(37.35, -122.2))  // Same longitude, and 16km to the south
               .add(new LatLng(37.35, -122.0)); // Closes the polyline.

// Get back the mutable Polyline
       Polyline polyline = mMap.addPolyline(rectOptions);*/


    //}



    private LatLng enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);


            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(locationManager
                    .getBestProvider(criteria, false));
            LatLng newbie = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(newbie).title("Marker of last known location"));
            return newbie;


        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

}
