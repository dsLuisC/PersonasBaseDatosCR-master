package com.example.luis.basedatos.utility;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import com.example.luis.basedatos.helpers.SqliteHelper;
import com.example.luis.basedatos.views.MainActivity;

/**
 * Created by lucho on 28/11/17.
 */

public class DeletePerson {

    public void delete (Integer id, View view) {

        SqliteHelper sqliteHelper = new SqliteHelper(view.getContext(), "dbprueba", null, 1);

        SQLiteDatabase db = sqliteHelper.getReadableDatabase();

        db.execSQL("delete from persons where id = " + id);

        Toast.makeText(view.getContext(), "Contacto fue eliminado correctamente", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(view.getContext(), MainActivity.class);

        view.getContext().startActivity(i);
    }
}
