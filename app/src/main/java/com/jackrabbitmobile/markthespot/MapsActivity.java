package com.jackrabbitmobile.markthespot;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * onMapReady: Manipulates the map once available.
 * This callback is triggered when the map is ready to be used.
 * If Google Play services is not installed on the device, the user will be prompted to install
 * it inside the SupportMapFragment. This method will only be triggered once the user has
 * installed Google Play services and returned to the app.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Button markTheSpotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        markTheSpotButton = (Button) findViewById(R.id.activity_maps_mark_spot_button);
        mapFragment.getMapAsync(this);

        markTheSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(googleMap != null) {
                    saveNewMarker(MapHelper.getMarkerInCenterOfMap(googleMap));
                    addAllMarkersToMap();
                }
            }
        });
    }

    private void addAllMarkersToMap() {
        MapHelper.addAllMarkersToMap(googleMap, SharedPrefHelper.getMarkerList());
    }

    private void saveNewMarker(MarkerOptions markerOptions) {
        SharedPrefHelper.addMarkerToList(markerOptions);

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        addAllMarkersToMap();
    }
}
