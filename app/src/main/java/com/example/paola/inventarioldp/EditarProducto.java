package com.example.paola.inventarioldp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarProducto extends AppCompatActivity {
    Button agregar, eliminar;
    EditText nombre, precio, descripcion, marca, stock, proveedor;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);
        agregar = (Button) findViewById(R.id.agregar);
        eliminar = (Button) findViewById(R.id.eliminar);
        nombre = (EditText) findViewById(R.id.nombre);
        precio = (EditText) findViewById(R.id.precio);
        descripcion = (EditText) findViewById(R.id.descripcion);
        marca = (EditText) findViewById(R.id.marca);
        stock = (EditText) findViewById(R.id.stock);
        proveedor = (EditText) findViewById(R.id.proveedor);

        Intent i = getIntent();
        id = i.getLongExtra("id",0);
        nombre.setText(i.getStringExtra("nombre"));
        precio.setText(i.getStringExtra("precio"));
        descripcion.setText(i.getStringExtra("descripcion"));
        marca.setText(i.getStringExtra("marca"));
        stock.setText(i.getStringExtra("stock"));
        proveedor.setText(i.getStringExtra("proveedor"));
    }

    public void actualizarProducto(View v) {
        if(!nombre.equals("") && !precio.equals("") && !descripcion.equals("") &&
                !marca.equals("") && !stock.equals("") && !proveedor.equals("")){
            Producto producto = new Producto(getBaseContext());
            producto.open();
            producto.actualizarProducto(id, nombre.getText().toString(), precio.getText().toString(), descripcion.getText().toString(), marca.getText().toString(), stock.getText().toString(), descripcion.getText().toString());
            Toast.makeText(getBaseContext(), "El elemento ha sido actualizado.", Toast.LENGTH_LONG).show();
            Intent i = new Intent(EditarProducto.this, Administrar.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(getBaseContext(), "Por favor llena todos los campos.",Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarProducto(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(EditarProducto.this);
        builder.setTitle("Confirmación ");
        builder.setMessage("¿Está seguro de que desea eliminar el producto?");

        builder.setPositiveButton("SÍ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Producto producto = new Producto(getBaseContext());
                producto.open();
                producto.borrarProducto(id);
                finish();
                dialog.dismiss();
                Toast.makeText(getBaseContext(), "Producto eliminado.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(EditarProducto.this, Administrar.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
