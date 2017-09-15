package il.co.procyon.aroundtheworld.api;

import java.util.List;

import il.co.procyon.aroundtheworld.objects.Country;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hanan on 15-Sep-17.
 */

public interface Services {

    @GET("all")
    Call<List<Country>> getCountries();
}
