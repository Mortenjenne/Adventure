package game;


public class Adventure {

    private Map map;
    private Player player;
    private UserInterface ui = new UserInterface();
    private boolean gameRunning;

    public Adventure() {
        map = new Map();
        map.buildMap();
        player = new Player();
        player.setCurrentRoom(map.getStartRoom());
        gameRunning = true;
    }

    public void startGame() {
        ui.printWelcome();

        while (gameRunning) {
            String[] commandString = ui.readInput();
            String firstWord = commandString[0];
            String secondWord = "";
            if (commandString.length > 1) {
                secondWord = commandString[1];
            }

            switch (firstWord) {
                case "inv":
                case "invent":
                case "inventory":
                    ui.showInventory(player);
                    break;
                case "look":
                    ui.describeRoom(player.getCurrentRoom());
                    break;
                case "help":
                case "info":
                    ui.showHelp();
                    break;
                case "quit":
                case "exit":
                case "bye":
                    gameRunning = false;
                    ui.printMessage("Thank you for playing Adventure");
                    break;
                case "go":
                    lowEnergyWarning();
                    move(secondWord,commandString);
                    break;
                case "take":
                    takeItem(secondWord);
                    break;
                case "eat":
                    eatItem(secondWord);
                    break;
                case "energy":
                    ui.showEnergyBar(player);
                    break;
                default:
                    ui.printMessage("I do not understand that command.");
            }


        }
    }

    public void goCommand(Direction direction) {
        if (goDirection(direction)) {
            ui.describeRoom(getCurrentRoom());
        } else {
            ui.printMessage("You cannot go in that direction");
        }
    }

    private Direction parseCommand(String word) {
        Direction requestedDirection = null;
        switch (word) {
            case "n", "north":
                requestedDirection = Direction.NORTH;
                break;
            case "s", "south":
                requestedDirection = Direction.SOUTH;
                break;
            case "e", "east":
                requestedDirection = Direction.EAST;
                break;
            case "w", "west":
                requestedDirection = Direction.WEST;
                break;
        }
        return requestedDirection;
    }

    public boolean goDirection(Direction direction) {
        return player.move(direction);
    }

    public Room getCurrentRoom() {
        return player.getCurrentRoom();
    }

    public void lowEnergyWarning() {
        int energy = player.getEnergy();

        if (energy <= 0) {
            gameRunning = false;
            ui.printBoxedMessage("💀 You have died! 💀 Thank you for playing Adventure!");
        } else if (energy <= 10) {
            ui.printBoxedMessage("⚠️ Critical Energy Warning ⚠️ Your energy is dangerously low! One more move might be your last.");
        } else if (energy <= 30) {
            ui.printBoxedMessage("⚠️ Warning ⚠️ Your energy is running low. Be careful!");
        }
    }

    public void eatItem(String secondWord){
        if(secondWord.isEmpty()){
            ui.printMessage("What do you want to eat?");
        } else {
            Food food = player.getFoodFromInventory(secondWord);
            if(food != null){
                player.eat(food);
                ui.eatFood(food,player);

            } else {
                ui.printMessage("You cant eat that!");
            }
        }
    }

    public void takeItem(String secondWord){
        if (secondWord.isEmpty()) {
            ui.printMessage("What do you want to take?");
        } else {
            Item takenItem = player.takeItem(secondWord);
            if(takenItem != null){
                ui.printPickedUpItem(takenItem);
            } else {
                ui.printMessage("There is nothing like " + secondWord + " to take around here.");
            }
        }
    }

    public void move(String secondWord, String[] commandString){
        if (secondWord.isEmpty()) {
            ui.printMessage("Where do you want to go?");
        } else {

            Direction direction = parseCommand(commandString[1]);
            goCommand(direction);
            boolean moved = goDirection(direction);
            if (moved) {
                ui.describeRoom(player.getCurrentRoom());
                player.changeEnergy(-10);
                lowEnergyWarning();
            }

        }
    }
}
