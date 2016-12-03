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
                        "distance text, " +
                        "accuracy);" +
                        "");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public void dodajPlanete(String name, String degrees, String weight, String gravity, String distance, String accuracy){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("degrees",degrees);
        values.put("weight",weight);
        values.put("gravity",gravity);
        values.put("distance", distance);
        values.put("accuracy", accuracy);
        db.insertOrThrow("planet",null, values);
    }

    public Cursor dajWszystkie(){
        String[] kolumny={"nr","NAME","DEGREES","WEIGHT", "GRAVITY", "DISTANCE", "ACCURACY"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor =db.query("planet",kolumny,null,null,null,null,null, null);
        return kursor;
    }

    public void makedatabase()
    {
        dodajPlanete("Moon", "250", "0.0123", "0.1654", "1", "100");
        dodajPlanete("Mars", "210", "0.107", "0.376", "1.523", "100");
        dodajPlanete("Ganimedes", "110", "0.0248", "0.1456", "5.203", "100");
        dodajPlanete("Europa", "102", "0.00804", "0.13399", "5.203", "100");
        dodajPlanete("Jupiter", "152", "317.83", "2.53", "5.203", "100");
        dodajPlanete("Titan", "93", "0.0225", "0.1377", "9.537", "100");
        dodajPlanete("Saturn", "143", "95.162", "1.065", "9.537", "100");
        dodajPlanete("Titania", "60", "0.00059", "0.0385", "19.19", "100");
        dodajPlanete("Uranos", "68", "14.536", "0.886", "19.19", "100");
        dodajPlanete("Triton", "38", "0.00358", "0.0794", "30.047", "100");
        dodajPlanete("Neptune", "47", "17.148", "1.14", "30.047", "100");
    }

    public void change_accuracy(int nr, int accuracy){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("accuracy",Integer.toString(accuracy));
        String args[]= {nr+""};
        db.update("planet", values, "nr=?", args);
    }
}
