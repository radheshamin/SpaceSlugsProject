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
import java.util.Map;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Planet;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;

/**
 * display of marketplace with methods for buying and selling goods
 */
public class MarketplaceActivity extends AppCompatActivity {

    private Player player;
    private Planet planet;
    private PlayerViewModel playerviewModel;
    private ShipViewModel shipViewModel;
    private Map<String, Integer> priceIndex;
    private SpaceShip ship;
    private City city;
    private TextView playerInfo;
    private TextView shipInfo;
    private HashMap<String, Integer> goods;
    private EditText buyWater;
    private int waterAmount;
    private EditText sellWater;
    private EditText buyFurs;
    private int fursAmount;
    private EditText sellFurs;
    private EditText buyFood;
    private int foodAmount;
    private EditText sellFood;
    private EditText buyOre;
    private int oreAmount;
    private EditText sellOre;
    private EditText buyGames;
    private int gamesAmount;
    private EditText sellGames;
    private EditText buyFirearms;
    private int firearmsAmount;
    private EditText sellFirearms;
    private EditText buyMedicine;
    private int medicineAmount;
    private EditText sellMedicine;
    private EditText buyMachines;
    private int machinesAmount;
    private EditText sellMachines;
    private EditText buyNarcotics;
    private int narcoticsAmount;
    private EditText sellNarcotics;
    private EditText buyRobots;
    private int robotsAmount;
    private EditText sellRobots;
    private TextView fuelText;

    private HashMap<String, Integer> amountIndex;

    public static final String CITY_AMOUNT = "edu.gatech.cs2340.spacetraders.views.CITY_AMOUNT";

