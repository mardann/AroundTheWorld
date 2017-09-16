package il.co.procyon.aroundtheworld.api;

import java.util.HashMap;
import java.util.List;

import il.co.procyon.aroundtheworld.objects.Country;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hanan on 15-Sep-17.
 */

public class NetworkManager {


    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://restcountries.eu/rest/v2/")
            .client(new OkHttpClient())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();




    public static void getAllCountries(Action1<HashMap<String, Country>> callback, Action1<Throwable> onError) {

        Observable<List<Country>> listObservable = retrofit.create(Services.class).getCountries();

        listObservable.map(getMappingFunc())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .doOnError(onError)
        .subscribe(callback);



    }

    private static Func1<List<Country>, HashMap<String, Country>> getMappingFunc(){
        Func1<List<Country>, HashMap<String, Country>> func1 = new Func1<List<Country>, HashMap<String, Country>>() {
            @Override
            public HashMap<String, Country> call(List<Country> countries) {
                HashMap<String, Country> output = new HashMap<>();
                for(Country country : countries){
                    output.put(country.getAlpha3Code(), country);
                }
                return output;
            }
        };
        return func1;
    }

}
