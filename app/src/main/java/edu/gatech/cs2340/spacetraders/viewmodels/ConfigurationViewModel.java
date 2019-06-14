package edu.gatech.cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.model.Repository;

/**
 * This viewmodel would support activities that need to create player
 *
 */
public class ConfigurationViewModel extends AndroidViewModel {

    private Repository myRepository;
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }

    public void addPlayer (Player player) {
        myRepository.addPlayer(player);
    }
}
