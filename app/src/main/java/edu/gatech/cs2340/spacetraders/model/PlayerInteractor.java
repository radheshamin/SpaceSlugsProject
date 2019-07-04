package edu.gatech.cs2340.spacetraders.model;

import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;

public class PlayerInteractor extends Interactor {

    PlayerInteractor(Repository repo) {
        super(repo);
    }

    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    public void addPlayer (Player player) {
        getRepository().addPlayer(player);
    }

    public List<Planet> getUniverse() {
        return getRepository().getUniverse();
    }


    public void setUniverse(List<Planet> universe) {getRepository().setUniverse(universe); }
}
