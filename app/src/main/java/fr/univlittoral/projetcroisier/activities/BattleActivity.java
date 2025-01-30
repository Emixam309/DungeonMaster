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

        TextView playerNameTextView = findViewById(R.id.tv_player_name_battle);
        TextView playerHealthTextView = findViewById(R.id.tv_player_health_battle);
        TextView playerPowerTextView = findViewById(R.id.tv_player_power_battle);
        TextView ennemyNameTextView = findViewById(R.id.tv_enemy_name);
        TextView ennemyPowerTextView = findViewById(R.id.tv_ennemy_power);
        ImageView enemyImageView = findViewById(R.id.enemy_image_view);

        String player_name = getIntent().getStringExtra(BattleIntents.PLAYER_NAME);
        playerNameTextView.setText(player_name);
        int player_health = getIntent().getIntExtra(BattleIntents.PLAYER_HEALTH, 0);
        playerHealthTextView.setText(String.valueOf(player_health));
        int player_power = getIntent().getIntExtra(BattleIntents.PLAYER_POWER, 0);
        playerPowerTextView.setText(String.valueOf(player_power));
        String entity_name = getIntent().getStringExtra(BattleIntents.ENTITY_NAME);
        int entity_power = getIntent().getIntExtra(BattleIntents.ENTITY_POWER, 0);
        ennemyPowerTextView.setText(String.valueOf(entity_power));

        assert entity_name != null;
        ennemyNameTextView.setText(getResources().getIdentifier(entity_name.toLowerCase(), "string", getPackageName()));
        enemyImageView.setImageResource(getResources().getIdentifier(entity_name.toLowerCase(), "drawable", getPackageName()));

        Button attackButton = findViewById(R.id.btn_attack);
        Button escapeButton = findViewById(R.id.btn_escape);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("room_x", getIntent().getIntExtra("room_x", 0));
        resultIntent.putExtra("room_y", getIntent().getIntExtra("room_y", 0));

        attackButton.setOnClickListener(v -> {
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        escapeButton.setOnClickListener(v -> {
            setResult(RESULT_CANCELED, resultIntent);
            finish();
        });

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