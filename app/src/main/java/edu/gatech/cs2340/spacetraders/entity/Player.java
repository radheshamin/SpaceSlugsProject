package edu.gatech.cs2340.spacetraders.entity;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * This is a class of the player object
 */
public class Player {

    /** name, skills, and difficulty of player */
    private String name;
    private final int pilotSkill;
    private final int fighterSkill;
    private final int traderSkill;
    private final int engineerSkill;
    private List<Integer> coordinates;
    private List<Integer> location;
    private final String difficulty;
    private int money;

    public static final List<String> legalDifficulties = Arrays.asList("Beginner", "Easy",
            "Normal", "Hard", "Impossible");

    /**
     * Constructor for a Player
     * @param name name of player
     * @param pilotSkill pilot skill of player
     * @param fighterSkill fighter skill of player
     * @param traderSkill trader skill of player
     * @param engineerSkill engineer skill of player
     * @param difficulty difficulty of game
     * @param coordinateX coordinate x of player
     * @param coordinateY coordinate y of player
     */
    public Player (String name, int pilotSkill, int fighterSkill,
                   int traderSkill, int engineerSkill,
                   String difficulty,
                   int coordinateX, int coordinateY) {
        this.name = name;
        this.pilotSkill = pilotSkill;
        this.fighterSkill = fighterSkill;
        this.traderSkill = traderSkill;
        this.engineerSkill = engineerSkill;
        this.difficulty = difficulty;
        coordinates = new ArrayList<>();
        coordinates.add(coordinateX);
        coordinates.add(coordinateY);
        location = new ArrayList<>();
        location.add(-1);
        location.add(-1);
        this.money = 1000;
    }

    /**
     * method to get the coordinates of player
     * @return coordinates of the player
     */
    public List<Integer> getCoordinates() {
        return Collections.unmodifiableList(coordinates);
    }

    /**
     * method to set the coordinates of player
     * @param coordinates new coordinates of the player
     */
    public void setCoordinates(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * method to get the location of player
     * @return location of the player
     */
    public List<Integer> getLocation() {
        return Collections.unmodifiableList(location);
    }

    /**
     * method to set the location of player
     * @param location new location of the player
     */
    public void setLocation(List<Integer> location) {
        this.location = location;
    }

    /**
     * method to set the money of player
     * @param money new amount of money of the player
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * method to get the money of player
     * @return the amount of money of the player
     */
    public int getMoney () {
        return money;
    }

    /**
     * method to get the current planet where player is
     * @return the planet where player is currently at
     */
    public String getCurrentPlanet() {
        Iterable<Planet> all = new ArrayList<>(Arrays.asList(Planet.values()));
        for (Planet p : all) {
            if (this.getCoordinates().equals(p.getCoordinates())) {
                return p.getName();
            }
        }
        return null;
    }

    /**
     * method to convert player object to string
     * @return a string that contains all attributes of a player
     */
    @Override
    @NonNull
    public String toString() {
        return String.format(Locale.getDefault(), "Player Name: %s," +
                        " Money: $%d, Pilot: %d, Fighter: %d, Trader: %d, Engineer: %d, " +
                        "Difficulty: %s.", name, money, pilotSkill, fighterSkill, traderSkill,
                engineerSkill, difficulty);
    }
}
