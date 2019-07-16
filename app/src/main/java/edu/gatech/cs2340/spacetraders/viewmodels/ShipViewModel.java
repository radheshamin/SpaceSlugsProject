package edu.gatech.cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.model.Model;
import edu.gatech.cs2340.spacetraders.model.ShipInteractor;

/**
 * * This viewModel would support activities that need to update ship
        *
        */
public class ShipViewModel extends AndroidViewModel {

    private final ShipInteractor interactor;

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

