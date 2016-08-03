package com.jackrabbitmobile.markthespot;

import android.text.TextUtils;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by SamMyxer on 8/3/16.
 */

public class MapHelper {

    public static void addAllMarkersToMap(GoogleMap googleMap, ArrayList<MarkerPref> markerPrefs) {
        if(markerPrefs != null) {
            for (MarkerPref markerPref : markerPrefs) {
                LatLng latLng = new LatLng(markerPref.getLatitude(), markerPref.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                String title = TextUtils.isEmpty(markerPref.getTitle()) ? "Nameless Marker" : markerPref.getTitle();
                markerOptions.title(title);
                googleMap.addMarker(markerOptions);
            }
        }
    }

    public static MarkerOptions getMarkerInCenterOfMap(GoogleMap googleMap) {
        return new MarkerOptions().position(googleMap.getCameraPosition().target);
    }
}
