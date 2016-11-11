package layout;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button b;
    private OnFragmentInteractionListener mListener;

    public Play2_Game() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Play2_Game.
     */
    // TODO: Rename and change types and number of parameters
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_play2__game, container, false);

         b = (Button) v.findViewById(R.id.button5);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ChoosePlanet(v);
            }
        });
        return v;



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
            }
        });
        builder.create();
        builder.show();
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setText(String text){
        Button button = (Button) getView().findViewById(R.id.button5);
        button.setText(text);
    }
}
