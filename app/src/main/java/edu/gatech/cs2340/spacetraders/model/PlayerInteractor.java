package edu.gatech.cs2340.spacetraders.model;

import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;

/**
 * This is a class of player interactors
 */
public class PlayerInteractor extends Interactor {
    /**
     * constructor for a player interactor
     * @param repo repository for the player interactor
     */
    PlayerInteractor(Repository repo) {
        super(repo);
    }

    /**
     * method to get the player of the repository
     * @return player in the repository
     */
    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    /**
     * method to set a new player in the repository
     * @param player new player for the repository
     */
    public void addPlayer (Player player) {
        getRepository().addPlayer(player);
    }

    /**
     * method to get the universe in the repository
     * @return universe in the repository
     */
    public List<Planet> getUniverse() {
        return getRepository().getUniverse();
    }

    /**
     * method to set new universe in the repository
     * @param universe new universe in the repository
     */
    public void setUniverse(List<Planet> universe) {getRepository().setUniverse(universe); }
}