    private static final String EXTRA_PLANET = "edu.gatech.cs2340.spacetraders.views.EXTRA_PLANET";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketplace_activity);

        playerviewModel = ViewModelProviders.of(this).get(PlayerViewModel.class);
        shipViewModel = ViewModelProviders.of(this).get(ShipViewModel.class);
        player = playerviewModel.getPlayer();
        ship = shipViewModel.getShip();
        goods = ship.getGoods();

        if (getIntent().hasExtra(PlanetDetailActivity.EXTRA_CITY)) {
            city = (City) getIntent().getSerializableExtra(PlanetDetailActivity.EXTRA_CITY);
        } else if (getIntent().hasExtra(PoliceEncounterActivity.EXTRA_CITY)) {
            city = (City) getIntent().getSerializableExtra(PoliceEncounterActivity.EXTRA_CITY);
        }
        if (getIntent().hasExtra(PlanetDetailActivity.CITY_AMOUNT)) {
            amountIndex = (HashMap<String, Integer>) getIntent()
                    .getSerializableExtra(PlanetDetailActivity.CITY_AMOUNT);
        } else {
            amountIndex = new HashMap<>();
        }

        playerInfo =  findViewById(R.id.player);
        playerInfo.setText(new StringBuilder("Money: $").append(player.getMoney()));

        shipInfo =  findViewById(R.id.ship);
        shipInfo.setText(ship.toString());

        TextView planetInfo = findViewById(R.id.planet);
        if (getIntent().hasExtra(PlanetDetailActivity.EXTRA_PLANET)) {
            planet = (Planet) getIntent().getSerializableExtra(PlanetDetailActivity.EXTRA_PLANET);
        } else if ((getIntent().hasExtra(PoliceEncounterActivity.EXTRA_PLANET))) {
            planet = (Planet) getIntent()
                    .getSerializableExtra(PoliceEncounterActivity.EXTRA_PLANET);
        }
        planetInfo.setText(new StringBuilder("Planet: ").append(planet.getName()).append(", City: ")
                .append(city.getName()));

        priceIndex = city.getPriceIndex();

        TextView waterPrice = findViewById(R.id.water_price);
        waterAmount = amountHelper(waterPrice, "Water", 3, 8);
        final TextView waterMax = findViewById(R.id.max_water);
        waterMax.setText(new StringBuilder("Amount Of Water Available: ").append(waterAmount));
        buyWater = findViewById(R.id.water_buy);
        sellWater = findViewById(R.id.water_sell);
        Button buyWaterButton = findViewById(R.id.water_button);
        buyWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterAmount = buttonHelper("Water", waterMax, buyWater, waterAmount);
            }
        });
        Button sellWaterButton = findViewById(R.id.water_sell_button);
        sellWaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waterAmount = sellButtonHelper("Water", waterMax, sellWater, waterAmount);
            }
        });


        TextView fursPrice =  findViewById(R.id.furs_price);
        fursAmount = amountHelper(fursPrice, "Furs", 3, 7);
        final TextView fursMax = findViewById(R.id.max_furs);
        fursMax.setText(new StringBuilder("Amount Of Furs Available: ").append(fursAmount));
        buyFurs = findViewById(R.id.furs_buy);
        sellFurs = findViewById(R.id.furs_sell);
        Button buyFursButton = findViewById(R.id.furs_button);
        buyFursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fursAmount = buttonHelper("Furs", fursMax, buyFurs, fursAmount);
            }
        });
        Button sellFursButton = findViewById(R.id.furs_sell_button);
        sellFursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fursAmount = sellButtonHelper("Furs", fursMax, sellFurs, fursAmount);
            }
        });


        TextView foodPrice =  findViewById(R.id.food_price);
        foodAmount = amountHelper(foodPrice, "Food", 2, 7);
        final TextView foodMax =  findViewById(R.id.max_food);
        foodMax.setText(new StringBuilder("Amount Of Food Available: ").append(foodAmount));
        buyFood = findViewById(R.id.food_buy);
        sellFood = findViewById(R.id.food_sell);
        Button buyFoodButton = findViewById(R.id.food_button);
        buyFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodAmount = buttonHelper("Food", foodMax, buyFood, foodAmount);
            }
        });
        Button sellFoodButton = findViewById(R.id.food_sell_button);
        sellFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foodAmount = sellButtonHelper("Food", foodMax, sellFood, foodAmount);
            }
        });


        TextView orePrice =  findViewById(R.id.ore_price);
        oreAmount = amountHelper(orePrice, "Ore", 3, 5);
        final TextView oreMax =  findViewById(R.id.max_ore);
        oreMax.setText(new StringBuilder("Amount Of Ore Available: ").append(oreAmount));
        buyOre = findViewById(R.id.ore_buy);
        sellOre = findViewById(R.id.ore_sell);
        Button buyOreButton = findViewById(R.id.ore_button);
        buyOreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oreAmount = buttonHelper("Ore", oreMax, buyOre, oreAmount);
            }
        });
        Button sellOreButton = findViewById(R.id.ore_sell_button);
        sellOreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oreAmount = sellButtonHelper("Ore", oreMax, sellOre, oreAmount);
            }
        });


        TextView gamesPrice =  findViewById(R.id.games_price);
        gamesAmount = amountHelper(gamesPrice, "Games", 2, 6);
        final TextView gamesMax = findViewById(R.id.max_games);
        gamesMax.setText(new StringBuilder("Amount Of Games Available: ").append(gamesAmount));
        buyGames = findViewById(R.id.games_buy);
        sellGames = findViewById(R.id.games_sell);
        Button buyGamesButton = findViewById(R.id.games_button);
        buyGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamesAmount = buttonHelper("Games", gamesMax, buyGames, gamesAmount);
            }
        });
        Button sellGamesButton = findViewById(R.id.games_sell_button);
        sellGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gamesAmount = sellButtonHelper("Games", gamesMax, sellGames, gamesAmount);
            }
        });


        TextView firearmsPrice =  findViewById(R.id.firearms_price);
        firearmsAmount = amountHelper(firearmsPrice, "Firearms", 2, 4);
        final TextView firearmsMax =  findViewById(R.id.max_firearms);
        firearmsMax.setText(new StringBuilder("Amount Of Firearms Available: ")
                .append(firearmsAmount));
        buyFirearms = findViewById(R.id.firearms_buy);
        sellFirearms = findViewById(R.id.firearms_sell);
        Button buyFirearmsButton = findViewById(R.id.firearms_button);
        buyFirearmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firearmsAmount = buttonHelper("Firearms", firearmsMax,
                        buyFirearms, firearmsAmount);
            }
        });
        Button sellFirearmsButton = findViewById(R.id.firearms_sell_button);
        sellFirearmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firearmsAmount = sellButtonHelper("Firearms", firearmsMax,
                        sellFirearms, firearmsAmount);
            }
        });


        TextView medicinePrice = findViewById(R.id.medicine_price);
        medicineAmount = amountHelper(medicinePrice, "Medicine", 1, 5);
        final TextView medicineMax = findViewById(R.id.max_medicine);
        medicineMax.setText(new StringBuilder("Amount Of Medicine Available: ")
                .append(medicineAmount));
        buyMedicine = findViewById(R.id.medicine_buy);
        sellMedicine = findViewById(R.id.medicine_sell);
        Button buyMedicineButton = findViewById(R.id.medicine_button);
        buyMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineAmount = buttonHelper("Medicine", medicineMax,
                        buyMedicine, medicineAmount);
            }
        });
        Button sellMedicineButton = findViewById(R.id.medicine_sell_button);
        sellMedicineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicineAmount = sellButtonHelper("Medicine", medicineMax,
                        sellMedicine, medicineAmount);
            }
        });


        TextView machinesPrice =  findViewById(R.id.machines_price);
        machinesAmount = amountHelper(machinesPrice, "Machines", 2, 3);
        final TextView machinesMax = findViewById(R.id.max_machines);
        machinesMax.setText(new StringBuilder("Amount Of Machines Available: ")
                .append(machinesAmount));
        buyMachines = findViewById(R.id.machines_buy);
        sellMachines = findViewById(R.id.machines_sell);
        Button buyMachinesButton = findViewById(R.id.machines_button);
        buyMachinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                machinesAmount = buttonHelper("Machines", machinesMax,
                        buyMachines, machinesAmount);
            }
        });
        Button sellMachinesButton = findViewById(R.id.machines_sell_button);
        sellMachinesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                machinesAmount = sellButtonHelper("Machines", machinesMax,
                        sellMachines, machinesAmount);
            }
        });


        TextView narcoticsPrice = findViewById(R.id.narcotics_price);
        narcoticsAmount = amountHelper(narcoticsPrice, "Narcotics", 2, 4);
        final TextView narcoticsMax = findViewById(R.id.max_narcotics);
        narcoticsMax.setText(new StringBuilder("Amount Of Narcotics Available: ")
                .append(narcoticsAmount));
        buyNarcotics = findViewById(R.id.narcotics_buy);
        sellNarcotics = findViewById(R.id.narcotics_sell);
        Button buyNarcoticsButton = findViewById(R.id.narcotics_button);
        buyNarcoticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                narcoticsAmount = buttonHelper("Narcotics", narcoticsMax,
                        buyNarcotics, narcoticsAmount);
            }
        });
        Button sellNarcoticsButton = findViewById(R.id.narcotics_sell_button);
        sellNarcoticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                narcoticsAmount = sellButtonHelper("Narcotics", narcoticsMax,
                        sellNarcotics, narcoticsAmount);
            }
        });


        TextView robotsPrice =  findViewById(R.id.robots_price);
        robotsAmount = amountHelper(robotsPrice, "Robots", 2, 2);
        final TextView robotsMax =  findViewById(R.id.max_robots);
        robotsMax.setText(new StringBuilder("Amount Of Robots Available: ").append(robotsAmount));
        buyRobots = findViewById(R.id.robots_buy);
        sellRobots = findViewById(R.id.robots_sell);
        Button buyRobotsButton = findViewById(R.id.robots_button);
        buyRobotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                robotsAmount = buttonHelper("Robots", robotsMax, buyRobots, robotsAmount);
            }
        });
        Button sellRobotsButton = findViewById(R.id.robots_sell_button);
        sellRobotsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                robotsAmount = sellButtonHelper("Robots", robotsMax, sellRobots, robotsAmount);
            }
        });

        final Button refillFuel = findViewById(R.id.refill_fuel);
        fuelText =  findViewById(R.id.fuel_text);
        refillFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int fuelCost = 50;
                if ((player.getMoney() >= fuelCost) && (ship.getFuel() != ship.getMaxFuel())) {
                    ship.setFuel(ship.getMaxFuel());
                    player.setMoney(player.getMoney() - fuelCost);
                    playerviewModel.addPlayer(player);
                    shipViewModel.setShip(ship);
                    playerInfo.setText(new StringBuilder("Money: $").append(player.getMoney()));
                    shipInfo.setText(ship.toString());
                    fuelText.setText(new StringBuilder("You Have Max Fuel"));
                } else if (player.getMoney() < fuelCost){
                    fuelText.setText(new StringBuilder("You Cannot Afford To Buy Fuel"));
                } else if (ship.getFuel() == ship.getMaxFuel()) {
                    fuelText.setText(new StringBuilder("You Have Max Fuel"));
                }
            }
        });
    }

    private int amountHelper(TextView price, String good,
                             int varianceAmount, int baseAmount) {
        int itemAmount = 0;
        if (priceIndex.get(good) <= 0) {
            price.setText(new StringBuilder(good).append(" cannot be bought or sold here."));
        } else {
            price.setText(new StringBuilder("Price: $").append(priceIndex.get(good)));
            if (amountIndex.get(good) != null) {
                itemAmount = amountIndex.get(good);
            } else {
                itemAmount = ((int) (Math.random() * varianceAmount)) + baseAmount;
            }
        }
        amountIndex.put(good, itemAmount);
        return itemAmount;
    }

    private int buttonHelper(String good, TextView max, EditText buy, int itemAmount) {
        int newAmount = itemAmount;
        if ((priceIndex.get(good) > 0) && !buy.getText().toString().trim().isEmpty()) {
            int amount = Integer.parseInt(buy.getText().toString());
            if ((ship.getCargoSpaceLeft() >= amount)
                    && (player.getMoney() >= (amount * priceIndex.get(good)))
                    && (amount <= itemAmount)) {
                player.setMoney(player.getMoney() - (amount * priceIndex.get(good)));
                goods.put(good, goods.get(good) + amount);
                newAmount = newAmount - amount;
                amountIndex.put(good, newAmount);
                max.setText(new StringBuilder("Amount Of ").append(good).append(" Available: ")
                        .append(newAmount));
                ship.setGoods(goods);
                shipViewModel.setShip(ship);
                playerviewModel.addPlayer(player);
                buy.getText().clear();
                playerInfo.setText(new StringBuilder("Money: $").append(player.getMoney()));
                shipInfo.setText(ship.toString());
            } else if (ship.getCargoSpaceLeft() < amount) {
                max.setText(new StringBuilder("Amount Of ").append(good).append(" Available: ")
                        .append(itemAmount).append(" You do not have enough cargo space")
                                .append(" for that amount."));
                buyWater.getText().clear();
            } else if (player.getMoney() < (amount * priceIndex.get(good))) {
                max.setText(new StringBuilder("Amount Of ").append(good).append(" Available: ")
                        .append(itemAmount).append(" You do not have enough money")
                                .append(" for that amount."));
            } else if (amount > itemAmount) {
                max.setText(new StringBuilder("Amount Of ").append(good).append(" Available: ")
                        .append(itemAmount).append(" There are not that many ")
                        .append(good.toLowerCase()).append(" for sale."));
            }
        }
        return newAmount;
    }

    private int sellButtonHelper(String good, TextView max, EditText sell, int itemAmount) {
        int newAmount = itemAmount;
        if ((priceIndex.get(good) > 0) && !sell.getText().toString().trim().isEmpty()) {
            int amount = Integer.parseInt(sell.getText().toString());
            if (goods.get(good) >= amount) {
                player.setMoney(player.getMoney() + (amount * priceIndex.get(good)));
                goods.put(good, goods.get(good) - amount);
                newAmount = newAmount + amount;
                max.setText(new StringBuilder("Amount Of ").append(good.toLowerCase())
                        .append(" Available: ").append(newAmount));
                ship.setGoods(goods);
                shipViewModel.setShip(ship);
                playerviewModel.addPlayer(player);
                sell.getText().clear();
                playerInfo.setText(new StringBuilder("Money: $").append(player.getMoney()));
                shipInfo.setText(ship.toString());
                amountIndex.put(good, newAmount);
            }
        }
        return newAmount;
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent currMarket = new Intent(MarketplaceActivity.this, PlanetDetailActivity.class);
        currMarket.putExtra(CITY_AMOUNT, amountIndex);
        currMarket.putExtra(EXTRA_PLANET, getIntent()
                .getSerializableExtra(MarketplaceActivity.EXTRA_PLANET));
        startActivity(currMarket);
        finish();

    }
}
