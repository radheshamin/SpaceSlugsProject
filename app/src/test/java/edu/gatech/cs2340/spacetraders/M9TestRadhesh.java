package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class M9TestRadhesh {

    @Test
    public void testPlayerCurrentPlanet() {
        Player player = new Player("Rad", 4, 4, 4, 4, "Easy", -1, -1);
        assertNull(player.getCurrentPlanet());

        player.setCoordinates(new ArrayList<>(Arrays.asList(61, 66)));
        assertEquals("Naboo", player.getCurrentPlanet());

        player.setCoordinates(new ArrayList<>(Arrays.asList(61, 2)));
        assertNull(player.getCurrentPlanet());

        player.setCoordinates(new ArrayList<>(Arrays.asList(0, 0)));
        assertEquals("Citadel", player.getCurrentPlanet());

        player.setCoordinates(new ArrayList<>(Arrays.asList(36, 67)));
        assertEquals("Endor", player.getCurrentPlanet());

        player.setCoordinates(new ArrayList<>(Arrays.asList(66, 39)));
        assertEquals("Normandy", player.getCurrentPlanet());

        player.setCoordinates(new ArrayList<>(Arrays.asList(46, 84)));
        assertEquals("Hoth", player.getCurrentPlanet());

        player.setCoordinates(new ArrayList<>(Arrays.asList(-50, -50)));
        assertNull(player.getCurrentPlanet());
    }

    @Test
    public void testCalculateGoodPrice() {
        City city = new City("City", 0, 0, 9, 9);
        Map<String, Integer> priceIndex = city.getPriceIndex();
        assertEquals((Integer) 57, priceIndex.get("Water"));
        assertEquals((Integer) 340, priceIndex.get("Furs"));
        assertEquals((Integer) 140, priceIndex.get("Food"));
        assertEquals((Integer) 490, priceIndex.get("Ore"));
        assertEquals((Integer) 190, priceIndex.get("Games"));
        assertEquals((Integer) 800, priceIndex.get("Firearms"));
        assertEquals((Integer) 550, priceIndex.get("Medicine"));
        assertEquals((Integer) 750, priceIndex.get("Machines"));
        assertEquals((Integer) 1500, priceIndex.get("Narcotics"));
        assertEquals((Integer) 4550, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 4, 5);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 42, priceIndex.get("Water"));
        assertEquals((Integer) 290, priceIndex.get("Furs"));
        assertEquals((Integer) 57, priceIndex.get("Food"));
        assertEquals((Integer) 390, priceIndex.get("Ore"));
        assertEquals((Integer) 240, priceIndex.get("Games"));
        assertEquals((Integer) 1175, priceIndex.get("Firearms"));
        assertEquals((Integer) 650, priceIndex.get("Medicine"));
        assertEquals((Integer) 900, priceIndex.get("Machines"));
        assertEquals((Integer) 0, priceIndex.get("Narcotics"));
        assertEquals((Integer) 0, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 0, 2);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 30, priceIndex.get("Water"));
        assertEquals((Integer) 250, priceIndex.get("Furs"));
        assertEquals((Integer) 0, priceIndex.get("Food"));
        assertEquals((Integer) 0, priceIndex.get("Ore"));
        assertEquals((Integer) 0, priceIndex.get("Games"));
        assertEquals((Integer) 0, priceIndex.get("Firearms"));
        assertEquals((Integer) 0, priceIndex.get("Medicine"));
        assertEquals((Integer) 0, priceIndex.get("Machines"));
        assertEquals((Integer) 0, priceIndex.get("Narcotics"));
        assertEquals((Integer) 0, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 6, 8);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 48, priceIndex.get("Water"));
        assertEquals((Integer) 620, priceIndex.get("Furs"));
        assertEquals((Integer) 125, priceIndex.get("Food"));
        assertEquals((Integer) 430, priceIndex.get("Ore"));
        assertEquals((Integer) 220, priceIndex.get("Games"));
        assertEquals((Integer) 1025, priceIndex.get("Firearms"));
        assertEquals((Integer) 610, priceIndex.get("Medicine"));
        assertEquals((Integer) 840, priceIndex.get("Machines"));
        assertEquals((Integer) 3375, priceIndex.get("Narcotics"));
        assertEquals((Integer) 5000, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 3, 11);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 39, priceIndex.get("Water"));
        assertEquals((Integer) 280, priceIndex.get("Furs"));
        assertEquals((Integer) 110, priceIndex.get("Food"));
        assertEquals((Integer) 370, priceIndex.get("Ore"));
        assertEquals((Integer) 125, priceIndex.get("Games"));
        assertEquals((Integer) 1250, priceIndex.get("Firearms"));
        assertEquals((Integer) 0, priceIndex.get("Medicine"));
        assertEquals((Integer) 0, priceIndex.get("Machines"));
        assertEquals((Integer) 0, priceIndex.get("Narcotics"));
        assertEquals((Integer) 0, priceIndex.get("Robots"));
    }
}

