package edu.gatech.cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public enum Planet {
    NABOO ("Naboo", new ArrayList<String>(Arrays.asList("Naboo")), 61, 66, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    HOTH ("Hoth", new ArrayList<String>(Arrays.asList("Hoth")), 46, 84, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    TATOOINE ("Tatooine", new ArrayList<String>(Arrays.asList("Tatooine")), 82, 63, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CORUSCANT ("Coruscant", new ArrayList<String>(Arrays.asList("Coruscant")), 26, 68, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    DAGOBAH ("Dagobah", new ArrayList<String>(Arrays.asList("Dagobah")), 32, 24, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    ALDERAAN ("Alderaan", new ArrayList<String>(Arrays.asList("Alderaan")), 25, 26, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    ENDOR ("Endor", new ArrayList<String>(Arrays.asList("Endor")), 36, 67, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    BESPIN ("Bespin", new ArrayList<String>(Arrays.asList("Bespin")), 23, 46, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CANTONICA ("Cantonica", new ArrayList<String>(Arrays.asList("Cantonica")), 22, 22, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    JAKKU ("Jakku", new ArrayList<String>(Arrays.asList("Jakku")), 52, 58, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CITADEL ("Citadel", new ArrayList<String>(Arrays.asList("Citadel")), 0, 0, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    THESSIA ("Thessia", new ArrayList<String>(Arrays.asList("Thessia")), 84, 42, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    VIRMIRE ("Virmire", new ArrayList<String>(Arrays.asList("Virmire")), 57, 26, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    NORMANDY ("Normandy", new ArrayList<String>(Arrays.asList("Normandy")), 66, 39, (int)(Math.random() * 8), (int)(Math.random() * 13));

    private final String name;

    private final List<City> cities;

    private final List<Integer> coordinates;

    private final int techLevel;

    private final int resources;

    /**
     * Constructor for the enumeration
     *
     * @param name name of planet
     * @param cities cities in region
     * @param coordinateX x-coordinate
     * @param coordinateY y-coordinate
     * @param techLevel tech level of planet
     * @param resources resource type of planet
     */
    Planet (String name, List<String> cities, int coordinateX, int coordinateY, int techLevel, int resources) {
        this.name = name;
        this.cities = new ArrayList<City>();
        int x = 0;
        int y = 0;
        for (String s : cities) {
            x += (int) (Math.random() * 10);
            y += (int) (Math.random() * 10);
            this.cities.add(new City(s, x, y, techLevel, resources));
        }
        coordinates = new ArrayList<Integer>();
        coordinates.add(coordinateX);
        coordinates.add(coordinateY);
        this.techLevel = techLevel;
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return cities;
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public int getTechLevel() {
        return techLevel;
    }

    public int getResources() {
        return resources;
    }
}
