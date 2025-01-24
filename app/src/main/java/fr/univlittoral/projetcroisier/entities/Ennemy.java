package fr.univlittoral.projetcroisier.entities;

public class Ennemy  {
    private String name;
    private int power;
    private boolean isAlive;

    public Ennemy(String name, int power) {
        this.name = name;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public String toString() {
        return "Ennemy{" +
                "name='" + name + '\'' +
                ", power=" + power +
                '}';
    }
}
