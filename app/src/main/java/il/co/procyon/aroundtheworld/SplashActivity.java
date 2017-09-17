package il.co.procyon.aroundtheworld;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import il.co.procyon.aroundtheworld.managers.CountriesManager;
import il.co.procyon.aroundtheworld.managers.InitCallback;

/**
 * Created by hanan on 16-Sep-17.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);

        init();
    }

    public void init() {
        CountriesManager.getInstance().initData(new InitCallback() {
            @Override
            public void onSuccess() {
                SplashActivity.this.startActivity(MainActivity.buildIntent(SplashActivity.this));
//                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));

                finish();

            }

            @Override
            public void onFailure() {
                Toast.makeText(SplashActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
