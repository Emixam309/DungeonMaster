package fr.univlittoral.projetcroisier.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import fr.univlittoral.projetcroisier.R;
import fr.univlittoral.projetcroisier.entities.Player;
import fr.univlittoral.projetcroisier.enums.Difficulty;
import fr.univlittoral.projetcroisier.intents.DungeonIntents;

public class ConfigurationActivity extends AppCompatActivity {
    TextView playerName;
    Spinner difficulty;
    LinearLayout customDifficultyLayout;

    TextView playerHealth;
    TextView playerPower;
    Spinner dungeonRows;
    Spinner dungeonColumns;
    SeekBar difficultyMultiplier;
    TextView difficultyMultValue;
    Button validateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_configuration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.configuration_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        playerName = findViewById(R.id.player_name_input);
        difficulty = findViewById(R.id.difficulty_spinner);
        customDifficultyLayout = findViewById(R.id.custom_difficulty_layout);

        playerHealth = findViewById(R.id.player_health_input);
        playerPower = findViewById(R.id.player_power_input);
        dungeonRows = findViewById(R.id.dungeon_rows_spinner);
        dungeonColumns = findViewById(R.id.dungeon_columns_spinner);
        difficultyMultiplier = findViewById(R.id.difficulty_mult_seekbar);
        difficultyMultValue = findViewById(R.id.difficulty_mult_value);
        validateButton = findViewById(R.id.validate_button);

        String[] difficultyLevels = {
                getString(R.string.difficulty_easy),
                getString(R.string.difficulty_medium),
                getString(R.string.difficulty_hard),
                getString(R.string.difficulty_custom)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficultyLevels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        difficulty.setAdapter(adapter);
        difficulty.setSelection(1); // set default value to medium

        // Show or hide custom difficulty layout based on selected difficulty
        difficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedDifficulty = (String) parent.getItemAtPosition(position);
                if (selectedDifficulty.equals(getString(R.string.difficulty_custom))) {
                    customDifficultyLayout.setVisibility(View.VISIBLE);
                } else {
                    customDifficultyLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                customDifficultyLayout.setVisibility(View.GONE);
            }
        });

        //Configure the dungeon rows and columns
        String[] numbers = {"3", "4", "5"};
        ArrayAdapter<String> rowsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbers);
        ArrayAdapter<String> columnsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numbers);
        rowsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        columnsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dungeonRows.setAdapter(rowsAdapter);
        dungeonRows.setSelection(1); // set default value to 4
        dungeonColumns.setAdapter(columnsAdapter);
        dungeonColumns.setSelection(1); // set default value to 4

        // Configure the SeekBar
        difficultyMultiplier.setMax(20); // Range from 0 to 2 (0.1 steps)
        difficultyMultiplier.setProgress(10); // Default value 1.0
        difficultyMultValue.setText(String.valueOf(difficultyMultiplier.getProgress() / 10.0));

        difficultyMultiplier.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                difficultyMultValue.setText(String.valueOf(progress / 10.0));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Do nothing
            }
        });

        validateButton.setOnClickListener(v -> {
            // Get the selected difficulty and map it to the enum
            String selectedDifficulty = (String) difficulty.getSelectedItem();

            Difficulty difficultyEnum = Difficulty.MEDIUM; // Default value
            if (selectedDifficulty.equals(getString(R.string.difficulty_easy))) {
                difficultyEnum = Difficulty.EASY;
            } else if (selectedDifficulty.equals(getString(R.string.difficulty_hard))) {
                difficultyEnum = Difficulty.HARD;
            } else if (selectedDifficulty.equals(getString(R.string.difficulty_custom))) {
                difficultyEnum = Difficulty.CUSTOM;
            }
            if (checkFieldsForEmptyValues(difficultyEnum)) {
                Toast.makeText(this, R.string.empty_fields_error, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, DungeonActivity.class);
            Player player = new Player(playerName.getText().toString());

            intent.putExtra(DungeonIntents.DIFFICULTY, difficultyEnum);
            if (difficultyEnum == Difficulty.CUSTOM) {
                intent.putExtra(DungeonIntents.ROWS, Integer.parseInt(dungeonRows.getSelectedItem().toString()));
                intent.putExtra(DungeonIntents.COLUMNS, Integer.parseInt(dungeonColumns.getSelectedItem().toString()));
                intent.putExtra(DungeonIntents.DIFFICULTY_MULTIPLIER, difficultyMultiplier.getProgress() / 10.0);
            } else {
                intent.putExtra(DungeonIntents.ROWS, 4);
                intent.putExtra(DungeonIntents.COLUMNS, 4);
                switch (difficultyEnum) {
                    case EASY:
                        intent.putExtra(DungeonIntents.DIFFICULTY_MULTIPLIER, 0.8);
                        break;
                    case MEDIUM:
                        intent.putExtra(DungeonIntents.DIFFICULTY_MULTIPLIER, 1.0);
                        break;
                    case HARD:
                        intent.putExtra(DungeonIntents.DIFFICULTY_MULTIPLIER, 1.2);
                        break;
                }
            }
            player.initializeAttributes(difficultyEnum, Integer.parseInt(playerHealth.getText().toString()), Integer.parseInt(playerPower.getText().toString()));
            intent.putExtra(DungeonIntents.PLAYER_NAME, player.getName());
            intent.putExtra(DungeonIntents.PLAYER_HEALTH, player.getHealth());
            intent.putExtra(DungeonIntents.PLAYER_POWER, player.getPower());
            intent.putExtra(DungeonIntents.LEVEL, 1);
            intent.putExtra(DungeonIntents.SCORE, 0);

            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_scores) {
            Intent intent = new Intent(this, ScoresActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /// Check if the fields are empty or equal to 0
    private boolean checkFieldsForEmptyValues(Difficulty difficulty) {
        String name = playerName.getText().toString();
        String health = playerHealth.getText().toString();
        String power = playerPower.getText().toString();
        return name.isBlank() || (difficulty == Difficulty.CUSTOM && health.isBlank() || Integer.parseInt(health) == 0) || (difficulty == Difficulty.CUSTOM && power.isBlank() || Integer.parseInt(power) == 0);
    }
}
