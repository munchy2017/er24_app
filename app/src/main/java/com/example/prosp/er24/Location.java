package com.example.prosp.er24;

/**
 * Created by prosp on 12/12/2018.
 */

interface Location {
    void onLocationChanged(android.location.Location location);

    void onProviderDisabled(String provider);
}
