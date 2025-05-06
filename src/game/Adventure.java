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
                    move(commandString);
                    break;
                case "take":
                    takeItem(secondWord);
                    break;
                case "eat":
                    eatItem(secondWord);
                    break;
                case "health":
                    ui.showHealthBar(player);
                    break;
                case "equip":
                case "change":
                    equipWeapon(secondWord);
                    break;
                case "weapon":
                    showWeapon();
                    break;
                case "drop":
                    dropWeapon(secondWord);
                    break;
                case "attack":
                    attack(secondWord);
                    break;
                default:
                    ui.printMessage("I do not understand that command.");
            }
        }
    }

    private void move(String[] commandString){
        if(commandString.length < 2 || commandString[1].isEmpty()){
            ui.printMessage("Please enter a direction");
        } else {
            Direction direction = parseCommand(commandString[1]);
            if(direction != null){
                goCommand(direction);
            } else {
                ui.printMessage("Not a valid direction");
            }
        }
    }

    private void goCommand(Direction direction) {
        if (goDirection(direction)) {
            Room currentRoom = getCurrentRoom();
            String name = currentRoom.getName();
            String line = "-".repeat(name.length());
            ui.printMessage(line + "\n" + name + "\n" + line);
            ui.describeRoom(currentRoom);
            player.changeHealth(-10);
            lowHealthWarning();
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

    private void lowHealthWarning() {
        int energy = player.getHealth();

        if (energy <= 0) {
            gameRunning = false;
            ui.printBoxedMessage("💀 You have died! 💀 Thank you for playing Adventure!");
        } else if (energy <= 10) {
            ui.printBoxedMessage("⚠️ Critical Health Warning ⚠️ Your health is dangerously low! One more move might be your last.");
        } else if (energy <= 30) {
            ui.printBoxedMessage("⚠️ Warning ⚠️ Your health is running low. Be careful!");
        }
    }

    private void eatItem(String secondWord) {
        ActionResult result = player.eat(secondWord);

        switch (result) {
            case EAT:
                ui.printMessage("You have eaten the " + secondWord + " and gained health.");
                ui.showHealthBar(player);
                break;
            case POISONOUS:
                ui.printMessage("You have eaten the " + secondWord + " and lost health, due to the food was poisonous.");
                ui.showHealthBar(player);
                break;
            case CANT:
                ui.printMessage("You cannot eat that!");
                break;
            case DONT_KNOW:
                ui.printMessage("What do you want to eat? You didn't specify anything.");
                break;
        }
    }

    private void takeItem(String secondWord){
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

    private void equipWeapon(String secondword){
        ActionResult result = player.equipWeapon(secondword);

        switch (result){
            case EQUIP:
                ui.printMessage("You have equipped the " + secondword);
                break;
            case CANT:
                ui.printMessage("That's not a weapon!");
                break;
            case DONT_KNOW:
                ui.printMessage("What do you want to take?");
        }
    }

    private void showWeapon(){
        if(player.getEquippedWeapon() != null){
            ui.printMessage("You are carrying " + player.getEquippedWeapon() + "Damage pr. hit: " + player.getWeaponDamage());
        } else {
            ui.printMessage("You are not equipped with a weapon");
        }
    }

    private void dropWeapon(String secondWord) {
        ActionResult result = player.dropWeapon(secondWord);

        switch (result){
            case DROP:
                ui.printMessage("You have dropped the " + secondWord);
                break;
            case CANT:
                ui.printMessage("You cant drop that! not a weapon");
                break;
            case DONT_KNOW:
                ui.printMessage("What do you want to drop?");
        }
    }

    private void attack(String secondword){
        if (secondword.isEmpty()) {
            ui.printMessage("What do you want to attack?");
            return;
        }

        Enemy enemy = getCurrentRoom().findEnemy(secondword);
        if (enemy == null) {
            ui.printMessage("There is no enemy named " + secondword + " here.");
            return;
        }

        ActionResult result = player.attack();

        switch (result) {
            case ATTACK:
                ui.printMessage("You hit the " + enemy.getName() + "!");
                startCombat(secondword);
                break;
            case CANT:
                ui.printMessage("You don’t have a weapon equipped.");
                break;
        }
    }


    private void startCombat(String enemyName) {
        Enemy enemy = getCurrentRoom().findEnemy(enemyName);

        if (enemy != null) {

            while (!enemy.isDead() && player.getHealth() > 0) {
                ui.printMessage(enemy.getName() + " (HP: " + enemy.getHealth() + ")");

                enemy.takeDamage(player.getWeaponDamage());
                ui.printMessage("You hit the " + enemy.getName() + "!");

                if (!enemy.isDead() || player.isDead()) {
                    player.changeHealth(-enemy.attack());
                    ui.printMessage(enemy.getName() + " hits you back!");
                    ui.showHealthBar(player);

                }
            }

            if (enemy.isDead()) {
                player.getCurrentRoom().removeEnemy(enemy);
                ui.printMessage("You defeated the " + enemy.getName() + "! ⚔️");
                getCurrentRoom().removeEnemy(enemy);
            }

            //TODO if(player.gethealth()<=0)
            //gamerunning = false;
        }
    }
}
