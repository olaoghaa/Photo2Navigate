package com.prgguru.example;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

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
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapClickListener,
        LocationSource.OnLocationChangedListener, com.google.android.gms.location.LocationListener, View.OnClickListener {
    private GoogleMap mMap;
    ArrayList<LatLng> markerPoints;
    UiSettings uiSettings;
   // com.google.android.gms.location.LocationListener locationListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

                // Invokes the thread for parsing the JSON data
                parserTask.execute(result);
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
                        String r = point.get("html_instructions");
                        Log.d("point here", "Here it is: "+r);
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

           /* @Override
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

            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String bestProvider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(bestProvider);
           /* if (location != null) {
                onLocationChanged(location);
            }*/
           // locationManager.requestLocationUpdates(bestProvider, 20000, 0, (android.location.LocationListener) this);
            Log.d("heta ttud", ""+location.getLongitude());
                LatLng newbie = new LatLng(location.getLatitude(), location.getLongitude());


            //Location currentlocation= LocationServices.FusedLocationApi.getLastLocation();
            Location hope = ((Application) this.getApplication()).getGPS();
            // Add a marker in Sydney and move the camera
            double lat = hope.getLatitude();
            double log = hope.getLongitude();
            LatLng picture = new LatLng(lat, log);
            LatLng sydney = new LatLng(-34, 151);
            LatLng nearSyd= new LatLng(-34, 148);
            Log.d("Hello is it me", "Lat and Lang"+newbie);
            mMap.addMarker(new MarkerOptions().position(picture).title("Marker in Sydney"));
            mMap.addMarker(new MarkerOptions().position(newbie).title("Marker not in Sydney"));
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
       /* Location gg=mMap.getMyLocation();
        double lat1= gg.getLatitude();
        double log1=gg.getLongitude();
        LatLng picture1= new LatLng(lat1, log1);
        mMap.addMarker(new MarkerOptions().position(picture1).title("Marker in not Sydney"));*/


            markerPoints = new ArrayList<LatLng>();
            if (mMap != null) {
                Log.d("Help", "Are we human or are we dancers my signs are vital. My hands are cold. And i'm on my knees looking for the answer");

                // Enable MyLocation Button in the Map
                mMap.setMyLocationEnabled(true);

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


    private void setUpMap() {
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


    }
}
