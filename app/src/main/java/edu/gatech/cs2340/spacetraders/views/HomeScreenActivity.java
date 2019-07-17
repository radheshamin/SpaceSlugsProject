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
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;


import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * activity for home screen of app with planet lists
 */
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
                if (!"".equals(contents)) {
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
                if (!"".equals(contents)) {
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
                if (!"".equals(contents)) {
                    universe = new Gson().fromJson(contents, listType);
                    viewModel.setUniverse(universe);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Map<Planet, List<Integer>> levels;
            Type mapType = new TypeToken<Map<Planet, List<Integer>>>() {
            }.getType();
            File planetFile = new File(path, "Planets.json");
            try {
                int length = (int) planetFile.length();
                byte[] bytes = new byte[length];
                FileInputStream in = new FileInputStream(planetFile);
                int i = in.read(bytes);
                Log.d("Bytes", Integer.toString(i));
                String contents = new String(bytes);
                in.close();
                Log.d("Check Planets", contents);
                if (!"".equals(contents)) {
                     levels = new Gson().fromJson(contents, mapType);
                     Log.d("HashMap", levels.toString());
                     for (Planet planet: levels.keySet()) {
                         planet.setTechLevel((levels.get(planet)).get(0));
                         planet.setResources((levels.get(planet)).get(1));
                     }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Map<String, Map<String,Integer>> priceIndeces;
            Type mapCities = new TypeToken<Map<String, Map<String,Integer>>>() {
            }.getType();
            File cityFile = new File(path, "Cities.json");
            try {
                int length = (int) cityFile.length();
                byte[] bytes = new byte[length];
                FileInputStream in = new FileInputStream(cityFile);
                int i = in.read(bytes);
                Log.d("Bytes", Integer.toString(i));
                String contents = new String(bytes);
                in.close();
                Log.d("Check Cities", contents);
                if (!"".equals(contents)) {
                    priceIndeces = new Gson().fromJson(contents, mapCities);
                    Log.d("Hashmap", priceIndeces.toString());
                    for (Planet planet : viewModel.getUniverse()) {
                        for (City city : planet.getCities()) {
                            city.setPriceIndex(priceIndeces.get(city.getName()));
                        }
                    }
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
            CharSequence loc = new StringBuilder("You are not at a planet.");
            location.setText(loc);
        } else {
            StringBuilder loc = new StringBuilder("You are at planet ");
            location.setText(loc.append(player.getCurrentPlanet()));
        }

        TextView coordinates = findViewById(R.id.location);
        if ((player.getCoordinates().get(0) == -1) || (player.getCoordinates().get(1) == -1)) {
            CharSequence coordinatesList =
                    new StringBuilder("You are on your ship. Visit a planet.");
            coordinates.setText(coordinatesList);
        } else {
            CharSequence coordinatesList =
                    new StringBuilder("Coordinates: " + player.getCoordinates().toString());
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
                    intent.putExtra(CITY_AMOUNT,
                            getIntent().getSerializableExtra(MarketplaceActivity.CITY_AMOUNT));
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
                Map<Planet, List<Integer>> levels = new HashMap<>();
                Map<String, Map<String,Integer>> priceIndeces = new HashMap<>();
                for (Planet planet : viewModel.getUniverse()) {
                    levels.put(planet, new ArrayList<>(Arrays.asList(planet.getTechLevel(),
                            planet.getResources())));
                    for (City city : planet.getCities()) {
                        priceIndeces.put(city.getName(), city.getPriceIndex());
                    }
                }
                String jsonPlanetLevels = gson.toJson(levels);
                String jsonCities = gson.toJson(priceIndeces);
                Log.d("Check Player", json);
                Log.d("Check Ship", jsonShip);
                Log.d("Check Universe", jsonUniverse);
                Log.d("Check Planets", jsonPlanetLevels);
                Log.d("Check Cities", jsonCities);


                //Make file
                File path = getApplicationContext().getFilesDir();
                File player = new File(path, "Player.json");
                File ship = new File(path, "Ship.json");
                File universe = new File(path, "Universe.json");
                File planets = new File(path, "Planets.json");
                File cities = new File(path, "Cities.json");



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

                try {
                    FileOutputStream stream = new FileOutputStream(planets);
                    stream.write(jsonPlanetLevels.getBytes());
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    FileOutputStream stream = new FileOutputStream(cities);
                    stream.write(jsonCities.getBytes());
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                CharSequence save = new StringBuilder("Game Saved");
            saveGame.setText(save);
            }
        });
    }

}
