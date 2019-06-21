package edu.gatech.cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.model.CityInteractor;
import edu.gatech.cs2340.spacetraders.model.Model;
import edu.gatech.cs2340.spacetraders.model.PlayerInteractor;

/**
 * This viewmodel would support activities that need to create marketplaces
 *
 */
public class CityViewModel extends AndroidViewModel {

    private CityInteractor interactor;

    public CityViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getCityInteractor();

    }
}
