package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import android.support.v7.widget.RecyclerView;

public class HomeScreenActivity extends AppCompatActivity {

    private Player player;
    private PlayerViewModel viewModel;
    private UniverseAdapter adapter;
    public static final String EXTRA_PLANET = "edu.gatech.cs2340.spacetraders.views.EXTRA_PLANET";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
        viewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
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
                startActivity(intent);
            }
        });
    }


}
