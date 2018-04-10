package dina.filgoalapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dina.filgoalapp.R;
import dina.filgoalapp.adapters.RecyclerviewAdapter;
import dina.filgoalapp.models.Model;
import dina.filgoalapp.models.ModelPages;
import dina.filgoalapp.network.APIClient;
import dina.filgoalapp.network.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
  //  private List<ModelPages> modelPages;

    View rootView;
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
        rootView = inflater.inflate(R.layout.fragment_fragment_home, container, false);
        recyclerView = rootView.findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        apiInterface = APIClient.GetAPIClient().create(APIInterface.class);
        CallModelPages();

        return rootView;

    }


    private void CallModelPages() {

        Call<Model> call = apiInterface.getModel();
        call.enqueue(new Callback <Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {


                recyclerView.setAdapter(new RecyclerviewAdapter(getContext(),response.body().getPages()));

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("ffffff : ", t.getMessage());

            }
        });
    }

}
