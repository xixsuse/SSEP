package layout;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

public class ProstySerwis extends IntentService{
    private int i;
    int notificationId;
    NotificationManager notificationManager;
    Notification.Builder builder;
    private Handler handler = new Handler();
    public ProstySerwis() {
        super("ProstySerwis");
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onHandleIntent(Intent arg0) {

        while(i<10){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                public void run() {
                    Toast.makeText(getApplicationContext(), "WITAJ W SERWISIE", Toast.LENGTH_SHORT).show();
                }
            });
            i++;
        }
    }

}