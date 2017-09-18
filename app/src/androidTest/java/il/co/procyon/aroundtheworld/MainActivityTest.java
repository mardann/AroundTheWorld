package il.co.procyon.aroundtheworld;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import il.co.procyon.aroundtheworld.managers.CountriesManager;
import il.co.procyon.aroundtheworld.objects.Country;

/**
 * Created by hanan on 18-Sep-17.
 */


@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mIntentsTestRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void initTestDb(){
        String mockData = mIntentsTestRule.getActivity().getString(R.string.countries_gson);

        CountriesManager.getInstance().testInit(mockData);
    }

    @Test
    public void testMockDbInitialize(){

        HashMap<String, Country>  countryHashMap = CountriesManager.getInstance().getCountiesMap();

        Assert.assertNotNull(countryHashMap);

        //mock db has correct number of countries (250)
        Assert.assertEquals("array size is off", 250, countryHashMap.values().size());
    }

    @Test
    public void testItemClickAction(){

    }
}
