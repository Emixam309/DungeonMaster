package fr.univlittoral.projetcroisier.game;

import android.util.Log;

import java.util.Random;

import fr.univlittoral.projetcroisier.entities.Ennemy;
import fr.univlittoral.projetcroisier.entities.Player;

public class Battle {
    private Player player;
    private Ennemy ennemy;
    private Random random;

    public Battle(Player player, Ennemy ennemy) {
        this.player = player;
        this.ennemy = ennemy;
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
        return player.getPower() * randPlayer - ennemy.getPower() * randEnnemy;
    }
}
