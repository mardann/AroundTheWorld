package il.co.procyon.aroundtheworld.api;

import java.util.List;

import il.co.procyon.aroundtheworld.objects.Country;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by hanan on 15-Sep-17.
 */

public interface Services {

    @GET("all")
    Observable<List<Country>> getCountries();
}
