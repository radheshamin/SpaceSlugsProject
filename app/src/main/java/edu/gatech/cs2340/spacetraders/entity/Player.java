package edu.gatech.cs2340.spacetraders.entity;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Player {

    /** name, skills, and difficulty of player */
    private String name;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private List<Integer> coordinates;
    private List<Integer> location;
    private String difficulty;
    private int money;

    public static List<String> legalDifficuties = Arrays.asList("Beginner", "Easy",
            "Normal", "Hard", "Impossible");

    public Player (String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill,
                   String difficulty, int coordinateX, int coordinateY) {
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

    public String getName () {
        return name;
    }

    public void setName(String name) {
		this.name = name;
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Integer> getLocation() {
        return location;
    }

    public void setLocation(List<Integer> location) {
        this.location = location;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney () {
        return money;
    }

    public String getCurrentPlanet() {
        List<Planet> all = new ArrayList<>(Arrays.asList(Planet.values()));
        for (Planet p : all) {
            if (this.getCoordinates().equals(p.getCoordinates())) {
                return p.getName();
            }
        }
        return null;
    }

    @Override
    @NonNull
    public String toString() {
        return String.format(Locale.getDefault(), "Player Name: %s, Money: $%d, Pilot: %d, Fighter: %d, Trader: %d, Engineer: %d, " +
                        "Difficulty: %s.", name, money, pilotSkill, fighterSkill, traderSkill,
                engineerSkill, difficulty);
    }
}
