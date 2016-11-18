package layout;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import artbaryl.ssep.Missions;
import artbaryl.ssep.Play_activity;
import artbaryl.ssep.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Play2_Game.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Play2_Game#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Play2_Game extends Fragment {
    CharSequence [] planets = {"Moon", "Mars", "Ganimedes" ,"Europa", "Jupiter", "Titan", "Saturn", "Titania", "Uranus", "Triton", "Neptune"};
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    NotificationManager notificationManager;

    boolean isNotificActive = false;

    int notifID = 33;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    Button b;
    boolean timer;
    TextView tl;
    ImageView mars;
    private OnFragmentInteractionListener mListener;
    private CountDownTimer countDownTimer;
    Play_activity siema = new Play_activity();

    public Play2_Game() {
        // Required empty public constructor
    }


    public static Play2_Game newInstance(String param1, String param2) {
        Play2_Game fragment = new Play2_Game();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        sharedPreferences = this.getActivity().getSharedPreferences("artbaryl.ssep", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_play2__game, container, false);
        mars = (ImageView) v.findViewById(R.id.mars);
        b = (Button) v.findViewById(R.id.button5);
        tl = (TextView) v.findViewById(R.id.tl);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ChoosePlanet(v);
            }
        });
        return v;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void ChoosePlanet(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose destiny");
        builder.setItems(planets, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int item) {
            b.setText(planets[item]);
                if(sharedPreferences.getBoolean("timer", false)==false) {
                    start();
                }
               hideplanets(item);
                //siema.notification();
                editor.putInt("photo", item);
                editor.commit();

            }
        });
        builder.create();
        builder.show();
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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

    private void start()
    {
        editor.putBoolean("timer", true);
        editor.commit();
        countDownTimer : new CountDownTimer(600*1000, 1000){
            @Override
            public void onTick(long millisUntilFinished)
            {

                tl.setText(""+millisUntilFinished/1000);
            }
          @Override
            public void onFinish(){
                tl.setText("Done !");
                editor.putBoolean("timer", false);
                editor.commit();

          }
        }.start();
    }


}
