package MineroTagle.prestamos;

import MineroTagle.prestamos.db.DbHelper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

public class PerfilCliente extends AppCompatActivity {

    TextView
            TxtNombre,
            TxtTel,
            TxtCorreo,
            TxtDireccion;

    int idcliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        TxtNombre = findViewById(R.id.txt_nombrecli);
        TxtTel = findViewById(R.id.txt_telefonocli);
        TxtCorreo = findViewById(R.id.txt_correocli);
        TxtDireccion = findViewById(R.id.txt_direccioncli);

        Intent intent = getIntent();
        idcliente = intent.getIntExtra("id", 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatosCliente();
    }

    private void DatosCliente(){
        DbHelper dbHelper = new DbHelper(PerfilCliente.this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor fila = db.rawQuery("select clienteid, nombre, telefono, correo, direccion from Clientes" +
                " where clienteid = " + idcliente, null);

        if(fila.moveToFirst()){
            TxtNombre.setText(fila.getString(1));
            TxtTel.setText(fila.getString(2));
            TxtCorreo.setText(fila.getString(3));
            TxtDireccion.setText(fila.getString(4));
        }else{
            Toast.makeText(PerfilCliente.this, "Error al obtener cliente" + idcliente, Toast.LENGTH_LONG).show();
        }
    }
}