package com.example.paola.inventarioldp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    public static final String TABLE_PRODUCTOS = "productos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_PRECIO = "precio";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_MARCA = "marca";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_PROVEEDOR = "proveedor";
    private static final String DATABASE_NAME = "tienda.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_PRODUCTOS+ "(" + COLUMN_ID
            + " integer primary key autoincrement,"
            + COLUMN_NOMBRE + " text not null,"
            + COLUMN_PRECIO + " text not null,"
            + COLUMN_DESCRIPCION + " text not null,"
            + COLUMN_MARCA + " text not null,"
            + COLUMN_STOCK + " text not null,"
            + COLUMN_PROVEEDOR + " text not null"
            +");";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTOS);
        onCreate(db);
    }
}
