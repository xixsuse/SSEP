package artbaryl.ssep;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Date;

public class MyBroadcastReceiver  extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, new Date().toString(),
                Toast.LENGTH_SHORT).show();
    }
}
