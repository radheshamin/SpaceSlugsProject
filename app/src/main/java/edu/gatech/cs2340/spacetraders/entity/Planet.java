package edu.gatech.cs2340.spacetraders.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This is an enum of the planet object
 */

public enum Planet {
    NABOO ("Naboo", new ArrayList<>(Arrays.asList("Naboo", "Theed")), 61, 66),
    HOTH ("Hoth", new ArrayList<>(Arrays.asList("Hoth", "Echo Base")), 46, 84),
    TATOOINE ("Tatooine", new ArrayList<>(Arrays.asList("Tatooine", "Palace")), 82, 63),
    CORUSCANT ("Coruscant", new ArrayList<>(Arrays.asList("Coruscant", "Republic")), 26, 68),
    DAGOBAH ("Dagobah", new ArrayList<>(Arrays.asList("Dagobah", "Yoda")), 32, 24),
    ALDERAAN ("Alderaan", new ArrayList<>(Arrays.asList("Alderaan", "Appenza")), 25, 26),
    ENDOR ("Endor", new ArrayList<>(Arrays.asList("Endor", "Star")), 36, 67),
    BESPIN ("Bespin", new ArrayList<>(Arrays.asList("Bespin", "Cloud City")), 23, 46),
    CANTONICA ("Cantonica", new ArrayList<>(Arrays.asList("Cantonica", "Canto Bight")), 22, 22),
    JAKKU ("Jakku", new ArrayList<>(Arrays.asList("Jakku", "Crater Town")), 52, 58),
    CITADEL ("Citadel", new ArrayList<>(Arrays.asList("Citadel", "Presidium")), 0, 0),
    THESSIA ("Thessia", new ArrayList<>(Arrays.asList("Thessia", "Liara")), 84, 42),
    VIRMIRE ("Virmire", new ArrayList<>(Arrays.asList("Virmire", "Wrex")), 57, 26),
    NORMANDY ("Normandy", new ArrayList<>(Arrays.asList("Normandy", "Shepard")), 66, 39);

    private final String name;

    private final List<City> cities;

    private final List<Integer> coordinates;

    private int techLevel;

    private int resources;

    /**
     * Constructor for the enumeration
     *
     * @param name name of planet
     * @param cities cities in region
     * @param coordinateX x-coordinate
     * @param coordinateY y-coordinate
     */
    Planet(String name, Iterable<String> cities, int coordinateX, int coordinateY) {
        this.name = name;
        this.cities = new ArrayList<>();
        int x = 0;
        int y = 0;
        techLevel = (int) (Math.random() * 8);
        resources = (int) (Math.random() * 13);
        for (String s : cities) {
            x += (int) (Math.random() * 10) + 1;
            y += (int) (Math.random() * 10) + 1;
            this.cities.add(new City(s, x, y, techLevel, resources));
        }
        coordinates = new ArrayList<>();
        coordinates.add(coordinateX);
        coordinates.add(coordinateY);
    }

    /**
     * method to get the name of planet
     * @return name of the planet
     */
    public String getName() {
        return name;
    }

    /**
     * method to get the cities in the planet
     * @return a collection of cities in the planet
     */
    public List<City> getCities() {
        return Collections.unmodifiableList(cities);
    }

    /**
     * method to get the coordinates of the planet
     * @return coordinates of the planet
     */
    public List<Integer> getCoordinates() {
        return Collections.unmodifiableList(coordinates);
    }

    /**
     * method to get the technology level of the planet
     * @return technology of the planet
     */
    public int getTechLevel() {
        return techLevel;
    }

    /**
     * method to get the resources of the planet
     * @return resources of the planet
     */
    public int getResources() {
        return resources;
    }

    /**
     * method to set the technology level of the planet
     * @param techLevel tech level of planet
     */
    public void setTechLevel(int techLevel) {
        this.techLevel = techLevel;
    }

    /**
     * method to set the resources of the planet
     * @param resources resource level of planet
     */
    public void setResources(int resources) {
        this.resources = resources;
    }
}
