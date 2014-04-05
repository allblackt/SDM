package com.tudor.sdm.test.localisation;

import com.tudor.sdm.RegionalLayout;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by tudor on 4/4/14.
 * Tests to ensure the validity of the read Regional Layout file and the actual reading process
 */
public class RegionalLayoutRead {

    RegionalLayout rl = null;
    private static Logger log = Logger.getLogger(RegionalLayoutRead.class.getName());

    @BeforeSuite
    public void loadRl () {
        rl = RegionalLayout.get();
    }

    @Test
    public void readAllLayouts() {
        Map<String, List<String>> districtLayout = rl.getDistrictLayout();
        Assert.assertNotEquals(districtLayout, null);
        Assert.assertNotEquals(districtLayout.size(), 0);
        for(Map.Entry<String, List<String>> entry : districtLayout.entrySet()) {
            Assert.assertNotEquals(entry, null);
            Assert.assertNotEquals(entry.getValue(), null);
            Assert.assertNotEquals(entry.getValue().size(), 0);
        }
    }

    @Test
    public void readAllCountries() {
        List<String> allCountries = rl.getCountryList();
        Assert.assertNotEquals(allCountries,null);
        Assert.assertNotEquals(allCountries.size(), 0);
    }

    @Test(dependsOnMethods = {"readAllCountries"})
    public void readDefaultCountry() {
        List<String> allCountries = rl.getCountryList();
        String defaultCountry = rl.getDefaultCountry();
        Assert.assertNotEquals(defaultCountry, null);

        int i = allCountries.indexOf(defaultCountry);
        Assert.assertNotEquals(i, -1);
    }
}
