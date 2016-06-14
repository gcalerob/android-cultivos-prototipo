package com.grupovermon.prototipo_cultivos;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class GPS extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    AlertDialog alerta;
    LocationManager mLocationManager;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;

    int UPDATE_INTERVAL = 5000;
    int FASTEST_INTERVAL = 1000;
    int DISPLACEMENT = 5;

    TextView tvCoordenadasActuales, tvCoordenadasH1, tvCoordenadasH2, tvCambios;
    ImageView ivAlerta;

    int cambios = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_layout);
//      mApp = ((AppApplication)getApplicationContext());

        if (isGooglePlayServicesAvailable(GPS.this)){buildGoogleApiClient();}

        tvCoordenadasActuales = (TextView)findViewById(R.id.tvCoordenadasActuales);
        tvCoordenadasH1 = (TextView)findViewById(R.id.tvCoordenadasH1);
        tvCoordenadasH2 = (TextView)findViewById(R.id.tvCoordenadasH2);
        tvCambios = (TextView)findViewById(R.id.tvCambios);
        ivAlerta = (ImageView)findViewById(R.id.ivAlerta);

        actualizarCambios();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        mLocationManager = (LocationManager)getSystemService((Context.LOCATION_SERVICE));
        if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){alertaNoGPS();}
    }

    @Override
    protected void onPause(){
        super.onPause();
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (alerta != null) {alerta.dismiss();}
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        displayLocation();
        createLocationRequest();
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i)  {
            mGoogleApiClient.connect();
        }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        tvCoordenadasH2.setText(tvCoordenadasH1.getText());
        tvCoordenadasH1.setText(tvCoordenadasActuales.getText());
        displayLocation();

        ivAlerta.setVisibility(View.INVISIBLE);
        cambios += 1;
        actualizarCambios();
    }

    private void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }

    private void displayLocation(){
        if (mLastLocation != null) {
            String latitud = String.valueOf(mLastLocation.getLatitude());
            String longitud = String.valueOf(mLastLocation.getLongitude());
            String coordenadas = latitud + " , " + longitud;
            tvCoordenadasActuales.setText(coordenadas);
        }
    }

    private void createLocationRequest(){
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL)
                .setFastestInterval(FASTEST_INTERVAL)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setSmallestDisplacement(DISPLACEMENT);
    }

    private void alertaNoGPS(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.alert_gps)
                .setCancelable(false)
                .setPositiveButton(R.string.alert_gps_yes, new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog,
                                        @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        alerta = builder.create();
        alerta.show();
    }

    private void actualizarCambios(){
        String cambiosS = "Cambios: " + String.valueOf(cambios);
        tvCambios.setText(cambiosS);
    }

}
