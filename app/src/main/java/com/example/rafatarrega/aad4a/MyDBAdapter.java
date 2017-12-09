package com.example.rafatarrega.aad4a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by rafatarrega.
 */

public class MyDBAdapter {

    private MyDBAdapter(){}

    // Definiciones y constantes
    static final String DATABASE_TABLE = "Estudiante";
    //static final String TABLE_NAME ="Profesor";

    private SQLiteDatabase db;

    public static final String NOMBREALUMNO = "Nombre del alumno";
    public static final String EDADALUMNO = "Edad alumno";
    public static final String CURSOALUMNO = "Curso alumno";
    public static final String CICLOALUMNO = "Ciclo alumno";
    public static final String MEDIA = "NotaMedia";

    //static final String DATABASE_TABLE = "Profesor";
    public static final String NOMBREPROFESOR = "Nombre del profesor";
    public static final String EDADPROFESOR = "Edad profesor";
    public static final String CICLOPROFESOR = "Ciclo profesor";
    public static final String DESPACHO = "Despacho";



    private static final String TEXT_TYPE= "TEXT";
    private static final String COMMA_SEP = ",";
    //¿Cómo elegimos la bbdd a la que tiene que entrar?
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MyDBAdapter.DATABASE_TABLE + " (" +
                    MyDBAdapter.NOMBREALUMNO + " INTEGER PRIMARY KEY," +
                    MyDBAdapter.EDADALUMNO + TEXT_TYPE +  COMMA_SEP + " TEXT," +
                    MyDBAdapter.CURSOALUMNO + TEXT_TYPE +" TEXT," +
                    MyDBAdapter.CICLOALUMNO + TEXT_TYPE + " TEXT, " +
                    MyDBAdapter.MEDIA + TEXT_TYPE + "TEXT" +

                    MyDBAdapter.NOMBREPROFESOR + TEXT_TYPE  +
                    MyDBAdapter.EDADPROFESOR + TEXT_TYPE +" TEXT," +
                    MyDBAdapter.CICLOPROFESOR + TEXT_TYPE + " TEXT, " +
                    MyDBAdapter.DESPACHO + TEXT_TYPE + "TEXT" ;

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MyDBAdapter.DATABASE_TABLE;


    /*public ArrayList<String> recuperarTodo(){
        ArrayList<String> profesores = new ArrayList<String>();
        //Recuperamos en un cursor la consulta
        Cursor cursor = db.query(DATABASE_TABLE, null,null,null,null,null,null,null,null,null);
        //Recorremos el cursor
        if (cursor != null && cursor.moveToFirst()){
            do {
                discos.add(cursor.getString(1));
            }while(cursor.moveToNext());
        }
    }*/

}