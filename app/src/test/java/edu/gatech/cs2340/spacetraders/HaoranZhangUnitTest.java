package edu.gatech.cs2340.spacetraders;

import org.junit.Test;

import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.City;

import static org.junit.Assert.*;

/**
 * This is a test class Haoran Zhang creates
 */
public class HaoranZhangUnitTest {
    /**
     * method to test whether the player is at the correct planet
     */
    @Test
    public void testPlayerGetCurrentPlanet() {
        Player testPlayer = new Player("Test", 5,5,5,
                5, "Normal", 84, 42);
        String testPlanet = testPlayer.getCurrentPlanet();
        assertEquals("Thessia", testPlanet);
    }

    /**
     * method to test whether the city has the correct price for furs
     */
    @Test
    public void testGoodPrice() {
        City testCity = new City("Test",50,50,2,7);

        double testFursCostMax = 295;
        double testFursCostMin = 245;
        double fursPrice = testCity.getPriceIndex().get("Furs");
        assertTrue((fursPrice < testFursCostMax) & (fursPrice > testFursCostMin));

        double testFoodCostMax = 110;
        double testFoodCostMin = 100;
        double foodPrice = testCity.getPriceIndex().get("Food");
        assertTrue((foodPrice < testFoodCostMax) & (foodPrice > testFoodCostMin));

        int TestGamesCost = 0;
        assertEquals((Object)testCity.getPriceIndex().get("Games"), TestGamesCost);
    }
}
