package edu.gatech.cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.model.Model;
import edu.gatech.cs2340.spacetraders.model.PlayerInteractor;
import edu.gatech.cs2340.spacetraders.model.ShipInteractor;

/**
 * * This viewmodel would support activities that need to update ship
        *
        */
public class ShipViewModel extends AndroidViewModel {

    private ShipInteractor interactor;

    public ShipViewModel(@NonNull Application application) {
        super(application);
        interactor = Model.getInstance().getShipInteractor();

    }

    public void setShip (SpaceShip ship) {
        interactor.setShip(ship);

    }

    public SpaceShip getShip() {
        return interactor.getShip();
    }

}

