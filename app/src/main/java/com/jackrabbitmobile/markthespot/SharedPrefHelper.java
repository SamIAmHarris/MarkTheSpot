package com.jackrabbitmobile.markthespot;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamMyxer on 8/3/16.
 */

public class SharedPrefHelper {

    public static final String MARKER_PREFS = "marker_prefs";

    public static final String MARKERS_LIST = "markers_list";

    private static SharedPreferences getSettings() {
        return MTSApp.getInstance().getSharedPreferences(MARKER_PREFS, Context.MODE_PRIVATE);
    }

    public static void addMarkerToList(MarkerOptions options) {
        ArrayList<MarkerPref> markerPrefs;
        if(getMarkerList() != null) {
            markerPrefs = getMarkerList();
        } else {
            markerPrefs = new ArrayList<MarkerPref>();
        }
        markerPrefs.add(new MarkerPref(options.getTitle(),
                options.getPosition().latitude, options.getPosition().longitude));
        saveMarkerList(markerPrefs);
    }

    public static void saveMarkerList(ArrayList<MarkerPref> markerList) {
        SharedPreferences prefs = getSettings();
        SharedPreferences.Editor editor = prefs.edit();
        if (markerList == null || markerList.isEmpty()) {
            editor.putString(MARKERS_LIST, "");
        } else {
            editor.putString(MARKERS_LIST, new Gson().toJson(
                    markerList, new TypeToken<List<MarkerPref>>(){}.getType()));
        }
        editor.apply();
    }

    public static ArrayList<MarkerPref> getMarkerList() {
        SharedPreferences prefs = getSettings();
        ArrayList<MarkerPref> listMarkers;

        String savedValue = prefs.getString(MARKERS_LIST, "");
        if (savedValue.equals("")) {
            listMarkers = null;
        } else {
            listMarkers = new Gson().fromJson(
                    savedValue, new TypeToken<List<MarkerPref>>(){}.getType());
        }

        return listMarkers;
    }
}
