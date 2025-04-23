package game;

public class Map {

    private Room startRoom;

    public void buildMap() {
        //create room
        Room room1 = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        room1.addItem("an old rusty flashlight", "flashlight");
        room1.addItem("a crinkly paper map with a big X in the middle", "map");

        Room room2 = new Room("Narrow cave", "narrow cave that seems to bend up and down almost infinitely");
        room2.addItem("a dusty old helmet with a cracked visor", "helmet");

        Room room3 = new Room("Large grotto", "large grotto with an opening very far above where sunlight shines through.");
        room3.addItem("a wicker bird cage", "bird cage");

        Room room4 = new Room("Wet cave", "large dark cave - the floor is quite wet and slippery here.");
        room4.addItem("a broken piece of chalk", "chalk");

        Room room5 = new Room("Treasure cave", "small dark cave with apparently only a single opening");
        room5.addItem("a cracked, ancient chest filled with gold coins", "chest");

        Room room6 = new Room("Underground Stream", "a dark, narrow stream of water that runs through the cave, the water glowing faintly due to minerals in it");
        room6.addItem("a rusted fishing net","net");

        Room room7 = new Room("Crystal Chamber", "a dazzling chamber where large crystal formations rise from the ground, their sharp edges glowing in the dark");
        room7.addItem("a glowing crystal shard", "crystal");

        Room room8 = new Room("Mellon cave", "small stony path next to the dark lake. A small door seems to be carved into the rock-face");
        room8.addItem("a worn shovel","shovel");

        Room room9 = new Room("Collapsed Tunnel", "a section of the cave where a tunnel has partially collapsed, blocking the way ahead with rocks and debris");
        room9.addItem("a vine-covered journal","journal");


        // Connect rooms
        //TODO connect all rooms according to the specified map DONE
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


        //TODO Set start room to room1
        this.startRoom = room1;
    }

    public Room getStartRoom() {
        return startRoom;
    }
}
