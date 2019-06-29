package edu.gatech.cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.spacetraders.R;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
    private Button loadButton;

    public static final String CHECK = "edu.gatech.cs2340.spacetraders.views.CHECK";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        continueButton = findViewById(R.id.button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ConfigurationActivity.class);
                startActivity(intent);
            }
        });

        loadButton = findViewById(R.id.load_button);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeScreenActivity.class);
                intent.putExtra(CHECK, true);
                startActivity(intent);
            }
        });
    }
}
