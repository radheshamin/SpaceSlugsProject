package edu.gatech.cs2340.spacetraders.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;

/**
 * This class is an abstraction of the data storage for the business classes
 */
class Repository {

    /** player of the application */
    private Player player;
    private List<Planet> universe;
    private SpaceShip ship;

    /**
     * Make a new Repository object
     */
    Repository() {
        player = new Player("Name", 4, 4, 4, 4, "Beginner", -1 , -1);
        List<Planet> all = new ArrayList<>(Arrays.asList(Planet.values()));
        universe = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            int n = (int)(Math.random() * all.size());
            universe.add(all.get(n));
            all.remove(n);
        }
        ship = new SpaceShip("Gnat", 15, 15, 15);
    }

    /**
     * method to set the player in this repository
     * @param player new player in the repository
     */
    void addPlayer(Player player) {
        this.player = player;
    }

    /**
     * method to get the player in this repository
     * @return player in the repository
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * method to get the universe in this repository
     * @return universe in the repository
     */
    List<Planet> getUniverse() {
        return Collections.unmodifiableList(universe);
    }

    /**
     * method to get the ship in this repository
     * @return ship in the repository
     */
    public SpaceShip getShip() {
        return ship;
    }

    /**
     * method to set the ship in this repository
     * @param ship new ship in the repository
     */
    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    /**
     * method to set the universe in this repository
     * @param universe new universe in this repository
     */
    void setUniverse(List<Planet> universe) {this.universe = universe; }
}
