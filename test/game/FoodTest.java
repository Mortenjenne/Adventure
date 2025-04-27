package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    Player player;
    Room room;
    Food apple;
    Food mushroom;

    @BeforeEach
    public void setUp() {
        player = new Player();
        room = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        apple = new Food("A shiny red apple", "apple", 20);
        mushroom = new Food("Brown mushroom", "mushroom",-50);
        room.addItem(mushroom);

        room.addItem(apple);
        player.setCurrentRoom(room);
        player.takeItem("apple");
    }

    @Test
    public void testTakeFoodItem() {
        assertEquals(1, player.getInventory().size());
        assertEquals("apple", player.getInventory().get(0).getName());
    }

    @Test
    public void testEatNeutralFood() {
        // Arrange
        Food neutralFood = new Food("Plain bread", "bread", 0); // No change in energy
        room.addItem(neutralFood);
        player.takeItem("bread");
        // Act
        player.eat(neutralFood);
        // Assert
        assertEquals(100, player.getEnergy()); // No change in energy
    }

    @Test
    public void testEatFoodIncreasesEnergy() {
        //Arange
        player.changeEnergy(-30);
        //Act
        player.eat(apple);
        //Assert
        assertEquals(90,player.getEnergy());

    }

    @Test
    public void testEatPoisunousFoodDecreaseEngergy(){
        //Arange
        player.takeItem("mushroom");
        //Act
        player.eat(mushroom);
        //Assert
        assertEquals(50,player.getEnergy());
    }

    @Test
    public void testEnergyDoesNotExceedMax() {
        //Arange
        assertTrue(100 == player.getEnergy());
        //Act
        player.eat(apple);
        //Assert
        assertEquals(100,player.getEnergy());
    }

    @Test
    public void testPlayerEnergyCannotGoBelowZero() {
        //Arrange
        player.changeEnergy(-75);
        player.takeItem("mushroom");
        //Act
        player.eat(mushroom);
        //Assert
        assertEquals(0, player.getEnergy());
    }


    @Test
    public void testEatNonInventoryFood() {
        // Arrange
        Food banana = new Food("Banana", "banana", 20);
        player.changeEnergy(-20);
        // Act
        player.eat(banana);
        // Assert
        assertEquals(80, player.getEnergy());
    }

}
