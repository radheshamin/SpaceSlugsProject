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

public class Model {

    /** the data repository */
    private Repository myRepository;

    private Map<String, Object> interactorMap;

    /** Singleton Pattern Code
     *  this allows us to get access to this class
     *  anywhere, which will allow our View models to access
     *  the "back end"  more easily
     */
    private static  Model instance = new Model();

    public static Model getInstance() { return instance; }
}

