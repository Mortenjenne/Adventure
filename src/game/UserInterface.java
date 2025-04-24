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

    public void eat(Player player, Food food){
        if(food != null){
            if(food.getNutrition() > 0){
                player.changeEnergy(food.getNutrition());
                System.out.println("You ate the " + food.getName() + " and gained " + food.getNutrition() + " energy."  );
                System.out.print("You're energy level is now ");
                showEnergyBar(player);
            }
            else {
                player.changeEnergy(food.getNutrition());
                System.out.println("You ate the " + food.getName() + " and lost " + food.getNutrition() + " energy, due to the food was poisonous"  );
                showEnergyBar(player);
            }
        }
    }

    public String[] readInput() {
        System.out.println("Awaiting your command:");
        String[] commands = scanner.nextLine().trim().toLowerCase().split(" ");
        return commands;
    }

    public void describeRoom(Room room) {
        System.out.println(room.getDescription());
        ArrayList<Item> items = room.getItems();
        if (!items.isEmpty()) {
            System.out.println("Items in the room: ");
            for (Item item: items) {
                System.out.println(item.getDescription() + " ");
            }
            System.out.println();
        }
    }

    public void printWelcome() {
        System.out.println("Welcome to the game of Adventure!");
        System.out.println("Type HELP or INFO for instructions on how to move around");
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
                 ENERGY Shows player energy level
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

    public void showEnergyBar(Player player) {
        int energy = player.getEnergy();
        int blocks = energy / 10;
        String bar = "[";

        for (int i = 0; i < 10; i++) {
            if (i < blocks) {
                bar += "#";
            } else {
                bar += " ";
            }
        }
        bar += "] " + energy + "%";
        printMessage("Energy: " + bar);
    }
}
