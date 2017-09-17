package il.co.procyon.aroundtheworld;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

/**
 * Created by hanan on 17-Sep-17.
 */


@RunWith(AndroidJUnit4.class)
public class SplashActivityTest {


    @Rule
    public IntentsTestRule<SplashActivity> mIntentsTestRule =
            new IntentsTestRule<>(SplashActivity.class);

    @Test
    public void testInit() {


        intended(hasComponent(MainActivity.class.getName()));

    }


}
