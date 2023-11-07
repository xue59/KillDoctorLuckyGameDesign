import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import model.item.Item;
import model.item.ItemImplement;
import model.room.Room;
import model.room.RoomImplement;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for testing the RoomImplement class.
 */
public class RoomImplementTest {
  private Room room;

  /**
   * Set up a test benchmark with Room input.
   */
  @Before
  public void setUp() {
    // Initialize the Room object with sample values for testing
    room = new RoomImplement("Living Room", 1, 0, 0, 10, 10);
  }

  /**
   * Tests the getRoomName method.
   */
  @Test
  public void testGetRoomName() {
    assertEquals("Living Room", room.getRoomName());
  }

  /**
   * Tests the getRoomNumber method.
   */
  @Test
  public void testGetRoomNumber() {
    assertEquals(1, room.getRoomNumber());
  }

  /**
   * Tests the getRoomCoordinate method.
   */
  @Test
  public void testGetRoomCoordinate() {
    int[] coordinates = room.getRoomCoordinate();
    assertNotNull(coordinates);
    assertEquals(0, coordinates[0]);
    assertEquals(0, coordinates[1]);
    assertEquals(10, coordinates[2]);
    assertEquals(10, coordinates[3]);
  }

  /**
   * Tests the getTopRowY method.
   */
  @Test
  public void testGetTopRowY() {
    assertEquals(0, room.getTopRowY());
  }

  /**
   * Tests the getTopColX method.
   */
  @Test
  public void testGetTopColX() {
    assertEquals(0, room.getTopColX());
  }

  /**
   * Tests the getBotColX method.
   */
  @Test
  public void testGetBotColX() {
    assertEquals(10, room.getBotColX());
  }

  /**
   * Tests the getBotRowY method.
   */
  @Test
  public void testGetBotRowY() {
    assertEquals(10, room.getBotRowY());
  }

  /**
   * Tests adding an item to the room using addOneItem and retrieving it using getOneItem.
   */
  @Test
  public void testAddAndGetOneItem() {
    Item item = new ItemImplement("Sword", 15);
    room.addOneItem(item);

    Item retrievedItem = room.getOneItem("Sword");
    assertNotNull(retrievedItem);
    assertEquals("Sword", retrievedItem.getName());
    assertEquals(15, retrievedItem.getDamage());
  }

  /**
   * Tests adding a duplicate item to the room, which should throw an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAddDuplicateItem() {
    Item item1 = new ItemImplement("Dagger", 10);
    Item item2 = new ItemImplement("Dagger", 10);

    room.addOneItem(item1);
    room.addOneItem(item2); // Adding a duplicate item should throw an exception
  }

  /**
   * Tests adding a null item to the room, which should throw a NullPointerException.
   */
  @Test(expected = NullPointerException.class)
  public void testAddNullItem() {
    room.addOneItem(null); // Adding a null item should throw a NullPointerException
  }

  /**
   * Tests removing an item from the room.
   */
  @Test
  public void testRemoveItem() {
    Item item = new ItemImplement("Potion", 3);
    room.addOneItem(item);
    room.removeOneItem(item);

    // After removing the item, it should not exist in the room
    boolean itemExists = room.getAllItemsWithDamage().containsKey("Potion");
    assertFalse(itemExists);
  }

  /**
   * Tests removing a nonexistent item from the room, which should throw an
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNonexistentItem() {
    Item item = new ItemImplement("Gloves", 5);

    room.removeOneItem(item); // Removing an item that doesn't exist should throw an exception
  }

  /**
   * Tests getting a map of all items in the room along with their associated damage values.
   */
  @Test
  public void testGetAllItemsWithDamage() {
    Item item1 = new ItemImplement("Shield", 5);
    Item item2 = new ItemImplement("Axe", 20);

    room.addOneItem(item1);
    room.addOneItem(item2);

    Map<String, Integer> itemMap = room.getAllItemsWithDamage();
    assertNotNull(itemMap);
    assertTrue(itemMap.containsKey("Shield"));
    assertTrue(itemMap.containsKey("Axe"));
    assertEquals(5, (int) itemMap.get("Shield"));
    assertEquals(20, (int) itemMap.get("Axe"));
  }

  /**
   * Tests the equals method for comparing two Room objects.
   */
  @Test
  public void testEquals() {
    Room room1 = new RoomImplement("Kitchen", 2, 0, 0, 10, 10);
    Room room2 = new RoomImplement("Kitchen", 2, 0, 0, 10, 10);

    assertEquals(room1, room2);
  }

  /**
   * Tests the hashCode method for comparing two Room objects.
   */
  @Test
  public void testHashCode() {
    Room room1 = new RoomImplement("Bedroom", 3, 0, 0, 10, 10);
    Room room2 = new RoomImplement("Bedroom", 3, 0, 0, 10, 10);

    assertEquals(room1.hashCode(), room2.hashCode());
  }
}
