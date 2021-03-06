package dina.filgoalapp.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dina.filgoalapp.R;
import dina.filgoalapp.adapters.MatchesRecyclerviewAdapter;
import dina.filgoalapp.adapters.RecyclerviewAdapter;
import dina.filgoalapp.models.MatchesModel;
import dina.filgoalapp.models.ModelDays;
import dina.filgoalapp.models.ModelPages;
import dina.filgoalapp.network.APIClient;
import dina.filgoalapp.network.APIInterface;
import dina.filgoalapp.network.InternetChecker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesFragment extends Fragment {
    private MatchesModel matchesModel;
    FirebaseDatabase database;

    private APIInterface apiInterface;
    private RecyclerView recyclerViewmatches;
    View rootView;
    private RadioButton todayButton, yesterdayButton, tomorrowButton;
    private ProgressDialog dialog;


    public MatchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_matches, container, false);
        dialog = new ProgressDialog(getActivity());

        todayButton = rootView.findViewById(R.id.btn_today);
        yesterdayButton = rootView.findViewById(R.id.btn_yesterday);
        tomorrowButton = rootView.findViewById(R.id.btn_tomorrow);
       recyclerViewmatches = rootView.findViewById(R.id.recyclerView_matches);
        recyclerViewmatches.setLayoutManager(new LinearLayoutManager(getContext()));
        apiInterface = APIClient.GetAPIClient().create(APIInterface.class);
        database = FirebaseDatabase.getInstance();


        todayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewmatches.setAdapter(new MatchesRecyclerviewAdapter(getContext(), (ArrayList<ModelDays>) matchesModel.getTodayModelList()));


            }
        });


        yesterdayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                recyclerViewmatches.setAdapter(new MatchesRecyclerviewAdapter(getContext(), (ArrayList<ModelDays>) matchesModel.getYesterdayModelList()));


            }
        });


        tomorrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewmatches.setAdapter(new MatchesRecyclerviewAdapter(getContext(), (ArrayList<ModelDays>) matchesModel.getTomorrowModelList()));

            }
        });

        CallModelMatches();
        return rootView;

    }


    private void CallModelMatches() {

        boolean isConnected = InternetChecker.isConnected(getActivity());
        if (isConnected) {
        Call<MatchesModel> call = apiInterface.getMatchesModel();
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        call.enqueue(new Callback<MatchesModel>() {
            @Override
            public void onResponse(Call<MatchesModel> call, Response<MatchesModel> response) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                matchesModel = response.body();
                recyclerViewmatches.setAdapter(new MatchesRecyclerviewAdapter(getContext(), (ArrayList<ModelDays>) response.body().getTodayModelList()));
                getDatFromFireBase();

            }

            @Override
            public void onFailure(Call<MatchesModel> call, Throwable t) {
                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
        } else {
            Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();
        }


    }


    void getDatFromFireBase() {
        final DatabaseReference myRef = database.getReference("live");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap value = (HashMap) dataSnapshot.getValue();
                for (ModelDays modelDays :
                        matchesModel.getTodayModelList()) {
                    if (value.containsKey(("00"+modelDays.getMatchID()))) {
                        HashMap newResult = (HashMap) value.get("00"+modelDays.getMatchID());
                        modelDays.setResult(newResult.get("firstTeam") + "-" + newResult.get("secondTeam"));
                        recyclerViewmatches.getAdapter().notifyDataSetChanged();
                    } else {
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.d("cancel : ", error.getMessage());
            }
        });
    }

}
