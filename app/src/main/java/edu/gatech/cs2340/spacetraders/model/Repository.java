package edu.gatech.cs2340.spacetraders.model;

import edu.gatech.cs2340.spacetraders.entity.Player;

/**
 * This class is an abstraction of the data storage for the business classes
 */
class Repository {

    /** player of the application */
    private Player player;

    /**
     * Make a new Repository object
     */
    public Repository() {
        player = new Player("", 0, 0, 0, 0, "Beginner");
    }

    public void addPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}
