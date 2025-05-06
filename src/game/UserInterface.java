package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public void showInventory(Player player) {
       List<Item> items = player.getInventory();
        if (items.size() == 0) {
            System.out.println("You aren't carrying anything");
        } else if (items.size() <= 3) {
            // Show a nice compact list if max. carrying three things
            System.out.println("You are carrying " + prettyCommaSeparatedList(items));

        } else {
            System.out.println("You are carrying: ");
            for (Item item : items) {
                System.out.print(" - " + item);
            }
            System.out.println();
        }
    }

    public String[] readInput() {
        System.out.println("Awaiting your command:");
        String inputLine = scanner.nextLine().trim().toLowerCase();
        return inputLine.split(" ", 2);
    }

    public void describeRoom(Room room) {
        String title = room.getName();
        String line = "+-" + "-".repeat(title.length()) + "-+";

        System.out.println("\n" + line);
        System.out.println("| " + title + " |");
        System.out.println(line);
        System.out.println(room.getDescription());

        List<Enemy> enemies = room.getEnemies();
        ArrayList<Item> items = room.getItems();

        if (!items.isEmpty()) {
            System.out.println("\nItems in the room:");
            for (Item item: items) {
                System.out.println(" - " + item.getDescription());
            }
        }
        System.out.println();

        if(!enemies.isEmpty()){
            for(Enemy enemy: enemies)
            describeEnemy(enemy);
        }
    }

    public void describeEnemy(Enemy enemy) {
        System.out.println("⚠️ An enemy appears! ⚠️");
        System.out.println("Name: " + enemy.getName() + " - " + "Description: " + enemy.getDescription() + " - " + "Health: " + enemy.getHealth());
    }

    public void printWelcome() {
        printBoxedMessage("Welcome to the game of Adventure!");
        printBoxedMessage("Type HELP or INFO for instructions on how to move around");
    }

    public void showHelp() {
        System.out.println("""
                Instructions
                ------------
                Type your command to the system, followed by enter. It doesn't matter if you use upper or lower-case.
                These are the instructions recognized:
                 HELP   or INFO, shows this help-text
                 GO     followed by one of the directions: North, South, East or West, moves the player in that direction (if possible)
                        You can also just write a direction, or simply the first letter of a direction.
                 LOOK   Looks around you, and describes what you can see
                 TAKE   or GET, followed by the name of an item, to pick up an item in the room.
                 EAT    Eats the food player has picked up, if food is ok, player gains health. Otherwise player looses health by eaten poisonous food.
                 EQUIP  Player can equip weapon, so it is ready for attacking enemies.
                 WEAPON Shows the player current equipped weapon
                 CHANGE Player can change weapon from the inventory
                 DROP   Player drop the item in the current room, the player is in. 
                 HEALTH Shows player health level
                 EXIT   Ends the game
                """);
    }

   private String prettyCommaSeparatedList(List items) {
        StringBuilder str = new StringBuilder();
        int length = items.size();
        for (int i = 0; i < length; i++) {
            str.append(items.get(i));
            if (i == length - 2) {
                str.append(" and ");
            } else if (i < length - 2) {
                str.append(", ");
            }
        }
        return str.toString();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void showHealthBar(Player player) {
        int health = player.getHealth();
        int blocks = health / 10;
        String bar = "[";

        for (int i = 0; i < 10; i++) {
            if (i < blocks) {
                bar += "#";
            } else {
                bar += " ";
            }
        }
        bar += "] " + health + "%";
        printMessage("Health: " + bar);
    }

    public void printBoxedMessage(String message) {
        String line = "+-";
        for(int i = 0; i < message.length(); i++){
            line += "-";
        }
        line += "-+";
        System.out.println(line);
        System.out.println("| " + message + " |");
        System.out.println(line);
    }

    public void printPickedUpItem(Item item){
        System.out.println("You took the " + item.getName());
    }
}
