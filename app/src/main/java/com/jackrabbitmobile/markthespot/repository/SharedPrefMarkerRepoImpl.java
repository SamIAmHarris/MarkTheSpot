package com.jackrabbitmobile.markthespot.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jackrabbitmobile.markthespot.MTSApp;
import com.jackrabbitmobile.markthespot.model.MarkerPref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SamMyxer on 8/3/16.
 */

public class SharedPrefMarkerRepoImpl implements MarkersRepository {

    private static final String MARKER_PREFS = "marker_prefs";

    private static final String MARKERS_LIST = "markers_list";

    private SharedPreferences getSettings() {
        return MTSApp.getInstance().getSharedPreferences(MARKER_PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public ArrayList<MarkerPref> getMarkersList() {
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

    @Override
    public void addNewMarker(LatLng latLng, String title) {
        ArrayList<MarkerPref> markerPrefs;
        if(getMarkersList() != null) {
            markerPrefs = getMarkersList();
        } else {
            markerPrefs = new ArrayList<MarkerPref>();
        }
        markerPrefs.add(new MarkerPref(title, latLng.latitude, latLng.longitude));
        saveMarkersList(markerPrefs);
    }

    @Override
    public void saveMarkersList(ArrayList<MarkerPref> markerList) {
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
}
