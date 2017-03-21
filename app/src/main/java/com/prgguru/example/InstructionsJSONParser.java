package com.prgguru.example;

/**
 * Created by AODAN on 02/03/2017.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;
public class InstructionsJSONParser {
    /** Receives a JSONObject and returns a list of lists containing latitude and longitude */
    public String []  parse(JSONObject jObject){

        List<List<HashMap<String, String>>> routes = new ArrayList<List<HashMap<String,String>>>() ;
        JSONArray jRoutes = null;
        JSONArray jLegs = null;
        JSONArray jSteps = null;
        String jj []= null;
        try {

            jRoutes = jObject.getJSONArray("routes");

            /** Traversing all routes */
            for(int i=0;i<jRoutes.length();i++){
                jLegs = ( (JSONObject)jRoutes.get(i)).getJSONArray("legs");
                List path = new ArrayList<HashMap<String, String>>();

                /** Traversing all legs */
                for(int j=0;j<jLegs.length();j++){
                    jSteps = ( (JSONObject)jLegs.get(j)).getJSONArray("steps");
                    jj=new String[jSteps.length()];
                    /** Traversing all steps */
                    for(int k=0;k<jSteps.length();k++){
                        String polyline = "";
                        jj[k]= (String)(((JSONObject)jSteps.get(k)).get("html_instructions"));
                    }
                    routes.add(path);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }catch (Exception e){
        }

        return jj;
    }

}
