package MineroTagle.prestamos;

import MineroTagle.prestamos.db.DbHelper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private TextView TXT;
    private CardView
            BtnNuevoCliente,
            BtnSeleccionaCliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TXT = findViewById(R.id.texto);
        BtnNuevoCliente = findViewById(R.id.btn_nuevocliente);
        BtnSeleccionaCliente = findViewById(R.id.btn_seleccionacliente);


        BtnNuevoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AltaClientes.class);
                startActivity(intent);
            }
        });

        BtnSeleccionaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SeleccionaCliente.class);
                startActivity(intent);
            }
        });
    }
}