package edu.gatech.cs2340.spacetraders.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the interface to the domain/business classes from anywhere else in the application
 *
 *
 * This class provides all the interactors for the application.
 *
 * It is a Singleton so that it can be accessed from anywhere in the application
 */

public final class Model {

    /** the data repository */
    private Repository myRepository;

    private Map<String, Object> interactorMap;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  Model instance = new Model();

    /**
     * method to get the model
     * @return model as the instance
     */
    public static Model getInstance() { return instance; }

    /**
     * Make a new Model instance
     */
    private Model() {
        myRepository = new Repository();
        interactorMap = new HashMap<>();
        registerInteractors();
    }

    /**
     * Create a set of interactors to be used by the application
     */
    private void registerInteractors() {
        interactorMap.put("Player", new PlayerInteractor(myRepository));
        interactorMap.put("Ship", new ShipInteractor(myRepository));

    }

    /**
     * method to get the player interactor of the model
     * @return player interactor of the model
     */
    public PlayerInteractor getPlayerInteractor() {
        return (PlayerInteractor) interactorMap.get("Player");
    }

    /**
     * method to get the ship interactor of the model
     * @return ship interactor of the model
     */
    public ShipInteractor getShipInteractor() {
        return (ShipInteractor) interactorMap.get("Ship");
    }
}

