package edu.gatech.cs2340.spacetraders.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

/**
 * This viewmodel would support activities that need to create player
 *
 */
public class ConfigurationViewModel extends AndroidViewModel {
    public ConfigurationViewModel(@NonNull Application application) {
        super(application);
    }
}