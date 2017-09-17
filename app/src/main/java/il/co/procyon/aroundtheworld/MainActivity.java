package il.co.procyon.aroundtheworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import il.co.procyon.aroundtheworld.adapters.CountriesAdapter;
import il.co.procyon.aroundtheworld.managers.CountriesManager;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private CountriesAdapter mAdapter;

    public static Intent buildIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);

        return intent;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView countriesRecycler = (RecyclerView) findViewById(R.id.rv_countries);
        countriesRecycler.setLayoutManager(new LinearLayoutManager(this));
//        countriesRecycler.setHasFixedSize(true);
        mAdapter = new CountriesAdapter();
        countriesRecycler.setAdapter(mAdapter);

        populateCountries();

    }



    private void populateCountries() {
        mAdapter.setData(CountriesManager.getInstance().getAllContires());

        mAdapter.setOnItemClickCallback(new CountriesAdapter.ItemClickCallback() {
            @Override
            public void onItemClick(String countryCode) {
                startActivity(NeighbouringCountry.buildIntent(MainActivity.this, countryCode));
            }
        });
    }
}
