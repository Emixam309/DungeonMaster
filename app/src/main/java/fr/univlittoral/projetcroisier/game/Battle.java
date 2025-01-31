package fr.univlittoral.projetcroisier.game;

import android.util.Log;

import java.util.Random;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Player;
import fr.univlittoral.projetcroisier.world.Room;

public class Battle {
    private Game game;
    private Player player;
    private Room room;
    private Enemy enemy;
    private Random random;

    public Battle(Game game, Room room) {
        this.game = game;
        this.player = game.getPlayer();
        this.room = room;
        this.enemy = (Enemy) room.getEntity();

        this.random = new Random();
    }

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

    public void win() {
        calculateScore();
        player.setPower(player.getPower() + 10);
        room.setEntity(null);
        Log.d("Battle", "Player: " + player);
    }

    public void lose() {
        player.setHealth(player.getHealth() - 3);
        Log.d("Battle", "Player: " + player);
    }

    public void escape() {
        player.setHealth(player.getHealth() - 1);
        Log.d("Battle", "Player: " + player);
    }

    public double calculateResult() {
        double randPlayer = random.nextDouble();
        double randEnnemy = random.nextDouble();
        return player.getPower() * randPlayer - enemy.getPower() * randEnnemy;
    }

    public void calculateScore() {
        int score = (int) ((100 + player.getHealth()) * (double) enemy.getPower() / player.getPower() * game.getLevel() * game.getDifficultyMultiplier());
        Log.d("Battle", "Score increased by: " + score);
        game.increaseScore(score);
    }

    public Room getRoom() {
        return room;
    }

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
