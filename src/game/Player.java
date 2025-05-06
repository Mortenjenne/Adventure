package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Room currentRoom;
    private List<Item> inventory;
    private int health;
    private Weapon equippedWeapon;

    public Player() {
        inventory = new ArrayList<>();
        this.health = 100;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room startRoom) {
        this.currentRoom = startRoom;
    }

    public Weapon getEquippedWeapon(){
        return this.equippedWeapon;
    }

    public int getWeaponDamage(){
        return this.equippedWeapon.getDamage();
    }

    public boolean move(Direction direction) {
        Room requestedRoom = null;

        switch (direction) {
            case NORTH:
                requestedRoom = currentRoom.getNorthRoom();
                break;
            case SOUTH:
                requestedRoom = currentRoom.getSouthRoom();
                break;
            case EAST:
                requestedRoom = currentRoom.getEastRoom();
                break;
            case WEST:
                requestedRoom = currentRoom.getWestRoom();
                break;
        }

        if(requestedRoom != null){
            this.currentRoom = requestedRoom;
            return true;
        } else {
            return false;
        }
    }

    public void changeHealth(int amount){
        this.health += amount;

        if(this.health > 100){
            this.health = 100;
        }
        if(this.health < 0){
            this.health = 0;
        }
    }

    public int getHealth(){
        return this.health;
    }

    public List<Item> getInventory() {
        return inventory;
    }


    public ActionResult eat(String foodName) {
        if (foodName.isEmpty()) {
            return ActionResult.DONT_KNOW;
        }

        Food food = getFoodFromInventory(foodName);
        if (food == null) {
            return ActionResult.CANT;
        }

        if(food.getNutrition() < 0){
            changeHealth(food.getNutrition());
            removeFood(food);
            return ActionResult.POISONOUS;
        } else {
            changeHealth(food.getNutrition());
            removeFood(food);
            return ActionResult.EAT;
        }
    }

    private Food getFoodFromInventory(String foodName) {
        for (Item item : this.inventory) {
            if (item instanceof Food && item.getName().equalsIgnoreCase(foodName)) {
                return (Food) item;
            }
        }
        return null;
    }

    private void removeFood(Food food){
        this.inventory.remove(food);
    }

    public Item takeItem(String itemName) {
        Item itemFromRoom = currentRoom.removeItem(itemName);
        if (itemFromRoom != null) {
            inventory.add(itemFromRoom);
        }
        return itemFromRoom;
    }

    public ActionResult equipWeapon(String weaponName){
        if(weaponName.isEmpty()){
            return ActionResult.DONT_KNOW;
        }
        Weapon weapon = getWeaponFromInventory(weaponName);
        if(weapon != null){
            this.equippedWeapon = weapon;
            return ActionResult.EQUIP;
        } else {
            return ActionResult.CANT;
        }
    }

    public ActionResult attack(){
        if (equippedWeapon == null) {
            return ActionResult.CANT;
        }
        return ActionResult.ATTACK;
    }

    public ActionResult dropWeapon(String weaponName) {
        if (weaponName.isEmpty()) {
            return ActionResult.DONT_KNOW;
        }
            Weapon weapon = getWeaponFromInventory(weaponName);

            if (weapon != null) {
                this.inventory.remove(weapon);
                this.currentRoom.addItem(weapon);
                return ActionResult.DROP;
            } else {
                return ActionResult.CANT;
            }
    }

    private Weapon getWeaponFromInventory(String weaponName){
        for(Item item: this.inventory){
            if(item instanceof Weapon && item.getName().equalsIgnoreCase(weaponName)){
                return (Weapon) item;
            }
        }
        return null;
    }

    public boolean isDead() {
        return health <= 0;
    }
}
