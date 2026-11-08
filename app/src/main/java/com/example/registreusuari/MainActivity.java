package com.example.registreusuari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // ===== VISTES =====
    EditText edtNom;
    EditText edtCognoms;
    EditText edtEdat;
    RadioButton rbSi;
    RadioButton rbNo;
    Button btnMostrar;
    TextView txtPolitica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ===== ENLLAÇAR VISTES =====
        edtNom = findViewById(R.id.edtNom);
        edtCognoms = findViewById(R.id.edtCognoms);
        edtEdat = findViewById(R.id.edtEdat);
        rbSi = findViewById(R.id.rbSi);
        rbNo = findViewById(R.id.rbNo);
        btnMostrar = findViewById(R.id.btnMostrar);
        txtPolitica = findViewById(R.id.txtPolitica);

        // ===== LISTENERS =====
        btnMostrar.setOnClickListener(this);
    }

    // ===== CLICS =====
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnMostrar) {
            if (validarDades()) {
                mostrarInfo();
            }
        }
    }

    private boolean validarDades() {
        String nom = edtNom.getText().toString();
        String cognoms = edtCognoms.getText().toString();
        String textEdat = edtEdat.getText().toString();

        if (nom.isEmpty()) {
            Toast.makeText(MainActivity.this, "Has d'escriure el nom", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cognoms.isEmpty()) {
            Toast.makeText(MainActivity.this, "Has d'escriure els cognoms", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (textEdat.isEmpty()) {
            Toast.makeText(MainActivity.this, "Has d'escriure l'edat", Toast.LENGTH_SHORT).show();
            return false;
        }

        int edat;
        try {
            edat = Integer.parseInt(textEdat);
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "L'edat ha de ser un número enter", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (edat < 0 || edat > 120) {
            Toast.makeText(MainActivity.this, "L'edat ha d'estar entre 0 i 120", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!rbSi.isChecked() && !rbNo.isChecked()) {
            Toast.makeText(MainActivity.this, "Has de dir si estàs matriculat", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void mostrarInfo() {
        String nom = edtNom.getText().toString();
        String cognoms = edtCognoms.getText().toString();
        int edat = Integer.parseInt(edtEdat.getText().toString());
        boolean matriculat = rbSi.isChecked();

        Intent i = new Intent(MainActivity.this, Activity2.class);
        Bundle b = new Bundle();
        b.putString("nom", nom);
        b.putString("cognoms", cognoms);
        b.putInt("edat", edat);
        b.putBoolean("matriculat", matriculat);
        i.putExtras(b);
        startActivityForResult(i, 123); // el 123 serveix per saber de quina crida tornem
    }

    // ===== RETORN =====
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK) {
            boolean acceptat = data.getBooleanExtra("acceptat", false);
            if (acceptat) {
                txtPolitica.setText("Has acceptat la política de privacitat");
            } else {
                txtPolitica.setText("No has acceptat la política de privacitat");
            }
        }
    }
}
