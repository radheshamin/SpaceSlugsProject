package edu.gatech.cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.model.Model;
import edu.gatech.cs2340.spacetraders.model.PlayerInteractor;

/**
 * This viewModel would support activities that need to create player test
 *
 */
public class PlayerViewModel extends AndroidViewModel {

    private final PlayerInteractor interactor;

    /**
     * view model to access player info
     * @param application application of game
     */
    public PlayerViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();

    }

    /**
     * set player info in repository
     * @param player player info
     */
    public void addPlayer(Player player) {
        interactor.addPlayer(player);

    }

    /**
     * getter for player
     * @return player info
     */
    public Player getPlayer() {
        return interactor.getPlayer();
    }

    /**
     * getter for universe
     * @return universe as list of planets
     */
    public List<Planet> getUniverse() {return interactor.getUniverse(); }

    /**
     * setter for universe
     * @param universe list of planets to make universe
     */
    public void setUniverse(List<Planet> universe) {interactor.setUniverse(universe); }
}
