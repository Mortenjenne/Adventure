package game;

public class RangedWeapon extends Weapon{

    private int ammo;

    public RangedWeapon(String description, String name, int damage, int usesLeft) {
        super(description, name, damage);
        this.ammo = usesLeft;
    }

    public int getAmmo(){
        return this.ammo;
    }

    public boolean hasAmmo() {
        return this.ammo > 0;
    }

}
