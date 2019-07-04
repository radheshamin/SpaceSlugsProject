package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;

/**
 * This view displays the information of the planet selected in home screen
 */

public class PlanetDetailActivity extends AppCompatActivity {


    private Player player;
    private PlayerViewModel playerViewModel;
    private ShipViewModel shipViewModel;
    private SpaceShip ship;
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
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);

        ship = shipViewModel.getShip();

        player = playerViewModel.getPlayer();
        TextView planetText = findViewById(R.id.planet);
        planetText.setText(planet.getName());

        TextView location = findViewById(R.id.coordinates);
        location.setText(new StringBuilder("Coordinates: ").append(planet.getCoordinates().toString()));

        TextView techLevel = findViewById(R.id.tech_level);
        techLevel.setText(new StringBuilder("Tech Level: ").append(planet.getTechLevel()));


        TextView resources = findViewById(R.id.resources);
        resources.setText(new StringBuilder("Resource Type: ").append(planet.getResources()));

        RecyclerView recyclerView = findViewById(R.id.city_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        PlanetAdapter adapter = new PlanetAdapter();
        adapter.setCityList(planet.getCities());
        adapter.setPlanet(planet);
        adapter.setPlayer(player);
        adapter.setShip(ship);
        recyclerView.setAdapter(adapter);

        adapter.setOnTravelClickListener(new PlanetAdapter.OnTravelClickListener() {
            @Override
            public void onTravelClicked(City city) {
                if (player.getCoordinates().get(0) < 0 || player.getCoordinates().get(1) < 0 && ship.getFuel() >= 5) {
                    ship.setFuel(ship.getFuel() - 5);
                    shipViewModel.setShip(ship);
                    travel(city);
                } else if (!player.getCurrentPlanet().equals(planet.getName()) && ship.getFuel() >= 5) {
                    ship.setFuel(ship.getFuel() - 5);
                    shipViewModel.setShip(ship);
                    travel(city);
                } else if (player.getCurrentPlanet().equals(planet.getName())
                        && (player.getLocation().get(0) < 0 || player.getLocation().get(1) < 0)) {
                    travel(city);
                } else if (player.getCurrentPlanet().equals(planet.getName())
                        && (!player.getLocation().equals(city.getLocation()))) {
                    travel(city);
                } else if (player.getCurrentPlanet().equals(planet.getName())
                        && (player.getLocation().equals(city.getLocation()))) {
                    Intent cityIntent = new Intent(PlanetDetailActivity.this, MarketplaceActivity.class);
                    cityIntent.putExtra(EXTRA_CITY, city);
                    cityIntent.putExtra(EXTRA_PLANET, planet);
                    if (getIntent().hasExtra(HomeScreenActivity.CITY_AMOUNT)) {
                        cityIntent.putExtra(CITY_AMOUNT, getIntent().getSerializableExtra(MarketplaceActivity.CITY_AMOUNT));
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
        Intent planetDetail = new Intent(PlanetDetailActivity.this, HomeScreenActivity.class);
        if (getIntent().hasExtra(MarketplaceActivity.CITY_AMOUNT)) {
            planetDetail.putExtra(CITY_AMOUNT, getIntent().getSerializableExtra(MarketplaceActivity.CITY_AMOUNT));
        }
        startActivity(planetDetail);
        finish();

    }

    public void travel(City city) {
        player.setCoordinates(planet.getCoordinates());
        player.setLocation(city.getLocation());
        playerViewModel.addPlayer(player);
        int random = (int) (Math.random() * 10);
        if (random < 1) {
            Intent intent = new Intent(PlanetDetailActivity.this, PoliceEncounterActivity.class);
            intent.putExtra(EXTRA_CITY, city);
            intent.putExtra(EXTRA_PLANET, planet);
            startActivity(intent);
            Log.d("Check", "Police Encounter");
        } else {
            Intent cityIntent = new Intent(PlanetDetailActivity.this, MarketplaceActivity.class);
            cityIntent.putExtra(EXTRA_CITY, city);
            cityIntent.putExtra(EXTRA_PLANET, planet);
            startActivity(cityIntent);
        }
    }
}
