package fr.univlittoral.projetcroisier.entities;

import java.io.Serializable;

import fr.univlittoral.projetcroisier.enums.Difficulty;

public class Player implements Serializable {
    private String name;
    private int health;
    private int power;
    private int score;

    public Player(String name, int health, int power) {
        this.name = name;
        this.health = health;
        this.power = power;
        this.score = 0;
    }

    public Player(String name) {
        this(name, 10, 100);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void initializeAttributes(Difficulty difficulty, int customHealth, int customPower) {
        switch (difficulty) {
            case EASY:
                setHealth(15);
                setPower(150);
                break;
            case MEDIUM:
                setHealth(10);
                setPower(100);
                break;
            case HARD:
                setHealth(10);
                setPower(50);
                break;
            case CUSTOM:
                setHealth(customHealth);
                setPower(customPower);
                break;
        }
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", power=" + power +
                ", score=" + score +
                '}';
    }
}
