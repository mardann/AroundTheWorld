package il.co.procyon.aroundtheworld.api;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import il.co.procyon.aroundtheworld.objects.Country;

/**
 * Created by hanan on 15-Sep-17.
 */

public class TestNetworkManager {

    @Test
    public void testGeCountries(){

        List<Country> countries = NetworkManager.getAllCountries();

        Assert.assertNotNull(countries);
    }
}
