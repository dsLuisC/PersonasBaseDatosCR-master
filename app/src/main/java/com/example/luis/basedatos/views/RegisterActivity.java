package com.example.luis.basedatos.views;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luis.basedatos.R;
import com.example.luis.basedatos.helpers.SqliteHelper;
import com.example.luis.basedatos.utility.Constants;

import static java.lang.String.valueOf;

public class RegisterActivity extends AppCompatActivity {

    EditText txtId;
    EditText txtNombres;
    EditText txtApellidos;
    EditText txtEdad;
    EditText txtTelefono;
    Button btnCreate;
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtId = (EditText) findViewById(R.id.txtId);
        txtNombres = (EditText) findViewById(R.id.txtNombres);
        txtApellidos = (EditText) findViewById(R.id.txtApellidos);
        txtEdad = (EditText) findViewById(R.id.txtEdad);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        btnCreate = (Button) findViewById(R.id.btnCreate);

        sqliteHelper = new SqliteHelper(this, "dbprueba", null, 1);

        txtId.setText( Integer.toString(getIntent().getExtras().getInt("id")) );
        txtNombres.setText( getIntent().getExtras().getString("name") );
        txtApellidos.setText( getIntent().getExtras().getString("lastname") );
        if (Integer.toString(getIntent().getExtras().getInt("id")) != "0") {
            txtEdad.setText( Integer.toString(getIntent().getExtras().getInt("age")) );
        } else {
            txtEdad.setText("");
        }
        txtTelefono.setText( getIntent().getExtras().getString("phone") );

        if (!(Integer.toString(getIntent().getExtras().getInt("id"))).equals("0")) {
            btnCreate.setText("ACTUALIZAR PERSONA");
        }
    }

    public void cancelCreatePerson(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void validateCreatePerson(View view) {
        String valorId = txtId.getText().toString();
        String valorNombres = txtNombres.getText().toString();
        String valorApellidos = txtApellidos.getText().toString();
        String valorEdad = txtEdad.getText().toString();
        String valorTelefono = txtTelefono.getText().toString();

        if (TextUtils.isEmpty(valorNombres) || TextUtils.isEmpty(valorApellidos) || TextUtils.isEmpty(valorEdad) || TextUtils.isEmpty(valorTelefono)) {
            Toast.makeText(this, "No pueden haber campos vacios", Toast.LENGTH_SHORT).show();
        } else {
            if (valorId.equals("0")) {
                createPerson();


            } else {
                updatePerson();
            }
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

        Toast.makeText(this, "Persona creada correctamente", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void updatePerson(){
        SQLiteDatabase db = sqliteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Constants.TABLA_FIELD_NAME, txtNombres.getText().toString());
        values.put(Constants.TABLA_FIELD_LASTNAME, txtApellidos.getText().toString());
        values.put(Constants.TABLA_FIELD_AGE, Integer.valueOf(txtEdad.getText().toString()));
        values.put(Constants.TABLA_FIELD_PHONE, txtTelefono.getText().toString());

        String[] params = {txtId.getText().toString()};

        db.update(Constants.TABLA_NAME_PERSONS, values, Constants.TABLA_FIELD_ID+"=?", params);

        //db.execSQL("update users set name = '"+editTextName.getText().toString()+"', phone = '"+editTextPhone.getText().toString()+"', email = '"+editTextEmail.getText().toString()+"' where id = "+idContact);

        Toast.makeText(this, "Persona actualizada correctamente", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
