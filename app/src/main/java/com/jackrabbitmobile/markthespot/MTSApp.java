package com.jackrabbitmobile.markthespot;

import android.app.Application;

/**
 * Created by SamMyxer on 8/3/16.
 */

public class MTSApp extends Application {

    private static MTSApp appInstance;

    public MTSApp(){
        this.appInstance = this;
    }

    public static MTSApp getInstance(){
        return appInstance;
    }

}
