package com.jackrabbitmobile.markthespot;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SamMyxer on 8/3/16.
 */

public class MarkerPref implements Parcelable {

    private String title;
    private double latitude;
    private double longitude;

    public MarkerPref(String title, double latitude, double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected MarkerPref(Parcel in) {
        title = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<MarkerPref> CREATOR = new Creator<MarkerPref>() {
        @Override
        public MarkerPref createFromParcel(Parcel in) {
            return new MarkerPref(in);
        }

        @Override
        public MarkerPref[] newArray(int size) {
            return new MarkerPref[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
