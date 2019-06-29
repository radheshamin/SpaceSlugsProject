package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;

import android.support.v7.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.HashMap;

public class HomeScreenActivity extends AppCompatActivity {

    private Player player;
    private PlayerViewModel viewModel;
    private ShipViewModel shipViewModel;
    private UniverseAdapter adapter;
    private SpaceShip ship;
    public static final String EXTRA_PLANET = "edu.gatech.cs2340.spacetraders.views.EXTRA_PLANET";

    public static final String CITY_AMOUNT = "edu.gatech.cs2340.spacetraders.views.CITY_AMOUNT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);
        ship = shipViewModel.getShip();
        player = viewModel.getPlayer();
        TextView playerText = (TextView)findViewById(R.id.player_text);
        playerText.setText(player.toString());

        TextView location = (TextView)findViewById(R.id.current_location);
        if (player.getCurrentPlanet() == null) {
            location.setText("You are not at a planet.");
        } else {
            location.setText("You are at planet " + player.getCurrentPlanet());
        }

        TextView coordinates = (TextView)findViewById(R.id.location);
        if (player.getCoordinates().get(0) == -1 || player.getCoordinates().get(1) == -1) {
            coordinates.setText("You are on your ship. Visit a planet.");
        } else {
            coordinates.setText("Coordinates: " + player.getCoordinates().toString());
        }

        TextView shipText = (TextView)findViewById(R.id.ship);
        shipText.setText(ship.toString());

        RecyclerView recyclerView = findViewById(R.id.planet_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new UniverseAdapter();
        adapter.setPlanetList(viewModel.getUniverse());
        recyclerView.setAdapter(adapter);

        adapter.setOnPlanetClickListener(new UniverseAdapter.OnPlanetClickListener() {
            @Override
            public void onPlanetClicked(Planet planet) {
                Intent intent = new Intent(HomeScreenActivity.this, PlanetDetailActivity.class);
                intent.putExtra(EXTRA_PLANET, planet);
                if (getIntent().hasExtra(MarketplaceActivity.CITY_AMOUNT)) {
                    intent.putExtra(CITY_AMOUNT, (HashMap<String, Integer>) getIntent().getSerializableExtra(MarketplaceActivity.CITY_AMOUNT));
                }
                startActivity(intent);
            }
        });
    }

}
