package fr.croisier.dungeonmaster.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import fr.croisier.dungeonmaster.R;
import fr.croisier.dungeonmaster.entities.Enemy;
import fr.croisier.dungeonmaster.entities.Item;
import fr.croisier.dungeonmaster.entities.Player;
import fr.croisier.dungeonmaster.enums.Difficulty;
import fr.croisier.dungeonmaster.enums.ItemType;
import fr.croisier.dungeonmaster.game.Battle;
import fr.croisier.dungeonmaster.game.Game;
import fr.croisier.dungeonmaster.game.Score;
import fr.croisier.dungeonmaster.intents.BattleIntents;
import fr.croisier.dungeonmaster.intents.DungeonIntents;
import fr.croisier.dungeonmaster.viewmodels.RoomViewModel;
import fr.croisier.dungeonmaster.world.Dungeon;
import fr.croisier.dungeonmaster.world.Room;

/**
 * Activity representing the dungeon exploration in the game.
 */
public class DungeonActivity extends AppCompatActivity {
    private Game game;
    private Player player;
    private Dungeon dungeon;
    private RoomViewModel roomViewModel;
    private TextView levelValue;
    private TextView playerPower;
    private TextView playerHealth;
    private TextView resultTitle;
    private TextView resultValue;
    private TextView roomUnexplored;
    private TableLayout tableLayout;
    private Button nextLevelButton;
    private ActivityResultLauncher<Intent> combatActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setTitle(R.string.dungeon);
        setContentView(R.layout.activity_dungeon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dungeon_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Check if there is a saved instance state to restore
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState);
        } else if (getIntent().getExtras() == null) {
            // If no extras are provided, redirect to ConfigurationActivity
            Intent intent = new Intent(this, ConfigurationActivity.class);
            startActivity(intent);
            finish();
            return;
        } else {
            // Retrieve player and game details from the intent
            String playerName = getIntent().getStringExtra(DungeonIntents.PLAYER_NAME);
            int playerHealth = getIntent().getIntExtra(DungeonIntents.PLAYER_HEALTH, 10);
            int playerPower = getIntent().getIntExtra(DungeonIntents.PLAYER_POWER, 100);
            int rows = getIntent().getIntExtra(DungeonIntents.ROWS, 4);
            int columns = getIntent().getIntExtra(DungeonIntents.COLUMNS, 4);
            int level = getIntent().getIntExtra(DungeonIntents.LEVEL, 1);
            int score = getIntent().getIntExtra(DungeonIntents.SCORE, 0);
            Difficulty difficulty = (Difficulty) getIntent().getSerializableExtra(DungeonIntents.DIFFICULTY);
            double difficultyMultiplier = getIntent().getDoubleExtra(DungeonIntents.DIFFICULTY_MULTIPLIER, 1.0);

            player = new Player(playerName, playerHealth, playerPower);
            game = new Game(player, difficulty, difficultyMultiplier, rows, columns, level, score);
        }
        player = game.getPlayer();
        dungeon = game.getDungeon();
        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        tableLayout = findViewById(R.id.room_table);
        levelValue = findViewById(R.id.tv_level_value);
        levelValue.setText(String.valueOf(game.getLevel()));
        roomUnexplored = findViewById(R.id.tv_unexplored_rooms_value);
        roomUnexplored.setText(String.valueOf(dungeon.getNumberOfUnvisitedRooms()));
        playerPower = findViewById(R.id.tv_power_value);
        playerPower.setText(String.valueOf(player.getPower()));
        playerHealth = findViewById(R.id.tv_health_value);
        playerHealth.setText(String.valueOf(player.getHealth()));
        resultTitle = findViewById(R.id.tv_result_title);
        resultValue = findViewById(R.id.tv_result_value);
        nextLevelButton = findViewById(R.id.next_level_btn);

        checkEndDungeon();

        // Register the activity result launcher for combat activity
        combatActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    assert result.getData() != null;
                    Room room = dungeon.getRoom(
                            result.getData().getIntExtra("room_x", 0),
                            result.getData().getIntExtra("room_y", 0)
                    );
                    if (room.getEntity() != null && room.getEntity() instanceof Enemy) {
                        Battle battle = new Battle(game, room);
                        room.setVisited(true);
                        if (result.getResultCode() == RESULT_OK) {
                            boolean isWon = battle.attack();
                            resultTitle.setText(R.string.battle_result);
                            resultValue.setText(isWon ? R.string.victory : R.string.defeat);
                            playerPower.setText(String.valueOf(player.getPower()));
                            roomUnexplored.setText(String.valueOf(dungeon.getNumberOfUnvisitedRooms()));

                            Log.d("DungeonActivity", "Result from CombatActivity: " + isWon);
                            Log.d("DungeonActivity", "Score: " + game.getScore());
                            checkEndDungeon();
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            battle.escape();
                            this.resultValue.setText(R.string.fled);
                            Log.d("DungeonActivity", "User cancelled the action");
                        }
                        roomViewModel.setRoom(room); // Update the room state
                        playerHealth.setText(String.valueOf(player.getHealth()));
                        if (player.getHealth() <= 0) {
                            disableAllRoomButtons();
                            // Display game over message
                            resultTitle.setText(R.string.game_over);
                            resultValue.setText(R.string.game_lose);
                            saveScore();
                        }
                    }
                }
        );

        // Create the dungeon layout with buttons for each room
        for (int i = 0; i < dungeon.getRooms().length; i++) {
            TableRow tableRow = new TableRow(this);
            tableRow.setGravity(1);
            for (int j = 0; j < dungeon.getRooms()[i].length; j++) {
                ImageButton button = new ImageButton(this);
                Room room = dungeon.getRoom(i, j);
                updateRoomButtonIcon(button, room);

                int finalI = i;
                int finalJ = j;
                button.setOnClickListener(v -> {
                    if (room.getEntity() == null) {
                        Toast.makeText(this, R.string.empty_room, Toast.LENGTH_SHORT).show();
                    } else if (room.getEntity() instanceof Item) {
                        // Display a dialog
                        showItemDialog((Item) room.getEntity(), room);
                        roomViewModel.setRoom(room); // Update the room state
                    } else if (room.getEntity() instanceof Enemy) {
                        Intent intent = new Intent(DungeonActivity.this, BattleActivity.class);
                        intent.putExtra(BattleIntents.PLAYER_NAME, player.getName());
                        intent.putExtra(BattleIntents.PLAYER_HEALTH, player.getHealth());
                        intent.putExtra(BattleIntents.PLAYER_POWER, player.getPower());
                        intent.putExtra(BattleIntents.ROOM_X, finalI);
                        intent.putExtra(BattleIntents.ROOM_Y, finalJ);
                        intent.putExtra(BattleIntents.ENTITY_NAME, dungeon.getRoom(finalI, finalJ).getEntity().getName());
                        intent.putExtra(BattleIntents.ENTITY_POWER, ((Enemy) dungeon.getRoom(finalI, finalJ).getEntity()).getPower());
                        combatActivityResultLauncher.launch(intent);
                        Log.d("DungeonActivity", "Room:" + dungeon.getRoom(finalI, finalJ));
                        Log.d("DungeonActivity", "Player: " + player);
                    }
                });

                // Observe room state changes
                roomViewModel.getRoom().observe(this, updatedRoom -> {
                    if (updatedRoom.equals(room)) {
                        updateRoomButtonIcon(button, updatedRoom);
                    }
                });

                tableRow.addView(button);
            }
            tableLayout.addView(tableRow);
        }

        // Set click listener for the next level button
        nextLevelButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DungeonActivity.class);
            Log.d("DungeonActivity", "Player: " + player);
            intent.putExtra(DungeonIntents.PLAYER_NAME, player.getName());
            intent.putExtra(DungeonIntents.PLAYER_HEALTH, player.getHealth());
            intent.putExtra(DungeonIntents.PLAYER_POWER, player.getPower());
            intent.putExtra(DungeonIntents.ROWS, dungeon.getRows());
            intent.putExtra(DungeonIntents.COLUMNS, dungeon.getColumns());
            intent.putExtra(DungeonIntents.LEVEL, game.getLevel() + 1);
            intent.putExtra(DungeonIntents.SCORE, game.getScore());
            intent.putExtra(DungeonIntents.DIFFICULTY, game.getDifficulty());
            intent.putExtra(DungeonIntents.DIFFICULTY_MULTIPLIER, game.getDifficultyMultiplier());
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current state of the game
        outState.putString(DungeonIntents.PLAYER_NAME, player.getName());
        outState.putInt(DungeonIntents.PLAYER_HEALTH, player.getHealth());
        outState.putInt(DungeonIntents.PLAYER_POWER, player.getPower());
        outState.putSerializable(DungeonIntents.DUNGEON, dungeon);
        outState.putInt(DungeonIntents.DIFFICULTY, game.getDifficulty().ordinal());
        outState.putInt(DungeonIntents.LEVEL, game.getLevel());
        outState.putInt(DungeonIntents.SCORE, game.getScore());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore the saved state of the game
        if (savedInstanceState != null) {
            String playerName = savedInstanceState.getString(DungeonIntents.PLAYER_NAME);
            int playerHealth = savedInstanceState.getInt(DungeonIntents.PLAYER_HEALTH);
            int playerPower = savedInstanceState.getInt(DungeonIntents.PLAYER_POWER);
            Difficulty difficulty = Difficulty.values()[savedInstanceState.getInt(DungeonIntents.DIFFICULTY)];
            Dungeon actualDungeon = (Dungeon) savedInstanceState.getSerializable(DungeonIntents.DUNGEON);
            int level = savedInstanceState.getInt(DungeonIntents.LEVEL);
            int score = savedInstanceState.getInt(DungeonIntents.SCORE);
            player = new Player(playerName, playerHealth, playerPower);
            game = new Game(player, difficulty, actualDungeon, level, score);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dungeon_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item selections
        if (item.getItemId() == R.id.action_restart_game) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_scores) {
            Intent intent = new Intent(this, ScoresActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Updates the icon of the room button based on the room's state.
     *
     * @param button The button representing the room.
     * @param room   The room to update the icon for.
     */
    private void updateRoomButtonIcon(ImageButton button, Room room) {
        if (!room.isVisited()) {
            button.setImageResource(R.drawable.circle);
        } else if (room.getEntity() != null) {
            button.setImageResource(R.drawable.exclamation_circle);
        } else {
            button.setImageResource(R.drawable.check_circle);
        }
    }

    /**
     * Displays a dialog when an item is found in a room.
     *
     * @param item  The item found.
     * @param room  The room where the item is found.
     */
    private void showItemDialog(Item item, Room room) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (item.getType() == ItemType.HEALTH_POTION) {
            builder.setTitle(R.string.health_potion_found);
            builder.setMessage(R.string.health_potion_description);
        } else {
            builder.setTitle(R.string.power_charm_found);
            builder.setMessage(R.string.power_charm_description);
        }

        builder.setPositiveButton(R.string.yes, (dialog, which) -> {
            item.useItem(player);
            playerHealth.setText(String.valueOf(player.getHealth()));
            playerPower.setText(String.valueOf(player.getPower()));
            room.setEntity(null);
            resultValue.setText(R.string.item_used);
        });

        builder.setNegativeButton(R.string.no, (dialog, which) -> {
            resultValue.setText(R.string.item_left);
        });

        builder.setOnCancelListener(dialog -> {
            resultValue.setText(R.string.item_left);
        });

        builder.setOnDismissListener(dialog -> {
            room.setVisited(true);
            resultTitle.setText(R.string.exploration_result);
            roomViewModel.setRoom(room); // Update the room state
            checkEndDungeon();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * Disables all room buttons, preventing further interaction.
     */
    private void disableAllRoomButtons() {
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                row.getChildAt(j).setEnabled(false);
            }
        }
    }

    /**
     * Checks if the dungeon level is completed and updates the UI accordingly.
     */
    private void checkEndDungeon() {
        if (areAllRoomsEmpty()) {
            disableAllRoomButtons();
            // Display level clear message
            resultTitle.setText(R.string.level_clear);
            resultValue.setText(R.string.level_win);
            // Display next level button
            nextLevelButton.setVisibility(Button.VISIBLE);
        }
    }

    /**
     * Checks if all rooms in the dungeon are empty.
     *
     * @return True if all rooms are empty, false otherwise.
     */
    private boolean areAllRoomsEmpty() {
        for (Room[] row : dungeon.getRooms()) {
            for (Room room : row) {
                if (room.getEntity() != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Saves the current game score to a file.
     */
    private void saveScore() {
        Score.saveScoreToFile(this, game);
        Toast.makeText(this, R.string.score_saved, Toast.LENGTH_SHORT).show();
    }
}
