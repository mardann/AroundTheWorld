package il.co.procyon.aroundtheworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import il.co.procyon.aroundtheworld.managers.CountriesManager;
import il.co.procyon.aroundtheworld.managers.InitCallback;
import il.co.procyon.aroundtheworld.testingObjects.CustomIdlingResource;

/**
 * Created by hanan on 16-Sep-17.
 */

public class SplashActivity extends AppCompatActivity implements InitCallback {
    private final String TAG = this.getClass().getSimpleName();


    @Nullable
    private CustomIdlingResource mIdlingResource;

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    boolean isSynced = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.splash_layout);

        init();
    }


    public void init() {
        Log.d(TAG, "init: ");
        CountriesManager.getInstance().initData(this);
    }

    @Override
    public void onSuccess() {
        Log.d(TAG, "init onSuccess: ");
//                SplashActivity.this.startActivity(MainActivity.buildIntent(SplashActivity.this));

        SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
        setSynced(true);

        finish();

    }

    @Override
    public void onFailure() {
        Log.d(TAG, "init onFailure: ");
        Toast.makeText(SplashActivity.this, "Error loading data", Toast.LENGTH_SHORT).show();
//        if (mIdlingResource != null) {
//            mIdlingResource.setIdleState(true);
//        }

    }

    @VisibleForTesting
    @NonNull
    public CustomIdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new CustomIdlingResource(this);
        }
        return mIdlingResource;
    }


}
