package ga.devel.silva.antrovazi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zouk on 16/12/17.
 */

public class DataBase {

    private static final String DB_NAME = "antrovazi.db";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "person";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;
    private static final String DB_INSERT = "insert into " + DB_TABLE + " (cpf, name, age, phone, email) values (?, ?, ?, ?, ?)";

    public DataBase(Context context) {
        this.context = context;
        OpenHelper openHelper = new OpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(DB_INSERT);
    }

    public long insert (String cpf, String name, String age, String phone, String email) {
        this.insertStmt.bindString(0, cpf);
        this.insertStmt.bindString(1, name);
        this.insertStmt.bindString(2, age);
        this.insertStmt.bindString(3, phone);
        this.insertStmt.bindString(4, email);

        return this.insertStmt.executeInsert();
    }

    public List<Person> queryAll () {
        List<Person> personList = new ArrayList<Person>();
        try {
            Cursor cursor = this.db.query(DB_TABLE, new String[]{"cpf", "name", "age", "phone", "email"},
                    null, null, null, null, null, null);
            int records = cursor.getCount();
            if (records != 0) {
                cursor.moveToFirst();
                do {
                    Person person = new Person(cursor.getString(0),
                            cursor.getString(1), cursor.getString(2),
                            cursor.getString(3), cursor.getString(4));
                    personList.add(person);
                }
                while (cursor.moveToNext());

                if (cursor !=null && ! cursor.isClosed()) {
                    cursor.close();
                }
                return personList;
            }
            else {
                return null;
            }
        }
        catch (Exception e) {
            return null;
        }
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        OpenHelper (Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        public void onCreate (SQLiteDatabase db) {
            String sql = "CREATE TABLE IF NOT EXISTS" + DB_TABLE +
                    " (cpf INTEGER PRIMARY KEY, name TEXT, age INTEGER, phone TEXT, email TEXT);";
            db.execSQL(sql);
        }
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}
