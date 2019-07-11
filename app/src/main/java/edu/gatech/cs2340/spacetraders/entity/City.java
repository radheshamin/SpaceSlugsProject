package edu.gatech.cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class City implements java.io.Serializable{
    private final String name;
    private final List<Integer> location;
    private final Map<String, Integer> priceIndex;

    /**
     * city object (variance amounts: 4, 10, 5, 10, 5, 100, 10, 5, 150, 100)
     *
     * @param name name of city
     * @param latitude latitude of city
     * @param longitude longitude of city
     * @param techLevel tech level of city
     * @param resources resources of city
     */
    public City(String name, int latitude, int longitude, int techLevel, int resources) {
        this.name = name;
        location = new ArrayList<>();
        location.add(latitude);
        location.add(longitude);
        priceIndex = new HashMap<>();
        calculateGoodPrice("Water", 30, 3, 4, techLevel, 0, resources);
        calculateGoodPrice("Furs", 250, 10, 10, techLevel, 0, resources);
        calculateGoodPrice("Food", 100, 5, 5, techLevel, 1, resources);
        calculateGoodPrice("Ore", 350, 20, 10, techLevel, 2, resources);
        calculateGoodPrice("Games", 250, -10, 5, techLevel, 3, resources);
        calculateGoodPrice("Firearms", 1250, -75, 100, techLevel, 3, resources);
        calculateGoodPrice("Medicine", 650, -20, 10, techLevel, 4, resources);
        calculateGoodPrice("Machines", 900, -30, 5, techLevel, 4, resources);
        calculateGoodPrice("Narcotics", 3500, -125, 150, techLevel, 5, resources);
        calculateGoodPrice("Robots", 5000, -150, 100, techLevel, 6, resources);
    }

    private void calculateGoodPrice(String good, int basePrice, int increase,
                                    int varianceAmount, int techLevel,
                                    int baseTech, int resources) {
        int variance = (int) (Math.random() * 2);
        int cost;
        double varianceMoney = ((Math.random() * varianceAmount));
        if (techLevel < baseTech) {
            priceIndex.put(good, 0);
        } else {
            if (variance == 0) {
                cost = basePrice + (increase * (techLevel - baseTech)) +
                        (int)((basePrice * varianceMoney)/100);
            } else {
                cost = (basePrice + (increase * (techLevel - baseTech))) -
                        (int) ((basePrice * varianceMoney) / 100);
            }
            if ("Water".equals(good) && (resources == 4)) {
                cost = cost / 2;
            } else if ("Water".equals(good) && (resources == 3)) {
                cost = cost * 2;
            } else if ("Furs".equals(good) && (resources == 7)) {
                cost = cost / 2;
            } else if ("Furs".equals(good) && (resources == 8)) {
                cost = cost * 2;
            } else if ("Food".equals(good) && (resources == 5)) {
                cost = cost / 2;
            } else if ("Food".equals(good) && (resources == 6)) {
                cost = cost * 2;
            } else if ("Ore".equals(good) && (resources == 1)) {
                cost = cost / 2;
            } else if ("Ore".equals(good) && (resources == 2)) {
                cost = cost * 2;
            } else if ("Games".equals(good) && (resources == 11)) {
                cost = cost / 2;
            } else if ("Firearms".equals(good) && (resources == 12)) {
                cost = cost / 2;
            } else if ("Medicine".equals(good) && (resources == 10)) {
                cost = cost / 2;
            } else if ("Narcotics".equals(good) && (resources == 9)) {
                cost = cost / 2;
            }
            priceIndex.put(good, cost);
        }
    }

    public String getName() {
        return name;
    }

    public List<Integer> getLocation() {
        return Collections.unmodifiableList(location);
    }

    public Map<String, Integer> getPriceIndex() {
        return Collections.unmodifiableMap(priceIndex);
    }
}
