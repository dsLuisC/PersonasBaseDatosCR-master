package com.example.luis.basedatos.views;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.luis.basedatos.R;
import com.example.luis.basedatos.adapters.PersonasAdapter;
import com.example.luis.basedatos.helpers.SqliteHelper;
import com.example.luis.basedatos.models.Persona;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewPersonas;
    PersonasAdapter personasAdapter;
    LinearLayoutManager linearLayoutManager;
    List<Persona> listaPersonas = new ArrayList<>();
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteHelper = new SqliteHelper(this, "dbprueba", null, 1);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.VERTICAL);

        recyclerViewPersonas = (RecyclerView) findViewById(R.id.personasRecylerView);
        recyclerViewPersonas.setLayoutManager(linearLayoutManager);

        Log.d("Estado", "wenasssss");



        listPersons();
    }

    public void goToRegister(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void deletePerson(Integer id) {
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();

        db.execSQL("delete from personas where id = " + id);

        Toast.makeText(this, "Contacto fue eliminado correctamente", Toast.LENGTH_SHORT).show();
    }

    public void listPersons() {
        SQLiteDatabase db = sqliteHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from persons", null);

        while (cursor.moveToNext()) {
            listaPersonas.add(new Persona(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4)));
        }

        cursor.close();

        if (listaPersonas.size() > 0) {
            processData();
        } else {
            Toast.makeText(this, "No hay elementos para mostrar", Toast.LENGTH_SHORT).show();
        }
    }

    public void processData() {
        personasAdapter = new PersonasAdapter(listaPersonas, getApplicationContext());
        recyclerViewPersonas.setAdapter(personasAdapter);
    }


    // Create Read Update Delete
}
