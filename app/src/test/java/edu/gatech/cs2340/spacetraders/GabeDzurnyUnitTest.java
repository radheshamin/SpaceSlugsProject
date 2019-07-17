package edu.gatech.cs2340.spacetraders;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Player;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GabeDzurnyUnitTest {

    /**
     * method  tests Player's getCurrentPlanet()
     */
    @Test
    public void testPlayerGetCurrentPlanet() {
        Player player;

        player = new Player("Bob", 4, 4, 4,
                4, "Easy", 12, 14);

        //Planet coordinates do not exist, should return null planet
        assertNull(player.getCurrentPlanet());


        player = new Player("Bob", 4, 4, 4,
                4, "Easy", 36, 67);

        //Planet coordinates should be those of "Endor"
        assertEquals("Endor", player.getCurrentPlanet());

    }

    /**
     * method tests city's calculateGoodPrice() through City() constructor
     */
    @Test
    public void testCityCalculateGoodPrice() {
        City city = new City("Name", 12,12,3,3);
        Map<String, Integer> priceIndex = city.getPriceIndex();


        // Cit must assign a price to these goods that is not 0
        assertTrue(priceIndex.get("Water") > 0);
        assertTrue(priceIndex.get("Furs") > 0);
        assertTrue(priceIndex.get("Food") > 0);
        assertTrue(priceIndex.get("Ore") > 0);
        assertTrue(priceIndex.get("Games") > 0);
        assertTrue(priceIndex.get("Firearms") > 0);

        // City has a tech level to low for following items, should return a price of 0
        assertEquals((Object)city.getPriceIndex().get("Medicine"), 0);
        assertEquals((Object)city.getPriceIndex().get("Machines"), 0);
        assertEquals((Object)city.getPriceIndex().get("Narcotics"), 0);
        assertEquals((Object)city.getPriceIndex().get("Robots"), 0);

    }

}
