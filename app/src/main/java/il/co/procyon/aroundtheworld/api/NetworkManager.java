package il.co.procyon.aroundtheworld.api;

import java.util.List;

import il.co.procyon.aroundtheworld.objects.Country;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hanan on 15-Sep-17.
 */

public class NetworkManager {


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .client(new OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build();



    public static void getAllCountries(Callback<List<Country>> callback) {

        Call<List<Country>> call = retrofit.create(Services.class).getCountries();

        call.enqueue(callback);
    }

}
