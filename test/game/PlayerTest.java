package game;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Map map;
    Player player;

    @BeforeEach
    public void setUp() {
        map = new Map();
        map.buildMap();
        player = new Player();
        player.setCurrentRoom(map.getStartRoom());
    }

    @Test
    public void testMoveEastFromStartroom() {
        //Arrange
        Room expected = new Room("Narrow cave", "narrow cave that seems to bend up and down almost infinitely");

        //Act
        player.move(Direction.EAST);
        //Assert
        assertEquals(expected.getName(), player.getCurrentRoom().getName());
    }

    @Test
    public void testMoveSouthFromStartroom() {
        //Arrange
        Room expected = new Room("Wet cave", "large dark cave - the floor is quite wet and slippery here.");
        //Act
        player.move(Direction.SOUTH);
        //Assert
         assertEquals(expected.getName(), player.getCurrentRoom().getName());
    }

    @Test
    public void testMoveNorthFromStartroom_NotPossible() {
        assertFalse(player.move(Direction.NORTH));
    }

    @Test
    public void testMoveWestFromStartroomNotPossible() {
        assertFalse(player.move(Direction.WEST));
    }

    @Disabled
    @Test
    public void testPickupItemFromRoom() {
        //Arrange BeforeEach
        assertTrue(player.getInventory().isEmpty());
        //Act
        player.takeItem("map");
        assertTrue(player.getInventory().size() == 1);
    }


    @Test
    public void testPickupNotExistingItemFromRoom() {
        //Arrange BeforeEach
        assertTrue(player.getInventory().isEmpty());
        // Act
        player.takeItem("ball");
        assertTrue(player.getInventory().isEmpty());
    }
}