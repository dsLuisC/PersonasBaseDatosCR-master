package com.example.luis.basedatos.utility;

/**
 * Created by luis on 7/11/17.
 */

public class Constants {
    public static final String TABLA_NAME_PERSONS = "persons";
    public static final String TABLA_FIELD_ID = "id";
    public static final String TABLA_FIELD_NAME = "name";
    public static final String TABLA_FIELD_LASTNAME = "lastname";
    public static final String TABLA_FIELD_AGE = "age";
    public static final String TABLA_FIELD_PHONE = "phone";
    public static final String CREATE_TABLE_PERSONS =
            "CREATE TABLE " + TABLA_NAME_PERSONS + " (" +
                TABLA_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLA_FIELD_NAME + " TEXT, " +
                TABLA_FIELD_LASTNAME + " TEXT, " +
                TABLA_FIELD_AGE + " INTEGER, " +
                TABLA_FIELD_PHONE + " TEXT)";
}
