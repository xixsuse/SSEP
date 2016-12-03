package artbaryl.ssep;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import layout.Play1_News;
import layout.Play2_Game;
import layout.Play3_Budget;

public class Play_activity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    int notificationId;
    NotificationManager notificationManager;
    Notification.Builder builder;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_game_tabbed_activity);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        sharedPreferences = getSharedPreferences("artbaryl.ssep", MODE_PRIVATE);

        //start();
    }





    private static final String ARG_SECTION_NUMBER = "section_number";

    public void Checkmission(View view) {
        Intent intent = new Intent(this, Missions.class);
        startActivity(intent);
    }

    public void Telescope(View view) {
        Intent intent = new Intent(this, Telescope.class);
        startActivity(intent);
    }


    public void Humanmission(View view) {
        if(sharedPreferences.getBoolean(String.valueOf(sharedPreferences.getInt("level", 0))+"level",false)==true) {
            Intent intent = new Intent(this, humanmission.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"You need use telescope first!",Toast.LENGTH_SHORT).show();
        }
    }

    public void Explorer(View view) {
        if(sharedPreferences.getBoolean(String.valueOf(sharedPreferences.getInt("level", 0))+"level",false)==true) {
            Intent intent = new Intent(this, Explorer.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"You need use telescope first!",Toast.LENGTH_SHORT).show();
        }
    }


    public  static   class PlaceholderFragment extends Fragment {


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1)
            {
                View rootView = inflater.inflate(R.layout.fragment_play1__news, container, false);
                return rootView;
            }
            if(getArguments().getInt(ARG_SECTION_NUMBER)==3)
            {
                View rootView = inflater.inflate(R.layout.fragment_play3__budget, container, false);
                return rootView;
            }
            else
            {
                View rootView = inflater.inflate(R.layout.fragment_play2__game, container, false);
                return rootView;
            }

        }
        public void setText(String text){
            Button textView = (Button) getActivity().findViewById(R.id.planet);
            textView.setText(text);
        }
    }


    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment =null;
            switch (position){
                case 0:fragment =  Play1_News.newInstance("String","string");
                    break;
                case 1:
                    fragment =  Play2_Game.newInstance("String","string");
                    break;
                case 2:
                    fragment =  Play3_Budget.newInstance("String","string");
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Page 01";
                case 1:
                    return "Page 02";
                case 2:
                    return "Page 03";
            }
            return null;
        }

    }



    public void start(){
        countDownTimer : new CountDownTimer(10*1000, 1000){
            @Override
            public void onTick(long millisUntilFinished)
            {
            }
            @Override
            public void onFinish(){

                notification();

            }
        }.start();
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