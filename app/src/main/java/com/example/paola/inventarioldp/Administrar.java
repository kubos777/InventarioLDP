package com.example.paola.inventarioldp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

public class Administrar extends ListActivity {
    Producto datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrar);
        datos = new Producto(this);
        datos.open();

        final List<Producto> values = datos.getAll();
        ArrayAdapter<Producto> adapter = new ArrayAdapter<Producto>(this, android.R.layout.simple_expandable_list_item_1,values);
        setListAdapter(adapter);
        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(Administrar.this, EditarProducto.class);
                i.putExtra("id",values.get(position).id);
                i.putExtra("nombre",values.get(position).nombre);
                i.putExtra("precio",values.get(position).precio);
                i.putExtra("descripcion",values.get(position).descripcion);
                i.putExtra("marca",values.get(position).marca);
                i.putExtra("stock",values.get(position).stock);
                i.putExtra("proveedor",values.get(position).proveedor);
                startActivity(i);
            }
        });
    }

    public void goToNuevoProducto(View v){
        Intent i = new Intent(Administrar.this, NuevoProducto.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }


}
