package edu.gatech.cs2340.spacetraders.model;

import edu.gatech.cs2340.spacetraders.entity.SpaceShip;

/**
 * This is a class of the ship interactors
 */
public class ShipInteractor extends Interactor {
    /**
     * constructor of a ship interactor
     * @param repo repository for the ship interactor
     */
    ShipInteractor(Repository repo) {
        super(repo);
    }

    /**
     * method to get the ship inside the repository
     * @return ship of the repository
     */
    public SpaceShip getShip() {
        return getRepository().getShip();
    }

    /**
     * method to set the ship in the repository
     * @param ship new ship of the repository
     */
    public void setShip (SpaceShip ship) {
        getRepository().setShip(ship);
    }
}

