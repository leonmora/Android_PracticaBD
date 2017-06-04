package com.reserva;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Actividad2 extends Activity {

	String nombre = "", fecha = "", hora = "";
	int personas = 0;
	TextView muestraDatos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.actividad2);

		muestraDatos = (TextView) findViewById(R.id.muestraDatos);

		Bundle recibe = new Bundle();
		recibe = this.getIntent().getExtras();

		nombre = recibe.getString("nombre");
		personas = recibe.getInt("personas");
		fecha = recibe.getString("fecha");
		hora = recibe.getString("hora");

		muestraDatos.setText("Reservacion a nombre de:\n" + nombre + "\n" + personas
				+ " personas\nFecha: " + fecha + "\nHora: " + hora + "\n");

//
//		//		Almacenamiento en Fichero en Memoria interna
//		String fichero = "Restaurant.txt";
//
//		String texto = "Reserva: "+ nombre + " " + personas + " " + fecha + " " + hora ;
//		FileOutputStream fos;
//		try {
//			fos = openFileOutput(fichero, Context.MODE_PRIVATE);
//			fos.write(texto.getBytes());
//			fos.close();
//		} catch (FileNotFoundException e) {
//			Toast.makeText(getApplicationContext(),
//						"Error en Insert " + e,
//						Toast.LENGTH_LONG).show();
//		} catch (IOException e) {
//			Toast.makeText(getApplicationContext(),
//						"Error en Insert " + e,
//						Toast.LENGTH_LONG).show();
//		}


		//		Almacenamiento en Fichero en Memoria xterna

//		boolean mExternalStorageAvailable = false;
//		boolean mExternalStorageWriteable = false;
//		String state = Environment.getExternalStorageState();
//
//		// COMPROBACION DEL ALMACENAMIENTO EXTERNO
//		if (Environment.MEDIA_MOUNTED.equals(state)) {
//			// Podremos leer y escribir en ella
//			mExternalStorageAvailable = mExternalStorageWriteable = true;
//		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
//			// En este caso solo podremos leer los datos
//			mExternalStorageAvailable = true;
//			mExternalStorageWriteable = false;
//		} else {
//			// No podremos leer ni escribir en ella
//			mExternalStorageAvailable = mExternalStorageWriteable = false;
//		}
//
//		// CREACION DE ARCHIVOS PUBLICOS
//		if (mExternalStorageWriteable == true) {
//
//			// Creamos una carpeta "MisBD" dentro del directorio "DCIM"
//			// Con el m�todo "mkdirs()" creamos el directorio si es necesario
//			File path = new File(Environment.getExternalStoragePublicDirectory(
//					Environment.DIRECTORY_DCIM), "MisBD");
//			path.mkdirs();
//
//			// Creamos un archivo dentro de la carpeta que hemos creado
//			File file = new File(path, "Restaurant.txt");
//
//			// Comprobamos si el archivo que estamos creando ya existe
//			if (file.exists()) {
//					Toast.makeText(getApplicationContext(),
//						"El archivo ya existe",
//						Toast.LENGTH_LONG).show();
//			}
//
//			String texto = "Reserva: "+ nombre + " " + personas + " " + fecha + " " + hora ;
//
//			try {
//				    OutputStream  fos = new FileOutputStream(file);;
////					fos = openFileOutput(file.toString(), Context.MODE_PRIVATE);
//					fos.write(texto.getBytes());
//					fos.close();
//			} catch (FileNotFoundException e) {
//				Toast.makeText(getApplicationContext(),
//						"No a sido posible acceder al archivo" + e.toString(),
//						Toast.LENGTH_LONG).show();
//				Log.e("ERROR", "No a sido posible acceder al archivo" + e.toString());
//			} catch (IOException e) {
//				Toast.makeText(getApplicationContext(),
//						"No ha sido posible ercribir el archivo" + e.toString(),
//						Toast.LENGTH_LONG).show();
//				Log.e("ERROR", "No ha sido posible ercribir el archivo" + e.toString());
//			}
//
//			// Borramos el archivo
////			file.delete();
//
//			// O lo borramos cuando termine la ejecuci�n del programa
////			file.deleteOnExit();
//
//			// Al borrar el archivo el directorio queda vac�o
//			// y para borrarlo podr�amos usar el siguiente c�digo
////			new File("/sdcard/DCIM/MisBD").delete();
//
//		} else {
//			Toast.makeText(getApplicationContext(),
//					"No ha sido posible crear archivos/carpetas",
//					Toast.LENGTH_LONG).show();
//			Log.d("ERROR", "No ha sido posible crear archivos/carpetas");
//		}


		//Almacenamiento en BD
		 //Definimos el nombre de la base de datos
		AuxiliarSQL sql = new AuxiliarSQL(this,"DB_Restaurant", null, 1);
		// Ocupamos la base para escribir
		final SQLiteDatabase db = sql.getWritableDatabase();

		ContentValues datos = new ContentValues();
		datos.put("Nombre", nombre);
		datos.put("Personas", personas);
		datos.put("Fecha", fecha);
		datos.put("Hora", hora);

		// Si la "conexion" se realiza
		if(db != null){
			try{
				db.insert("Reservacion", null, datos);

			} catch (Exception e){
				Toast.makeText(getApplicationContext(),
						"Error en Insert " + e,
						Toast.LENGTH_LONG).show();
			}
		}else{
			Toast.makeText(getApplicationContext(),
					"No existe la base de datos",
					Toast.LENGTH_LONG).show();
		}


	}

	public void hacerOtraReserva(View v) {
		Intent envia = new Intent(this, MainActivity.class);
		finish();
		startActivity(envia);
	}
}
