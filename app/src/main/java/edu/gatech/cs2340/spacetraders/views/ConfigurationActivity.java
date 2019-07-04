package edu.gatech.cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;

/**
 * This is the starting screen
 */
public class ConfigurationActivity extends AppCompatActivity {

    /** local instance of view model */
    private PlayerViewModel configurationViewModel;
    private ShipViewModel shipViewModel;


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

        List<Integer> skillPoints = new ArrayList<>();
        for (int i = 0; i <= 16; i++) {
            skillPoints.add(i);
        }
        ArrayAdapter<Integer> skillAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skillPoints);
        skillAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pilotSpinner.setAdapter(skillAdapter);
        fighterSpinner.setAdapter(skillAdapter);
        traderSpinner.setAdapter(skillAdapter);
        engineerSpinner.setAdapter(skillAdapter);

        Button beginButton = findViewById(R.id.begin_button);
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBeginPressed(view);
            }
        });


        configurationViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);
    }

    /**
     * Button handler for the add new student button
     *
     * @param view the button that was pressed
     */
    public void onBeginPressed(View view) {
        Log.d("Edit", "Begin Button Pressed");

        if (((int) pilotSpinner.getSelectedItem() + (int) fighterSpinner.getSelectedItem() +
                (int) traderSpinner.getSelectedItem() +
                (int) engineerSpinner.getSelectedItem()) != 16) {
            Log.d("Edit", "Not correct number of skill points");
            Intent intent = new Intent(this, ConfigurationRedoActivity.class);
            startActivity(intent);
        } else {

            Player player = new Player(nameField.getText().toString(), (int) pilotSpinner.getSelectedItem(),
                    (int) fighterSpinner.getSelectedItem(), (int) traderSpinner.getSelectedItem(),
                    (int) engineerSpinner.getSelectedItem(), (String) difficultySpinner.getSelectedItem(), -1, -1);
            configurationViewModel.addPlayer(player);
            SpaceShip ship = new SpaceShip("Gnat", 15, 15, 15);
            shipViewModel.setShip(ship);
            Log.d("Edit", "Got new player data: " + player);
            openHomeScreen();
        }
    }

    private void openHomeScreen() {
        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }


}
