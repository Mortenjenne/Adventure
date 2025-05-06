package game;

public class Weapon extends Item{
    private int damage;

    public Weapon(String description, String name, int damage) {
        super(description, name);
        this.damage = damage;
    }

    public int getDamage(){
        return this.damage;
    }
}

