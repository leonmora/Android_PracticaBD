package com.reserva;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class cambiar extends Activity {


    EditText nombre;
    SeekBar personas;
    TextView info;
    private int id;
    private String nomb;
    private int persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar);

        id=this.getIntent().getExtras().getInt("ID");
        nomb=this.getIntent().getExtras().getString("Nombre");
        persons=this.getIntent().getExtras().getInt("Personas");

        nombre=(EditText)findViewById(R.id.nombre);
        personas=(SeekBar)findViewById(R.id.personas);
        info=(TextView)findViewById(R.id.infoPersonas);
        personas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    persons = seekBar.getProgress();
                    info.setText("Personas: " + personas.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        personas.setProgress(persons);
        info.setText("Personas: "+personas.getProgress());
        nombre.setText(nomb);


    }

    public void cambiar(View vw)
    {
        AuxiliarSQL sql = new AuxiliarSQL(this,	"DB_Restaurant", null, 1);
        final SQLiteDatabase db = sql.getWritableDatabase();
        ContentValues datos=new ContentValues();
        datos.put("Nombre",nombre.getText().toString());
        datos.put("Personas",persons);
        db.update("Reservacion",datos,"_id="+id,null);
        db.close();
        Toast.makeText(getApplicationContext(),"Reserva Modificada",Toast.LENGTH_SHORT).show();
        finish();
        Intent main=new Intent(this,MainActivity.class);
        startActivity(main);
    }
}
