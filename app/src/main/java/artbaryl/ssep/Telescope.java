package artbaryl.ssep;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import layout.ProstySerwis;
import layout.planetdatabase;

public class Telescope extends AppCompatActivity {
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private TextView object, accuracy, cost;
    private EditText days;
    int day;
    int notificationId;
    NotificationManager notificationManager;
    Notification.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferences = getSharedPreferences("artbaryl.ssep", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        setContentView(R.layout.activity_telescope);
        object = (TextView)findViewById(R.id.textView4);
        accuracy = (TextView)findViewById(R.id.textView8);
        planetdatabase zb = new planetdatabase(this);
        Cursor k = zb.dajWszystkie();
        k.moveToPosition(sharedPreferences.getInt("photo", 0));
        object.setText("How many days you want observe " + k.getString(1)+"?");
        days = (EditText)findViewById(R.id.editText);
        cost = (TextView)findViewById(R.id.textView2);
    }

    public void days(View view) {
            day = 20 - Integer.parseInt(days.getText().toString());
            accuracy.setText("Measurement error is: " + day * 2 + "%");
            if (day == 20)
                accuracy.setText("Measurement error is: " + 100 + "%");
            cost.setText("Total cost: " + days.getText().toString() + "mld");
    }
    public void start(View view) {
        int accuracy=day*2;
        planetdatabase zb = new planetdatabase(this);
        zb.change_accuracy(sharedPreferences.getInt("photo", 0)+1,accuracy );
        editor.putFloat("money", sharedPreferences.getFloat("money", 30)-Integer.parseInt(days.getText().toString()));
        editor.commit();

        /*int i = 5;
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (i * 1000), 0, pendingIntent);
  //alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
    //+ (i * 1000), 8000, pendingIntent);
        Toast.makeText(this, "Starting alarm in " + i + " seconds",
                Toast.LENGTH_LONG).show();
    */
    }
    public void notification()
    {
        builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ganimedes)
                .setAutoCancel(true)
                .setContentTitle("Elo")
                .setContentText("Ziomek hehe");
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId, builder.build());
    }
}
