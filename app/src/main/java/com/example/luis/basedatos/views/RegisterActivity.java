package com.example.luis.basedatos.views;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luis.basedatos.R;
import com.example.luis.basedatos.helpers.SqliteHelper;
import com.example.luis.basedatos.utility.Constants;

import static java.lang.String.valueOf;

public class RegisterActivity extends AppCompatActivity {

    EditText txtNombres;
    EditText txtApellidos;
    EditText txtEdad;
    EditText txtTelefono;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtNombres = (EditText) findViewById(R.id.txtNombres);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);

        sqliteHelper = new SqliteHelper(this, "dbprueba", null, 1);
    }

    public void validateCreatePerson(View view) {
        String valorNombres = txtNombres.getText().toString();
        String valorApellidos = txtApellidos.getText().toString();
        String valorEdad = txtEdad.getText().toString();
        String valorTelefono = txtTelefono.getText().toString();

        if (TextUtils.isEmpty(valorNombres) || TextUtils.isEmpty(valorApellidos) || TextUtils.isEmpty(valorEdad) || TextUtils.isEmpty(valorTelefono)) {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            createPerson();
        }
    }

    public void createPerson() {
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.TABLA_FIELD_NAME, txtNombres.getText().toString());
        values.put(Constants.TABLA_FIELD_LASTNAME, txtApellidos.getText().toString());
        values.put(Constants.TABLA_FIELD_AGE, Integer.valueOf(txtEdad.getText().toString()));
        values.put(Constants.TABLA_FIELD_PHONE, txtTelefono.getText().toString());

        db.insert(Constants.TABLA_NAME_PERSONS, Constants.TABLA_FIELD_ID, values);

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
