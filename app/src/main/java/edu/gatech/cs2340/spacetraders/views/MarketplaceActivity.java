package edu.gatech.cs2340.spacetraders.views;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

import edu.gatech.cs2340.spacetraders.R;
import edu.gatech.cs2340.spacetraders.entity.City;
import edu.gatech.cs2340.spacetraders.entity.Player;
import edu.gatech.cs2340.spacetraders.entity.SpaceShip;
import edu.gatech.cs2340.spacetraders.viewmodels.PlayerViewModel;
import edu.gatech.cs2340.spacetraders.viewmodels.ShipViewModel;

public class MarketplaceActivity extends AppCompatActivity {

    private Player player;
    private PlayerViewModel playerviewModel;
    private ShipViewModel shipViewModel;
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

    public HashMap<String, Integer> amountIndex;

    public static final String CITY_AMOUNT = "edu.gatech.cs2340.spacetraders.views.CITY_AMOUNT";

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
        } else {
            //no city is an internal error, this should not happen
            Log.d("APP", "INTERNAL ERROR< NO CITY PASSED");
        }
        if (getIntent().hasExtra(PlanetDetailActivity.CITY_AMOUNT)) {
            amountIndex = (HashMap<String, Integer>) getIntent().getSerializableExtra(PlanetDetailActivity.CITY_AMOUNT);
        } else {
            amountIndex = new HashMap<String, Integer>();
        }

        playerInfo = (TextView) findViewById(R.id.player);
        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));

        shipInfo = (TextView) findViewById(R.id.ship);
        shipInfo.setText(ship.toString());

        final HashMap<String, Integer> priceIndex = city.getPriceIndex();

        TextView waterPrice = (TextView) findViewById(R.id.water_price);
        waterAmount = 0;
        if (priceIndex.get("Water") <= 0) {
            waterPrice.setText("Water cannot be bought or sold here.");
        } else {
            waterPrice.setText("Price: $" + Integer.toString(priceIndex.get("Water")));
            if (amountIndex.get("Water") != null) {
                waterAmount = amountIndex.get("Water");
            } else {
                waterAmount = ((int) (Math.random() * 3)) + 8;
            }
        }
        amountIndex.put("Water", waterAmount);
        final TextView waterMax = (TextView) findViewById(R.id.max_water);
        waterMax.setText("Amount Of Water Available: " + waterAmount);
        buyWater = (EditText)findViewById(R.id.water_buy);
        sellWater = (EditText)findViewById(R.id.water_sell);
        Button buyWaterButton = (Button)findViewById(R.id.water_button);
        buyWaterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Water") > 0 && !buyWater.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyWater.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Water")) && amount <= waterAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Water")));
                        goods.put("Water", goods.get("Water") + amount);
                        waterAmount = waterAmount - amount;
                        amountIndex.put("Water", waterAmount);
                        waterMax.setText("Amount Of Water Available: " + waterAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        buyWater.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    } else if (ship.getCargoSpace() < amount) {
                        waterMax.setText("Amount Of Water Available: " + waterAmount +
                                " You do not have enough cargo space for that amount.");
                        buyWater.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Water"))) {
                        waterMax.setText("Amount Of Water Available: " + waterAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > waterAmount) {
                        waterMax.setText("Amount Of Water Available: " + waterAmount +
                                " There are not that many water for sale.");
                    }
                }
            }
            });
        Button sellWaterButton = (Button)findViewById(R.id.water_sell_button);
        sellWaterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Water") > 0 && !sellWater.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellWater.getText().toString());
                    if (goods.get("Water") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Water")));
                        goods.put("Water", goods.get("Water") - amount);
                        waterAmount = waterAmount + amount;
                        waterMax.setText("Amount Of Water Available: " + waterAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellWater.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Water", waterAmount);
                    }
                }
            }
        });


        TextView fursPrice = (TextView) findViewById(R.id.furs_price);
        fursAmount = 0;
        if (priceIndex.get("Furs") <= 0) {
            fursPrice.setText("Furs cannot be bought or sold here.");
        } else {
            fursPrice.setText("Price: $" + Integer.toString(priceIndex.get("Furs")));
            if (amountIndex.get("Furs") != null) {
                fursAmount = amountIndex.get("Furs");
            } else {
                fursAmount = ((int) (Math.random() * 3)) + 7;
            }
        }
        amountIndex.put("Furs", fursAmount);
        final TextView fursMax = (TextView) findViewById(R.id.max_furs);
        fursMax.setText("Amount Of Furs Available: " + fursAmount);
        buyFurs = (EditText)findViewById(R.id.furs_buy);
        sellFurs = (EditText)findViewById(R.id.furs_sell);
        Button buyFursButton = (Button)findViewById(R.id.furs_button);
        buyFursButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Furs") > 0 && !buyFurs.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyFurs.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Furs")) && amount <= fursAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Furs")));
                        goods.put("Furs", goods.get("Furs") + amount);
                        fursAmount = fursAmount - amount;
                        fursMax.setText("Amount Of Furs Available: " + fursAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        amountIndex.put("Furs", fursAmount);
                        buyFurs.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    } else if (ship.getCargoSpace() < amount) {
                        fursMax.setText("Amount Of Furs Available: " + fursAmount +
                                " You do not have enough cargo space for that amount.");
                        buyFurs.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Furs"))) {
                        fursMax.setText("Amount Of Furs Available: " + fursAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > fursAmount) {
                        fursMax.setText("Amount Of Furs Available: " + fursAmount +
                                " There are not that many fur for sale.");
                    }
                }
            }
        });
        Button sellFursButton = (Button)findViewById(R.id.furs_sell_button);
        sellFursButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Furs") > 0 && !sellFurs.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellFurs.getText().toString());
                    if (goods.get("Furs") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Furs")));
                        goods.put("Furs", goods.get("Furs") - amount);
                        fursAmount = fursAmount + amount;
                        fursMax.setText("Amount Of Furs Available: " + fursAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellFurs.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Furs", fursAmount);

                    }
                }
            }
        });


        TextView foodPrice = (TextView) findViewById(R.id.food_price);
        foodAmount = 0;
        if (priceIndex.get("Food") <= 0) {
            foodPrice.setText("Food cannot be bought or sold here.");
        } else {
            foodPrice.setText("Price: $" + Integer.toString(priceIndex.get("Food")));
            if (amountIndex.get("Food") != null) {
                foodAmount = amountIndex.get("Food");
            } else {
                foodAmount = ((int) (Math.random() * 2)) + 7;
            }
        }
        amountIndex.put("Food", foodAmount);
        final TextView foodMax = (TextView) findViewById(R.id.max_food);
        foodMax.setText("Amount Of Food Available: " + foodAmount);
        buyFood = (EditText)findViewById(R.id.food_buy);
        sellFood = (EditText)findViewById(R.id.food_sell);
        Button buyFoodButton = (Button)findViewById(R.id.food_button);
        buyFoodButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Food") > 0 && !buyFood.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyFood.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Food")) && amount <= foodAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Food")));
                        goods.put("Food", goods.get("Food") + amount);
                        foodAmount = foodAmount - amount;
                        foodMax.setText("Amount Of Food Available: " + foodAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        amountIndex.put("Food", foodAmount);
                        buyFood.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    } else if (ship.getCargoSpace() < amount) {
                        foodMax.setText("Amount Of Food Available: " + foodAmount +
                                " You do not have enough cargo space for that amount.");
                        buyFood.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Food"))) {
                        foodMax.setText("Amount Of Food Available: " + foodAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > foodAmount) {
                        foodMax.setText("Amount Of Food Available: " + foodAmount +
                                " There are not that many food for sale.");
                    }
                }
            }
        });
        Button sellFoodButton = (Button)findViewById(R.id.food_sell_button);
        sellFoodButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Food") > 0 && !sellFood.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellFood.getText().toString());
                    if (goods.get("Food") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Food")));
                        goods.put("Food", goods.get("Food") - amount);
                        foodAmount = foodAmount + amount;
                        foodMax.setText("Amount Of Food Available: " + foodAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellFood.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Food", foodAmount);
                    }
                }
            }
        });


        TextView orePrice = (TextView) findViewById(R.id.ore_price);
        oreAmount = 0;
        if (priceIndex.get("Ore") <= 0) {
            orePrice.setText("Ore cannot be bought or sold here.");
        } else {
            orePrice.setText("Price: $" + Integer.toString(priceIndex.get("Ore")));
            if (amountIndex.get("Ore") != null) {
                oreAmount = amountIndex.get("Ore");
            } else {
                oreAmount = ((int) (Math.random() * 3)) + 5;
            }
        }
        amountIndex.put("Ore", oreAmount);
        final TextView oreMax = (TextView) findViewById(R.id.max_ore);
        oreMax.setText("Amount Of Ore Available: " + oreAmount);
        buyOre = (EditText)findViewById(R.id.ore_buy);
        sellOre = (EditText)findViewById(R.id.ore_sell);
        Button buyOreButton = (Button)findViewById(R.id.ore_button);
        buyOreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Ore") > 0 && !buyOre.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyOre.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Ore")) && amount <= oreAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Ore")));
                        goods.put("Ore", goods.get("Ore") + amount);
                        oreAmount = oreAmount - amount;
                        oreMax.setText("Amount Of Ore Available: " + oreAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        buyOre.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Ore", oreAmount);
                    } else if (ship.getCargoSpace() < amount) {
                        oreMax.setText("Amount Of Ore Available: " + oreAmount +
                                " You do not have enough cargo space for that amount.");
                        buyOre.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Ore"))) {
                        oreMax.setText("Amount Of Ore Available: " + oreAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > oreAmount) {
                        oreMax.setText("Amount Of Ore Available: " + oreAmount +
                                " There are not that many ore for sale.");
                    }
                }
            }
        });
        Button sellOreButton = (Button)findViewById(R.id.ore_sell_button);
        sellOreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Ore") > 0 && !sellOre.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellOre.getText().toString());
                    if (goods.get("Ore") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Ore")));
                        goods.put("Ore", goods.get("Ore") - amount);
                        oreAmount = oreAmount + amount;
                        oreMax.setText("Amount Of Ore Available: " + oreAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellOre.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Ore", oreAmount);
                    }
                }
            }
        });


        TextView gamesPrice = (TextView) findViewById(R.id.games_price);
        gamesAmount = 0;
        if (priceIndex.get("Games") <= 0) {
            gamesPrice.setText("Games cannot be bought or sold here.");
        } else {
            gamesPrice.setText("Price: $" + Integer.toString(priceIndex.get("Games")));
            if (amountIndex.get("Games") != null) {
                gamesAmount = amountIndex.get("Games");
            } else {
                gamesAmount = ((int) (Math.random() * 2)) + 6;
            }
        }
        amountIndex.put("Games", gamesAmount);
        final TextView gamesMax = (TextView) findViewById(R.id.max_games);
        gamesMax.setText("Amount Of Games Available: " + gamesAmount);
        buyGames = (EditText)findViewById(R.id.games_buy);
        sellGames = (EditText)findViewById(R.id.games_sell);
        Button buyGamesButton = (Button)findViewById(R.id.games_button);
        buyGamesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Games") > 0 && !buyGames.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyGames.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Games")) && amount <= gamesAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Games")));
                        goods.put("Games", goods.get("Games") + amount);
                        gamesAmount = gamesAmount - amount;
                        gamesMax.setText("Amount Of Games Available: " + gamesAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        buyGames.getText().clear();
                        amountIndex.put("Games", gamesAmount);
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    } else if (ship.getCargoSpace() < amount) {
                        gamesMax.setText("Amount Of Games Available: " + gamesAmount +
                                " You do not have enough cargo space for that amount.");
                        buyGames.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Games"))) {
                        gamesMax.setText("Amount Of Games Available: " + gamesAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > gamesAmount) {
                        gamesMax.setText("Amount Of Games Available: " + gamesAmount +
                                " There are not that many games for sale.");
                    }
                }
            }
        });
        Button sellGamesButton = (Button)findViewById(R.id.games_sell_button);
        sellGamesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Games") > 0 && !sellGames.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellGames.getText().toString());
                    if (goods.get("Games") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Games")));
                        goods.put("Games", goods.get("Games") - amount);
                        gamesAmount = gamesAmount + amount;
                        gamesMax.setText("Amount Of Games Available: " + gamesAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellGames.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Games", gamesAmount);
                    }
                }
            }
        });


        TextView firearmsPrice = (TextView) findViewById(R.id.firearms_price);
        firearmsAmount = 0;
        if (priceIndex.get("Firearms") <= 0) {
            firearmsPrice.setText("Firearms cannot be bought or sold here.");
        } else {
            firearmsPrice.setText("Price: $" + Integer.toString(priceIndex.get("Firearms")));
            if (amountIndex.get("Firearms") != null) {
                firearmsAmount = amountIndex.get("Firearms");
            } else {
                firearmsAmount = ((int) (Math.random() * 2)) + 4;
            }
        }
        amountIndex.put("Firearms", firearmsAmount);
        final TextView firearmsMax = (TextView) findViewById(R.id.max_firearms);
        firearmsMax.setText("Amount Of Firearms Available: " + firearmsAmount);
        buyFirearms = (EditText)findViewById(R.id.firearms_buy);
        sellFirearms = (EditText)findViewById(R.id.firearms_sell);
        Button buyFirearmsButton = (Button)findViewById(R.id.firearms_button);
        buyFirearmsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Firearms") > 0 && !buyFirearms.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyFirearms.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Firearms")) && amount <= firearmsAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Firearms")));
                        goods.put("Firearms", goods.get("Firearms") + amount);
                        firearmsAmount = firearmsAmount - amount;
                        firearmsMax.setText("Amount Of Firearms Available: " + firearmsAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        buyFirearms.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Firearms", firearmsAmount);
                    }else if (ship.getCargoSpace() < amount) {
                        firearmsMax.setText("Amount Of Firearms Available: " + firearmsAmount +
                                " You do not have enough cargo space for that amount.");
                        buyFirearms.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Firearms"))) {
                        firearmsMax.setText("Amount Of Firearms Available: " + firearmsAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > firearmsAmount) {
                        firearmsMax.setText("Amount Of Firearms Available: " + firearmsAmount +
                                " There are not that many firearms for sale.");
                    }
                }
            }
        });
        Button sellFirearmsButton = (Button)findViewById(R.id.firearms_sell_button);
        sellFirearmsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Firearms") > 0 && !sellFirearms.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellFirearms.getText().toString());
                    if (goods.get("Firearms") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Firearms")));
                        goods.put("Firearms", goods.get("Firearms") - amount);
                        firearmsAmount = firearmsAmount + amount;
                        firearmsMax.setText("Amount Of Firearms Available: " + firearmsAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellFirearms.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Firearms", firearmsAmount);
                    }
                }
            }
        });


        TextView medicinePrice = (TextView) findViewById(R.id.medicine_price);
        medicineAmount = 0;
        if (priceIndex.get("Medicine") <= 0) {
            medicinePrice.setText("Medicine cannot be bought or sold here.");
        } else {
            medicinePrice.setText("Price: $" + Integer.toString(priceIndex.get("Medicine")));
            if (amountIndex.get("Medicine") != null) {
                medicineAmount = amountIndex.get("Medicine");
            } else {
                medicineAmount = ((int) (Math.random() * 1)) + 5;
            }
        }
        amountIndex.put("Medicine", medicineAmount);
        final TextView medicineMax = (TextView) findViewById(R.id.max_medicine);
        medicineMax.setText("Amount Of Medicine Available: " + medicineAmount);
        buyMedicine = (EditText)findViewById(R.id.medicine_buy);
        sellMedicine = (EditText)findViewById(R.id.medicine_sell);
        Button buyMedicineButton = (Button)findViewById(R.id.medicine_button);
        buyMedicineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Medicine") > 0 && !buyMedicine.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyMedicine.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Medicine")) && amount <= medicineAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Medicine")));
                        goods.put("Medicine", goods.get("Medicine") + amount);
                        medicineAmount = medicineAmount - amount;
                        medicineMax.setText("Amount Of Medicine Available: " + medicineAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        amountIndex.put("Medicine", medicineAmount);
                        buyMedicine.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    } else if (ship.getCargoSpace() < amount) {
                        medicineMax.setText("Amount Of Medicine Available: " + medicineAmount +
                                " You do not have enough cargo space for that amount.");
                        buyMedicine.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Medicine"))) {
                        medicineMax.setText("Amount Of Medicine Available: " + medicineAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > medicineAmount) {
                        medicineMax.setText("Amount Of Medicine Available: " + medicineAmount +
                                " There are not that many medicine for sale.");
                    }
                }
            }
        });
        Button sellMedicineButton = (Button)findViewById(R.id.medicine_sell_button);
        sellMedicineButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Medicine") > 0 && !sellMedicine.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellMedicine.getText().toString());
                    if (goods.get("Medicine") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Medicine")));
                        goods.put("Medicine", goods.get("Medicine") - amount);
                        medicineAmount = medicineAmount + amount;
                        medicineMax.setText("Amount Of Medicine Available: " + medicineAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellMedicine.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Medicine", medicineAmount);
                    }
                }
            }
        });


        TextView machinesPrice = (TextView) findViewById(R.id.machines_price);
        machinesAmount = 0;
        if (priceIndex.get("Machines") <= 0) {
            machinesPrice.setText("Machines cannot be bought or sold here.");
        } else {
            machinesPrice.setText("Price: $" + Integer.toString(priceIndex.get("Machines")));
            if (amountIndex.get("Machines") != null) {
                machinesAmount = amountIndex.get("Machines");
            } else {
                machinesAmount = ((int) (Math.random() * 2)) + 3;
            }
        }
        amountIndex.put("Machines", machinesAmount);
        final TextView machinesMax = (TextView) findViewById(R.id.max_machines);
        machinesMax.setText("Amount Of Machines Available: " + machinesAmount);
        buyMachines = (EditText)findViewById(R.id.machines_buy);
        sellMachines = (EditText)findViewById(R.id.machines_sell);
        Button buyMachinesButton = (Button)findViewById(R.id.machines_button);
        buyMachinesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Machines") > 0 && !buyMachines.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyMachines.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Machines")) && amount <= machinesAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Machines")));
                        goods.put("Machines", goods.get("Machines") + amount);
                        machinesAmount = machinesAmount - amount;
                        machinesMax.setText("Amount Of Machines Available: " + machinesAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        buyMachines.getText().clear();
                        amountIndex.put("Machines", machinesAmount);
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    } else if (ship.getCargoSpace() < amount) {
                        machinesMax.setText("Amount Of Machines Available: " + machinesAmount +
                                " You do not have enough cargo space for that amount.");
                        buyMachines.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Machines"))) {
                        machinesMax.setText("Amount Of Machines Available: " + machinesAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > machinesAmount) {
                        machinesMax.setText("Amount Of Machines Available: " + machinesAmount +
                                " There are not that many machines for sale.");
                    }
                }
            }
        });
        Button sellMachinesButton = (Button)findViewById(R.id.machines_sell_button);
        sellMachinesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Machines") > 0 && !sellMachines.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellMachines.getText().toString());
                    if (goods.get("Machines") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Machines")));
                        goods.put("Machines", goods.get("Machines") - amount);
                        machinesAmount = machinesAmount + amount;
                        machinesMax.setText("Amount Of Machines Available: " + machinesAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        amountIndex.put("Machines", machinesAmount);
                        sellMachines.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    }
                }
            }
        });


        TextView narcoticsPrice = (TextView) findViewById(R.id.narcotics_price);
        narcoticsAmount = 0;
        if (priceIndex.get("Narcotics") <= 0) {
            narcoticsPrice.setText("Narcotics cannot be bought or sold here.");
        } else {
            narcoticsPrice.setText("Price: $" + Integer.toString(priceIndex.get("Narcotics")));
            if (amountIndex.get("Narcotics") != null) {
                narcoticsAmount = amountIndex.get("Narcotics");
            } else {
                narcoticsAmount = ((int) (Math.random() * 2)) + 4;
            }
        }
        amountIndex.put("Narcotics", narcoticsAmount);
        final TextView narcoticsMax = (TextView) findViewById(R.id.max_narcotics);
        narcoticsMax.setText("Amount Of Narcotics Available: " + narcoticsAmount);
        buyNarcotics = (EditText)findViewById(R.id.narcotics_buy);
        sellNarcotics = (EditText)findViewById(R.id.narcotics_sell);
        Button buyNarcoticsButton = (Button)findViewById(R.id.narcotics_button);
        buyNarcoticsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Narcotics") > 0 && !buyNarcotics.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyNarcotics.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Narcotics")) && amount <= narcoticsAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Narcotics")));
                        goods.put("Narcotics", goods.get("Narcotics") + amount);
                        narcoticsAmount = narcoticsAmount - amount;
                        narcoticsMax.setText("Amount Of Narcotics Available: " + narcoticsAmount);
                        ship.setGoods(goods);
                        amountIndex.put("Narcotics", narcoticsAmount);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        buyNarcotics.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                    } else if (ship.getCargoSpace() < amount) {
                        narcoticsMax.setText("Amount Of Narcotics Available: " + narcoticsAmount +
                                " You do not have enough cargo space for that amount.");
                        buyNarcotics.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Narcotics"))) {
                        narcoticsMax.setText("Amount Of Narcotics Available: " + narcoticsAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > narcoticsAmount) {
                        narcoticsMax.setText("Amount Of Narcotics Available: " + narcoticsAmount +
                                " There are not that many narcotics for sale.");
                    }
                }
            }
        });
        Button sellNarcoticsButton = (Button)findViewById(R.id.narcotics_sell_button);
        sellNarcoticsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Narcotics") > 0 && !sellNarcotics.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellNarcotics.getText().toString());
                    if (goods.get("Narcotics") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Narcotics")));
                        goods.put("Narcotics", goods.get("Narcotics") - amount);
                        narcoticsAmount = narcoticsAmount + amount;
                        narcoticsMax.setText("Amount Of Narcotics Available: " + narcoticsAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellNarcotics.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Narcotics", narcoticsAmount);
                    }
                }
            }
        });


        TextView robotsPrice = (TextView) findViewById(R.id.robots_price);
        robotsAmount = 0;
        if (priceIndex.get("Robots") <= 0) {
            robotsPrice.setText("Robots cannot be bought or sold here.");
        } else {
            robotsPrice.setText("Price: $" + Integer.toString(priceIndex.get("Robots")));
            if (amountIndex.get("Robots") != null) {
                robotsAmount = amountIndex.get("Robots");
            } else {
                robotsAmount = ((int) (Math.random() * 2)) + 2;
            }
        }
        amountIndex.put("Robots", robotsAmount);
        final TextView robotsMax = (TextView) findViewById(R.id.max_robots);
        robotsMax.setText("Amount Of Robots Available: " + robotsAmount);
        buyRobots = (EditText)findViewById(R.id.robots_buy);
        sellRobots = (EditText)findViewById(R.id.robots_sell);
        Button buyRobotsButton = (Button)findViewById(R.id.robots_button);
        buyRobotsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Robots") > 0 && !buyRobots.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(buyRobots.getText().toString());
                    if (ship.getCargoSpace() >= amount && player.getMoney() >= (amount * priceIndex.get("Robots")) && amount <= robotsAmount) {
                        player.setMoney(player.getMoney() - (amount * priceIndex.get("Robots")));
                        goods.put("Robots", goods.get("Robots") + amount);
                        robotsAmount = robotsAmount - amount;
                        robotsMax.setText("Amount Of Robots Available: " + robotsAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        buyRobots.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Robots", robotsAmount);
                    } else if (ship.getCargoSpace() < amount) {
                        robotsMax.setText("Amount Of Robots Available: " + robotsAmount +
                                " You do not have enough cargo space for that amount.");
                        buyRobots.getText().clear();
                    } else if (player.getMoney() < (amount * priceIndex.get("Robots"))) {
                        robotsMax.setText("Amount Of Robots Available: " + robotsAmount +
                                " You do not have enough money for that amount.");
                    } else if (amount > robotsAmount) {
                        robotsMax.setText("Amount Of Robots Available: " + robotsAmount +
                                " There are not that many robots for sale.");
                    }
                }
            }
        });
        Button sellRobotsButton = (Button)findViewById(R.id.robots_sell_button);
        sellRobotsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (priceIndex.get("Robots") > 0 && !sellRobots.getText().toString().trim().isEmpty()) {
                    int amount = Integer.parseInt(sellRobots.getText().toString());
                    if (goods.get("Robots") >= amount) {
                        player.setMoney(player.getMoney() + (amount * priceIndex.get("Robots")));
                        goods.put("Robots", goods.get("Robots") - amount);
                        robotsAmount = robotsAmount + amount;
                        robotsMax.setText("Amount Of Robots Available: " + robotsAmount);
                        ship.setGoods(goods);
                        shipViewModel.setShip(ship);
                        playerviewModel.addPlayer(player);
                        sellRobots.getText().clear();
                        playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                        shipInfo.setText(ship.toString());
                        amountIndex.put("Robots", robotsAmount);
                    }
                }
            }
        });

        final Button refillFuel = (Button)findViewById(R.id.refill_fuel);
        fuelText = (TextView) findViewById(R.id.fuel_text);
        refillFuel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (player.getMoney() >= 50 && ship.getFuel() != ship.getMaxFuel()) {
                    ship.setFuel(ship.getMaxFuel());
                    player.setMoney(player.getMoney() - 50);
                    playerviewModel.addPlayer(player);
                    shipViewModel.setShip(ship);
                    playerInfo.setText("Money: $" + Integer.toString(player.getMoney()));
                    shipInfo.setText(ship.toString());
                } else if (player.getMoney() < 50){
                    fuelText.setText("You Cannot Afford To Buy Fuel");
                } else if (ship.getFuel() == ship.getMaxFuel()) {
                    fuelText.setText("You Have Max Fuel");
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent currMarket = new Intent(MarketplaceActivity.this, HomeScreenActivity.class);
        currMarket.putExtra(CITY_AMOUNT, amountIndex);
        startActivity(currMarket);
        finish();

    }
}
