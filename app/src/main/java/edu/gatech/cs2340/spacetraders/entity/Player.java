package edu.gatech.cs2340.spacetraders.entity;

import java.util.Arrays;
import java.util.List;

public class Player {

    /** name, skills, and difficulty of player */
    private String name;
    private int pilotSkill;
    private int fighterSkill;
    private int traderSkill;
    private int engineerSkill;
    private String difficulty;

    public static List<String> legalDifficuties = Arrays.asList("Beginner", "Easy",
            "Normal", "Hard", "Impossible");

    public Player (String name, int pilotSkill, int fighterSkill, int traderSkill, int engineerSkill,
                   String difficulty) {
        this.name = name;
        this.pilotSkill = pilotSkill;
        this.fighterSkill = fighterSkill;
        this.traderSkill = traderSkill;
        this.engineerSkill = engineerSkill;
        this.difficulty = difficulty;
    }

    public String getName () {
        return name;
    }

    public void setName(String name) {
		this.name = name;
    }

    public int getPilotSkill () {
        return pilotSkill;
    }

    public void setPilotSkill(int pilotSkill) {
        this.pilotSkill = pilotSkill;
    }

    public int getFighterSkill () {
        return fighterSkill;
    }

    public void setFighterSkill(int fighterSkill) {
        this.fighterSkill = fighterSkill;
    }

    public int getTraderSkill () {
        return traderSkill;
    }

    public void setTraderSkill(int traderSkill) {
        this.traderSkill = traderSkill;
    }

    public int getEngineerSkill () {
        return engineerSkill;
    }

    public void setEngineerSkill(int engineerSkill) {
        this.engineerSkill = engineerSkill;
    }

    public String getDifficulty () {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return String.format("Student: %s, pilot: %d, fighter: %d, trader: %d, engineer: %d, " +
                        "difficulty: %s.", name, pilotSkill, fighterSkill, traderSkill,
                        engineerSkill, difficulty);

}
