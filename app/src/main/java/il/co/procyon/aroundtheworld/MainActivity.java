package il.co.procyon.aroundtheworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import il.co.procyon.aroundtheworld.adapters.CountriesAdapter;
import il.co.procyon.aroundtheworld.managers.CountriesManager;
import il.co.procyon.aroundtheworld.managers.InitCallback;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private CountriesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView countriesRecycler = (RecyclerView) findViewById(R.id.rv_countries);
        countriesRecycler.setLayoutManager(new LinearLayoutManager(this));
        countriesRecycler.setHasFixedSize(true);
        mAdapter = new CountriesAdapter();
        countriesRecycler.setAdapter(mAdapter);

        initData();

    }

    private void initData() {
        CountriesManager.getInstance().initData(new InitCallback() {
            @Override
            public void onSuccess() {
                populateCountries();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void populateCountries() {
        mAdapter.setData(CountriesManager.getInstance().getAllContires());
    }
}
