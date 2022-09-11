package com.commons.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnSuccessListener;

import com.commons.interfaces.LocationInterface;

/**
 * Created by Wajid on 27-03-2016.
 */
public class LocationPoller extends LocationCallback implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final int LOCATION_REQUEST_INTERVAL = 10000;
    private static final int LOCATION_REQUEST_FASTEST_INTERVAL = 5000;
    private static final String TAG = LocationPoller.class.getName();
    private LocationInterface locationInterface;
    private Context context;
    private Activity activity;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Location mCurrentLocation;
    private final LocationRequest mLocationRequest;
    private boolean shouldShowPromptToEnableGPS =false;
    public LocationPoller(Context context) {
        this.context = context;
        initGoogleApiClient();
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(LOCATION_REQUEST_INTERVAL);
        mLocationRequest.setFastestInterval(LOCATION_REQUEST_FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void initGoogleApiClient() {
        if (mGoogleApiClient == null) { mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    public void setUpdateInterval(long fastestInterval, long interval) {
        mLocationRequest.setFastestInterval(fastestInterval);
        mLocationRequest.setInterval(interval);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void startPolling(Context context, LocationInterface locationInterface) {
        this.context = context;
        startPolling(locationInterface);
    }

    public void startPolling(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
        initGoogleApiClient();
        if(mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LogUtils.LOGE(TAG, "Permission Not Granted!");
            return;
        }
        if (mGoogleApiClient == null) {
            LogUtils.LOGE(TAG, "Google Api Client Seems to be null!");
            return;
        }
        //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(mLocationRequest, this, Looper.getMainLooper());
    }

    public void stopPolling() {
        this.locationInterface = null;
        stopLocationUpdates();
        mGoogleApiClient.disconnect();
        context = null;
        activity = null;
    }

    public void stopPollingAndFlush() {
        if (mGoogleApiClient == null)
            return;
        mGoogleApiClient.disconnect();
        stopLocationUpdates();
        flush();
    }

    private void flush() {
        this.context = null;
        mGoogleApiClient = null;
        this.locationInterface = null;
    }

    @Override
    public void onConnected(Bundle bundle) {
        if(context !=null) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                LogUtils.LOGE(TAG, "Permission Not Granted!");
                return;
            }
            // mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            LocationServices.getFusedLocationProviderClient(context).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    mLastLocation = location;
                }
            });
            startLocationUpdates();
            if (shouldShowPromptToEnableGPS) {
                promptToEnableGPS(activity);
            }
        }
    }

    private void stopLocationUpdates() {
        if (mGoogleApiClient == null) {
            LogUtils.LOGE(TAG, "Google Api Client Seems to be null!");
            return;
        }
        if (!mGoogleApiClient.isConnected()) {
            LogUtils.LOGE(TAG, "Google Api Client Not Connected!");
            return;
        }
        if(context !=null) {
            //LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            LocationServices.getFusedLocationProviderClient(context).removeLocationUpdates(this);
        }
    }

    public boolean isConnected() {
        if (mGoogleApiClient == null) return false;
        return mGoogleApiClient.isConnected();
    }

    public Location getLastLocation() {
        return mLastLocation;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        LogUtils.LOGE(TAG, "Failed to fetch location");
        LogUtils.LOGE(TAG, connectionResult.getErrorMessage());
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        if (locationInterface != null)
            locationInterface.onLocationChanged(location);
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);
        Location location = locationResult.getLastLocation();
        mCurrentLocation = location;
        if (locationInterface != null)
            locationInterface.onLocationChanged(location);
                
    }

    public Location getmCurrentLocation() {
        return mCurrentLocation;
    }

    public boolean promptToEnableGPS(final Activity activity) {

        if (mLocationRequest == null)
            return false;
        if (mGoogleApiClient == null)
            return false;
        if (!mGoogleApiClient.isConnected())
            return false;
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        //...
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    activity,
                                    123);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        //...
                        break;
                }
            }
        });
        return true;

    }

    private void showGPSAlert(final Activity activity) {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                //final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        //...
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(
                                    activity,
                                    123);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        //...
                        break;
                }
            }
        });
    }

    public void startPollingByPromptToEnableGPS(Activity activity,LocationInterface locationInterface) {
        this.activity = activity;
        shouldShowPromptToEnableGPS = true;
        startPolling(locationInterface);
    }
}
