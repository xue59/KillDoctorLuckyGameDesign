import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import model.world.CreateWorldHelper;
import model.world.WorldImplement;
import org.junit.Before;
import org.junit.Test;
public class Ms2WorldImplementTest {

  private WorldImplement ms2TestWorld;

  @Before
  public void setUp() {
    try {
      FileReader fileReader = new FileReader("res/testRes/TestMansionGood.txt");
      BufferedReader br = new BufferedReader(fileReader);
      CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
      this.ms2TestWorld = (WorldImplement) createHelper.createWorld();
      this.ms2TestWorld.setTotalAllowedPlayers(3);
      this.ms2TestWorld.setTotalAllowedTurns(9);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testWorldName() {
    assertEquals("Your Expected World Name", ms2TestWorld.getWorldName());
  }

  @Test
  public void testTotalRooms() {
    assertEquals(20, ms2TestWorld.getTotalOfRoom());
  }

  @Test
  public void testTotalItems() {
    assertEquals(50, ms2TestWorld.getTotalOfItem());
  }

  @Test
  public void testGetNeighborsRoomList() {
    // Mock the worldMapIndex for a room's neighbors
    Map<Integer, Set<Integer>> worldMapIndexMock = mock(Map.class);
    when(worldMapIndexMock.get(anyInt())).thenReturn(Set.of(2, 3, 4));

    ms2TestWorld = new WorldImplement(5, 5, "Test World", 25, 50, null, null, worldMapIndexMock, null);
    assertEquals(Set.of("Room 2", "Room 3", "Room 4"), new HashSet<>(ms2TestWorld.getNeighborsRoomList("Room 1")));
  }

  @Test
  public void testGetOneRoomInfo() {
    String roomInfo = ms2TestWorld.getOneRoomInfo("Library");
    assertNotNull(roomInfo);
    assertTrue(roomInfo.contains("Library"));
    // Add more checks based on the expected format of room information
  }

  @Test
  public void testGetOnePlayerAndRoomInfo() {
    String playerInfo = ms2TestWorld.getOnePlayerAndRoomInfo("Player1");
    assertNotNull(playerInfo);
    assertTrue(playerInfo.contains("Player1"));
    // Add more checks based on the expected format of player information
  }

  @Test
  public void testGetDrLuckyInfo() {
    String drLuckyInfo = ms2TestWorld.getDrLuckyInfo();
    assertNotNull(drLuckyInfo);
    // Add checks for the expected format of DrLucky information
  }

  @Test
  public void testSetTotalAllowedPlayers() {
    ms2TestWorld.setTotalAllowedPlayers(4);
    assertEquals(4, ms2TestWorld.getTotalAllowedPlayers());
  }

  @Test
  public void testSetTotalAllowedTurns() {
    ms2TestWorld.setTotalAllowedTurns(30);
    assertEquals(30, ms2TestWorld.getTotalAllowedTurns());
  }

  @Test
  public void testAddOnePlayer() {
    int totalPlayerBefore = ms2TestWorld.getAllPlayerNames().size();
    ms2TestWorld.addOnePlayer("human1", 0, false, 10);
    // Add assertions for verifying the player addition
    assertEquals(1+totalPlayerBefore, ms2TestWorld.getAllPlayerNames().size());
    assertEquals("Player type: Human Player\n" +
        "Player's Name: human1 \n" +
        "Player's limit: 10, can still carry: 10\n" +
        "Carrying: [] \n" +
        "Current Room: Test11Good (**Dr.Lucky**(Doctor Lucky HP=50) is in this #0 room.)\n" +
        "#0 Room: Test11Good, has items: [Revolver(Damage=3)]\n" +
        "Neighbor Rooms: Test22Good, Test44Good, Test55Good\n",
        ms2TestWorld.getOnePlayerAndRoomInfo("human1") );
  }

  @Test
  public void testCmdPlayerMove() {
    try {
      ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
      ms2TestWorld.cmdPlayerMove("AdjacentRoomName");
      // Add assertions for verifying player movement
      // Check if the player is now in the expected room
      // Check if the turn has changed
    } catch (Exception e) {
      fail("Exception occurred while moving the player.");
    }
  }

  @Test
  public void testCmdPlayerPick() {
    try {
      ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
      ms2TestWorld.cmdPlayerPick("ItemName");
      // Add assertions for verifying item pickup
      // Check if the player's capacity has been updated
      // Check if the room's items have been updated
      // Check if the turn has changed
    } catch (Exception e) {
      fail("Exception occurred while making the player pick an item.");
    }
  }


  @Test
  public void testCmdComputerPlayerAction() throws IllegalAccessException {
    // Create a mock for ms2TestWorld
    WorldImplement ms2TestWorld = mock(WorldImplement.class);

    // Mocking player actions for the computer player
    when(ms2TestWorld.checkGameOver()).thenReturn(false);
    String actionResult = ms2TestWorld.cmdComputerPlayerAction();
    assertNotNull(actionResult);
    // Add checks for verifying computer player actions

    // Now, test the case when checkGameOver() returns true
    reset(ms2TestWorld); // Reset previous mocking

    when(ms2TestWorld.checkGameOver()).thenReturn(true);
    String gameOverResult = ms2TestWorld.cmdComputerPlayerAction();
    assertNotNull(gameOverResult);
    // Add checks for when the game is over, e.g., check for game over message or state
  }


  @Test
  public void testCheckGameOver() {
    // Test game over conditions and check if checkGameOver() returns true
    // Add assertions for different game over conditions
    // For example, Dr. Lucky's HP <= 0 or turns exceeded
  }
}
