package com.jackrabbitmobile.markthespot.view;

import com.google.android.gms.maps.model.LatLng;
import com.jackrabbitmobile.markthespot.model.MarkerPref;

import java.util.ArrayList;

/**
 * Created by SamMyxer on 8/3/16.
 */

public interface MapsView {

    LatLng getLatLng();

    void updateMapWithMarkers(ArrayList<MarkerPref> markerPrefs);

    void showNoMarkerMessage();

    String getMarkerName();

}
