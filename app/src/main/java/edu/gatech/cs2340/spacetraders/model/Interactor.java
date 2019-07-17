package edu.gatech.cs2340.spacetraders.model;

/**
 * Interface for our concrete interactors
 */
abstract class Interactor {

    private final Repository myRepository;

    /**
     * constructor of an interactor
     * @param repo repository for the interactor
     */
    Interactor(Repository repo) {
        myRepository = repo;
    }

    /**
     * method to get the repository of the interactor
     * @return repository of the interactor
     */
    Repository getRepository() {
        return myRepository;
    }
}
