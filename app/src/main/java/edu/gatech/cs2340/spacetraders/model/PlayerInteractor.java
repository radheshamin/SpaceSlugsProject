package edu.gatech.cs2340.spacetraders.model;

import edu.gatech.cs2340.spacetraders.entity.Player;

public class PlayerInteractor extends Interactor {

    public PlayerInteractor(Repository repo) {
        super(repo);
    }

    public Player getPlayer() {
        return getRepository().getPlayer();
    }

    public void addPlayer (Player player) {
        getRepository().addPlayer(player);

    }
}
