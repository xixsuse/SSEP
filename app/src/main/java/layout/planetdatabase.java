package layout;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Artur on 26.11.2016.
 */

public class planetdatabase extends SQLiteOpenHelper{
        public planetdatabase(Context context)
        {
            super(context, "Planets.db", null, 1);
        }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "create table planet (" +
                        "nr integer primary key autoincrement, " +
                        "name text, " +
                        "degrees text, " +
                        "weight text, " +
                        "gravity text, " +
                        "distance text);" +
                        "");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public void dodajPlanete(String name, String degrees, String weight, String gravity, String distance){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("degrees",degrees);
        values.put("weight",weight);
        values.put("gravity",gravity);
        values.put("distance", distance);
        db.insertOrThrow("planet",null, values);
    }

    public Cursor dajWszystkie(){
        String[] kolumny={"nr","NAME","DEGREES","WEIGHT", "GRAVITY", "DISTANCE"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("planet",kolumny,null,null,null,null,null, null);
        return kursor;
    }
}
