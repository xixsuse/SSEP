package artbaryl.ssep;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import layout.planetdatabase;

public class NewOldGame extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_old_game);
        test = (Button) findViewById(R.id.button2);
        sharedPreferences = getSharedPreferences("artbaryl.ssep", MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }


    @Override
    public void onPause()
    {
        super.onPause();
        finish();
    }

    public void NewGame(View view) {
        editor.clear();
        editor.putBoolean("Newgame", true);
        editor.commit();
        planetdatabase zb = new planetdatabase(this);
        for(int x=0; x < 12; x++) {
            zb.change_accuracy(x, 100);
        }
        Intent intent = new Intent(this, Play_activity.class);
        startActivity(intent);
    }

    public void ContinueGame(View view) {
        Intent intent = new Intent(this, Play_activity.class);
        startActivity(intent);
    }
}
