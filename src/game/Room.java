package game;

import java.util.ArrayList;
import java.util.List;
import java.util.SequencedCollection;

public class Room {
    private Room northRoom;
    private Room eastRoom;
    private Room southRoom;
    private Room westRoom;

    private String name;
    private String description;
    private ArrayList<Item> items;
    private List<Enemy> enemyList;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        items = new ArrayList<>();
        this.enemyList = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addItem(String description) {
        this.addItem(new Item(description));
    }

    public void addItem(String description, String name) {
        this.addItem(new Item(description, name));
    }

    public void addEnemy(String description, String name, int health, int damage){
        this.enemyList.add(new Enemy(description, name, health, damage));
    }

    public Room getNorthRoom() {
        return this.northRoom;
    }

    public void setNorthRoom(Room northRoom) {
        this.northRoom = northRoom;
        if (northRoom.getSouthRoom() != this) {
            northRoom.setSouthRoom(this);
        }
    }

    public Room getEastRoom() {
        return this.eastRoom;
    }

    public void setEastRoom(Room eastRoom) {
        this.eastRoom = eastRoom;
        if (eastRoom.getWestRoom() != this) {
            eastRoom.setWestRoom(this);
        }
    }

    public Room getSouthRoom() {
        return this.southRoom;
    }

    public void setSouthRoom(Room southRoom) {
        this.southRoom = southRoom;
        if (southRoom.getNorthRoom() != this) {
            southRoom.setNorthRoom(this);
        }
    }

    public Room getWestRoom() {
        return this.westRoom;
    }

    public void setWestRoom(Room westRoom) {
        this.westRoom = westRoom;
        if (westRoom.getEastRoom() != this) {
            westRoom.setEastRoom(this);
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Item removeItem(String itemName) {
        Item itemToBeRemoved = findItem(itemName);
        if (itemToBeRemoved != null) {
            items.remove(itemToBeRemoved);

        }
        return itemToBeRemoved;
    }

    public Item findItem(String itemName) {
        String search = itemName.toLowerCase().replace(" ", "");

        for (Item item : items) {
            String itemNameInList = item.getName().toLowerCase().replace(" ", "");
            if (itemNameInList.contains(search)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public List<Enemy> getEnemies() {
        return this.enemyList;
    }

    public void removeEnemy(Enemy enemy) {
        enemyList.remove(enemy);
    }

    public Enemy findEnemy(String enemyName) {
        String search = enemyName.toLowerCase().replace(" ", "");

        for (Enemy enemy : enemyList) {
            String nameInList = enemy.getName().toLowerCase().replace(" ", "");
            if (nameInList.contains(search)) {
                return enemy;
            }
        }
        return null;
    }
}
