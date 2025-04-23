package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    Map map;
    Room startRoom;

    @BeforeEach
    public void setUp() {
        map = new Map();
        map.buildMap();
        startRoom = map.getStartRoom();
     }

    @Test
    public void testGetEastRoom_From_StartRoom() {
        // Arrange + BeforeEach
        Room expectedEastRoom = new Room("Narrow cave", "narrow cave that seems to bend up and down almost infinitely");

        //Act
        Room actualEastRoom = startRoom.getEastRoom();

        //Assert
        assertEquals(expectedEastRoom.getName(), actualEastRoom.getName());
    }

    @Test
    public void testGetSouthRoom_From_StartRoom() {
        // Arrange + BeforeEach
        Room expectedSouthRoom = new Room("Wet cave", "large dark cave - the floor is quite wet and slippery here.");

        //Act
        Room actualSouthRoom = startRoom.getSouthRoom();

        //Assert
        assertEquals(expectedSouthRoom.getName(), actualSouthRoom.getName());
    }

    @Test
    public void testGetNorthRoom_From_StartRoom_Not_Possible() {
        //Act + BeforeEach
        Room actualNorthRoom = startRoom.getNorthRoom();
        //Assert
        assertNull(actualNorthRoom);
    }

    @Test
    public void testGetWestRoom_From_StartRoom_Not_Possible() {
        //Act + BeforeEach
        Room actualWestRoom = startRoom.getWestRoom();
        //Assert
        assertNull(actualWestRoom);
    }

    @Test
    public void testGetToRoom8FromRoom5() {
        //Arrange
        Room room5 = new Room("Treasure cave", "small dark cave with apparently only a single opening");
        Room room8 = new Room("Mellon cave", "small stony path next to the dark lake. A small door seems to be carved into the rock-face");
        room5.setSouthRoom(room8);
        //Act
        Room actualRoom8 = room5.getSouthRoom();
        //Assert
        assertEquals(room8.getName(), actualRoom8.getName());
    }
    @Test
    public void testThatRoomsAreAutomaticallyConnectedInBothDirections() {
        //Arrange
        Room room5 = new Room("Treasure cave", "small dark cave with apparently only a single opening");
        Room room8 = new Room("Mellon cave", "small stony path next to the dark lake. A small door seems to be carved into the rock-face");
        room5.setSouthRoom(room8);
        //Act
        Room expectedNorthRoom = room8.getNorthRoom();
        //Assert
        assertEquals(expectedNorthRoom.getName(), room5.getName());
    }


    @Test
    public void testAddItemToRoom() {
        //Arrange
        Room room = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        //Act
        room.addItem("an old rusty flashlight");
        //Assert
        Item actual = room.findItem("flashlight");
        assertEquals("flashlight", actual.getName());
    }


    @Test
    public void testRemoveItemFromRoom() {
        //Arrange
        Room room = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        room.addItem("an old rusty flashlight");
        //Act
        Item found = room.removeItem("flashlight");
        //Assert
        assertNotNull(found);
    }


    @Test
    public void testRemoveItemNotInRoom() {
        //Arrange
        Room room = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        //Act
        Item found = room.removeItem("map");
        //Assert
        assertNull(found);
    }
}