package dina.filgoalapp.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dina.filgoalapp.R;
import dina.filgoalapp.activities.MainActivity;
import dina.filgoalapp.adapters.MatchesRecyclerviewAdapter;
import dina.filgoalapp.adapters.MyAdapterSlider;
import dina.filgoalapp.adapters.RecyclerviewAdapter;
import dina.filgoalapp.models.MatchesModel;
import dina.filgoalapp.models.Model;
import dina.filgoalapp.models.ModelDays;
import dina.filgoalapp.models.ModelPages;
import dina.filgoalapp.network.APIClient;
import dina.filgoalapp.network.APIInterface;
import dina.filgoalapp.network.InternetChecker;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private List<ModelPages> modelsList;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private RadioButton todayButton, yesterdayButton, tomorrowButton;
    private MatchesModel matchesModel;
    FirebaseDatabase database;
    ProgressBar progressBar;
    View rootView;

    private RecyclerView recyclerViewmatches;
    private RecyclerView recyclerView;
    private APIInterface apiInterface;
    CircleIndicator indicator;
    private ProgressDialog dialog;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        database = FirebaseDatabase.getInstance();
        dialog = new ProgressDialog(getActivity());

        recyclerViewmatches = rootView.findViewById(R.id.recyclerView_matches);
        recyclerViewmatches.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewmatches.setHasFixedSize(true);
        recyclerViewmatches.setNestedScrollingEnabled(false);


        apiInterface = APIClient.GetAPIClient().create(APIInterface.class);
        mPager = rootView.findViewById(R.id.pager);
        indicator = rootView.findViewById(R.id.indicator);

        todayButton = rootView.findViewById(R.id.btn_today);
        yesterdayButton = rootView.findViewById(R.id.btn_yesterday);
        tomorrowButton = rootView.findViewById(R.id.btn_tomorrow);


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


        CallModelPages();
        CallModelMatches();
        return rootView;

    }


    private void AutoStrat() {

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == modelsList.size()) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }


    private void CallModelPages() {
        boolean isConnected = InternetChecker.isConnected(getActivity());
        if (isConnected) {
            Call<Model> call = apiInterface.getModel();

            dialog.setMessage("Doing something, please wait.");
            dialog.show();
            call.enqueue(new Callback<Model>() {
                @Override
                public void onResponse(Call<Model> call, Response<Model> response) {


                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    modelsList = response.body().getPages();

                    recyclerView.setAdapter(new RecyclerviewAdapter(getContext(), response.body().getPages()));
                    mPager.setAdapter(new MyAdapterSlider(getContext(), response.body().getPages()));
                    AutoStrat();
                    indicator.setViewPager(mPager);

                }

                @Override
                public void onFailure(Call<Model> call, Throwable t) {
                    Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                    t.printStackTrace();

                }
            });
        } else {
            Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();
        }


    }


    private void CallModelMatches() {
        boolean isConnected = InternetChecker.isConnected(getActivity());
        if (isConnected) {

            Call<MatchesModel> call = apiInterface.getMatchesModel();
            call.enqueue(new Callback<MatchesModel>() {
                @Override
                public void onResponse(Call<MatchesModel> call, Response<MatchesModel> response) {
                    matchesModel = response.body();
                    recyclerViewmatches.setAdapter(new MatchesRecyclerviewAdapter(getContext(), (ArrayList<ModelDays>) response.body().getTodayModelList()));
                    getDatFromFireBase();


                }

                @Override
                public void onFailure(Call<MatchesModel> call, Throwable t) {

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
                    if (value.containsKey(("00" + modelDays.getMatchID()))) {
                        HashMap newResult = (HashMap) value.get("00" + modelDays.getMatchID());
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
