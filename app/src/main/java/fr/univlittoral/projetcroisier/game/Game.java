package fr.univlittoral.projetcroisier.game;

import fr.univlittoral.projetcroisier.entities.Player;
import fr.univlittoral.projetcroisier.enums.Difficulty;
import fr.univlittoral.projetcroisier.world.Dungeon;

public class Game {
    private Player player;
    private Difficulty difficulty;
    private Dungeon dungeon;

    public Game(String playerName, Difficulty difficulty) {
        this.difficulty = difficulty;
        this.player = new Player(playerName, 10, 100);
        initializePlayerAttributes();
        this.dungeon = new Dungeon(1);
    }

    private void initializePlayerAttributes() {
        switch (difficulty) {
            case EASY:
                player.setPower(150);
                player.setHealth(15);
                break;
            case MEDIUM:
                player.setPower(100);
                player.setHealth(10);
                break;
            case HARD:
                player.setPower(50);
                player.setHealth(5);
                break;
            case CUSTOM:
                // Do nothing
                break;
        }
    }
}
