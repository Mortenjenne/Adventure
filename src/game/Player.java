package game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int energy;

    public Player() {
        inventory = new ArrayList<>();
        this.energy = 100;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room startRoom) {
        this.currentRoom = startRoom;
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

    public void changeEnergy(int amount){
        this.energy += amount;

        if(this.energy > 100){
            this.energy = 100;
        }
        if(this.energy < 0){
            this.energy = 0;
        }

    }

    public int getEnergy(){
        return this.energy;
    }

    public List<Item> getInventory() {
        return inventory;
    }


    public void eat(Food food){
        Food foodInventory = getFoodFromInventory(food.getName());
            if(foodInventory == null){
                return;
            }
            changeEnergy(food.getNutrition());
            removeFood(food);
        }

    public Food getFoodFromInventory(String foodName) {
        for (Item item : this.inventory) {
            if (item instanceof Food && item.getName().equalsIgnoreCase(foodName)) {
                return (Food) item;
            }
        }
        return null;
    }

    public void removeFood(Food food){
        this.inventory.remove(food);
    }

    public Item takeItem(String itemName) {
        Item itemFromRoom = currentRoom.removeItem(itemName);
        if (itemFromRoom != null) {
            inventory.add(itemFromRoom);
        }
        return itemFromRoom;
    }
}
