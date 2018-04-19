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
import android.widget.Toast;

import java.util.List;

import dina.filgoalapp.R;
import dina.filgoalapp.adapters.MyAdapterSlider;
import dina.filgoalapp.adapters.RecyclerviewAdapter;
import dina.filgoalapp.models.Model;
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
public class NewsFragment extends Fragment {
    private List<ModelPages> modelsList;
    private RecyclerView recyclerView;
    private APIInterface apiInterface;
    private RecyclerviewAdapter recyclerviewAdapter;
    View rootView;
    private ProgressDialog dialog;


    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_news, container, false);
        dialog = new ProgressDialog(getActivity());


        recyclerView = rootView.findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        apiInterface = APIClient.GetAPIClient().create(APIInterface.class);

        CallModelPages();
        return rootView;

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


            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.d("ffffff : ", t.getMessage());

            }
        });

        } else {
            Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_LONG).show();
        }

    }

}
