import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import model.item.Item;
import model.item.ItemImplement;
import model.player.Player;
import model.player.PlayerImplement;
import org.junit.Before;
import org.junit.Test;

public class PlayerImplementTest {
  private PlayerImplement player;

  @Before
  public void setUp() {
    player = new PlayerImplement("John", 0, false, 5);
  }

  @Test
  public void testGetPlayerName() {
    assertEquals("John", player.getPlayerName());
  }

  @Test
  public void testGetInitialRoomNumber(){
    Player playerTest = new PlayerImplement("Ben", 5, false, 5);
    assertEquals(5,playerTest.getCurrentRoomNumber());

  }
  @Test
  public void testGetCurrentRoomNumber() {
    assertEquals(0, player.getCurrentRoomNumber());
  }



  @Test
  public void testCheckComputer() {
    assertFalse(player.checkComputer());
  }

  @Test
  public void testPickUpOneItem() throws IllegalAccessException {
    Item item = new ItemImplement("Sword", 10);
    player.pickUpOneItem(item);
    assertTrue(player.getItemListMapInfo().containsKey("Sword"));
  }

  @Test (expected = IllegalAccessException.class)
  public void testPickUpOneItemWithLimitExceeded() throws IllegalAccessException {
    Item item = new ItemImplement("Sword", 10);
    for (int i = 0; i < 5; i++) {
      player.pickUpOneItem(new ItemImplement("Item" + i, 5));
    }
    try {
      player.pickUpOneItem(item);
      fail("Expected IllegalStateException");
    } catch (IllegalStateException e) {
      // This exception is expected
      throw e;
    }
  }

  @Test
  public void testMoveToRoomNumber() {
    player.moveToRoomNumber(1);
    assertEquals(1, player.getCurrentRoomNumber());
  }

  @Test
  public void testMoveToRoomNumberWithNegativeRoomNumber() {
    try {
      player.moveToRoomNumber(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // This exception is expected
    }
  }

  @Test
  public void testDeleteOneItem() throws IllegalAccessException {
    Item item = new ItemImplement("Sword", 10);
    player.pickUpOneItem(item);
    player.deleteOneItem(item);
    assertFalse(player.getItemListMapInfo().containsKey("Sword"));
  }

  @Test
  public void testDeleteNonExistentItem() {
    Item item = new ItemImplement("Axe", 8);
    try {
      player.deleteOneItem(item);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // This exception is expected
    }
  }

  @Test
  public void testEqualsAndHashCode() {
    PlayerImplement samePlayer = new PlayerImplement("John", 0, false, 5);
    PlayerImplement differentPlayer = new PlayerImplement("Alice", 1, true, 3);

    assertEquals(player, samePlayer);
    assertNotEquals(player, differentPlayer);

    assertEquals(player.hashCode(), samePlayer.hashCode());
    assertNotEquals(player.hashCode(), differentPlayer.hashCode());
  }

  @Test
  public void testToStringForHumanPlayer() {
    String expectedHumanString = "Player type: Human Player\n"
        + "Player's Name: John \n"
        + "Player's limit: 5, can still carry: 5\n"
        + "Carrying: [] \n";

    // Ensure the toString method returns the expected string for a human player
    assertEquals(expectedHumanString, player.toString());
  }

  @Test
  public void testToStringForComputerPlayer() {
    // Create a computer player
    PlayerImplement computerPlayer = new PlayerImplement("Alice", 1, true, 3);

    String expectedComputerString = "Player type: **Computer Player**\n"
        + "Player's Name: Alice \n"
        + "Player's limit: 3, can still carry: 3\n"
        + "Carrying: [] \n";

    // Ensure the toString method returns the expected string for a computer player
    assertEquals(expectedComputerString, computerPlayer.toString());
  }



}
