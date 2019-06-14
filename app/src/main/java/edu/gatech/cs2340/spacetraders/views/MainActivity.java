package edu.gatech.cs2340.spacetraders.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.spacetraders.R;

public class MainActivity extends AppCompatActivity {

    private Button continueButton;
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
    }
}
