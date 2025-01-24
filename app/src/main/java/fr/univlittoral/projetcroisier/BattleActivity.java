package fr.univlittoral.projetcroisier;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BattleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.combat_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView playerNameTextView = findViewById(R.id.tv_player_name_battle);
        TextView playerHealthTextView = findViewById(R.id.tv_player_health_battle);
        TextView playerPowerTextView = findViewById(R.id.tv_player_power_battle);
        TextView ennemyNameTextView = findViewById(R.id.tv_ennemy_name);
        TextView ennemyPowerTextView = findViewById(R.id.tv_ennemy_power);


        String player_name = getIntent().getStringExtra("player_name");
        playerNameTextView.setText(player_name);
        int player_health = getIntent().getIntExtra("player_health", 0);
        playerHealthTextView.setText(String.valueOf(player_health));
        int player_power = getIntent().getIntExtra("player_power", 0);
        playerPowerTextView.setText(String.valueOf(player_power));
        String entity_name = getIntent().getStringExtra("entity_name");
        ennemyNameTextView.setText(entity_name);
        int entity_power = getIntent().getIntExtra("entity_power", 0);
        ennemyPowerTextView.setText(String.valueOf(entity_power));

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