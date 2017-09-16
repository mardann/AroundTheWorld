package il.co.procyon.aroundtheworld.managers;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import il.co.procyon.aroundtheworld.api.NetworkManager;
import il.co.procyon.aroundtheworld.objects.Country;
import rx.functions.Action1;

/**
 * Created by hanan on 16-Sep-17.
 */

public class CountriesManager {

    private final String TAG = this.getClass().getSimpleName();
    private static final CountriesManager ourInstance = new CountriesManager();

    private HashMap<String, Country> mCountriesList;

    public static CountriesManager getInstance() {
        return ourInstance;
    }

    private CountriesManager() {
        mCountriesList = new HashMap<>();
    }

    public void initData(final InitCallback initCallback) {
        NetworkManager.getAllCountries(new Action1<HashMap<String, Country>>() {
            @Override
            public void call(HashMap<String, Country> stringCountryHashMap) {
                mCountriesList = stringCountryHashMap;
                initCallback.onSuccess();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                initCallback.onFailure();
            }
        });
    }

    public ArrayList<Country> getAllContires(){
        return new ArrayList<Country>(mCountriesList.values());
    }

    public ArrayList<Country> getNeibouringCountries(String sourceCountryAlpha3) throws CountryNotFoundException{
        Country sourceCountry = mCountriesList.get(sourceCountryAlpha3);
        if (sourceCountry == null) {
            throw new CountryNotFoundException("can't find country matching " + sourceCountryAlpha3);
        }

        ArrayList<Country> neighbours = new ArrayList<>();
        for (String neighbourAlphaCode : sourceCountry.getBorders()){
            Country neighbour = mCountriesList.get(neighbourAlphaCode);
            if (neighbour == null) {
                Log.w(TAG, "getNeighbouringCountries: country " + neighbourAlphaCode + " not found");
                continue;
            }

            neighbours.add(neighbour);
        }

        return neighbours;
    }


    public class CountryNotFoundException extends Exception{

        public CountryNotFoundException(String message) {
            super(message);
        }
    }
}
