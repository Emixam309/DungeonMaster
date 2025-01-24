package fr.univlittoral.projetcroisier;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.snackbar.Snackbar;

import fr.univlittoral.projetcroisier.entities.Player;
import fr.univlittoral.projetcroisier.game.Battle;
import fr.univlittoral.projetcroisier.world.Dungeon;
import fr.univlittoral.projetcroisier.world.Room;

public class DungeonActivity extends AppCompatActivity {
    private Player player;
    private Dungeon dungeon;
    private ActivityResultLauncher<Intent> combatActivityResultLauncher;
    private RoomViewModel roomViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dungeon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.dungeon_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        player = new Player("Player", 10, 100);
        dungeon = new Dungeon();
        roomViewModel = new ViewModelProvider(this).get(RoomViewModel.class);
        TableLayout tableLayout = findViewById(R.id.room_table);

        TextView roomUnexplored = findViewById(R.id.tv_unexplored_rooms_value);
        roomUnexplored.setText(String.valueOf(dungeon.getNumberOfUnvisitedRooms()));
        TextView playerPower = findViewById(R.id.tv_power_value);
        playerPower.setText(String.valueOf(player.getPower()));
        TextView playerHealth = findViewById(R.id.tv_health_value);
        playerHealth.setText(String.valueOf(player.getHealth()));
        TextView battleResultTitle = findViewById(R.id.tv_battle_result_title);
        TextView battleResult = findViewById(R.id.tv_battle_result);

        combatActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    assert result.getData() != null;
                    Room room = dungeon.getRoom(
                            result.getData().getIntExtra("room_x", 0),
                            result.getData().getIntExtra("room_y", 0)
                    );
                    Battle battle = new Battle(player, room);
                    room.setVisited(true);
                    if (result.getResultCode() == RESULT_OK) {
                        boolean isWon = battle.attack();
                        battleResult.setText(isWon ? R.string.victory : R.string.defeat);
                        playerPower.setText(String.valueOf(player.getPower()));
                        roomUnexplored.setText(String.valueOf(dungeon.getNumberOfUnvisitedRooms()));
                        Log.d("DungeonActivity", "Result from CombatActivity: " + isWon);
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        battle.escape();
                        battleResult.setText(R.string.fled);
                        Log.d("DungeonActivity", "User cancelled the action");
                    }
                    roomViewModel.setRoom(room); // Update the room state
                    playerHealth.setText(String.valueOf(player.getHealth()));
                    if (player.getHealth() <= 0) {
                        // Disable all buttons
                        for (int i = 0; i < tableLayout.getChildCount(); i++) {
                            TableRow row = (TableRow) tableLayout.getChildAt(i);
                            for (int j = 0; j < row.getChildCount(); j++) {
                                row.getChildAt(j).setEnabled(false);
                            }
                        }
                        // Display game over message
                        battleResultTitle.setText(R.string.game_over);
                        battleResult.setText(R.string.game_lose);
                    }
                }
        );

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
                    if (room.getEnnemy() == null) {
                        Toast.makeText(this, R.string.empty_room, Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(DungeonActivity.this, BattleActivity.class);
                    intent.putExtra("player_name", player.getName());
                    intent.putExtra("player_health", player.getHealth());
                    intent.putExtra("player_power", player.getPower());
                    intent.putExtra("room_x", finalI);
                    intent.putExtra("room_y", finalJ);
                    intent.putExtra("entity_name", dungeon.getRoom(finalI, finalJ).getEnnemy().getName());
                    intent.putExtra("entity_power", dungeon.getRoom(finalI, finalJ).getEnnemy().getPower());
                    combatActivityResultLauncher.launch(intent);
                    Log.d("DungeonActivity", "Room " + dungeon.getRoom(finalI, finalJ));
                    Log.d("DungeonActivity", "Player: " + player);
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
    }

    private void updateRoomButtonIcon(ImageButton button, Room room) {
        if (!room.isVisited()) {
            button.setImageResource(R.drawable.circle);
        } else if (room.getEnnemy() != null) {
            button.setImageResource(R.drawable.exclamation_circle);
        } else {
            button.setImageResource(R.drawable.check_circle);
        }
    }
}