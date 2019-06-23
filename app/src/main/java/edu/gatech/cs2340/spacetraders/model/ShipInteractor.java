package edu.gatech.cs2340.spacetraders.model;

import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;

public class ShipInteractor extends Interactor {

    public ShipInteractor(Repository repo) {
        super(repo);
    }

    public SpaceShip getShip() {
        return getRepository().getShip();
    }

    public void setShip (SpaceShip ship) {
        getRepository().setShip(ship);
    }

    public List<Planet> getUniverse() {
        return getRepository().getUniverse();
    }
}

