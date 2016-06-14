package com.grupovermon.prototipo_cultivos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends AppCompatActivity {
    private Button bGPS, bMantenimientos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        bGPS = (Button)findViewById(R.id.bGPS);
        bMantenimientos = (Button)findViewById(R.id.bMantenimientos);

        bGPS.setOnClickListener(onClickToActivityListener);
        bMantenimientos.setOnClickListener(onClickToActivityListener);
    }

    private OnClickListener onClickToActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v==bGPS){
                Intent intent = new Intent(Main.this, GPS.class);
                Main.this.startActivity(intent);
            }
            if (v==bMantenimientos){
                Intent intent = new Intent(Main.this, Mantenimientos.class);
                Main.this.startActivity(intent);
            }
        }
    };

}