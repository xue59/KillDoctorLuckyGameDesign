import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.util.List;
import model.world.World;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for testing the methods of Ms2MockWorld.
 */
public class Ms2MockWorldTest {
  private World world;
  private StringBuilder log;

  @Before
  public void setUp() {
    log = new StringBuilder();
    world = new WorldMockModel(log);
  }

  /**
   * Test case for the method that retrieves neighbors of a room.
   */
  @Test
  public void testGetNeighborsRoomList() {
    List<String> neighbors = world.getNeighborsRoomList("Room1");
    assertNotNull(neighbors);
    assertEquals(1, neighbors.size());
    assertEquals("ListOfNeighbors", neighbors.get(0));
  }

  /**
   * Test case for getting the world name.
   */
  @Test
  public void testGetWorldName() {
    String worldName = world.getWorldName();
    assertNotNull(worldName);
    assertEquals("Dr. Lucky's Mansion", worldName);
  }

  /**
   * Test case for getting the total number of rooms in the world.
   */
  @Test
  public void testGetTotalOfRoom() {
    int totalRooms = world.getTotalOfRoom();
    assertEquals(21, totalRooms);
  }

  /**
   * Test case for getting the total number of items in the world.
   */
  @Test
  public void testGetTotalOfItem() {
    int totalItems = world.getTotalOfItem();
    assertEquals(20, totalItems);
  }

  /**
   * Test case for getting information about a specific room.
   */
  @Test
  public void testGetOneRoomInfo() {
    String roomInfo = world.getOneRoomInfo("Room1");
    assertNotNull(roomInfo);
    assertEquals("One Room Info", roomInfo);
  }

  /**
   * Test case for moving Dr. Lucky within the world.
   */
  @Test
  public void testMoveDrLucky() {
    world.moveDrLucky();
    assertEquals("DrLucky moved by one!", log.toString());
  }

  /**
   * Test case for creating a graph-based BufferedImage of the world.
   */
  @Test
  public void testCreateGraphBufferedImage() {
    BufferedImage image = world.createGraphBufferedImage();
    assertNotNull(image);
  }

  /**
   * Test case for printing the neighbor map of the world.
   */
  @Test
  public void testPrintWorldNeighborMap() {
    world.printWorldNeighborMap();
    assertEquals("printWorldNeighborMap world map printed!", log.toString());
  }

  /**
   * Test case for getting information about Dr. Lucky.
   */
  @Test
  public void testGetDrLuckyInfo() {
    String drLuckyInfo = world.getDrLuckyInfo();
    assertEquals("getDrLuckyInfo executed!", log.toString());
    assertNotNull(drLuckyInfo);
  }

  /**
   * Test case for printing information about all rooms in the world.
   */
  @Test
  public void testPrintAllRoomInfo() {
    world.printAllRoomInfo();
    assertEquals("printAllRoomInfo all room info printed!", log.toString());
  }

  /**
   * Test case for adding a new player to the world.
   */
  @Test
  public void testAddOnePlayer() {
    world.addOnePlayer("Player1", 0, false, 5);
    assertEquals(
        "addOnePlayer executed: name=Player1, initialRoomNum=0, checkComputer=false, limit=5",
        log.toString());
  }

  /**
   * Test case for setting the total allowed turns in the game.
   */
  @Test
  public void testSetTotalAllowedTurns() {
    world.setTotalAllowedTurns(10);
    assertEquals("setTotalAllowedTurns executed: totalAllowedTurns=10", log.toString());
    assertEquals(10, world.getTotalAllowedTurns());
  }

  /**
   * Test case for setting the total allowed players in the game.
   */
  @Test
  public void testSetTotalAllowedPlayers() {
    world.setTotalAllowedPlayers(4);
    assertEquals("setTotalAllowedPlayers executed: totalAllowedPlayers=4", log.toString());
    assertEquals(4, world.getTotalAllowedPlayers());
  }

  /**
   * Test case for a computer player's action.
   */
  @Test
  public void testCmdComputerPlayerAction() throws IllegalAccessException {
    String actionResult = world.cmdComputerPlayerAction();
    assertEquals("cmdComputerPlayerAction executed!", log.toString());
    assertNotNull(actionResult);
  }

  /**
   * Test case for a player moving to a specific room.
   */
  @Test
  public void testCmdPlayerMove() throws IllegalAccessException {
    world.cmdPlayerMove("Room2");
    assertEquals("cmdPlayerMove executed: roomName=Room2", log.toString());
  }

  /**
   * Test case for a player looking around the room.
   */
  @Test
  public void testCmdPlayerLook() {
    String lookResult = world.cmdPlayerLook();
    assertEquals("cmdPlayerLook executed!", log.toString());
    assertNotNull(lookResult);
  }

