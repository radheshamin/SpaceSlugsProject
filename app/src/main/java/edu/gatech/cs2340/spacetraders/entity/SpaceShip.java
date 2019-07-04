package edu.gatech.cs2340.spacetraders.entity;

import android.support.annotation.NonNull;

import java.util.HashMap;

public class SpaceShip {
    private int cargoSpace;
    private HashMap<String,Integer> goods;
    private int fuel;
    private final int maxFuel;
    private String name;

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


    public int getCargoSpaceLeft() {
        int total = 0;
        for (String key: goods.keySet()) {
            total += goods.get(key);
        }
        return cargoSpace - total;
    }
    public void setFuel(int fuel) {
        this.fuel = fuel;
    }
    public int getFuel() {
        return fuel;
    }
    public void setGoods(HashMap<String, Integer> goods){
        this.goods = goods;
    }
    public HashMap<String, Integer> getGoods() {
        return goods;
    }
    public String getName() {
        return name;
    }
    public int getMaxFuel() {
        return maxFuel;
    }

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
