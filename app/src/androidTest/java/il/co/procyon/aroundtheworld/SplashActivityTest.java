package il.co.procyon.aroundtheworld;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import il.co.procyon.aroundtheworld.testingObjects.CustomIdlingResource;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Created by hanan on 17-Sep-17.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class SplashActivityTest {
    private final String TAG = this.getClass().getSimpleName();

    @Rule
    public IntentsTestRule<SplashActivity> mIntentsTestRule =
            new IntentsTestRule<SplashActivity>(SplashActivity.class, true, true);


    private CustomIdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        // To prove that the test fails, omit this call:


        mIdlingResource = new CustomIdlingResource(mIntentsTestRule.getActivity());

        IdlingRegistry.getInstance().register(mIdlingResource);


    }

    /**
     * test app launches, loads data from network and automatically navigates to next activity. By detecting the "start activity" intent - the test completes
     */
    @Test
    public void testInit() {
        Log.d(TAG, "testInit: ");

        intended(hasComponent(MainActivity.class.getName()));

        //un-block this line to fail test
//        intended(hasComponent("bljb lkj"));


    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

}
