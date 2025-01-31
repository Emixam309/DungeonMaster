package fr.univlittoral.projetcroisier.game;

import android.util.Log;

import java.io.Serializable;

import fr.univlittoral.projetcroisier.entities.Enemy;
import fr.univlittoral.projetcroisier.entities.Player;
import fr.univlittoral.projetcroisier.enums.Difficulty;
import fr.univlittoral.projetcroisier.world.Dungeon;
import fr.univlittoral.projetcroisier.world.Room;

public class Game {
    private Player player;
    private Difficulty difficulty;
    private double difficultyMultiplier;
    private Dungeon dungeon;
    private int score;
    private int level;

    public Game(Player player, Difficulty difficulty, double difficultyMultiplier, int rows, int columns, int level, int score) {
        this.player = player;
        this.difficulty = difficulty;
        this.dungeon = new Dungeon(difficultyMultiplier, rows, columns, level);
        this.difficultyMultiplier = difficultyMultiplier;
        this.level = level;
        this.score = score;
    }

    public Game(Player player, Difficulty difficulty, Dungeon dungeon, int level, int score) {
        this.player = player;
        this.difficulty = difficulty;
        this.dungeon = dungeon;
        this.level = level;
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public double getDifficultyMultiplier() {
        return difficultyMultiplier;
    }

    public void setDifficultyMultiplier(double difficultyMultiplier) {
        this.difficultyMultiplier = difficultyMultiplier;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Game{" +
                "player=" + player +
                ", difficulty=" + difficulty +
                ", difficultyMultiplier=" + difficultyMultiplier +
                ", dungeon=" + dungeon +
                ", score=" + score +
                ", level=" + level +
                '}';
    }
}
