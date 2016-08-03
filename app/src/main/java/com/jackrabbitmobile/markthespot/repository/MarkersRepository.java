package com.jackrabbitmobile.markthespot.repository;

import com.google.android.gms.maps.model.LatLng;
import com.jackrabbitmobile.markthespot.model.MarkerPref;

import java.util.ArrayList;

/**
 * Created by SamMyxer on 8/3/16.
 */

public interface MarkersRepository {

    ArrayList<MarkerPref> getMarkersList();

    void addNewMarker(LatLng latLng, String title);

    void saveMarkersList(ArrayList<MarkerPref> markerPrefs);
}
