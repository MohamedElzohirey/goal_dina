package dina.filgoalapp.fragments;


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

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import dina.filgoalapp.R;
import dina.filgoalapp.activities.MainActivity;
import dina.filgoalapp.adapters.MyAdapterSlider;
import dina.filgoalapp.adapters.RecyclerviewAdapter;
import dina.filgoalapp.models.Model;
import dina.filgoalapp.models.ModelPages;
import dina.filgoalapp.network.APIClient;
import dina.filgoalapp.network.APIInterface;
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


    View rootView;
    private RecyclerView recyclerView;
    private APIInterface apiInterface;
    private RecyclerviewAdapter recyclerviewAdapter;
    CircleIndicator indicator;
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
        apiInterface = APIClient.GetAPIClient().create(APIInterface.class);
        mPager = rootView.findViewById(R.id.pager);
         indicator = rootView.findViewById(R.id.indicator);

        CallModelPages();

        return rootView;

    }



    private  void AutoStrat(){

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

        Call<Model> call = apiInterface.getModel();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                modelsList = response.body().getPages();

                recyclerView.setAdapter(new RecyclerviewAdapter(getContext(), response.body().getPages()));
                mPager.setAdapter(new MyAdapterSlider(getContext(), response.body().getPages()));
                AutoStrat();
                indicator.setViewPager(mPager);

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("ffffff : ", t.getMessage());

            }
        });
    }

}
