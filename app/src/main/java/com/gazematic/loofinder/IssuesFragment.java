package com.gazematic.loofinder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link IssuesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IssuesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static int [] prgmImages={R.drawable.toilet_0,R.drawable.toilet_1,R.drawable.toilet_2,R.drawable.toilet_3,R.drawable.toilet_4,R.drawable.toilet_5};
    public static String [] prgmNameList={"Rashtrapati Bhavan, Delhi", "Koramangala 4th Block, Bangalore", "Noida highway road","3rd Sector, Gurgaon","Koyembedu public toilet, Chennai","Kakriya public toilet, Ahmedabad"};


    ListView lv;
    Context context;

    ArrayList prgmName;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public IssuesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IssuesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IssuesFragment newInstance(String param1, String param2) {
        IssuesFragment fragment = new IssuesFragment();
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
        View v = inflater.inflate(R.layout.fragment_issues, container, false);

        lv = (ListView) v.findViewById(R.id.listView);
        lv.setAdapter(new CustomAdapter(getActivity(), prgmNameList, prgmImages));
        return v;


    }


}
