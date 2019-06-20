package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.viewmodels.ConfigurationViewModel;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

public class HomeScreenActivity extends AppCompatActivity {

    private Player player;
    private ConfigurationViewModel viewModel;
    private UniverseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        player = viewModel.getPlayer();
        TextView playerText = (TextView)findViewById(R.id.player_text);
        playerText.setText(player.toString());

        RecyclerView recyclerView = findViewById(R.id.planet_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new UniverseAdapter();
        adapter.setPlanetList(viewModel.getUniverse());
        recyclerView.setAdapter(adapter);


        Log.d("APP", viewModel.getUniverse().toString());
    }
}
