package com.grupovermon.prototipo_cultivos;

import classes.Mantenimiento;
import com.google.gson.*;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Mantenimientos extends AppCompatActivity {
    int id = 1;
    Mantenimiento mantenimiento;
    GsonBuilder gsonBuilder;
    Gson gson;
    String JSON;

    private TextView tvCrearId, tvNuevoJSON, tvCarga;
    private Button bCrear, bGuardar, bCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mantenimientos_layout);

        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        tvCrearId = (TextView)findViewById(R.id.tvCrearId);
        tvNuevoJSON = (TextView)findViewById(R.id.tvNuevoJSON);
        tvCarga = (TextView)findViewById(R.id.tvCarga);
        bCrear = (Button)findViewById(R.id.bCrear);
        bGuardar = (Button)findViewById(R.id.bGuardar);
        bCargar = (Button)findViewById(R.id.bCargar);

        actualizarId();

        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mantenimiento = new Mantenimiento(id, 2016, "finca1", "tipo1", 69.696969, -1.6964234);
                JSON = gson.toJson(mantenimiento);
                tvNuevoJSON.setText(JSON);
                id += 1;
                actualizarId();
            }
        });

        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (JSON != null) {writeData(JSON);}
                else {tvNuevoJSON.setText("NADA QUE GUARDAR");}
            }
        });

        bCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String res = readSavedData();
                tvCarga.setText(res);
            }
        });
    }

    private void actualizarId(){
        String s = "ID: " + id;
        tvCrearId.setText(s);
    }

    public void writeData(String data) {
        try {
            FileOutputStream fOut = openFileOutput ("mantenimientos_layout" , Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter ( fOut ) ;
            osw.write (data) ;
            osw.flush() ;
            osw.close() ;
        } catch ( Exception e ) {
            e.printStackTrace ( ) ;
        }
    }

    public String readSavedData () {
        StringBuffer datax = new StringBuffer("");
        try {
            FileInputStream fIn = openFileInput ("mantenimientos_layout");
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                datax.append(readString);
                readString = buffreader.readLine ( ) ;
            }
            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return datax.toString() ;
    }
}
