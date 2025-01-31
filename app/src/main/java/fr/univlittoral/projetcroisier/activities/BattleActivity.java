package fr.univlittoral.projetcroisier.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.univlittoral.projetcroisier.R;
import fr.univlittoral.projetcroisier.intents.BattleIntents;

/**
 * Activity representing the battle between the player and an enemy.
 */
public class BattleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setTitle(R.string.battle_title);
        setContentView(R.layout.activity_battle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.combat_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI elements
        TextView playerNameTextView = findViewById(R.id.tv_player_name_battle);
        TextView playerHealthTextView = findViewById(R.id.tv_player_health_battle);
        TextView playerPowerTextView = findViewById(R.id.tv_player_power_battle);
        TextView enemyNameTextView = findViewById(R.id.tv_enemy_name);
        TextView enemyPowerTextView = findViewById(R.id.tv_ennemy_power);
        ImageView enemyImageView = findViewById(R.id.enemy_image_view);

        // Get player and enemy details from the intent
        String playerName = getIntent().getStringExtra(BattleIntents.PLAYER_NAME);
        playerNameTextView.setText(playerName);
        int playerHealth = getIntent().getIntExtra(BattleIntents.PLAYER_HEALTH, 0);
        playerHealthTextView.setText(String.valueOf(playerHealth));
        int playerPower = getIntent().getIntExtra(BattleIntents.PLAYER_POWER, 0);
        playerPowerTextView.setText(String.valueOf(playerPower));
        String enemyName = getIntent().getStringExtra(BattleIntents.ENTITY_NAME);
        int enemyPower = getIntent().getIntExtra(BattleIntents.ENTITY_POWER, 0);
        enemyPowerTextView.setText(String.valueOf(enemyPower));

        // Set enemy name and image
        assert enemyName != null;
        enemyNameTextView.setText(getResources().getIdentifier(enemyName.toLowerCase(), "string", getPackageName()));
        enemyImageView.setImageResource(getResources().getIdentifier(enemyName.toLowerCase(), "drawable", getPackageName()));

        // Initialize attack and escape buttons
        Button attackButton = findViewById(R.id.btn_attack);
        Button escapeButton = findViewById(R.id.btn_escape);

        // Prepare result intent with room coordinates
        Intent resultIntent = new Intent();
        resultIntent.putExtra("room_x", getIntent().getIntExtra("room_x", 0));
        resultIntent.putExtra("room_y", getIntent().getIntExtra("room_y", 0));

        // Set attack button click listener
        attackButton.setOnClickListener(v -> {
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Set escape button click listener
        escapeButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        });

        // Handle back press to cancel the action
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                setResult(RESULT_CANCELED, resultIntent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}