package database.shankar.com.databasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Shankar on 12/16/17.
 */

public class DatabaseManager {
    private AcadGildDatabaseOpenHelper acadGildDatabaseOpenHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {

        this.context = context;
    }

    //opens the database and you can read or write the database

    public DatabaseManager open(boolean isReadable) {
        acadGildDatabaseOpenHelper = new AcadGildDatabaseOpenHelper(context);
        if (isReadable) {
            sqLiteDatabase = acadGildDatabaseOpenHelper.getReadableDatabase();
        } else {
            sqLiteDatabase = acadGildDatabaseOpenHelper.getWritableDatabase();
        }
        return this;
    }

    public void close() {

        acadGildDatabaseOpenHelper.close();
    }

    //note we are inserting the values in the database using contentValues

    public void insertEmployee(String name, String lname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AcadGildDatabaseOpenHelper.NAME, name);
        contentValues.put(AcadGildDatabaseOpenHelper.LNAME, lname);

        sqLiteDatabase.insert(AcadGildDatabaseOpenHelper.TABLE_NAME, null, contentValues); //if field value contains null it replaces with null
    }


    //now to read the data from the database table we use cursor which goes through each row

    public Cursor getEmployees() {
        String[] columns = new String[]{AcadGildDatabaseOpenHelper._ID,
                AcadGildDatabaseOpenHelper.NAME,
                AcadGildDatabaseOpenHelper.LNAME};

//        String whereClause = AcadGildDatabaseOpenHelper.NAME + "=?";
//        String[] whereArgs = new String[]{"Shankar"};
//        Cursor cursor = sqLiteDatabase.query(AcadGildDatabaseOpenHelper.TABLE_NAME, columns, whereClause, whereArgs, null, null, null);

        Cursor cursor = sqLiteDatabase.query(AcadGildDatabaseOpenHelper.TABLE_NAME, columns, null, null, null, null, null);
//moveToFirst goes to the firstrow of the table
        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

      }


