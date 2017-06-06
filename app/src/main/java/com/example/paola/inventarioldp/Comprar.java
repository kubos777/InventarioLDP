package com.example.paola.inventarioldp;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Comprar extends ListActivity {
    Producto datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);
        datos = new Producto(this);
        datos.open();

        final List<Producto> values = datos.getAll();
        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Comprar.this, "Se agregó el elemento al carrito.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void comprar(View v) {
        Toast.makeText(this, "Compra finalizada.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(Comprar.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }
}