  /**
   * Test case for a player picking up an item.
   */
  @Test
  public void testCmdPlayerPick() throws IllegalAccessException {
    world.cmdPlayerPick("Item1");
    assertEquals("cmdPlayerPick executed: inputItemName=Item1", log.toString());
  }

  /**
   * Test case for getting information on what a player can pick up.
   */
  @Test
  public void testGetPlayerWhatCanPickInfo() {
    String whatCanPickInfo = world.getPlayerWhatCanPickInfo("Player1");
    assertEquals("getPlayerWhatCanPickInfo executed: playerName=Player1", log.toString());
    assertNotNull(whatCanPickInfo);
  }

  /**
   * Test case for checking if the game is over.
   */
  @Test
  public void testCheckGameOver() throws IllegalAccessException {
    world.setTotalAllowedTurns(5);
    world.cmdComputerPlayerAction();
    world.cmdComputerPlayerAction();
    world.cmdComputerPlayerAction();
    world.cmdComputerPlayerAction();
    assertFalse(world.checkGameOver());
    world.cmdComputerPlayerAction();
    assertTrue(world.checkGameOver());
    assertEquals("setTotalAllowedTurns executed: "
        + "totalAllowedTurns=5cmdComputerPlayerAction "
        + "executed!cmdComputerPlayerAction executed!cmdComputerPlayerAction "
        + "executed!cmdComputerPlayerAction executed!checkGameOver executed!\n"
        + "cmdComputerPlayerAction executed!checkGameOver executed!\n", log.toString());
  }

  /**
   * Test case for getting the names of all players in the game.
   */
  @Test
  public void testGetAllPlayerNames() {
    List<String> playerNames = world.getAllPlayerNames();
    assertEquals("getAllPlayerNames executed!", log.toString());
    assertNotNull(playerNames);
    assertTrue(playerNames.isEmpty());
  }

  /**
   * Test case for getting information about all players in the game.
   */
  @Test
  public void testGetAllPlayerInfo() {
    String allPlayerInfo = world.getAllPlayerInfo();
    assertEquals("getAllPlayerInfo executed!", log.toString());
    assertNotNull(allPlayerInfo);
  }

  /**
   * Test case for getting the names of all rooms in the world.
   */
  @Test
  public void testGetAllRoomNames() {
    List<String> roomNames = world.getAllRoomNames();
    assertEquals("getAllRoomNames executed!", log.toString());
    assertNotNull(roomNames);
    assertTrue(roomNames.isEmpty());
  }

  /**
   * Test case for getting information about a specific player and their current room.
   */
  @Test
  public void testGetOnePlayerAndRoomInfo() {
    String playerAndRoomInfo = world.getOnePlayerAndRoomInfo("Player1");
    assertEquals("getOnePlayerAndRoomInfo executed: playerName=Player1", log.toString());
    assertNotNull(playerAndRoomInfo);
  }

  /**
   * Test case for getting the current room name of a specific player.
   */
  @Test
  public void testGetOnePlayerCurrentRoomName() {
    String currentRoomName = world.getOnePlayerCurrentRoomName("Player1");
    assertEquals("getOnePlayerCurrentRoomName executed: playerName=Player1", log.toString());
    assertNotNull(currentRoomName);
  }

  /**
   * Test case for getting the name of the current player.
   */
  @Test
  public void testGetCurrentPlayerName() {
    String currentPlayerName = world.getCurrentPlayerName();
    assertEquals("getCurrentPlayerName executed!", log.toString());
    assertNotNull(currentPlayerName);
  }

  /**
   * Test case for checking if the current player is a computer player.
   */
  @Test
  public void testIsCurrentPlayerComputer() {
    boolean isComputer = world.isCurrentPlayerComputer();
    assertEquals("isCurrentPlayerComputer executed!", log.toString());
    assertFalse(isComputer); // By default, the test mock is not a computer player.
  }

  /**
   * Test case for getting the index of the current player.
   */
  @Test
  public void testGetCurrentPlayerIndex() {
    int currentPlayerIndex = world.getCurrentPlayerIndex();
    assertEquals("getCurrentPlayerIndex executed!", log.toString());
    assertEquals(1, currentPlayerIndex);
  }

  /**
   * Test case for getting the current turn number.
   */
  @Test
  public void testGetCurrentTurnNumber() {
    int currentTurnNumber = world.getCurrentTurnNumber();
    assertEquals("getCurrentTurnNumber executed!", log.toString());
    assertEquals(0, currentTurnNumber);
  }
}
