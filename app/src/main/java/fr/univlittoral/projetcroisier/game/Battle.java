package fr.univlittoral.projetcroisier.game;

import android.util.Log;

import java.util.Random;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Player;
import fr.univlittoral.projetcroisier.world.Room;

public class Battle {
    private Player player;
    private Room room;
    private Enemy enemy;
    private Random random;

    public Battle(Player player, Room room) {
        this.player = player;
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
        player.setScore(player.getScore() + 1);
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
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
