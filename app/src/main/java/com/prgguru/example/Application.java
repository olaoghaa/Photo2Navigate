package com.prgguru.example;

import android.location.Location;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

/**
 * Created by AODAN on 20/02/2017.
 */

public class Application extends MultiDexApplication {
    private Location imageLocation;
    private ArrayList<Location> imageLocations;

    public Location getGPS(){
        return imageLocation;
    }

    public void setGPS(Location imageLocation){
        this.imageLocation=imageLocation;
    }

    public ArrayList<Location> getGPSmulti(){
        return imageLocations;
    }

    public void setGPSmulti(ArrayList<Location> imageLocations){
        this.imageLocations=imageLocations;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Parse SDK stuff goes here
    }
}
