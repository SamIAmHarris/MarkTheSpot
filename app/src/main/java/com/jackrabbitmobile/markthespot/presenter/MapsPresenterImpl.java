package com.jackrabbitmobile.markthespot.presenter;

import com.jackrabbitmobile.markthespot.model.MarkerPref;
import com.jackrabbitmobile.markthespot.repository.MarkersRepository;
import com.jackrabbitmobile.markthespot.view.MapsView;

import java.util.ArrayList;

/**
 * Created by SamMyxer on 8/3/16.
 */

public class MapsPresenterImpl implements MapsPresenter {

    private MapsView mapsView;
    private MarkersRepository markersRepository;

    public MapsPresenterImpl(MarkersRepository markersRepository) {
        this.markersRepository = markersRepository;
    }

    @Override
    public void setView(MapsView mapsView) {
        this.mapsView = mapsView;
    }

    @Override
    public void onMapReady() {
        loadMapDetails();
    }

    @Override
    public void newMarkerAdded() {
        markersRepository.addNewMarker(mapsView.getLatLng(), mapsView.getMarkerName());
        mapsView.updateMapWithMarkers(markersRepository.getMarkersList());
    }

    @Override
    public void loadMapDetails() {
        ArrayList<MarkerPref> markerPrefs = markersRepository.getMarkersList();

        if(markerPrefs == null || markerPrefs.isEmpty()) {
            mapsView.showNoMarkerMessage();
        } else {
            mapsView.updateMapWithMarkers(markerPrefs);
        }
    }
}
