package MineroTagle.prestamos;

import MineroTagle.prestamos.db.DbHelper;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

public class AltaClientes extends AppCompatActivity {

    private CardView BtnAltaCliente;
    private EditText
            EditNombre,
            EditTel,
            EditEmail,
            EditDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alta_clientes);
        BtnAltaCliente = findViewById(R.id.btn_altacliente);
        EditNombre = findViewById(R.id.edit_nombre);
        EditTel = findViewById(R.id.edit_telefono);
        EditEmail = findViewById(R.id.edit_mail);
        EditDireccion = findViewById(R.id.edit_Direccion);

        BtnAltaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = EditNombre.getText().toString();
                double telefono = Double.parseDouble(EditTel.getText().toString());
                String correo = EditEmail.getText().toString();
                String direccion = EditDireccion.getText().toString();

                ContentValues registro = new ContentValues();
                registro.put("nombre", nombre);
                registro.put("telefono", telefono);
                registro.put("correo", correo);
                registro.put("direccion", direccion);


                DbHelper dbHelper = new DbHelper(AltaClientes.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                if(db != null){
                    Toast.makeText(AltaClientes.this, "Base de datos Abierta", Toast.LENGTH_LONG).show();
                    db.insert("Clientes", null, registro);
                }else{
                    Toast.makeText(AltaClientes.this, "Error al crear Base de Datos", Toast.LENGTH_LONG).show();
                }

                db.close();

            }
        });
    }
}