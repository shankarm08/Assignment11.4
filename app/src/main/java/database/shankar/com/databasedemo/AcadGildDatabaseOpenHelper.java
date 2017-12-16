package database.shankar.com.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shankar on 16/12/17.
 */

public class AcadGildDatabaseOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "EMPLOYEE";

    //created a database table name called Employee

    //note i have used autoincrement for the field id

    public static final String _ID = "id";  //fields of database table
    public static final String NAME = "name";
    public static final String LNAME = "lname";

    public static final String DB_NAME = "DMP.DB"; //database name

    public static final int DB_VER = 1;  //version of the database


    //syntax for creating a table

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + NAME + " TEXT NOT NULL, " + LNAME + " TEXT);";


    public AcadGildDatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    //methods which executes the database
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //write code to alter the table/s.
//if the table exists drop the table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


}
