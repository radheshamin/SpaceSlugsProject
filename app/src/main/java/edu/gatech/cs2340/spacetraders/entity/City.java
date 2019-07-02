package edu.gatech.cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class City implements java.io.Serializable{
    private String name;
    private List<Integer> location;
    private HashMap<String, Integer> priceIndex;

    public City(String name, int latitude, int longitude, int techLevel, int resources) {
        this.name = name;
        location = new ArrayList<Integer>();
        location.add(latitude);
        location.add(longitude);
        priceIndex = new HashMap<String, Integer>();
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

    private void calculateGoodPrice(String good, int basePrice, int increase, int varianceAmount, int techLevel, int baseTech, int resources) {
        int variance = (int) (Math.random() * 2);
        int cost;
        double varianceMoney = ((Math.random() * varianceAmount));
        if (techLevel < baseTech) {
            priceIndex.put(good, 0);
        } else {
            if (variance == 0) {
                cost = basePrice + (increase * (techLevel - baseTech)) + (int)((basePrice * varianceMoney)/100);
            } else {
                cost = basePrice + (increase * (techLevel - baseTech)) - (int)((basePrice * varianceMoney)/100);
            }
            if (good.equals("Water") && resources == 4) {
                cost = cost / 2;
            } else if (good.equals("Water") && resources == 3) {
                cost = cost * 2;
            } else if (good.equals("Furs") && resources == 7) {
                cost = cost / 2;
            } else if (good.equals("Furs") && resources == 8) {
                cost = cost * 2;
            } else if (good.equals("Food") && resources == 5) {
                cost = cost / 2;
            } else if (good.equals("Food") && resources == 6) {
                cost = cost * 2;
            } else if (good.equals("Ore") && resources == 1) {
                cost = cost / 2;
            } else if (good.equals("Ore") && resources == 2) {
                cost = cost * 2;
            } else if (good.equals("Games") && resources == 11) {
                cost = cost / 2;
            } else if (good.equals("Firearms") && resources == 12) {
                cost = cost / 2;
            } else if (good.equals("Medicine") && resources == 10) {
                cost = cost / 2;
            } else if (good.equals("Narcotics") && resources == 9) {
                cost = cost / 2;
            }
            priceIndex.put(good, cost);
        }
    }

    public String getName() {
        return name;
    }

    public List<Integer> getLocation() {
        return location;
    }

    public HashMap<String, Integer> getPriceIndex() {
        return priceIndex;
    }
}
