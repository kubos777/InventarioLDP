package com.example.paola.inventarioldp;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Paola on 05/06/2017.
 */

public class Producto {
    //Atributos modificables del elemento en la BD
    public long id;
    public String nombre;
    public String precio;
    public String descripcion;
    public String marca;
    public String stock;
    public String proveedor;
    private SQLiteDatabase database; //Objeto útil para manipulación de tablas en la BD
    private Database dbHelper; //Objeto de clase creada por nosotros para manipulación de la BD
    //Arreglo de cadenas que almacena toda una fila de una tabla (datos de un contacto)
    private String[] allColumns = { Database.COLUMN_ID,
            Database.COLUMN_NOMBRE,
            Database.COLUMN_PRECIO,
            Database.COLUMN_DESCRIPCION,
            Database.COLUMN_MARCA,
            Database.COLUMN_STOCK,
            Database.COLUMN_PROVEEDOR};

    public long getId() {
        return id;
    }

    //Método que desplegará nombres en la lista de los contactos.
    @Override
    public String toString() {
        return nombre + " ";
    }

    //Se instancía un objeto de la clase que estamos creando a partir de este contexto
    public Producto (Context context) {
        dbHelper = new Database(context);
    }

    //Método que nos permite abrir la base de datos para su escritura
    public void open() throws SQLException {
        //Se asigna la base de datos modificable a la variable database
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    //Método que nos permite crear un contacto a partir de lo que le pasemos en NewActivity
    public Producto crearProducto(String nombre,String precio, String descripcion, String marca, String stock, String proveedor) {
        //ContentValues nos permite almacenar un set de valores
        ContentValues values = new ContentValues();
        //Le decimos a values qué valor se agregará en qué columna
        values.put(Database.COLUMN_NOMBRE, nombre);
        values.put(Database.COLUMN_PRECIO, precio);
        values.put(Database.COLUMN_DESCRIPCION, descripcion);
        values.put(Database.COLUMN_MARCA, marca);
        values.put(Database.COLUMN_STOCK, stock);
        values.put(Database.COLUMN_PROVEEDOR, proveedor);
        //Con el método insert agregamos los valores a la BD
        //Además, el método "insert" regresa el id, por eso lo almacenamos
        long insertId = database.insert(Database.TABLE_PRODUCTOS, null, values);
        //Creamos un cursor para desplazarnos por las filas de la tabla con "query" con el ID dado
        Cursor cursor = database.query(Database.TABLE_PRODUCTOS,
                allColumns, Database.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Producto nuevoProducto = cursorToProducto(cursor);
        //Cerramos el cursor
        cursor.close();
        return nuevoProducto;
    }

    //Método para actualizar contactos a partir de los datos pasados por EditActivity
    public void actualizarProducto(long id, String nombre, String precio, String descripcion, String marca, String stock, String proveedor) {
        ContentValues values = new ContentValues();
        values.put(Database.COLUMN_NOMBRE, nombre);
        values.put(Database.COLUMN_PRECIO, precio);
        values.put(Database.COLUMN_DESCRIPCION, descripcion);
        values.put(Database.COLUMN_MARCA, marca);
        values.put(Database.COLUMN_STOCK, stock);
        values.put(Database.COLUMN_PROVEEDOR, proveedor);
        String where = "id=?"; //Permite que whereargs se utilice en vez de where
        String[] whereargs = new String[]{String.valueOf(id)};
        long insertId = database.update(Database.TABLE_PRODUCTOS,
                values,where,whereargs );
    }

    //Se elimina un contacto a partir de su ID.
    public void borrarProducto(long id) {
        System.out.println("Producto con id '"+id+"' eliminado.");
        database.delete(Database.TABLE_PRODUCTOS, Database.COLUMN_ID
                + " = " + id, null);
    }

    //Método que nos devuelve una lista con todos los contactos
    public List<Producto> getAll() {
        //Creamos un objeto de tipo lista que contiene contactos
        List<Producto> productos = new ArrayList<Producto>();

        //Creamos un cursor sobre la tabla de contactos
        Cursor cursor = database.query(Database.TABLE_PRODUCTOS,
                allColumns, null, null, null, null, null);

        //Nos desplazamos por la tabla y agregamos los elementos a la lista
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Producto producto= cursorToProducto(cursor);
            productos.add(producto);
            cursor.moveToNext();
        }
        //Debemos cerrar el cursor
        cursor.close();
        return productos;
    }

    //Método que nos devuelve los datos de un contacto utilizando un cursor
    private Producto cursorToProducto(Cursor cursor) {
        Producto producto = new Producto(null);
        //Obtenemos los valores a partir de su número de columna
        producto.id = cursor.getLong(0);
        producto.nombre = cursor.getString(1);
        producto.precio = cursor.getString(2);
        producto.descripcion = cursor.getString(3);
        producto.marca = cursor.getString(4);
        producto.stock = cursor.getString(5);
        producto.proveedor = cursor.getString(6);
        return producto;
    }
}
