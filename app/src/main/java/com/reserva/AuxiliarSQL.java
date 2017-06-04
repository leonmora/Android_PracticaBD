package com.reserva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AuxiliarSQL extends SQLiteOpenHelper{
	
	String SQL_Tabla = "CREATE TABLE Reservacion ("
			+ "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			"Nombre TEXT, Personas INTEGER, Hora TEXT," +
			"Fecha TEXT)";

	public AuxiliarSQL(Context context, String DBname, 
			CursorFactory factory,
			int version) {
		super(context, DBname, factory, version);
	}

	@Override
	// La primera vez que se ejecute la app entra aqui
	// para crear la tabla
	public void onCreate(SQLiteDatabase baseDatos) {
		baseDatos.execSQL(SQL_Tabla);
	}

	@Override
	// Este se ejecuta cuando modificamos algo (agregamos algun
	// otro campo en alguna tabla)
	public void onUpgrade(SQLiteDatabase baseDatos, 
			int oldVersion, int newVersion) {
		baseDatos.execSQL("DROP TABLE IF EXISTS Reservacion");
		baseDatos.execSQL(SQL_Tabla);
	}
}
