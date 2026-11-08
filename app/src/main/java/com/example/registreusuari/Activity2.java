package com.example.registreusuari;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity implements View.OnClickListener {

    // ===== VISTES =====
    TextView txtNom;
    TextView txtCognoms;
    TextView txtEdat;
    TextView txtMatriculat;
    CheckBox chkPolitica;
    Button btnSortir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ===== ENLLAÇAR VISTES =====
        txtNom = findViewById(R.id.txtNom);
        txtCognoms = findViewById(R.id.txtCognoms);
        txtEdat = findViewById(R.id.txtEdat);
        txtMatriculat = findViewById(R.id.txtMatriculat);
        chkPolitica = findViewById(R.id.chkPolitica);
        btnSortir = findViewById(R.id.btnSortir);

        // ===== LISTENERS =====
        btnSortir.setOnClickListener(this);

        // ===== DADES REBUDES =====
        Bundle b = getIntent().getExtras();
        String nom = b.getString("nom");
        String cognoms = b.getString("cognoms");
        int edat = b.getInt("edat");
        boolean matriculat = b.getBoolean("matriculat");

        txtNom.setText("Nom: " + nom);
        txtCognoms.setText("Cognoms: " + cognoms);
        txtEdat.setText("Edat: " + edat);
        if (matriculat) {
            txtMatriculat.setText("Matriculat: Sí");
        } else {
            txtMatriculat.setText("Matriculat: No");
        }
    }

    // ===== CLICS =====
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSortir) {
            Intent i = new Intent();
            i.putExtra("acceptat", chkPolitica.isChecked());
            setResult(RESULT_OK, i); // torna el resultat a MainActivity
            finish();
        }
    }
}
