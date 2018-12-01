package edu.oakland.nbayagich.learningdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    static public String dbname = "game.db";
    String player_table = "create table Player( " +
            "id integer primary key autoincrement," +
            "firstName text not null," +
            "lastName text not null" +
            ")";

    public MyDatabaseHelper(Context context)
    {
        super(context, dbname, null, 1);
    }

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, version);
    }

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, dbname, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(player_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Player");
        onCreate(db);
    }

    public ArrayList<String> getPlayers()
    {
        ArrayList<String> list = new ArrayList<>();

        String sql = "select id, firstName, lastName from Player";
        Cursor cur = this.getReadableDatabase().rawQuery(sql, null);

        int idCol = cur.getColumnIndex("id");
        int firstNameCol = cur.getColumnIndex("firstName");
        int lastNameCol = cur.getColumnIndex("lastName");

        while(cur.moveToNext())
        {
            String value;
            value = cur.getInt(idCol) + ": " + cur.getString(firstNameCol) + " " + cur.getString(lastNameCol);
            list.add(value);
        }


        return list;
    }

    public boolean createPlayer(String firstName, String lastName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        db.insert("Player", null, contentValues);

        return true;
    }
}
