package edu.gatech.cs2340.spacetraders.model;

import edu.gatech.cs2340.spacetraders.entity.SpaceShip;

public class ShipInteractor extends Interactor {

    ShipInteractor(Repository repo) {
        super(repo);
    }

    public SpaceShip getShip() {
        return getRepository().getShip();
    }

    public void setShip (SpaceShip ship) {
        getRepository().setShip(ship);
    }
}

