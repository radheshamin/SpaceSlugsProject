package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.viewmodels.ConfigurationViewModel;

public class HomeScreenActivity extends AppCompatActivity {

    private Player player;
    private ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);
        viewModel = ViewModelProviders.of(this).get(ConfigurationViewModel.class);
        player = viewModel.getPlayer();
        Log.d("Check", "Player data: " + player);

    }
}
