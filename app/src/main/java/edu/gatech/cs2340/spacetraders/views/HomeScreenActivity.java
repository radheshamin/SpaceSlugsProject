package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;


import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private Player player;
    private PlayerViewModel viewModel;
    private SpaceShip ship;
    private List<Planet> universe;


    private Gson gson;

    public static final String EXTRA_PLANET = "edu.gatech.cs2340.spacetraders.views.EXTRA_PLANET";

    public static final String CITY_AMOUNT = "edu.gatech.cs2340.spacetraders.views.CITY_AMOUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);

        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        ShipViewModel shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);

        gson = new Gson();

        if (getIntent().hasExtra(MainActivity.CHECK)) {
            //read file
            File path = getApplicationContext().getFilesDir();
            File file = new File(path, "Player.json");
            try {
                int length = (int) file.length();
                byte[] bytes = new byte[length];
                FileInputStream in = new FileInputStream(file);
                int i = in.read(bytes);
                Log.d("Bytes", Integer.toString(i));
                String contents = new String(bytes);
                in.close();
                Log.d("Check Player", contents);
                if (!contents.equals("")) {
                    player = gson.fromJson(contents, Player.class);
                    viewModel.addPlayer(player);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            File shipFile = new File(path, "Ship.json");
            try {
                int length = (int) shipFile.length();
                byte[] bytes = new byte[length];
                FileInputStream in = new FileInputStream(shipFile);
                int i = in.read(bytes);
                Log.d("Bytes", Integer.toString(i));
                String contents = new String(bytes);
                in.close();
                Log.d("Check Ship", contents);
                if (!contents.equals("")) {
                    ship = gson.fromJson(contents, SpaceShip.class);
                    shipViewModel.setShip(ship);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Type listType = new TypeToken<ArrayList<Planet>>() {
            }.getType();
            File universeFile = new File(path, "Universe.json");
            try {
                int length = (int) universeFile.length();
                byte[] bytes = new byte[length];
                FileInputStream in = new FileInputStream(universeFile);
                int i = in.read(bytes);
                Log.d("Bytes", Integer.toString(i));
                String contents = new String(bytes);
                in.close();
                Log.d("Check Universe", contents);
                if (!contents.equals("")) {
                    universe = new Gson().fromJson(contents, listType);
                    viewModel.setUniverse(universe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ship = shipViewModel.getShip();
            player = viewModel.getPlayer();
            universe = viewModel.getUniverse();
        }

        TextView playerText = findViewById(R.id.player_text);
        playerText.setText(player.toString());

        TextView location = findViewById(R.id.current_location);
        if (player.getCurrentPlanet() == null) {
            StringBuilder loc = new StringBuilder("You are not at a planet.");
            location.setText(loc);
        } else {
            StringBuilder loc = new StringBuilder("You are at planet ");
            location.setText(loc.append(player.getCurrentPlanet()));
        }

        TextView coordinates = findViewById(R.id.location);
        if (player.getCoordinates().get(0) == -1 || player.getCoordinates().get(1) == -1) {
            StringBuilder coordinatesList = new StringBuilder("You are on your ship. Visit a planet.");
            coordinates.setText(coordinatesList);
        } else {
            StringBuilder coordinatesList = new StringBuilder("Coordinates: " + player.getCoordinates().toString());
            coordinates.setText(coordinatesList);
        }

        TextView shipText = findViewById(R.id.ship);
        shipText.setText(ship.toString());

        RecyclerView recyclerView = findViewById(R.id.planet_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        UniverseAdapter adapter = new UniverseAdapter();
        adapter.setPlanetList(universe);
        recyclerView.setAdapter(adapter);

        adapter.setOnPlanetClickListener(new UniverseAdapter.OnPlanetClickListener() {
            @Override
            public void onPlanetClicked(Planet planet) {
                Intent intent = new Intent(HomeScreenActivity.this, PlanetDetailActivity.class);
                intent.putExtra(EXTRA_PLANET, planet);
                if (getIntent().hasExtra(MarketplaceActivity.CITY_AMOUNT)) {
                    intent.putExtra(CITY_AMOUNT, getIntent().getSerializableExtra(MarketplaceActivity.CITY_AMOUNT));
                }
                startActivity(intent);
            }
        });

        final Button saveGame = findViewById(R.id.save);
        saveGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String json = gson.toJson(player);
                String jsonShip = gson.toJson(ship);
                String jsonUniverse = gson.toJson(viewModel.getUniverse());
                Log.d("Check Player", json);
                Log.d("Check Ship", jsonShip);
                Log.d("Check Universe", jsonUniverse);


                //Make file
                File path = getApplicationContext().getFilesDir();
                File player = new File(path, "Player.json");
                File ship = new File(path, "Ship.json");
                File universe = new File(path, "Universe.json");



                //write to file
                try {
                    FileOutputStream stream = new FileOutputStream(player);
                    stream.write(json.getBytes());
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    FileOutputStream stream = new FileOutputStream(ship);
                    stream.write(jsonShip.getBytes());
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    FileOutputStream stream = new FileOutputStream(universe);
                    stream.write(jsonUniverse.getBytes());
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                StringBuilder save = new StringBuilder("GameSaved");
            saveGame.setText(save);
            }
        });
    }

}
