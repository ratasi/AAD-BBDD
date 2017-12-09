package com.example.rafatarrega.aad4a;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {



    Button btnInsertar,btnBorrar,btnActualizar, btnBuscar;
    EditText nombreAlumno, edadAlumno, cursoAlumno, cicloAlumno, mediaAlumno;
    EditText nombreProfesor, edadProfesor,cicloProfesor, despacho;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnInsertar=(Button)findViewById(R.id.insertar);
        btnBorrar=(Button)findViewById(R.id.borrar);
        btnActualizar=(Button)findViewById(R.id.actualizar);
        btnBuscar=(Button)findViewById(R.id.buscar);

        nombreAlumno=(EditText) findViewById(R.id.nombreAlumno);
        edadAlumno=(EditText) findViewById(R.id.edadAlumno);
        cursoAlumno=(EditText) findViewById(R.id.cursoAlumno);
        cicloAlumno=(EditText) findViewById(R.id.cicloAlumno);
        mediaAlumno=(EditText) findViewById(R.id.notaMedia);

        nombreProfesor=(EditText) findViewById(R.id.nombreProfesor);
        edadProfesor=(EditText)findViewById(R.id.edadProfesor);
        cicloProfesor=(EditText)findViewById(R.id.cicloProfesor);
        despacho=(EditText)findViewById(R.id.despacho);

        final MyDBAdapater_Helper helper = new MyDBAdapater_Helper(this);


        btnInsertar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // Hacemos que nuestra bbdd sea "escribible"
                SQLiteDatabase db = helper.getWritableDatabase();

                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                    //Alumno
                values.put(MyDBAdapter.NOMBREALUMNO, nombreAlumno.getText().toString());
                values.put(MyDBAdapter.EDADALUMNO,edadAlumno.getText().toString());
                values.put(MyDBAdapter.CURSOALUMNO,cursoAlumno.getText().toString());
                values.put(MyDBAdapter.CICLOALUMNO,cicloAlumno.getText().toString());
                values.put(MyDBAdapter.MEDIA,mediaAlumno.getText().toString());

                    //Profesor
                values.put(MyDBAdapter.NOMBREPROFESOR,nombreProfesor.getText().toString());
                values.put(MyDBAdapter.EDADPROFESOR,edadProfesor.getText().toString());
                values.put(MyDBAdapter.CICLOPROFESOR,cicloProfesor.getText().toString());
                values.put(MyDBAdapter.DESPACHO,despacho.getText().toString());






                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(MyDBAdapter.DATABASE_TABLE, null, values);

                Toast.makeText(getApplicationContext(), " Se ha guardado el registro con clave: " + newRowId, Toast.LENGTH_LONG).show();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                SQLiteDatabase db = helper.getWritableDatabase();

                try {
                    // Define 'where' part of query.
                    String selection = MyDBAdapter.NOMBREALUMNO + " LIKE ?";
                    String selection2 = MyDBAdapter.NOMBREPROFESOR + " LIKE ?";

                    // Specify arguments in placeholder order.
                    String[] selectionArgs = {nombreAlumno.getText().toString()};
                    String[] selectionArgs2 = {nombreProfesor.getText().toString()};

                    // Issue SQL statement.
                    db.delete(MyDBAdapter.DATABASE_TABLE, selection, selectionArgs);
                    db.delete(MyDBAdapter.DATABASE_TABLE, selection2, selectionArgs2);


                    Toast.makeText(getApplicationContext(), " Se ha eliminado el usuario: "
                            + nombreAlumno.getText().toString() + nombreProfesor.getText().toString(), Toast.LENGTH_LONG).show();

                    //Vaciar los campos al borrar
                    nombreAlumno.setText("");
                    cursoAlumno.setText("");
                    cicloAlumno.setText("");
                    edadAlumno.setText("");
                    mediaAlumno.setText("");

                    nombreProfesor.setText("");
                    edadProfesor.setText("");
                    cicloProfesor.setText("");
                    despacho.setText("");

                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No se ha podido eliminar ningún resultado ", + Toast.LENGTH_LONG).show();

                }

            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                try {
                    SQLiteDatabase db = helper.getWritableDatabase();

                    // New value for one column
                    ContentValues values = new ContentValues();
                    //Los datos que se pueden modificar, en mi caso nombreAlumno no será modificable
                    //ya que no tengo id y tiene que haber uno
                    values.put(MyDBAdapter.EDADALUMNO, edadAlumno.getText().toString());
                    values.put(MyDBAdapter.CICLOALUMNO, cicloAlumno.getText().toString());
                    values.put(MyDBAdapter.CURSOALUMNO, cursoAlumno.getText().toString());
                    values.put(MyDBAdapter.MEDIA, mediaAlumno.getText().toString());


                    values.put(MyDBAdapter.EDADPROFESOR, edadProfesor.getText().toString());
                    values.put(MyDBAdapter.CICLOPROFESOR, cicloProfesor.getText().toString());
                    values.put(MyDBAdapter.DESPACHO, despacho.getText().toString());



                    // Which row to update, based on the title
                    String selection = MyDBAdapter.NOMBREALUMNO + " LIKE ?";
                    String[] selectionArgs = {nombreAlumno.getText().toString()};

                    String selection2 = MyDBAdapter.NOMBREPROFESOR + "LIKE ?";
                    String[] selectionArgs2 = {nombreProfesor.getText().toString()};

                    int count = db.update(
                            MyDBAdapter.DATABASE_TABLE,
                            values,
                            selection,
                            selectionArgs);

                    int count2 = db.update(
                            MyDBAdapter.DATABASE_TABLE,
                            values,
                            selection2,
                            selectionArgs2);

                    Toast.makeText(getApplicationContext(), " Se ha actualizado el usuario: ", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No se ha podido modificar ningún usuario ", + Toast.LENGTH_LONG).show();

                }

            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                //Hacemos que neustra bbdd se haga leible
                SQLiteDatabase db = helper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        //FeedEntry._ID,
                        MyDBAdapter.NOMBREALUMNO,
                        MyDBAdapter.EDADALUMNO,
                        MyDBAdapter.CICLOALUMNO,
                        MyDBAdapter.CURSOALUMNO,
                        MyDBAdapter.MEDIA,

                        MyDBAdapter.NOMBREPROFESOR,
                        MyDBAdapter.EDADPROFESOR,
                        MyDBAdapter.CICLOPROFESOR,
                        MyDBAdapter.DESPACHO



                };

                // Filter results WHERE "title" = 'My Title'
                //Campo de criterio, es decir, el id(nombre)
                String selection = MyDBAdapter.NOMBREALUMNO + " = ?";
                String[] selectionArgs = { nombreAlumno.getText().toString() };

                String selection2 = MyDBAdapter.NOMBREPROFESOR + " = ?";
                String[] selectionArgs2 = { nombreProfesor.getText().toString() };

                // How you want the results sorted in the resulting Cursor
                //Nos serviría para poder ordenar números
                /*String sortOrder =
                        FeedEntry.COLUMN_NAME_SUBTITLE + " DESC";*/


                try {


                    Cursor cursor = db.query(
                            //¿Hay que especificar la tabla donde va a ir?

                            MyDBAdapter.DATABASE_TABLE,               // The table to query
                            projection,                               // The columns to return
                            selection,                                // The columns for the WHERE clause
                            selectionArgs,                            // The values for the WHERE clause
                            null,                            // don't group the rows (Agrupar por país por ejemplo)
                            null,                             // don't filter by row groups (Agrupar alfabéticamente)
                            null  //sortOrder                                // The sort order

                    );

                    Cursor cursor2 = db.query(
                            //¿Hay que especificar la tabla donde va a ir?
                            MyDBAdapter.DATABASE_TABLE,               // The table to query
                            projection,                               // The columns to return
                            selection2,                                // The columns for the WHERE clause
                            selectionArgs2,                            // The values for the WHERE clause
                            null,                            // don't group the rows (Agrupar por país por ejemplo)
                            null,                             // don't filter by row groups (Agrupar alfabéticamente)
                            null  //sortOrder                                // The sort order
                    );

                    cursor.moveToFirst();
                    cursor2.moveToFirst();



                    nombreAlumno.setText(cursor.getString(0));
                    edadAlumno.setText(cursor.getString(1));
                    cicloAlumno.setText(cursor.getString(2));
                    cursoAlumno.setText(cursor.getString(3));
                    mediaAlumno.setText(cursor.getString(4));



                    nombreProfesor.setText(cursor2.getString(0));
                    edadProfesor.setText(cursor2.getString(1));
                    cicloProfesor.setText(cursor2.getString(2));
                    despacho.setText(cursor2.getString(3));


                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "No se ha encontrado ningún resultado ", + Toast.LENGTH_LONG).show();
                }


            }
        });


  }
}