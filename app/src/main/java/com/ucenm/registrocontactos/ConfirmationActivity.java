package com.ucenm.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {
    private static final String TAG = "CICLO_VIDA";
    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_CORREO = "correo";
    private static final String EXTRA_TELEFONO = "telefono";
    private static final String EXTRA_OPCION = "opcion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation);

        Intent entrada = getIntent();
        String nombre = entrada.getStringExtra(EXTRA_NOMBRE);
        String correo = entrada.getStringExtra(EXTRA_CORREO);
        String telefono = entrada.getStringExtra(EXTRA_TELEFONO);
        String opcion = entrada.getStringExtra(EXTRA_OPCION);
        TextView resumen = findViewById(R.id.tvResumen);
        resumen.setText("Nombre: " + nombre + "\nCorreo: " + correo + "\nTeléfono: " + telefono + "\nOpción: " + opcion);

        Button volver = findViewById(R.id.btnVolver);
        volver.setOnClickListener(v -> finish());
        Button confirmar = findViewById(R.id.btnConfirmar);
        confirmar.setOnClickListener(v -> {
            Intent resultado = new Intent();
            resultado.putExtra(EXTRA_NOMBRE, nombre);
            resultado.putExtra(EXTRA_CORREO, correo);
            resultado.putExtra(EXTRA_TELEFONO, telefono);
            resultado.putExtra(EXTRA_OPCION, opcion);
            setResult(RESULT_OK, resultado);
            finish();
        });
    }

    private void log(String metodo) { Log.d(TAG, "ConfirmationActivity: " + metodo); }

    @Override protected void onStart() { super.onStart(); log("onStart"); }
    @Override protected void onResume() { super.onResume(); log("onResume"); }
    @Override protected void onPause() { log("onPause"); super.onPause(); }
    @Override protected void onStop() { log("onStop"); super.onStop(); }
    @Override protected void onRestart() { super.onRestart(); log("onRestart"); }
    @Override protected void onDestroy() { log("onDestroy"); super.onDestroy(); }
}
