package com.ucenm.registrocontactos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucenm.registrocontactos.adapter.ContactoAdapter;
import com.ucenm.registrocontactos.model.Contacto;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "CICLO_VIDA";
    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_CORREO = "correo";
    private static final String EXTRA_TELEFONO = "telefono";
    private static final String EXTRA_OPCION = "opcion";
    private final ArrayList<Contacto> contactos = new ArrayList<>();
    private EditText etNombre, etCorreo, etTelefono;
    private RadioGroup rgOpcion;
    private ActivityResultLauncher<Intent> confirmacionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log("onCreate");
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etTelefono = findViewById(R.id.etTelefono);
        rgOpcion = findViewById(R.id.rgOpcion);
        RecyclerView recyclerView = findViewById(R.id.rvContactos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ContactoAdapter(contactos));

        confirmacionLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        contactos.add(new Contacto(data.getStringExtra(EXTRA_NOMBRE),
                                data.getStringExtra(EXTRA_CORREO), data.getStringExtra(EXTRA_TELEFONO),
                                data.getStringExtra(EXTRA_OPCION)));
                        recyclerView.getAdapter().notifyItemInserted(contactos.size() - 1);
                        limpiarFormulario();
                    }
                });

        Button btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(v -> enviarSiValido());
    }

    private void enviarSiValido() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String telefono = etTelefono.getText().toString().trim();
        boolean valido = true;

        if (nombre.isEmpty()) { etNombre.setError("Ingrese el nombre"); valido = false; }
        if (correo.isEmpty()) { etCorreo.setError("Ingrese el correo"); valido = false; }
        else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) { etCorreo.setError("Correo no válido"); valido = false; }
        if (telefono.isEmpty()) { etTelefono.setError("Ingrese el teléfono"); valido = false; }
        else if (!telefono.matches("\\d{8}")) { etTelefono.setError("Debe tener exactamente 8 dígitos"); valido = false; }
        if (!valido) return;

        int checkedId = rgOpcion.getCheckedRadioButtonId();
        String opcion = checkedId == R.id.rbTrabajo ? "Trabajo" : "Personal";
        Intent intent = new Intent(this, ConfirmationActivity.class);
        intent.putExtra(EXTRA_NOMBRE, nombre);
        intent.putExtra(EXTRA_CORREO, correo);
        intent.putExtra(EXTRA_TELEFONO, telefono);
        intent.putExtra(EXTRA_OPCION, opcion);
        confirmacionLauncher.launch(intent);
    }

    private void limpiarFormulario() {
        etNombre.setText(""); etCorreo.setText(""); etTelefono.setText("");
        etNombre.setError(null); etCorreo.setError(null); etTelefono.setError(null);
        rgOpcion.check(R.id.rbPersonal);
    }

    private void log(String metodo) { Log.d(TAG, "MainActivity: " + metodo); }

    @Override protected void onStart() { super.onStart(); log("onStart"); }
    @Override protected void onResume() { super.onResume(); log("onResume"); }
    @Override protected void onPause() { log("onPause"); super.onPause(); }
    @Override protected void onStop() { log("onStop"); super.onStop(); }
    @Override protected void onRestart() { super.onRestart(); log("onRestart"); }
    @Override protected void onDestroy() { log("onDestroy"); super.onDestroy(); }
}
