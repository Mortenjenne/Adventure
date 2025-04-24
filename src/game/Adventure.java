package game;


public class Adventure {

    private Map map;
    private Player player;
    private UserInterface ui = new UserInterface();

    public Adventure() {
        map = new Map();
        map.buildMap();
        player = new Player();
        player.setCurrentRoom(map.getStartRoom());
    }

    public void startGame() {
        boolean gameRunning = true;
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
                    if(secondWord.isEmpty()){
                        ui.printMessage("Where do you want to go?");
                    } else {
                        Direction direction = parseCommand(commandString[1]);
                        goCommand(direction);
                        player.changeEnergy(-10);
                    }
                    break;
                case "take":
                    if (secondWord.isEmpty()) {
                        ui.printMessage("What do you want to take?");
                    } else if (player.takeItem(secondWord)) {
                        ui.printMessage("You have taken the " + secondWord);
                    } else {
                        ui.printMessage("There is nothing like " + secondWord + " to take around here.");
                    }
                    break;
                case "eat":
                    if(secondWord.isEmpty()){
                        ui.printMessage("What do you want to eat?");
                    } else {
                        Item item = player.findFood(secondWord);
                        if(item != null){
                            Food food = (Food) item;
                            ui.eat(player,food);
                            player.removeFood(food);
                        } else {
                            ui.printMessage("You cant eat that!");
                        }
                    }
                    break;
                case "energy":
                    ui.showEnergyBar(player);
                    break;
                default:
                    ui.printMessage("I do not understand that command.");
            }

            if(player.getEnergy() <= 30 || player.getEnergy() <= 20 || player.getEnergy() <= 10){
                ui.printMessage("⚠️ WARNING ⚠️\nYour energy is critically low...\nOne more move might be your last.");
            }
            if(player.getEnergy() <= 0){
                gameRunning = false;
                ui.printMessage("You died!" + "\n" + "Thank you for playing Adventure");
            }
        }
    }

    public void goCommand(Direction direction) {
        if (goDirection(direction)) {
            Room currentRoom = getCurrentRoom();
            String name = currentRoom.getName();
            String line = "-".repeat(name.length());
            ui.printMessage(line + "\n" + name + "\n" + line);
            ui.describeRoom(currentRoom);
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
}
