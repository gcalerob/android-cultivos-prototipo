package com.grupovermon.prototipo_cultivos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    private Button bNuevoFormulario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bNuevoFormulario = (Button)findViewById(R.id.bNuevoFormulario);
        bNuevoFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, NuevoFormulario.class);
                Main.this.startActivity(intent);
            }
        });
    }
}