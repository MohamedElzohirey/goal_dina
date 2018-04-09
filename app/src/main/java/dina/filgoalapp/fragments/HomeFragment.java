package dina.filgoalapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dina.filgoalapp.R;
import dina.filgoalapp.adapters.RecyclerviewAdapter;
import dina.filgoalapp.network.APIClient;
import dina.filgoalapp.network.APIInterface;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private APIInterface apiInterface;
    private RecyclerviewAdapter recyclerviewAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_home, container, false);
     //   return inflater.inflate(R.id.recycler_View,container,false);
     //   recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // apiInterface = APIClient.GetAPIClient().create(APIInterface.class);


    }

}
