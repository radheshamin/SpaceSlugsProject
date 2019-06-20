package edu.gatech.cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;



public enum Planet {
    NABOO ("Naboo", new ArrayList<String>(Arrays.asList("Naboo")), 25, 100, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    HOTH ("Hoth", new ArrayList<String>(Arrays.asList("Hoth")), 50, 23, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    TATOOINE ("Tatooine", new ArrayList<String>(Arrays.asList("Tatooine")), 100, 139, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CORUSCANT ("Coruscant", new ArrayList<String>(Arrays.asList("Coruscant")), 65, 90, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    DAGOBAH ("Dagobah", new ArrayList<String>(Arrays.asList("Dagobah")), 0, 0, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    ALDERAAN ("Alderaan", new ArrayList<String>(Arrays.asList("Alderaan")), 100, 75, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    ENDOR ("Endor", new ArrayList<String>(Arrays.asList("Endor")), 59, 130, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    BESPIN ("Bespin", new ArrayList<String>(Arrays.asList("Bespin")), 15, 28, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CANTONICA ("Cantonica", new ArrayList<String>(Arrays.asList("Cantonica")), 90, 120, (int)(Math.random() * 8), (int)(Math.random() * 13)),
    JAKKU ("Jakku", new ArrayList<String>(Arrays.asList("Jakku")), 60, 145, (int)(Math.random() * 8), (int)(Math.random() * 13));

    private final String name;

    private final List<String> cities;

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
        this.cities = cities;
        coordinates = new ArrayList<Integer>();
        coordinates.add(coordinateX);
        coordinates.add(coordinateY);
        this.techLevel = techLevel;
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public List<String> getCities() {
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