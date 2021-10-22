package MineroTagle.prestamos;

import MineroTagle.prestamos.db.DbHelper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class SeleccionaCliente extends AppCompatActivity {

    ArrayList <String> listaClientes;
    ListView listViewClientes;
    CardView btnBuscar;
    EditText editNombreCli;
    Cursor fila;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_cliente);
        listaClientes = new ArrayList<String>();
        listViewClientes = findViewById(R.id.lista_clientes);
        btnBuscar = findViewById(R.id.btn_buscar);
        editNombreCli = findViewById(R.id.edit_busca);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaClientes.clear();
                DbHelper dbHelper = new DbHelper(SeleccionaCliente.this);

                SQLiteDatabase db = dbHelper.getReadableDatabase();

                fila = db.query(true, dbHelper.TABLE_CLIENTES, new String[] {"clienteid","nombre"},
                        "nombre LIKE ?", new String[] {editNombreCli.getText().toString() + "%"},
                        null, null, null, null);

                boolean siguiente=fila.moveToFirst();
                if(siguiente) {
                    while (siguiente) {

                        listaClientes.add("ID: " + fila.getString(0) + "\n"+
                                "Nombre: " + fila.getString(1));
                        siguiente = fila.moveToNext();
                    }
                }else{
                    Toast.makeText(SeleccionaCliente.this, "Sin resultados", Toast.LENGTH_LONG).show();
                }
                db.close();

                String datos[] = listaClientes.toArray(new String[listaClientes.size()]);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SeleccionaCliente.this, android.R.layout.simple_list_item_1, datos);
                listViewClientes.setAdapter(adapter);
            }
        });

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(fila.moveToPosition(position)) {
                    Intent intent = new Intent(SeleccionaCliente.this, PerfilCliente.class);
                    intent.putExtra("id",fila.getInt(0));
                    startActivity(intent);
                }else{
                    Toast.makeText(SeleccionaCliente.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}