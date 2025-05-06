package game;

public class Map {

    private Room startRoom;

    public void buildMap() {

        // Room 1: Outside
        Room room1 = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        room1.addItem("an old rusty flashlight", "flashlight");
        room1.addItem("a crinkly paper map with a big X in the middle", "map");
        room1.addItem(new Food("A shiny red apple, perfect for a quick snack", "apple", 10));
        room1.addItem(new Weapon("Rusty Sword", "sword",5));
        room1.addEnemy("A Knight apears from the forrest","knight",20,10);

        // Room 2: Narrow cave
        Room room2 = new Room("Narrow cave", "narrow cave that seems to bend up and down almost infinitely");
        room2.addItem("a dusty old helmet with a cracked visor", "helmet");
        room2.addItem(new Food("A strange-looking mushroom, could be poisonous", "mushroom", -20));
        room2.addItem(new Weapon("Iron Dagger", "dagger",7));

        // Room 3: Large grotto
        Room room3 = new Room("Large grotto", "large grotto with an opening very far above where sunlight shines through.");
        room3.addItem("a wicker bird cage", "bird cage");
        room3.addItem(new Food("A handful of dried berries, a small but helpful snack", "berries", 15));
        room3.addItem(new RangedWeapon("Long bow with arrows","bow",8,12));

        // Room 4: Wet cave
        Room room4 = new Room("Wet cave", "large dark cave - the floor is quite wet and slippery here.");
        room4.addItem("a broken piece of chalk", "chalk");
        room4.addItem(new Food("A stale piece of bread, better than nothing", "bread", 20));
        room4.addItem(new Food("A putrid-looking fish, definitely not edible", "fish", -50));
        room4.addItem(new RangedWeapon("Phoenix bow, Each arrow bursts into flames mid-air","bow",10,8));
        room4.addEnemy("A bandit steps out from behind a rock", "bandit", 18, 6);

        // Room 5: Treasure cave
        Room room5 = new Room("Treasure cave", "small dark cave with apparently only a single opening");
        room5.addItem("a cracked, ancient chest filled with gold coins", "chest");
        room5.addItem(new Food("A golden apple, rumored to have magical properties", "golden apple", 50));
        room5.addItem(new RangedWeapon("A wand imbued with magical energy, capable of firing ethereal projectiles.","wand",25,10));
        room5.addItem(new Weapon("Rusty axe with wodden handle","axe",4));
        room5.addEnemy("A fearsome dragon appears from the cave, roaring as it breathes fire!", "dragon", 50, 15);

        // Room 6: Underground Stream
        Room room6 = new Room("Underground Stream", "a dark, narrow stream of water that runs through the cave, the water glowing faintly due to minerals in it");
        room6.addItem("a rusted fishing net","net");
        room6.addItem(new Food("A freshly caught fish, might be raw but it's food", "fish", 25));
        room6.addItem(new Food("A bucket full of oysters, might been here for a long time", "oyters",-15));


        // Room 7: Crystal Chamber
        Room room7 = new Room("Crystal Chamber", "a dazzling chamber where large crystal formations rise from the ground, their sharp edges glowing in the dark");
        room7.addItem("a glowing crystal shard", "crystal");
        room7.addItem(new Food("A crystallized candy, sweet and energizing", "candy", 30));
        room7.addItem(new Food("A bright purple berry, looks poisonous", "berry", -30));
        room1.addEnemy("A massive troll lumbers toward you, swinging its club!", "troll", 40, 12);

        // Room 8: Mellon cave
        Room room8 = new Room("Mellon cave", "small stony path next to the dark lake. A small door seems to be carved into the rock-face");
        room8.addItem("a worn shovel","shovel");
        room8.addItem(new Food("A juicy melon, refreshing and filling", "melon", 40));
        room8.addItem(new Food("A cursed pear, eating it may grant a powerful boost or put you into a deep sleep","cursed pear",-50));

        // Room 9: Collapsed Tunnel
        Room room9 = new Room("Collapsed Tunnel", "a section of the cave where a tunnel has partially collapsed, blocking the way ahead with rocks and debris");
        room9.addItem("a vine-covered journal","journal");
        room9.addItem(new Food("A chunk of hard cheese, could be a piece of comte", "cheese", 20));
        room9.addItem(new Weapon("A large industrial wrench","wrench",3));
        room1.addEnemy("A goblin jumps out of the shadows, ready to attack!", "goblin", 15, 5);


        // Connect rooms
        room1.setEastRoom(room2);
        room1.setSouthRoom(room4);

        room2.setEastRoom(room3);

        room3.setWestRoom(room2);
        room3.setSouthRoom(room6);

        room4.setNorthRoom(room1);
        room4.setSouthRoom(room7);

        room5.setSouthRoom(room8);

        room6.setNorthRoom(room3);
        room6.setSouthRoom(room9);

        room7.setNorthRoom(room4);
        room7.setEastRoom(room8);

        room8.setWestRoom(room7);
        room8.setNorthRoom(room5);
        room8.setEastRoom(room9);

        room9.setWestRoom(room8);
        room9.setNorthRoom(room6);

        this.startRoom = room1;
    }

    public Room getStartRoom() {
        return startRoom;
    }
}
