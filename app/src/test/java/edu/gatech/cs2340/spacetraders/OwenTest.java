package edu.gatech.cs2340.spacetraders;

import edu.gatech.cs2340.spacetraders.entity.SpaceShip;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Owen Miller M9 JUnit Tests
 */
public class OwenTest {
    /**
     * Tests getCargoSpaceLeft method of SpaceShip class.
     */
    @Test
    public void getCargoSpaceLeftTest() {

        SpaceShip ship = new SpaceShip("Shup", 20, 50, 100);

        // default cargo space left should be what was initialized, 100
        assertEquals(100, ship.getCargoSpaceLeft());

        HashMap<String, Integer> goods = new HashMap<>();
        goods.put("Water", 8);
        goods.put("Furs", 5);
        goods.put("Food", 2);
        goods.put("Ore", 1);
        goods.put("Games", 18);
        goods.put("Firearms", 4);
        goods.put("Medicine", 13);
        goods.put("Machines", 0);
        goods.put("Narcotics", 42);
        goods.put("Robots", 2);
        ship.setGoods(goods);

        // cargo space should now be decremented by a total of 95 as a result of the for loop
        assertEquals(5, ship.getCargoSpaceLeft());

    }

    /**
     * Tests toString method of SpaceShip class.
     */
    @Test
    public void toStringTest() {

        SpaceShip ship = new SpaceShip("Shep", 20, 50, 100);

        // default values of goods should all be 0
        // fuel and cargoSpace should print the same as passed into constructor
        String expected = "Ship: Shep, Fuel: 20, Water: 0, Medicine: 0, Furs: 0, Ore: 0, " +
                "Narcotics: 0, Games: 0, Firearms: 0, Machines: 0, Robots: 0, Food: 0, " +
                "Cargo Space Left: 100";
        assertEquals(expected, ship.toString());

        // test that modifying fuel prints out changes
        ship.setFuel(10);
        expected = "Ship: Shep, Fuel: 10, Water: 0, Medicine: 0, Furs: 0, Ore: 0, " +
                "Narcotics: 0, Games: 0, Firearms: 0, Machines: 0, Robots: 0, Food: 0, " +
                "Cargo Space Left: 100";
        assertEquals(expected, ship.toString());

        HashMap<String, Integer> goods = new HashMap<>();
        goods.put("Water", 8);
        goods.put("Furs", 5);
        goods.put("Food", 2);
        goods.put("Ore", 1);
        goods.put("Games", 18);
        goods.put("Firearms", 4);
        goods.put("Medicine", 13);
        goods.put("Machines", 0);
        goods.put("Narcotics", 42);
        goods.put("Robots", 2);
        ship.setGoods(goods);

        // correct good values should be printed out as a result of the for loop
        expected = "Ship: Shep, Fuel: 10, Water: 8, Medicine: 13, Furs: 5, Ore: 1, " +
                "Narcotics: 42, Games: 18, Firearms: 4, Machines: 0, Robots: 2, Food: 2, " +
                "Cargo Space Left: 5";

        assertEquals(expected, ship.toString());

    }
}