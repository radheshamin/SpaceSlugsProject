package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Player;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class M9TestRadhesh {

    /**
     * Naboo 61,66
     * Hoth 46,84
     * Tatooine 82,63
     * Coruscant 26,68
     * Dagobah 32,24
     * Alderaan 25,26
     * Endor 36,67
     * Bespin 23,46
     * Cantonica 22,22
     * Jakku 52,58
     * Citadel 0,0
     * Thessia 84,42
     * Virmire 57,26
     * Normandy 66,39
     */
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

    /**
     * Good baseprice, increase, baseTech, DR, IR
     * Water 30, 3, 0, 4, 3
     * Furs 250, 10, 0, 7, 8
     * Food 100, 5, 1, 5, 6
     * Ore 350, 20, 2, 1, 2
     * Games 250, -10, 3, 11, not
     * Firearms 1250, -75, 3, 12, not
     * Medicine 650, -20, 4, 10, not
     * Machines 900, -30, 4, not, not
     * Narcotics 3500, -125, 5, 9, not
     * Robots 5000, -150, 6, not, not
     */
    @Test
    public void testCalculateGoodPrice() {
        City city = new City("City", 0, 0, 7, 9);
        Map<String, Integer> priceIndex = city.getPriceIndex();
        assertEquals((Integer) 51, priceIndex.get("Water"));
        assertEquals((Integer) 320, priceIndex.get("Furs"));
        assertEquals((Integer) 130, priceIndex.get("Food"));
        assertEquals((Integer) 450, priceIndex.get("Ore"));
        assertEquals((Integer) 210, priceIndex.get("Games"));
        assertEquals((Integer) 950, priceIndex.get("Firearms"));
        assertEquals((Integer) 590, priceIndex.get("Medicine"));
        assertEquals((Integer) 810, priceIndex.get("Machines"));
        assertEquals((Integer) 1625, priceIndex.get("Narcotics"));
        assertEquals((Integer) 4850, priceIndex.get("Robots"));

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

        city = new City("City", 0, 0, 5, 6);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 45, priceIndex.get("Water"));
        assertEquals((Integer) 300, priceIndex.get("Furs"));
        assertEquals((Integer) 240, priceIndex.get("Food"));
        assertEquals((Integer) 410, priceIndex.get("Ore"));
        assertEquals((Integer) 230, priceIndex.get("Games"));
        assertEquals((Integer) 1100, priceIndex.get("Firearms"));
        assertEquals((Integer) 630, priceIndex.get("Medicine"));
        assertEquals((Integer) 870, priceIndex.get("Machines"));
        assertEquals((Integer) 3500, priceIndex.get("Narcotics"));
        assertEquals((Integer) 0, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 1, 4);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 16, priceIndex.get("Water"));
        assertEquals((Integer) 260, priceIndex.get("Furs"));
        assertEquals((Integer) 100, priceIndex.get("Food"));
        assertEquals((Integer) 0, priceIndex.get("Ore"));
        assertEquals((Integer) 0, priceIndex.get("Games"));
        assertEquals((Integer) 0, priceIndex.get("Firearms"));
        assertEquals((Integer) 0, priceIndex.get("Medicine"));
        assertEquals((Integer) 0, priceIndex.get("Machines"));
        assertEquals((Integer) 0, priceIndex.get("Narcotics"));
        assertEquals((Integer) 0, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 2, 1);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 36, priceIndex.get("Water"));
        assertEquals((Integer) 270, priceIndex.get("Furs"));
        assertEquals((Integer) 105, priceIndex.get("Food"));
        assertEquals((Integer) 175, priceIndex.get("Ore"));
        assertEquals((Integer) 0, priceIndex.get("Games"));
        assertEquals((Integer) 0, priceIndex.get("Firearms"));
        assertEquals((Integer) 0, priceIndex.get("Medicine"));
        assertEquals((Integer) 0, priceIndex.get("Machines"));
        assertEquals((Integer) 0, priceIndex.get("Narcotics"));
        assertEquals((Integer) 0, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 7, 10);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 51, priceIndex.get("Water"));
        assertEquals((Integer) 320, priceIndex.get("Furs"));
        assertEquals((Integer) 130, priceIndex.get("Food"));
        assertEquals((Integer) 450, priceIndex.get("Ore"));
        assertEquals((Integer) 210, priceIndex.get("Games"));
        assertEquals((Integer) 950, priceIndex.get("Firearms"));
        assertEquals((Integer) 295, priceIndex.get("Medicine"));
        assertEquals((Integer) 810, priceIndex.get("Machines"));
        assertEquals((Integer) 3250, priceIndex.get("Narcotics"));
        assertEquals((Integer) 4850, priceIndex.get("Robots"));

        city = new City("City", 0, 0, 7, 3);
        priceIndex = city.getPriceIndex();
        assertEquals((Integer) 102, priceIndex.get("Water"));
        assertEquals((Integer) 320, priceIndex.get("Furs"));
        assertEquals((Integer) 130, priceIndex.get("Food"));
        assertEquals((Integer) 450, priceIndex.get("Ore"));
        assertEquals((Integer) 210, priceIndex.get("Games"));
        assertEquals((Integer) 950, priceIndex.get("Firearms"));
        assertEquals((Integer) 590, priceIndex.get("Medicine"));
        assertEquals((Integer) 810, priceIndex.get("Machines"));
        assertEquals((Integer) 3250, priceIndex.get("Narcotics"));
        assertEquals((Integer) 4850, priceIndex.get("Robots"));
    }
}

