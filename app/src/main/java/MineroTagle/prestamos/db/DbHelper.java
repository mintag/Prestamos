package MineroTagle.prestamos.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "Prestamos";
    public static final String TABLE_CLIENTES = "Clientes";
    public static final String TABLE_PRESTAMOS = "Prestamos";
    public static final String TABLE_PAGOS = "Pagos";
    public static final String TABLE_ABONOS = "Abonos";


    public DbHelper(@Nullable Context context){
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CLIENTES + "(" +
                "clienteid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "telefono INTEGER NOT NULL," +
                "correo TEXT," +
                "direccion TEXT)";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_PRESTAMOS + "(" +
                "prestamoid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "clienteid INTEGER NOT NULL," +
                "monto INTEGER NOT NULL," +
                "fecha INTEGER NOT NULL," +
                "tasa INTEGER NOT NULL," +
                "FOREIGN KEY(clienteid) REFERENCES Clientes(clienteid))";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_PAGOS + "(" +
                "pagoid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "prestamoid INTEGER NOT NULL," +
                "monto INTEGER NOT NULL," +
                "fecha INTEGER NOT NULL," +
                "FOREIGN KEY(prestamoid) REFERENCES Prestamos(prestamoid))";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_ABONOS + "(" +
                "abonoid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "prestamoid INTEGER NOT NULL," +
                "monto INTEGER NOT NULL," +
                "fecha INTEGER NOT NULL," +
                "FOREIGN KEY(prestamoid) REFERENCES Prestamos(prestamoid))";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE " + TABLE_CLIENTES;
        db.execSQL(query);

        query = "DROP TABLE " + TABLE_PRESTAMOS;
        db.execSQL(query);

        query = "DROP TABLE " + TABLE_PAGOS;
        db.execSQL(query);

        query = "DROP TABLE " + TABLE_ABONOS;
        db.execSQL(query);

        this.onCreate(db);

    }
}
