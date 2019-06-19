package edu.gatech.cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.model.Model;
import edu.gatech.cs2340.spacetraders.model.PlayerInteractor;

/**
 * This viewmodel would support activities that need to create player
 *
 */
public class ConfigurationViewModel extends AndroidViewModel {

    private PlayerInteractor interactor;

    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getPlayerInteractor();

    }

    public void addPlayer (Player player) {
        interactor.addPlayer(player);

    }

    public Player getPlayer() {
        return interactor.getPlayer();
    }
}
