package edu.gatech.cs2340.spacetraders.entity;

import android.support.annotation.NonNull;

import java.util.HashMap;

/**
 * This is a class of the spaceship object
 */
public class SpaceShip {
    private int cargoSpace;
    private HashMap<String,Integer> goods;
    private int fuel;
    private final int maxFuel;
    private String name;

    /**
     * Constructor for a SpaceShip
     * @param name name of spaceship
     * @param fuel fuel of spaceship
     * @param maxFuel max fuel of spaceship
     * @param cargoSpace cargo space of spaceship
     */
    public SpaceShip(String name, int fuel, int maxFuel, int cargoSpace) {
        this.cargoSpace = cargoSpace;
        this.name = name;
        this.fuel = fuel;
        goods = new HashMap<>();
        goods.put("Water", 0);
        goods.put("Furs", 0);
        goods.put("Food", 0);
        goods.put("Ore", 0);
        goods.put("Games", 0);
        goods.put("Firearms", 0);
        goods.put("Medicine", 0);
        goods.put("Machines", 0);
        goods.put("Narcotics", 0);
        goods.put("Robots", 0);
        this.maxFuel = maxFuel;
    }

    /**
     * method to get cargo space left in a spaceship
     * @return cargo space left in the spaceship
     */
    public int getCargoSpaceLeft() {
        int total = 0;
        for (String key: goods.keySet()) {
            total += goods.get(key);
        }
        return cargoSpace - total;
    }

    /**
     * method to set the fuel of spaceship
     * @param fuel new amount of fuel of the spaceship
     */
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    /**
     * method to get the fuel of spaceship
     * @return the amount of fuel of the spaceship
     */
    public int getFuel() {
        return fuel;
    }

    /**
     * method to set the goods of spaceship
     * @param goods new collection of goods in the spaceship
     */
    public void setGoods(HashMap<String, Integer> goods){
        this.goods = goods;
    }

    /**
     * method to get the goods of spaceship
     * @return collection of goods in the spaceship
     */
    public HashMap<String, Integer> getGoods() {
        return goods;
    }

    /**
     * method to get the name of spaceship
     * @return name of the spaceship
     */
    public String getName() {
        return name;
    }

    /**
     * method to get the max fuel of spaceship
     * @return maximum amount of fuel of the spaceship
     */
    public int getMaxFuel() {
        return maxFuel;
    }

    /**
     * method to convert a spaceship object to string
     * @return a string that prints the name, fuel, all goods, and cargo space left of the spaceship
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder state = new StringBuilder("Ship: " + name + ", Fuel: " + fuel);
        int total = 0;
        for (String key: goods.keySet()) {
            state.append(", ").append(key).append(": ").append(goods.get(key));
            total += goods.get(key);
        }
        state.append(", Cargo Space Left: ").append((cargoSpace - total));
        return state.toString();
    }
}
