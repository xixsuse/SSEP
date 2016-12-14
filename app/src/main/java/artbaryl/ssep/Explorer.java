package artbaryl.ssep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import layout.planetdatabase;

public class Explorer extends AppCompatActivity {
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private TextView name, price, time;
    private EditText degrees, gravity, distance, error;
    boolean check=false;
    String check1, check2, check3, check4;
    float count=0, shared_error=100, explorer_degrees, explorer_gravity, explorer_distance, explorer_error, money;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_explorer);
        sharedPreferences = getSharedPreferences("artbaryl.ssep", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        name = (TextView)findViewById(R.id.textView10);
        price = (TextView)findViewById(R.id.textView16);
        time = (TextView)findViewById(R.id.textView12);
        degrees = (EditText)findViewById(R.id.editText3);
        gravity = (EditText)findViewById(R.id.editText2);
        distance = (EditText)findViewById(R.id.editText5);
        error = (EditText)findViewById(R.id.editText4);
        planetdatabase zb = new planetdatabase(this);
        Cursor k = zb.dajWszystkie();
        k.moveToPosition(sharedPreferences.getInt("photo", 0));
        name.setText(k.getString(1));
    }

    public void explorer_start(View view) {
        if(check == true) {
            if(sharedPreferences.getFloat("money", 30)>money) {
                planetdatabase zb = new planetdatabase(this);
                zb.change_accuracy(sharedPreferences.getInt("photo", 0) + 1, (int) shared_error);
                editor.putFloat("money", sharedPreferences.getFloat("money", 30) - money);
                editor.commit();
                Toast.makeText(getApplicationContext(), "We're send object", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "You have no money", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Count data first", Toast.LENGTH_SHORT).show();
        }
    }

    public void explorer_count(View view) {
        check1 = degrees.getText().toString();
        check2 = distance.getText().toString();
        check3 = gravity.getText().toString();
        check4 = error.getText().toString();
        if (check1.matches("") || check2.matches("") || check3.matches("") || check4.matches("")) {
            Toast.makeText(this, "You did not complete all data", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            planetdatabase zb = new planetdatabase(this);
            Cursor k = zb.dajWszystkie();
            check=true;
            k.moveToPosition(sharedPreferences.getInt("photo", 0));
            explorer_degrees = Math.abs(Float.parseFloat(degrees.getText().toString()) / Float.parseFloat(k.getString(2)) - 1);
            explorer_distance = Math.abs(Float.parseFloat(distance.getText().toString()) / Float.parseFloat(k.getString(5)) - 1);
            explorer_gravity = Math.abs(Float.parseFloat(gravity.getText().toString()) / Float.parseFloat(k.getString(4)) - 1);
            explorer_error = Float.parseFloat(error.getText().toString()) / 100;
            count = (explorer_degrees + explorer_error + explorer_gravity + explorer_distance) * 100 / 4;
            count = count-(count%1);
            float check = count;
            time.setText("New measurement error: " + count);
            shared_error=count;
            count +=20-Float.parseFloat(error.getText().toString());
            if (check==0)
                count+=Math.pow((20-check)/4,2);
            price.setText("Price: " + count + "mln");
            money=count;
        }
    }

    public void explorer_database(View view) {
        Intent intent = new Intent(this, Missions.class);
        startActivity(intent);
    }

    public void eplorer_help(View view) {
    }
}
