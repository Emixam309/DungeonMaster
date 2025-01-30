package fr.univlittoral.projetcroisier.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    String name;

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                '}';
    }
}
