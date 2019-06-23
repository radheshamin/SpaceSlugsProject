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
        int variance = (int) (Math.random() * 2);
        int waterCost;
        if (variance == 0) {
            waterCost = 30 + (3 * (techLevel)) + (30 * (((int)(Math.random() * 4)) /100 ));
        } else {
            waterCost = 30 + (3 * (techLevel)) - (30 * (((int) (Math.random() * 4)) /100 ));
        }
        if (resources == 4) {
            waterCost = waterCost / 2;
        } else if (resources == 3) {
            waterCost = waterCost * 2;
        }
        priceIndex.put("Water", waterCost);

        variance = (int) (Math.random() * 2);
        int furCost;
        if (variance == 0) {
            furCost = 250 + (10 * (techLevel)) + (250 * (((int) (Math.random() * 10)) /100 ));
        } else {
            furCost = 250 + (10 * (techLevel)) - (250 * (((int) (Math.random() * 10)) / 100 ));
        }
        if (resources == 7) {
            furCost = furCost / 2;
        } else if (resources == 8) {
            furCost = furCost * 2;
        }
        priceIndex.put("Furs", furCost);

        variance = (int) (Math.random() * 2);
        int foodCost;
        if (techLevel < 1) {
            priceIndex.put("Food", 0);
        } else {
            if (variance == 0) {
                foodCost = 100 + (5 * (techLevel - 1)) + (100 * (((int) (Math.random() * 5)) /100 ));
            } else {
                foodCost = 100 + (5 * (techLevel - 1)) - (100 * (((int) (Math.random() * 5)) /100 ));
            }
            if (resources == 5) {
                foodCost = foodCost / 2;
            } else if (resources == 6) {
                foodCost = foodCost * 2;
            }
            priceIndex.put("Food", foodCost);
        }

        variance = (int) (Math.random() * 2);
        int oreCost;
        if (techLevel < 2) {
            priceIndex.put("Ore", 0);
        } else {
            if (variance == 0) {
                oreCost = 350 + (20 * (techLevel - 2)) + (350 * (((int) (Math.random() * 10)) /100 ));
            } else {
                oreCost = 350 + (20 * (techLevel - 2)) - (350 * (((int) (Math.random() * 10)) /100 ));
            }
            if (resources == 1) {
                oreCost = oreCost / 2;
            } else if (resources == 2) {
                oreCost = oreCost * 2;
            }
            priceIndex.put("Ore", oreCost);
        }

        variance = (int) (Math.random() * 2);
        int gamesCost;
        if (techLevel < 3) {
            priceIndex.put("Games", 0);
        } else {
            if (variance == 0) {
                gamesCost = 250 + (-10 * (techLevel - 3)) + (250 * (((int) (Math.random() * 5)) /100 ));
            } else {
                gamesCost = 250 + (-10 * (techLevel - 3)) - (250 * (((int) (Math.random() * 5)) /100 ));
            }
            if (resources == 11) {
                gamesCost = gamesCost / 2;
            }
            priceIndex.put("Games", gamesCost);
        }

        variance = (int) (Math.random() * 2);
        int firearmsCost;
        if (techLevel < 3) {
            priceIndex.put("Firearms", 0);
        } else {
            if (variance == 0) {
                firearmsCost = 1250 + (-75 * (techLevel - 3)) + (1250 * (((int) (Math.random() * 100)) /100 ));
            } else {
                firearmsCost = 1250 + (-75 * (techLevel - 3)) - (1250 * (((int) (Math.random() * 100)) /100 ));
            }
            if (resources == 12) {
                firearmsCost = firearmsCost / 2;
            }
            priceIndex.put("Firearms", firearmsCost);
        }

        variance = (int) (Math.random() * 2);
        int medicineCost;
        if (techLevel < 4) {
            priceIndex.put("Medicine", 0);
        } else {
            if (variance == 0) {
                medicineCost = 650 + (-20 * (techLevel - 4)) + (650 * (((int) (Math.random() * 10)) /100 ));
            } else {
                medicineCost = 650 + (-20 * (techLevel - 4)) - (650 * (((int) (Math.random() * 10)) /100 ));
            }
            if (resources == 10) {
                medicineCost = medicineCost / 2;
            }
            priceIndex.put("Medicine", medicineCost);
        }

        variance = (int) (Math.random() * 2);
        int machineCost;
        if (techLevel < 4) {
            priceIndex.put("Machines", 0);
        } else {
            if (variance == 0) {
                machineCost = 900 + (-30 * (techLevel - 4)) + (900 * (((int) (Math.random() * 5)) /100 ));
            } else {
                machineCost = 900 + (-30 * (techLevel - 4)) - (900 * (((int) (Math.random() * 5)) /100 ));
            }
            priceIndex.put("Machines", machineCost);
        }

        variance = (int) (Math.random() * 2);
        int narcoticCost;
        if (techLevel < 5) {
            priceIndex.put("Narcotics", 0);
        } else {
            if (variance == 0) {
                narcoticCost = 3500 + (-125 * (techLevel - 5)) + (3500 * (((int) (Math.random() * 150)) /100 ));
            } else {
                narcoticCost = 3500 + (-125 * (techLevel - 5)) - (3500 * (((int) (Math.random() * 150)) /100 ));
            }
            if (resources == 9) {
                narcoticCost = narcoticCost / 2;
            }
            priceIndex.put("Narcotics", narcoticCost);
        }

        variance = (int) (Math.random() * 2);
        int robotCost;
        if (techLevel < 6) {
            priceIndex.put("Robots", 0);
        } else {
            if (variance == 0) {
                robotCost = 5000 + (-150 * (techLevel - 6)) + (5000 * (((int) (Math.random() * 100)) /100 ));
            } else {
                robotCost = 5000 + (-150 * (techLevel - 6)) - (5000 * (((int) (Math.random() * 100)) /100 ));
            }
            priceIndex.put("Robots", robotCost);
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
