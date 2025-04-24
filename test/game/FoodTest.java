package game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    Player player;
    Room room;
    Food apple;

    @BeforeEach
    public void setUp() {
        player = new Player();
        room = new Room("Outside", "forrest outside a cave - the cave-entrance splits in two different directions.");
        apple = new Food("A shiny red apple", "apple", 20);
        room.addItem(apple);
        player.setCurrentRoom(room);
    }

    @Test
    public void testTakeFoodItem() {
        boolean wasTaken = player.takeItem("apple");
        assertTrue(wasTaken);
        assertEquals(1, player.getInventory().size());
        assertEquals("apple", player.getInventory().get(0).getName());
    }

    @Test
    public void testEatFoodIncreasesEnergy() {
        player.changeEnergy(-30);
        player.takeItem("apple");
        Food food = (Food) player.findFood("apple");
        player.changeEnergy(food.getNutrition());
        player.removeFood(food);
        assertEquals(90, player.getEnergy());
        assertNull(player.findFood("apple"));
    }

    @Test
    public void testEnergyDoesNotExceedMax() {
        player.takeItem("apple");
        Food food = (Food) player.findFood("apple");
        player.changeEnergy(food.getNutrition());
        player.removeFood(food);
        assertEquals(100, player.getEnergy());
    }

    @Test
    public void testEatNonExistentFood() {
        Item item = player.findFood("banana");
        assertNull(item);
    }

    @Test
    public void testPlayerEnergyCannotGoBelowZero() {
        player.changeEnergy(-150);
        assertEquals(0, player.getEnergy());
    }

}
