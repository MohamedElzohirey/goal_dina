package dina.filgoalapp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit retrofit = null;

    public static Retrofit GetAPIClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl("http://122apps.com/pepsi/").addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }



}
