package il.co.procyon.aroundtheworld.testingObjects;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

import il.co.procyon.aroundtheworld.SplashActivity;

/**
 * Created by hanan on 17-Sep-17.
 */

public class CustomIdlingResource implements IdlingResource {

    SplashActivity mActivity;

    @Nullable
    private volatile ResourceCallback mCallback;

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);

    public CustomIdlingResource(SplashActivity activity) {
        mActivity = activity;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        Boolean idle = isIdle();
        if (idle) mCallback.onTransitionToIdle();
        return idle;
    }

    public boolean isIdle() {
        return mActivity != null && mCallback != null && mActivity.isSynced();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        mCallback = callback;
    }

//    public void setIdleState(boolean isIdleNow){
//        mIsIdleNow.set(isIdleNow);
//        if (isIdleNow && mCallback != null) {
//            mCallback.onTransitionToIdle();
//        }
//    }
}
