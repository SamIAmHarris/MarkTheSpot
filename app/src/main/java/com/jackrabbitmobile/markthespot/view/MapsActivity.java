package com.jackrabbitmobile.markthespot.view;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.jackrabbitmobile.markthespot.model.MarkerPref;
import com.jackrabbitmobile.markthespot.R;
import com.jackrabbitmobile.markthespot.presenter.MapsPresenter;
import com.jackrabbitmobile.markthespot.presenter.MapsPresenterImpl;
import com.jackrabbitmobile.markthespot.repository.SharedPrefMarkerRepoImpl;

import java.util.ArrayList;

/**
 * onMapReady: Manipulates the map once available.
 * This callback is triggered when the map is ready to be used.
 * If Google Play services is not installed on the device, the user will be prompted to install
 * it inside the SupportMapFragment. This method will only be triggered once the user has
 * installed Google Play services and returned to the app.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, MapsView {

    private GoogleMap googleMap;
    private MapsPresenter mapsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        initializeView();

        initializePresenter();
    }

    private void initializePresenter() {
        mapsPresenter = new MapsPresenterImpl(new SharedPrefMarkerRepoImpl());
        mapsPresenter.setView(this);
    }

    private void initializeView() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Button markTheSpotButton = (Button) findViewById(R.id.activity_maps_mark_spot_button);
        mapFragment.getMapAsync(this);

        markTheSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(googleMap != null) {
                    mapsPresenter.newMarkerAdded();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        mapsPresenter.onMapReady();
    }

    @Override
    public LatLng getLatLng() {
        return GoogleMapHelper.getCenterOfMap(googleMap);
    }

    @Override
    public void updateMapWithMarkers(ArrayList<MarkerPref> markersList) {
        GoogleMapHelper.addAllMarkersToMap(googleMap, markersList);
    }

    @Override
    public void showNoMarkerMessage() {
        Toast toast = Toast.makeText(this,
                "Looks like you have no markers. Hit the \"Mark The Spot\" button below to add one",
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public String getMarkerName() {
        return "A Marker has no name";
    }

}
