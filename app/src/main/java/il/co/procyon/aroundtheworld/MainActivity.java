package il.co.procyon.aroundtheworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import il.co.procyon.aroundtheworld.api.NetworkManager;
import il.co.procyon.aroundtheworld.objects.Country;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private final String TAG = this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         TextView helloWorld= (TextView) findViewById(R.id.test_btn);
        helloWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkManager.getAllCountries(new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                        final List<Country> body = response.body();
                        Log.d(TAG, "onResponse: list size= " + body.size());
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {

                    }
                });

            }
        });
    }
}
