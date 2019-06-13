package edu.gatech.cs2340.spacetraders.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.model.Player;
import edu.gatech.cs2340.spacetraders.viewmodels.ConfigurationViewModel;

/**
 * This is the starting screen
 */
public class ConfigurationActivity extends AppCompatActivity {

    /** local instance of view model */
    private ConfigurationViewModel configurationViewModel;

    /** widgets for configuration screen */
    private EditText nameField;
    private Spinner pilotSpinner;
    private Spinner fighterSpinner;
    private Spinner traderSpinner;
    private Spinner engineerSpinner;
    private Spinner difficultySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_character_activity);

        nameField = findViewById(R.id.name);
        pilotSpinner = findViewById(R.id.pilot_spinner);
        fighterSpinner = findViewById(R.id.fighter_spinner);
        traderSpinner = findViewById(R.id.trader_spinner);
        engineerSpinner = findViewById(R.id.engineer_spinner);
        difficultySpinner = findViewById(R.id.difficulty_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Player.legalDifficuties);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficultySpinner.setAdapter(adapter);

        List<Integer> skillPoints = new ArrayList<Integer>();
        for (int i = 0; i <= 16; i++) {
            skillPoints.add(i);
        }
        ArrayAdapter<Integer> skillAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillPoints);
        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilotSpinner.setAdapter(skillAdapter);
        fighterSpinner.setAdapter(skillAdapter);
        traderSpinner.setAdapter(skillAdapter);
        engineerSpinner.setAdapter(skillAdapter);
    }

}
