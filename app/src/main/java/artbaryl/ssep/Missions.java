package artbaryl.ssep;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import layout.planetdatabase;

public class Missions extends AppCompatActivity {

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private ImageView mars;
    private TextView planet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_missions);
        sharedPreferences = getSharedPreferences("artbaryl.ssep", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mars = (ImageView) findViewById(R.id.imageView);
        hideplanets(sharedPreferences.getInt("photo", 0));
        planet = (TextView)findViewById(R.id.planetname);

        planetdatabase zb = new planetdatabase(this);
        zb.dodajPlanete("Moon", "250", "00123", "01654", "384403");
        zb.dodajPlanete("Mars", "250", "0.0123", "0.1654", "384403");
        Cursor k = zb.dajWszystkie();
        k.moveToPosition(0);
            String imie = k.getString(1);
            planet.setText(imie);

    }


    public void hideplanets (int item) {
        switch (item){
            case 0:
                mars.setImageResource(R.mipmap.moon);
                break;
            case 1:
                mars.setImageResource(R.mipmap.mars);
                break;
            case 2:
                mars.setImageResource(R.mipmap.ganimedes);
                break;
            case 3:
                mars.setImageResource(R.mipmap.europa);
                break;
            case 4:
                mars.setImageResource(R.mipmap.jupiter);
                break;
            case 5:
                mars.setImageResource(R.mipmap.titan);
                break;
            case 6:
                mars.setImageResource(R.mipmap.saturn);
                break;
            case 7:
                mars.setImageResource(R.mipmap.titania);
                break;
            case 8:
                mars.setImageResource(R.mipmap.uranos);
                break;
            case 9:
                mars.setImageResource(R.mipmap.triton);
                break;
            case 10:
                mars.setImageResource(R.mipmap.neptune);
                break;
        }
    }
}
