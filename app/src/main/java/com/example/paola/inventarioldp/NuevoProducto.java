package com.example.paola.inventarioldp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoProducto extends AppCompatActivity {
    Button agregar;
    EditText nombre, precio, descripcion, marca, stock, proveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_producto);
        agregar = (Button) findViewById(R.id.agregar);
        nombre = (EditText) findViewById(R.id.nombre);
        precio = (EditText) findViewById(R.id.precio);
        descripcion = (EditText) findViewById(R.id.descripcion);
        marca = (EditText) findViewById(R.id.marca);
        stock = (EditText) findViewById(R.id.stock);
        proveedor = (EditText) findViewById(R.id.proveedor);
    }
    public void agregarProducto(View v){
        if(!nombre.equals("") && !precio.equals("") && !descripcion.equals("") &&
                !marca.equals("") && !stock.equals("") && !proveedor.equals("")){
            Producto producto = new Producto(getBaseContext());
            producto.open();
            producto.crearProducto(nombre.getText().toString(), precio.getText().toString(),
                    descripcion.getText().toString(), marca.getText().toString(),
                    stock.getText().toString(), proveedor.getText().toString());
            nombre.setText("");
            precio.setText("");
            descripcion.setText("");
            marca.setText("");
            stock.setText("");
            proveedor.setText("");
            Toast.makeText(getBaseContext(), "Se agregó un nuevo producto.",Toast.LENGTH_LONG).show();
            Intent i = new Intent(NuevoProducto.this, Administrar.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Por favor llena todos los campos.",Toast.LENGTH_LONG).show();
        }
    }
}
