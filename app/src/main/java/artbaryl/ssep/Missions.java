package artbaryl.ssep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import layout.planetdatabase;

public class Missions extends AppCompatActivity {

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private ImageView mars;
    private TextView planet, error, degrees, weight, distance, gravity;
    float planet_measurement, planet_degrees, planet_weight, planet_distance,planet_gravity;
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
        error = (TextView)findViewById(R.id.textView6);
        degrees = (TextView)findViewById(R.id.degrees);
        weight = (TextView)findViewById(R.id.textView3);
        distance = (TextView)findViewById(R.id.textView5);
        gravity = (TextView)findViewById(R.id.distance);

        planetdatabase zb = new planetdatabase(this);
        Cursor k = zb.dajWszystkie();
        k.moveToPosition(sharedPreferences.getInt("photo", 0));
        planet.setText(k.getString(1));

        if(Integer.parseInt(k.getString(6))==100)
        {
            error.setText("Measurement error: ?");
            degrees.setText("Degrees: ? K");
            weight.setText("Weight: ? earth mass");
            distance.setText("Distance: ? j.a.");
            gravity.setText("Gravity: ? g");
        }
        else {
            planet_measurement=Float.parseFloat(k.getString(6));
            Random rand = new Random();
            float random = rand.nextFloat() * (2* planet_measurement) + 100 - planet_measurement;
            random=random/100.0f;
            if (k.getString(6)=="0")
            {
                planet_degrees =  Float.parseFloat(k.getString(2));
                planet_weight =  Float.parseFloat(k.getString(3));
                planet_distance =  Float.parseFloat(k.getString(5));
                planet_gravity =  Float.parseFloat(k.getString(4));
            }
            else {
                planet_degrees = random * Float.parseFloat(k.getString(2));
                planet_weight = random * Float.parseFloat(k.getString(3));
                planet_distance = random * Float.parseFloat(k.getString(5));
                planet_gravity = random * Float.parseFloat(k.getString(4));
                planet_degrees = planet_degrees - planet_degrees % 1f;
                planet_weight = planet_weight - planet_weight % 0.00001f;
                planet_gravity = planet_gravity - planet_gravity % 0.0001f;
                planet_distance = planet_distance - planet_distance % 0.001f;
            }
            error.setText("Measurement error: " + planet_measurement + "%");
            degrees.setText("Degrees: " + planet_degrees + " K");
            weight.setText("Weight: " + planet_weight + " earth mass");
            distance.setText("Distance: " + planet_distance + " j.a.");
            gravity.setText("Gravity: " + planet_gravity + " g");
        }

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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
            Intent intent = new Intent(this, Play_activity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);

    }
}
