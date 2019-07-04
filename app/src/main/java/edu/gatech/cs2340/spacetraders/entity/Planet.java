package edu.gatech.cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public enum Planet {
    NABOO ("Naboo", new ArrayList<>(Arrays.asList("Naboo", "Theed")), 61, 66,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    HOTH ("Hoth", new ArrayList<>(Arrays.asList("Hoth", "Echo Base")), 46, 84,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    TATOOINE ("Tatooine", new ArrayList<>(Arrays.asList("Tatooine", "Palace")), 82, 63,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CORUSCANT ("Coruscant", new ArrayList<>(Arrays.asList("Coruscant", "Republic")), 26, 68,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    DAGOBAH ("Dagobah", new ArrayList<>(Arrays.asList("Dagobah", "Yoda")), 32, 24,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    ALDERAAN ("Alderaan", new ArrayList<>(Arrays.asList("Alderaan", "Appenza")), 25, 26,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    ENDOR ("Endor", new ArrayList<>(Arrays.asList("Endor", "Star")), 36, 67,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    BESPIN ("Bespin", new ArrayList<>(Arrays.asList("Bespin", "Cloud City")), 23, 46,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CANTONICA ("Cantonica", new ArrayList<>(Arrays.asList("Cantonica", "Canto Bight")), 22, 22,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    JAKKU ("Jakku", new ArrayList<>(Arrays.asList("Jakku", "Crater Town")), 52, 58,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    CITADEL ("Citadel", new ArrayList<>(Arrays.asList("Citadel", "Presidium")), 0, 0,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    THESSIA ("Thessia", new ArrayList<>(Arrays.asList("Thessia", "Liara")), 84, 42,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    VIRMIRE ("Virmire", new ArrayList<>(Arrays.asList("Virmire", "Wrex")), 57, 26,
            (int)(Math.random() * 8), (int)(Math.random() * 13)),
    NORMANDY ("Normandy", new ArrayList<>(Arrays.asList("Normandy", "Shepard")), 66, 39,
            (int)(Math.random() * 8), (int)(Math.random() * 13));

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
    Planet (String name, Iterable<String> cities, int coordinateX, int coordinateY,
            int techLevel, int resources) {
        this.name = name;
        this.cities = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String s : cities) {
            x += (int) (Math.random() * 10) + 1;
            y += (int) (Math.random() * 10) + 1;
            this.cities.add(new City(s, x, y, techLevel, resources));
        }
        coordinates = new ArrayList<>();
        coordinates.add(coordinateX);
        coordinates.add(coordinateY);
        this.techLevel = techLevel;
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public List<City> getCities() {
        return Collections.unmodifiableList(cities);
    }

    public List<Integer> getCoordinates() {
        return Collections.unmodifiableList(coordinates);
    }

    public int getTechLevel() {
        return techLevel;
    }

    public int getResources() {
        return resources;
    }
}
