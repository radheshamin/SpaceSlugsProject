package edu.gatech.cs2340.spacetraders.model;

import java.util.List;

import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;

public class CityInteractor extends Interactor {

    public CityInteractor(Repository repo) {
        super(repo);
    }

    public List<Planet> getUniverse() {
        return getRepository().getUniverse();
    }


}
