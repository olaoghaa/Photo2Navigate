package com.prgguru.example;

import android.location.Location;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;

/**
 * Created by AODAN on 20/02/2017.
 */

public class Application extends MultiDexApplication {
    private Location imageLocation;

    public Location getGPS(){
        return imageLocation;
    }

    public void setGPS(Location imageLocation){
        this.imageLocation=imageLocation;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Parse SDK stuff goes here
    }
}
