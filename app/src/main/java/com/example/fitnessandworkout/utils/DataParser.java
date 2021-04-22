package com.example.fitnessandworkout.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * REFERENCES
 * Get nearby place functions is referred from Coding Cafe Youtube channel
 * https://youtu.be/0QzKquJ4j8Y
 */
public class DataParser {
    private HashMap<String, String> getSingleNearbyPlace(JSONObject googlePlaceJSON) {
        HashMap<String, String> googlePlaceMap = new HashMap<> ();
        String NameOfPlace = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";

        /**
         * Find nearby places with Google location API
         * Filter nearby place details from Google JSON document
         */

        try {
            if (!googlePlaceJSON.isNull("name")) {
                NameOfPlace = googlePlaceJSON.getString("name");
            }
            if (!googlePlaceJSON.isNull("vicinity")) {
                vicinity = googlePlaceJSON.getString("vicinity");
            }
            // Shortcut for location details that have been parsed form JSON docs from Google
            latitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lat");
            longitude = googlePlaceJSON.getJSONObject("geometry").getJSONObject("location").getString("lng");
            reference = googlePlaceJSON.getString("reference");

            // Verify locations details
            googlePlaceMap.put("place_name", NameOfPlace);
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return googlePlaceMap;
    }

    // List out all nearby places
    private List<HashMap<String, String>> getAllNearbyPlaces(JSONArray jsonArray) {
        int counter = jsonArray.length();

        // Create arraylist to view all nearby locations
        List<HashMap<String, String>> NearbyPlacesList = new ArrayList<>();
        HashMap<String, String> NearbyPlaceMap = null;

        for (int i = 0; i < counter; i++) {
            try {
                NearbyPlaceMap = getSingleNearbyPlace((JSONObject) jsonArray.get(i));
                NearbyPlacesList.add(NearbyPlaceMap);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return NearbyPlacesList;
    }

    /**
     * Parse JSON data from Google location API
     * @param jSONdata
     * @return location details
     */
    public List<HashMap<String, String>> parse(String jSONdata)
    {
        JSONArray jsonArray = null;
        JSONObject jsonObject;

        try
        {
            jsonObject = new JSONObject(jSONdata);
            jsonArray = jsonObject.getJSONArray("results");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return getAllNearbyPlaces(jsonArray);
    }
}
