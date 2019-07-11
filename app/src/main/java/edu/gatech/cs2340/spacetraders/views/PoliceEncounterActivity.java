package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;

public class PoliceEncounterActivity extends AppCompatActivity {

    public static final String EXTRA_CITY = "edu.gatech.cs2340.spacetraders.views.EXTRA_CITY";
    public static final String EXTRA_PLANET = "edu.gatech.cs2340.spacetraders.views.EXTRA_PLANET";
    private Player player;
    private PlayerViewModel playerViewModel;
    private ShipViewModel shipViewModel;
    private SpaceShip ship;
    private HashMap<String, Integer> goods;
    private int illegalAmount;
    private int illegalMoney;
    private int money;
    private EditText bribeAmount;
    private Button truth;
    private Button flee;
    private Button bribe;
    private Button bluff;
    private TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.police_encounter);

        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);
        ship = shipViewModel.getShip();
        player = playerViewModel.getPlayer();

        final TextView playerInfo = findViewById(R.id.player);
        playerInfo.setText(player.toString());

        final TextView shipInfo = findViewById(R.id.ship);
        shipInfo.setText(ship.toString());

        goods = ship.getGoods();
        money = player.getMoney();
        illegalMoney = goods.get("Firearms") * 1250;
        illegalMoney += goods.get("Narcotics") * 3500;
        illegalAmount = goods.get("Firearms");
        illegalAmount += goods.get("Narcotics");

        result = findViewById(R.id.result);

        truth = findViewById(R.id.truth);
        truth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goods.put("Firearms", 0);
                goods.put("Narcotics", 0);
                ship.setGoods(goods);
                player.setMoney(money - ((int)(illegalMoney * .1)));
                shipViewModel.setShip(ship);
                playerViewModel.addPlayer(player);
                if (illegalAmount == 0) {
                    result.setText(new StringBuilder("You had no illegal goods and left the police peacefully. Click any of the buttons to continue"));
                } else {
                    result.setText(new StringBuilder("You surrendered your illegal goods and paid a fine of $")
                            .append((int) (illegalMoney * .1)).append(". Click any of the buttons to continue."));
                }
                setContinue();
            }
        });

        bluff = findViewById(R.id.bluff);
        bluff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = (int)(Math.random() * illegalAmount);
                if (random >= 2) {
                    goods.put("Firearms", 0);
                    goods.put("Narcotics", 0);
                    ship.setGoods(goods);
                    player.setMoney(money - ((int)(illegalMoney * .25)));
                    shipViewModel.setShip(ship);
                    playerViewModel.addPlayer(player);
                    result.setText(new StringBuilder("The police caught your bluff. You forfeited your illegal goods and paid a fine of $")
                            .append(((int)(illegalMoney * .25))).append(
                            ". Click any of the buttons to continue."));
                } else {
                    result.setText(new StringBuilder("You successfully bluffed your way out of the situation.")
                            .append(" You kept your illegal goods.").append(" Click any of the buttons to continue."));
                }
                setContinue();
            }
        });

        bribeAmount = findViewById(R.id.bribe_amount);

        bribe = findViewById(R.id.bribe_button);
        bribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!bribeAmount.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(bribeAmount.getText().toString());
                    if (amount > (illegalMoney / 2) && amount <= money) {
                        player.setMoney(money - amount);
                        playerViewModel.addPlayer(player);
                        result.setText(new StringBuilder("Your bribe was successful. You kept your illegal goods and paid $")
                                .append(amount).append(". Click any of the buttons to continue."));
                    } else {
                        goods.put("Firearms", 0);
                        goods.put("Narcotics", 0);
                        ship.setGoods(goods);
                        player.setMoney(money - ((int)(illegalMoney * .25)));
                        shipViewModel.setShip(ship);
                        playerViewModel.addPlayer(player);
                        result.setText(new StringBuilder("The police didn't accept your bribe. ")
                                .append("You forfeited your illegal goods and paid a fine of $")
                                .append((int)(illegalMoney * .25)).append(". Click any of the buttons to continue."));
                    }
                }
                setContinue();
            }
        });

        flee = findViewById(R.id.flee);
        flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = (int)(Math.random() * illegalAmount);
                if (random >= 2) {
                    goods.put("Firearms", 0);
                    goods.put("Narcotics", 0);
                    ship.setGoods(goods);
                    player.setMoney(money - ((int)(illegalMoney * .25)));
                    shipViewModel.setShip(ship);
                    playerViewModel.addPlayer(player);
                    result.setText(new StringBuilder("The police caught you while you were attempting to flee. ")
                            .append("You forfeited your illegal goods and paid a fine of $")
                            .append((int)(illegalMoney * .25)).append(". Click any of the buttons to continue."));
                } else {
                    result.setText(new StringBuilder("You successfully fled from the police. ")
                            .append("You kept your illegal goods").append(". Click any of the buttons to continue."));
                }
                setContinue();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(PoliceEncounterActivity.this, PoliceEncounterActivity.class);
        startActivityForResult(intent, 1);
        finish();

    }

    public void setContinue() {
        final TextView playerInfo = findViewById(R.id.player);
        playerInfo.setText(player.toString());

        final TextView shipInfo = findViewById(R.id.ship);
        shipInfo.setText(ship.toString());
        playerInfo.setText(player.toString());
        shipInfo.setText(ship.toString());
        StringBuilder continueText = new StringBuilder("Continue");
        truth.setText(continueText);
        truth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                finishEncounter();
            }
        });
        bluff.setText(continueText);
        bluff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                finishEncounter();
            }
        });
        bribe.setText(continueText);
        bribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                finishEncounter();
            }
        });
        flee.setText(continueText);
        flee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                finishEncounter();
            }
        });
    }

    public void finishEncounter() {
        Intent detail = new Intent(PoliceEncounterActivity.this, MarketplaceActivity.class);

        Planet planet = (Planet) getIntent().getSerializableExtra(PlanetDetailActivity.EXTRA_PLANET);
        detail.putExtra(EXTRA_PLANET, planet);
        City city =(City) getIntent().getSerializableExtra(PlanetDetailActivity.EXTRA_CITY);
        detail.putExtra(EXTRA_CITY, city);

        startActivity(detail);
        finish();
    }
}
