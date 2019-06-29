package edu.gatech.cs2340.spacetraders.model;

import android.widget.Space;

import java.util.ArrayList;
import java.util.Arrays;
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
    public Repository() {
        player = new Player("Name", 4, 4, 4, 4, "Beginner", -1 , -1);
        List<Planet> all = new ArrayList<Planet>(Arrays.asList(Planet.values()));
        universe = new ArrayList<Planet>();
        for (int i = 0; i < 10; i++) {
            int n = (int)(Math.random() * all.size());
            universe.add(all.get(n));
            all.remove(n);
        }
        ship = new SpaceShip("Gnat", 15, 15, 15);
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Planet> getUniverse() {
        return universe;
    }

    public SpaceShip getShip() {
        return ship;
    }
    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public void setUniverse(List<Planet> universe) {this.universe = universe; }
}
