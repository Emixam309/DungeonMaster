package fr.croisier.dungeonmaster.game;

import java.util.Random;

import fr.croisier.dungeonmaster.entities.Enemy;
import fr.croisier.dungeonmaster.entities.Player;
import fr.croisier.dungeonmaster.world.Room;

/**
 * Represents a battle between the player and an enemy in a room.
 */
public class Battle {
    private Game game;
    private Player player;
    private Room room;
    private Enemy enemy;
    private Random random;

    /**
     * Constructs a Battle instance.
     *
     * @param game the game instance
     * @param room the room where the battle takes place
     */
    public Battle(Game game, Room room) {
        this.game = game;
        this.player = game.getPlayer();
        this.room = room;
        this.enemy = (Enemy) room.getEntity();
        this.random = new Random();
    }

    /**
     * Executes an attack in the battle.
     *
     * @return true if the player wins, false otherwise
     */
    public boolean attack() {
        double result = calculateResult();
        if (result > 0) {
            win();
            return true;
        } else {
            lose();
            return false;
        }
    }

    /**
     * Handles the logic when the player wins the battle.
     */
    public void win() {
        calculateScore();
        player.setPower(player.getPower() + 10);
        room.setEntity(null);
    }

    /**
     * Handles the logic when the player loses the battle.
     */
    public void lose() {
        player.setHealth(player.getHealth() - 3);
    }

    /**
     * Handles the logic when the player escapes the battle.
     */
    public void escape() {
        player.setHealth(player.getHealth() - 1);
    }

    /**
     * Calculates the result of the battle.
     *
     * @return the result of the battle
     */
    public double calculateResult() {
        double randPlayer = random.nextDouble();
        double randEnemy = random.nextDouble();
        return player.getPower() * randPlayer - enemy.getPower() * randEnemy;
    }

    /**
     * Calculates and updates the score based on the battle outcome.
     */
    public void calculateScore() {
        int score = (int) ((100 + player.getHealth()) * (double) enemy.getPower() / player.getPower() * game.getLevel() * game.getDifficultyMultiplier());
        game.increaseScore(score);
    }

    /**
     * Gets the room where the battle takes place.
     *
     * @return the room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room where the battle takes place.
     *
     * @param room the room to set
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "player=" + player +
                ", room=" + room +
                ", enemy=" + enemy +
                '}';
    }
}
