package il.co.procyon.aroundtheworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import il.co.procyon.aroundtheworld.adapters.CountriesAdapter;
import il.co.procyon.aroundtheworld.managers.CountriesManager;
import il.co.procyon.aroundtheworld.objects.Country;

public class NeighbouringCountry extends AppCompatActivity implements CountriesAdapter.ItemClickCallback, View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    private static final String COUNTRY_CODE = "COUNTRY_CODE";

    public static Intent buildIntent(Context context, String countryCode) {
        Intent intent = new Intent(context, NeighbouringCountry.class);
        intent.putExtra(COUNTRY_CODE, countryCode);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neibouring_country);
        String countryCode = getIntent().getStringExtra(COUNTRY_CODE);

        ActionBar toolbar = getSupportActionBar();
        toolbar.setDisplayShowHomeEnabled(true);
        toolbar.setDisplayHomeAsUpEnabled(true);

//find views
        RecyclerView mNeighboursRecycler = (RecyclerView) findViewById(R.id.rv_neighbours);
        RelativeLayout islandContainer = (RelativeLayout) findViewById(R.id.no_borders_wrapper);

        //setup views
        mNeighboursRecycler.setLayoutManager(new LinearLayoutManager(this));
        CountriesAdapter adapter = new CountriesAdapter();
        mNeighboursRecycler.setAdapter(adapter);

        adapter.setOnItemClickCallback(this);

        try {
            ArrayList<Country> neighbouringCountries = CountriesManager.getInstance().getNeibouringCountries(countryCode);

            Country country = CountriesManager.getInstance().getCountry(countryCode);
            setTitle(getString(R.string.country_neighbours, country.getName(), country.getNativeName()));


            if (!neighbouringCountries.isEmpty()) {
                adapter.setData(neighbouringCountries);
                islandContainer.setVisibility(View.GONE);
            } else {
                islandContainer.setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.tv_is_island)).setText(String.format(getString(R.string.island_notice), country.getName()));
            }

        } catch (CountriesManager.CountryNotFoundException e) {
            Toast.makeText(this, "Error loading countries", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    public void onItemClick(String countryCode) {
        startActivity(buildIntent(this, countryCode));
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick: id=" + view.getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.d(TAG, "onOptionsItemSelected: id= " + item.getItemId());

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
