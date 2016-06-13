package com.grupovermon.prototipo_cultivos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import classes.AppApplication;

public class NuevoFormulario extends AppCompatActivity{

    AppApplication mApp;
    TextView tvGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rellenar_formulario);
        mApp = ((AppApplication)getApplicationContext());
        tvGPS = (TextView)findViewById(R.id.tvGPS);
        tvGPS.setText(mApp.latitud + " - " + mApp.longitud);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mApp.mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mApp.mGoogleApiClient.isConnected()) {
            mApp.mGoogleApiClient.disconnect();
        }
    }

}
