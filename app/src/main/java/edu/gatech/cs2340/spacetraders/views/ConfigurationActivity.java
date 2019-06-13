package edu.gatech.cs2340.spacetraders.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.viewmodels.ConfigurationViewModel;

/**
 * This is the starting screen
 */
public class ConfigurationActivity extends AppCompatActivity {

    /** local instance of viewmodel */
    private ConfigurationViewModel configurationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_character_activity);

    }

}
