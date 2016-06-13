package classes;

import android.content.Context;
import android.location.LocationListener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

public class myLocationListener implements LocationListener {
    public static double latitude;
    public static double longitude;

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
