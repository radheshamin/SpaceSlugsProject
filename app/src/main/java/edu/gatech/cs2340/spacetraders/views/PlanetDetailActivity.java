package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.CityViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;

/**
 * This view displays the information of the planet selected in home screen
 */

public class PlanetDetailActivity extends AppCompatActivity {


    private Player player;
    private PlayerViewModel playerViewModel;
    private CityViewModel cityViewModel;
    private ShipViewModel shipViewModel;
    private SpaceShip ship;
    private PlanetAdapter adapter;
    private Planet planet;
    public static final String EXTRA_CITY = "edu.gatech.cs2340.spacetraders.views.EXTRA_CITY";

    public static final String CITY_AMOUNT = "edu.gatech.cs2340.spacetraders.views.CITY_AMOUNT";


    public static final String EXTRA_PLANET = "edu.gatech.cs2340.spacetraders.views.EXTRA_PLANET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_detail);

        planet = (Planet) getIntent().getSerializableExtra(HomeScreenActivity.EXTRA_PLANET);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);

        ship = shipViewModel.getShip();

        player = playerViewModel.getPlayer();
        TextView planetText = (TextView)findViewById(R.id.planet);
        planetText.setText(planet.getName());

        TextView location = (TextView)findViewById(R.id.coordinates);
        location.setText("Coordinates: " + planet.getCoordinates().toString());

        TextView techLevel = (TextView)findViewById(R.id.tech_level);
        techLevel.setText("Tech Level: " + (Integer.toString(planet.getTechLevel())));


        TextView resources = (TextView)findViewById(R.id.resources);
        resources.setText("Resource Type: " + (Integer.toString(planet.getResources())));

        RecyclerView recyclerView = findViewById(R.id.city_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new PlanetAdapter();
        adapter.setCityList(planet.getCities());
        adapter.setPlanet(planet);
        adapter.setPlayer(player);
        adapter.setShip(ship);
        recyclerView.setAdapter(adapter);

        adapter.setOnTravelClickListener(new PlanetAdapter.OnTravelClickListener() {
            @Override
            public void onTravelClicked(City city) {
                if (player.getCoordinates().get(0) < 0 || player.getCoordinates().get(1) < 0) {
                    ship.setFuel(ship.getFuel() - 5);
                    shipViewModel.setShip(ship);
                    player.setCoordinates(planet.getCoordinates());
                    player.setLocation(city.getLocation());
                    playerViewModel.addPlayer(player);
                    Intent cityIntent = new Intent(PlanetDetailActivity.this, MarketplaceActivity.class);
                    cityIntent.putExtra(EXTRA_CITY, city);
                    startActivity(cityIntent);
                } else if (!player.getCurrentPlanet().equals(planet.getName()) && ship.getFuel() >= 5) {
                    ship.setFuel(ship.getFuel() - 5);
                    shipViewModel.setShip(ship);
                    player.setCoordinates(planet.getCoordinates());
                    player.setLocation(city.getLocation());
                    playerViewModel.addPlayer(player);
                    Intent cityIntent = new Intent(PlanetDetailActivity.this, MarketplaceActivity.class);
                    cityIntent.putExtra(EXTRA_CITY, city);
                    startActivity(cityIntent);
                } else if (player.getCurrentPlanet().equals(planet.getName())
                        && (player.getLocation().get(0) < 0 || player.getLocation().get(1) < 0)) {
                    ship.setFuel(ship.getFuel() - 5);
                    shipViewModel.setShip(ship);
                    player.setCoordinates(planet.getCoordinates());
                    player.setLocation(city.getLocation());
                    playerViewModel.addPlayer(player);
                    Intent cityIntent = new Intent(PlanetDetailActivity.this, MarketplaceActivity.class);
                    cityIntent.putExtra(EXTRA_CITY, city);
                    startActivity(cityIntent);
                } else if (player.getCurrentPlanet().equals(planet.getName())
                        && (!player.getLocation().equals(city.getLocation()))) {
                    player.setLocation(city.getLocation());
                    playerViewModel.addPlayer(player);
                    Intent cityIntent = new Intent(PlanetDetailActivity.this, MarketplaceActivity.class);
                    cityIntent.putExtra(EXTRA_CITY, city);
                    startActivity(cityIntent);
                } else if (player.getCurrentPlanet().equals(planet.getName())
                        && (player.getLocation().equals(city.getLocation()))) {
                    Intent cityIntent = new Intent(PlanetDetailActivity.this, MarketplaceActivity.class);
                    cityIntent.putExtra(EXTRA_CITY, city);
                    if (getIntent().hasExtra(HomeScreenActivity.CITY_AMOUNT)) {
                        cityIntent.putExtra(CITY_AMOUNT, (HashMap<String, Integer>) getIntent().getSerializableExtra(MarketplaceActivity.CITY_AMOUNT));
                    }
                    startActivity(cityIntent);
                }
            }
        });
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(PlanetDetailActivity.this, HomeScreenActivity.class));
        finish();

    }
}
