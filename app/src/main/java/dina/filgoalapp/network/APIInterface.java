package dina.filgoalapp.network;

import java.util.ArrayList;

import dina.filgoalapp.models.MatchesModel;
import dina.filgoalapp.models.Model;
import dina.filgoalapp.models.ModelPages;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {




    @GET("landingPager.json")
    Call<Model>getModel();



    @GET("matchesAPI.json")
    Call<MatchesModel>getMatchesModel();
}
