package game;

import java.util.Random;

public class Enemy implements Creature {

    private String description;
    private String name;
    private int health;
    private int damage;
    private Random random;

    public Enemy(String description, String name, int health, int damage) {
        this.description = description;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.random = new Random();
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void takeDamage(int amount) {
        health -= amount;
        if (health < 0) health = 0;
    }

    @Override
    public int attack() {
        return random.nextInt(damage);
    }

    public boolean isDead() {
        return health <= 0;
    }

    @Override
    public String getDescription() {
        return this.description;}
}
