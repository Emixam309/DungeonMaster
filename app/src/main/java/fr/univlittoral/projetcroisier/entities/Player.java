package fr.univlittoral.projetcroisier.entities;

public class Player {
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
