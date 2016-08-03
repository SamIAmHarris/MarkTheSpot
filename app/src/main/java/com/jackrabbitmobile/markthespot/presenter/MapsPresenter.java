package com.jackrabbitmobile.markthespot.presenter;

import com.jackrabbitmobile.markthespot.view.MapsView;

/**
 * Created by SamMyxer on 8/3/16.
 */

public interface MapsPresenter {

    void setView(MapsView mapsView);

    void onMapReady();

    void newMarkerAdded();

    void loadMapDetails();

}
