package layout;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import artbaryl.ssep.R;


public class Play1_News extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    TextView YourNews1, YourNews2, YourNews3;
    public Play1_News() {
    }

    // TODO: Rename and change types and number of parameters
    public static Play1_News newInstance(String param1, String param2) {
        Play1_News fragment = new Play1_News();
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
        View v = inflater.inflate(R.layout.fragment_play1__news, container, false);

        YourNews1 = (TextView) v.findViewById(R.id.textView24);
        YourNews2 = (TextView) v.findViewById(R.id.textView25);
        YourNews3 = (TextView) v.findViewById(R.id.textView22);
        setNews();

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void setNews()
    {
        YourNews1.setText("The new company enters the space industry.");
        YourNews2.setText("They prepares the first rocket to land on the moon.");
        YourNews3.setText("Let's check what they can do!");
        if(sharedPreferences.getBoolean(String.valueOf(sharedPreferences.getInt("level", 0))+"level",false)==true)
        {
            YourNews1.setText("Scientists used telescope to observe the moon");
            YourNews2.setText("They are waiting " + sharedPreferences.getInt("days", 0) + " days");
            YourNews3.setText("Let's check what they can do!");
        }
    }
}
