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
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.viewmodels.CityViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;

/**
 * This view displays the information of the planet selected in home screen
 */

public class PlanetDetailActivity extends AppCompatActivity {

    private Player player;
    private PlayerViewModel playerViewModel;
    private CityViewModel cityViewModel;
    private PlanetAdapter adapter;
    private Planet planet;

    public static final String EXTRA_PLANET = "edu.gatech.cs2340.spacetraders.views.EXTRA_PLANET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_detail);

        if (getIntent().hasExtra(HomeScreenActivity.EXTRA_PLANET)) {
            planet = (Planet) getIntent().getSerializableExtra(HomeScreenActivity.EXTRA_PLANET);
        } else {
            //no planet is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR< NO COURSE PASSED");
        }

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        cityViewModel = ViewModelProviders.of(this).get(CityViewModel.class);

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
        adapter.setPlayer(player);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(PlanetDetailActivity.this, HomeScreenActivity.class));
        finish();

    }
}
