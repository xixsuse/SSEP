package artbaryl.ssep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import layout.planetdatabase;

public class humanmission extends AppCompatActivity {
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    TextView name, cost, success,total;
    Switch aSwitch;
    int money;
    float randcheck;
    boolean check=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferences = getSharedPreferences("artbaryl.ssep", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setContentView(R.layout.activity_humanmission);
        name = (TextView)findViewById(R.id.textView14);
        cost = (TextView)findViewById(R.id.textView21);
        success = (TextView)findViewById(R.id.textView20);
        total = (TextView)findViewById(R.id.totalcost);
        planetdatabase zb = new planetdatabase(this);
        Cursor k = zb.dajWszystkie();
        k.moveToPosition(sharedPreferences.getInt("photo", 0));
        name.setText(k.getString(1));
        money = 5*Integer.parseInt(k.getString(6));
        randcheck = 2*Integer.parseInt(k.getString(6));
        aSwitch = (Switch)findViewById(R.id.switch2);
        success.setText("mission success: " + (100-randcheck) + "%");
        cost.setText("Cost of the mission: " + 5*Integer.parseInt(k.getString(6)) + "mln" );
        total.setText("Total cost: " + money + "mln");
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    money+=3;
                    total.setText("Total cost: " + money +"mln");
                    check = true;
                }
                else
                {
                    money-=3;
                    total.setText("Total cost: " + money + "mln");
                    check = false;
                }
            }
        });
    }

    public void humanmission(View view) {
        Random rand = new Random();
        float random = rand.nextFloat()*100;
        random-=randcheck;
            if (sharedPreferences.getFloat("money", 30) >= (money)) {
                editor.putFloat("money", sharedPreferences.getFloat("money", 30) - money);
                if(random>0) {
                    planetdatabase zb = new planetdatabase(this);
                    zb.change_accuracy(sharedPreferences.getInt("photo", 0) + 1, 0);
                    editor.putInt("level", sharedPreferences.getInt("level", 0)+1);
                    Toast.makeText(getApplicationContext(), "Mission Succesed!", Toast.LENGTH_SHORT).show();
                    editor.putFloat("money", sharedPreferences.getFloat("money", 30) + 40);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Mission Failed!", Toast.LENGTH_SHORT).show();
                    if(check==true)
                    {
                        editor.putFloat("money", sharedPreferences.getFloat("money", 30) + money*0.8f);
                    }

                }
            } else {
                Toast.makeText(getApplicationContext(), "You have no money!", Toast.LENGTH_SHORT).show();
            }

        editor.commit();
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
