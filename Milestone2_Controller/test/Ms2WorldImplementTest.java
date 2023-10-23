import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import model.world.CreateWorldHelper;
import model.world.WorldImplement;
import org.junit.Before;
import org.junit.Test;


/**
 * This class contains unit tests for the `WorldImplement` class in the context of the Ms2 project.
 */
public class Ms2WorldImplementTest {

  private WorldImplement ms2TestWorld;

  /**
   * Sets up the test environment by creating a test world with specific properties.
   */
  @Before
  public void setUp() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/mansion2023.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    this.ms2TestWorld = (WorldImplement) createHelper.createWorld();
    this.ms2TestWorld.setTotalAllowedPlayers(3);
    this.ms2TestWorld.setTotalAllowedTurns(9);
  }

  /**
   * Tests the retrieval of the world name.
   */
  @Test
  public void testWorldName() {
    assertEquals("Doctor Lucky's Mansion", ms2TestWorld.getWorldName());
  }

  /**
   * Tests the retrieval of the total number of rooms in the world.
   */
  @Test
  public void testTotalRooms() {
    assertEquals(21, ms2TestWorld.getTotalOfRoom());
  }

  /**
   * Tests the retrieval of the total number of items in the world.
   */
  @Test
  public void testTotalItems() {
    assertEquals(20, ms2TestWorld.getTotalOfItem());
  }

  /**
   * Tests the retrieval of information about a specific room.
   */
  @Test
  public void testGetOneRoomInfo() {
    String roomInfo = ms2TestWorld.getOneRoomInfo("Library");
    assertNotNull(roomInfo);
    assertTrue(roomInfo.contains("Library"));
    // Add more checks based on the expected format of room information
  }

  /**
   * Tests the retrieval of player and room information when the player is not carrying any items.
   */
  @Test
  public void testGetOnePlayerAndRoomInfoNoCarryingItem() {
    ms2TestWorld.addOnePlayer("human1", 4, false, 10);
    ms2TestWorld.addOnePlayer("computer2", 4, true, 11);
    String playerInfo = ms2TestWorld.getOnePlayerAndRoomInfo("human1");
    assertNotNull(playerInfo);
    assertEquals("Player type: Human Player\n"
        + "Player's Name: human1 \n"
        + "Player's limit: 10, can still carry: 10\n"
        + "Carrying: [] \n"
        + "Current Room: Drawing Room\n"
        + "#4 Room: Drawing Room, has items: [Letter Opener(Damage=2)]\n"
        + "Neighbor Rooms: Armory, Dining Hall, Wine Cellar, Foyer\n", playerInfo);

  }

  /**
   * Tests the retrieval of player and room information when the player is carrying one item.
   */
  @Test
  public void testGetOnePlayerAndRoomInfoCarrying1Item() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human1", 4, false, 10);
    ms2TestWorld.addOnePlayer("computer2", 4, true, 11);
    ms2TestWorld.cmdPlayerPick("Letter Opener");
    String playerInfo = ms2TestWorld.getOnePlayerAndRoomInfo("human1");
    assertNotNull(playerInfo);
    assertEquals("Player type: Human Player\n"
        + "Player's Name: human1 \n"
        + "Player's limit: 10, can still carry: 9\n"
        + "Carrying: [Letter Opener(Damage=2)] \n"
        + "Current Room: Drawing Room\n"
        + "#4 Room: Drawing Room, has items: []\n"
        + "Neighbor Rooms: Armory, Dining Hall, Wine Cellar, Foyer\n", playerInfo);
  }

  /**
   * Tests the retrieval of player and room information when the player is carrying two items.
   */
  @Test
  public void testGetOnePlayerAndRoomInfoCarrying2Items() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human1", 6, false, 10);
    ms2TestWorld.cmdPlayerPick("Trowel");
    ms2TestWorld.cmdPlayerPick("Pinking Shears");
    String playerInfo = ms2TestWorld.getOnePlayerAndRoomInfo("human1");
    assertNotNull(playerInfo);
    assertEquals("Player type: Human Player\n"
        + "Player's Name: human1 \n"
        + "Player's limit: 10, can still carry: 8\n"
        + "Carrying: [Trowel(Damage=2), Pinking Shears(Damage=2)] \n"
        + "Current Room: Green House\n"
        + "#6 Room: Green House, has items: []\n"
        + "Neighbor Rooms: Hedge Maze\n", playerInfo);
  }

  /**
   * Tests the "Look" command when there are no items available in the room.
   */
  @Test
  public void testCmdLookNoItemAvailableInRoom() {
    ms2TestWorld.addOnePlayer("human1", 3, false, 10);
    ms2TestWorld.addOnePlayer("computer2", 3, true, 11);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals(
        "You (player: human1) are currently in room #3 Dining Hall and can be seen from "
            + "rooms: [Armory, Tennessee Room, Billiard Room, Trophy Room, Wine Cellar, Drawing "
            + "Room, "
            + "Kitchen, Parlor]\n"
            + "#3 Room: Dining Hall, has items: []\n"
            + "Players in the same room: computer2, \n", human1LookRes);
  }

  /**
   * Tests the "Look" command when there are two items in the room.
   */
  @Test
  public void testCmdLookWith2ItemsSuccess() {
    ms2TestWorld.addOnePlayer("human1", 2, false, 10);
    ms2TestWorld.addOnePlayer("computer2", 2, true, 11);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals(
        "You (player: human1) are currently in room #2 Carriage "
            + "House and can be seen from rooms:"
            + " [Winter Garden]\n"
            + "#2 Room: Carriage House, has items: [Chain Saw(Damage=4), "
            + "Big Red Hammer(Damage=4)]\n"
            + "Players in the same room: computer2, \n", human1LookRes);
  }

  /**
   * Tests the "Look" command when there is one item in the room.
   */
  @Test
  public void testCmdLookWith1ItemSuccess() {
    ms2TestWorld.addOnePlayer("human1", 15, false, 10);
    ms2TestWorld.addOnePlayer("computer2", 15, true, 11);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals(
        "You (player: human1) are currently in room #15 Piazza "
            + "and can be seen from rooms: [Winter Garden, Foyer, Hedge Maze]\n"
            + "#15 Room: Piazza, has items: [Civil War Cannon(Damage=3)]\n"
            + "Players in the same room: computer2, \n", human1LookRes);
  }

  /**
   * Tests the "Look" command when Dr. Lucky has appeared in the room.
   */
  @Test
  public void testCmdLookWithNoDrLucky() {
    ms2TestWorld.addOnePlayer("human1", 15, false, 10);
    ms2TestWorld.addOnePlayer("computer2", 15, true, 11);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals(
        "You (player: human1) are currently in room #15 Piazza "
            + "and can be seen from rooms: [Winter Garden, Foyer, Hedge Maze]\n"
            + "#15 Room: Piazza, has items: [Civil War Cannon(Damage=3)]\n"
            + "Players in the same room: computer2, \n", human1LookRes);
  }

  /**
   * Tests the "Look" command when there are no other players in the room.
   */
  @Test
  public void testCmdLookWithDrLuckyAppeared() {
    ms2TestWorld.addOnePlayer("human1", 0, false, 10);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals(
        "You (player: human1) are currently in "
            + "room #0 Armory and can be seen from rooms: "
            + "[Billiard Room, Dining Hall, Drawing Room]\n"
            + "#0 Room: Armory, has items: [Revolver(Damage=3)]\n"
            + "**Dr. Lucky is in the room: Target name: Doctor Lucky, "
            + "Current HP: 50, Current room "
            + "index: 0\nPlayers in the same room: \n", human1LookRes);
  }

  /**
   * Tests the "Look" command when there are no other players in the room.
   */
  @Test
  public void testCmdLookWithNoOtherPlayer() {
    ms2TestWorld.addOnePlayer("human1", 2, false, 10);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals("You (player: human1) are currently in room #2 Carriage "
        + "House and can be seen from rooms:"
        + " [Winter Garden]\n"
        + "#2 Room: Carriage House, has items: [Chain Saw(Damage=4), Big Red Hammer(Damage=4)"
        + "]\n"
        + "Players in the same room: \n", human1LookRes);

  }

  /**
   * Tests the "Look" command when there are 1 other players in the room.
   */
  @Test
  public void testCmdLookWith1OtherPlayer() {
    ms2TestWorld.addOnePlayer("human1", 2, false, 10);
    ms2TestWorld.addOnePlayer("human2", 2, false, 10);

    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals("You (player: human1) are currently in room #2 "
        + "Carriage House and can be seen from rooms:"
        + " [Winter Garden]\n"
        + "#2 Room: Carriage House, has items: [Chain Saw(Damage=4), Big Red Hammer(Damage=4)"
        + "]\n"
        + "Players in the same room: human2, \n", human1LookRes);

  }

  /**
   * Tests the "Look" command when there are two other players in the room.
   */
  @Test
  public void testCmdLookWith2OtherPlayer() {
    ms2TestWorld.addOnePlayer("human1", 2, false, 10);
    ms2TestWorld.addOnePlayer("human2", 2, false, 10);
    ms2TestWorld.addOnePlayer("human3", 2, false, 10);

    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals(
        "You (player: human1) are currently in room #2 Carriage "
            + "House and can be seen from rooms:"
            + " [Winter Garden]\n"
            + "#2 Room: Carriage House, has items: [Chain Saw(Damage=4), Big Red Hammer(Damage=4)"
            + "]\nPlayers in the same room: human2, human3, \n", human1LookRes);

  }


  /**
   * Tests the retrieval of information about Dr. Lucky.
   */
  @Test
  public void testGetDrLuckyInfo() {
    String drLuckyInfo = ms2TestWorld.getDrLuckyInfo();
    assertNotNull(drLuckyInfo);
    // Add checks for the expected format of DrLucky information
  }

  /**
   * Tests the setting of the total number of allowed players.
   */
  @Test
  public void testSetTotalAllowedPlayers() {
    ms2TestWorld.setTotalAllowedPlayers(4);
    assertEquals(4, ms2TestWorld.getTotalAllowedPlayers());
  }

  /**
   * Tests the setting of the total number of allowed turns.
   */
  @Test
  public void testSetTotalAllowedTurns() {
    ms2TestWorld.setTotalAllowedTurns(30);
    assertEquals(30, ms2TestWorld.getTotalAllowedTurns());
  }

  /**
   * Tests the addition of a new player to the world.
   */
  @Test
  public void testAddOnePlayer() {
    int totalPlayerBefore = ms2TestWorld.getAllPlayerNames().size();
    ms2TestWorld.addOnePlayer("human1", 0, false, 10);
    // Add assertions for verifying the player addition
    assertEquals(1 + totalPlayerBefore, ms2TestWorld.getAllPlayerNames().size());
    assertEquals("Player type: Human Player\n"
            + "Player's Name: human1 \n"
            + "Player's limit: 10, can still carry: 10\n"
            + "Carrying: [] \n"
            + "Current Room: Armory (**Dr.Lucky**(Doctor Lucky HP=50) is in this #0 room.)\n"
            + "#0 Room: Armory, has items: [Revolver(Damage=3)]\n"
            + "Neighbor Rooms: Billiard Room, Dining Hall, Drawing Room\n",
        ms2TestWorld.getOnePlayerAndRoomInfo("human1"));
  }

  /**
   * Tests the "Move" command for a player, which is expected to be successful.
   */
  @Test
  public void testCmdPlayerMoveSuccess() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
    ms2TestWorld.cmdPlayerMove("Billiard Room");
    assertEquals("Billiard Room",
        ms2TestWorld.getOnePlayerCurrentRoomName("TestPlayer"));
  }

  /**
   * Tests the "Move" command for a player, which fails because the target room is not a neighbor.
   */
  @Test(expected = IllegalAccessException.class)
  public void testCmdPlayerMoveFailedNonNeighbor() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
    ms2TestWorld.cmdPlayerMove("Parlor");
  }

  /**
   * Tests the "Move" command for a player, which fails because the target room does not exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCmdPlayerMoveFailedRoomNotExist() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
    ms2TestWorld.cmdPlayerMove("NotExistRoomName");
  }


  /**
   * Tests the "Pick" command for a player, which is expected to be successful.
   */
  @Test
  public void testCmdPlayerPickSuccess() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 18, false, 10);
    ms2TestWorld.cmdPlayerPick("Duck Decoy");
    String playerRes = ms2TestWorld.getOnePlayerAndRoomInfo("TestPlayer");
    assertEquals("Player type: Human Player\n"
        + "Player's Name: TestPlayer \n"
        + "Player's limit: 10, can still carry: 9\n"
        + "Carrying: [Duck Decoy(Damage=3)] \n"
        + "Current Room: Trophy Room\n"
        + "#18 Room: Trophy Room, has items: [Monkey Hand(Damage=2)]\n"
        + "Neighbor Rooms: Tennessee Room, Billiard Room, Dining Hall, Library\n", playerRes);
  }

  /**
   * Tests the "Pick" command for a player, which fails because the item is not in the room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCmdPlayerPickFailedItemNotInRoom() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
    ms2TestWorld.cmdPlayerPick("Bad Cream");


  }

  /**
   * Tests the "Pick" command for a player, which fails because the item does not exist in the
   * world.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCmdPlayerPickFailedItemNotInWorld() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
    ms2TestWorld.cmdPlayerPick("InvalidItemName");


  }

  /**
   * Tests the "Pick" command for a player, which fails because the item name is null.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCmdPlayerPickFailedNullItem() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 0, false, 10);
    ms2TestWorld.cmdPlayerPick("");


  }

  /**
   * Tests the "Pick" command for a player, which fails because the player has reached the carrying
   * limit.
   */
  @Test(expected = IllegalAccessException.class)
  public void testCmdPlayerPickFailedOverLimit() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("TestPlayer", 19, false, 1);
    ms2TestWorld.cmdPlayerPick("Rat Poison");
    ms2TestWorld.cmdPlayerPick("Piece of Rope");


  }

  /**
   * This method test the computer logic would always first pick items in the room if they have
   * capacity to pick. After pick item they are carrying the item Shoe Horn, and the item would no
   * longer in the same room.
   *
   * @throws IllegalAccessException Throw IllegalAccessException from the model.
   */
  @Test
  public void testCmdComputerActionPick() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("Com1", 12, true, 1);
    assertEquals("#12 Room: Master Suite, has items: [Shoe Horn(Damage=2)]",
        ms2TestWorld.getPlayerWhatCanPickInfo("Com1"));
    ms2TestWorld.cmdComputerPlayerAction();
    assertEquals("Player type: **Computer Player**\n"
            + "Player's Name: Com1 \n"
            + "Player's limit: 1, can still carry: 0\n"
            + "Carrying: [Shoe Horn(Damage=2)] \n"
            + "Current Room: Master Suite\n"
            + "#12 Room: Master Suite, has items: []\n"
            + "Neighbor Rooms: Tennessee Room, Library, Lilac Room, Nursery\n",
        ms2TestWorld.getOnePlayerAndRoomInfo("Com1"));
    assertEquals(null, ms2TestWorld.getPlayerWhatCanPickInfo("Com1"));


  }

  /**
   * This method tests the computer logic when picking items from the room. The computer player,
   * Com1 is given a capacity to carry items and is placed in the room #18, Trophy Room, which
   * containsitems: [Duck Decoy(Damage=3), Monkey Hand(Damage=2)]. The test verifies computer
   * player correctly chooses to pick the item with the highest damage value, in this case,
   * Duck Decoy (Damage=3).
   *
   * @throws IllegalAccessException if there is an illegal access exception
   */
  @Test
  public void testCmdComputerActionPickHighestDamage() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("Com1", 18, true, 1);
    assertEquals("#18 Room: Trophy Room, has items: [Duck Decoy(Damage=3), "
        + "Monkey Hand(Damage=2)]", ms2TestWorld.getPlayerWhatCanPickInfo("Com1"));
    ms2TestWorld.cmdComputerPlayerAction();
    assertEquals("Player type: **Computer Player**\n"
            + "Player's Name: Com1 \n"
            + "Player's limit: 1, can still carry: 0\n"
            + "Carrying: [Duck Decoy(Damage=3)] \n"
            + "Current Room: Trophy Room\n"
            + "#18 Room: Trophy Room, has items: [Monkey Hand(Damage=2)]\n"
            + "Neighbor Rooms: Tennessee Room, Billiard Room, Dining Hall, Library\n",
        ms2TestWorld.getOnePlayerAndRoomInfo("Com1"));
    assertEquals("#18 Room: Trophy Room, has items: [Monkey Hand(Damage=2)]",
        ms2TestWorld.getPlayerWhatCanPickInfo("Com1"));


  }

  /**
   * This item test the computer player move due to no item in the room can be picked. It checked
   * the room what can be picked info, it is none and then chose to move to next room. It will move
   * randomly, so there are two possible moved rooms both are checked with the if statement.
   *
   * @throws IllegalAccessException Throw IllegalAccessException from the model.
   */
  @Test
  public void testCmdComputerActionMoveDueToNoItemCanPick() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("Com1", 20, true, 1);
    assertEquals(null, ms2TestWorld.getPlayerWhatCanPickInfo("Com1"));
    String actionResult = ms2TestWorld.cmdComputerPlayerAction();
    if (actionResult.contains("Piazza")) {
      assertEquals("**Computer player**: Com1 MOVE to room: Piazza.\n",
          actionResult);
    } else {
      assertEquals("**Computer player**: Com1 MOVE to room: Carriage House.\n",
          actionResult);
    }


  }


  /**
   * This item test the computer player move due to no item in the room can be picked. It checked
   * the room what can be picked info, it is none and then chose to move to next room. It will move
   * randomly, so there are two possible moved rooms both are checked with the if statement.
   *
   * @throws IllegalAccessException Throw IllegalAccessException from the model.
   */
  @Test()
  public void testCmdComputerActionMoveDueToOverLimit() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("Com1", 7, true, 1);
    String actionResult1 = ms2TestWorld.cmdComputerPlayerAction();
    assertEquals("**Computer player**: Com1 PICK up Loud Noise with 2 damage.\n",
        actionResult1);

    String actionResult2 = ms2TestWorld.cmdComputerPlayerAction();
    if (actionResult2.contains("Piazza")) {
      assertEquals("**Computer player**: Com1 MOVE to room: Piazza.\n",
          actionResult2);
    } else {
      assertEquals("**Computer player**: Com1 MOVE to room: Green House.\n",
          actionResult2);
    }


  }

  /**
   * This would test the computer player moved to next room due to carrying items full. It first
   * picked item in 13 Nursery with Bad Cream and reaching carrying limit, then move to the
   * next room randomly. The if statement would catch and test both final room is valid.
   *
   * @throws IllegalAccessException Throw IllegalAccessException from the model.
   */
  @Test
  public void testCmdComputerActionMoveDueToLimitFull() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("Com1", 13, true, 1);
    assertEquals("**Computer player**: Com1 PICK up Bad Cream with 2 damage.\n",
        ms2TestWorld.cmdComputerPlayerAction());
    String actionResult = ms2TestWorld.cmdComputerPlayerAction();
    if (actionResult.contains("Library")) {
      assertEquals("**Computer player**: Com1 MOVE to room: Library.\n", actionResult);
    } else {
      assertEquals("**Computer player**: Com1 MOVE to room: Master Suite.\n",
          actionResult);
    }


  }

  /**
   * This test verifies that the game implementation properly enforces each player to take one
   * action
   * at a time in a turn-based manner. Two players, "player1" and "player2," are added to the game.
   * The test ensures that after "player1" takes an action (LOOK, MOVE, or PICK), it is "player2's"
   * turn to perform the next action. This sequence is repeated with a combination of actions for
   * "player1" and "player2" to validate the turn-based nature of the game.
   *
   * @throws Exception if there's an exception during the test execution.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerChangeTurnAfterOneAction() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("player1", 19, false, 1);
    ms2TestWorld.addOnePlayer("player2", 15, false, 1);

    // player1 LOOK then player2 action
    ms2TestWorld.cmdPlayerLook();
    assertEquals("player2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();

    // player1 MOVE then player2 action
    ms2TestWorld.cmdPlayerMove("Kitchen");
    assertEquals("player2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();

    // player1 PICK then player2 action
    ms2TestWorld.cmdPlayerPick("Sharp Kinfe");
    assertEquals("player2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();


  }

  /**
   * This tests that the game implementation correctly alternates turns between players in
   * the order they were added. Here, three computer players, "computer1," "computer2," and
   * "computer3," are added. The test ensures that after the first computer player ("computer1")
   * takes an action, it is the next player's turn ("computer2") to perform the next action.
   * This sequence is repeated to validate that the game maintains correct order of player turns.
   * All players are Computer players.
   *
   * @throws Exception if there's an exception during the test execution.
   */
  @Test
  public void testPlayerChangeTurnOrderCorrect3() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("computer1", 19, true, 1);
    ms2TestWorld.addOnePlayer("computer2", 15, true, 1);
    ms2TestWorld.addOnePlayer("computer3", 15, true, 1);

    // The first computer player ("computer1") takes an action,
    assertEquals("computer1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    // then it's "computer2's" turn, 2 take action
    assertEquals("computer2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    //then it's "computer3's" turn, 3 take action
    assertEquals("computer3", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    // Then back to 1's turn, 1 take action
    assertEquals("computer1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    //Then 2's turn, after 2 is 3
    assertEquals("computer2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();
    assertEquals("computer3", ms2TestWorld.getCurrentPlayerName());


  }

  /**
   * This tests that two Human players, "player1" and "player2," alternate taking turns over four
   * actions. The test ensures that the players' turns are correctly alternated between the two.
   *
   * @throws Exception if there's an exception during the test execution.
   */
  @Test
  public void testTwoPlayersAlternateTurns4Actions() throws IllegalAccessException {
    // Add two players, "player1" and "player2."
    ms2TestWorld.addOnePlayer("player1", 0, false, 1);
    ms2TestWorld.addOnePlayer("player2", 14, false, 1);

    // Player1's turn, take the first action (e.g., LOOK).
    assertEquals("player1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();

    // Player2's turn, they take the second action (e.g., MOVE).
    assertEquals("player2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerMove("Kitchen");

    // Player1's turn, they take the third action (e.g., PICK).
    assertEquals("player1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerPick("Revolver");

    // Player2's turn, they take the fourth action (e.g., LOOK).
    assertEquals("player2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();


  }

  /**
   * This test ensures that the game ends when the maximum number of turns has been reached.
   * In this scenario, 3 computer players, "computer1," "computer2," and "computer3," are added.
   * The test allows each computer player to take turns until the maximum turn (9) is reached.
   * After the maximum turn limit is reached, any additional action attempts result in the game
   * ending.
   *
   * @throws Exception if there's an exception during the test execution.
   */
  @Test(expected = IllegalStateException.class)
  public void testGameOverMaxTurnComputerPlayer() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("computer1", 19, true, 1);
    ms2TestWorld.addOnePlayer("computer2", 15, true, 1);
    ms2TestWorld.addOnePlayer("computer3", 15, true, 1);

    // The first computer player ("computer1") takes an action,
    assertEquals("computer1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    // then it's "computer2's" turn, 2 takes an action
    assertEquals("computer2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    // then it's "computer3's" turn, 3 takes an action
    assertEquals("computer3", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    // Then back to 1's turn, 1 takes an action
    assertEquals("computer1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();

    // Then 2's turn, after 2, it's 3's turn
    assertEquals("computer2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdComputerPlayerAction();
    assertEquals("computer3", ms2TestWorld.getCurrentPlayerName());

    // Turns 6-9:
    ms2TestWorld.cmdComputerPlayerAction(); // turn 6
    ms2TestWorld.cmdComputerPlayerAction(); // turn 7
    ms2TestWorld.cmdComputerPlayerAction(); // turn 8

    // The total number of turns reaches the maximum limit (9).
    // After this, any additional action attempt should result in the game ending.
    assertEquals(ms2TestWorld.getTotalAllowedTurns(), ms2TestWorld.getCurrentTurnNumber());

    // Attempting one more action after reaching the maximum turn limit should end the game.
    ms2TestWorld.cmdComputerPlayerAction(); // turn 9

    // Verify that the game has ended.
    assertEquals(true, ms2TestWorld.checkGameOver());

    // Attempting one more action after the game has ended should throw a game over error.
    ms2TestWorld.cmdComputerPlayerAction(); // one more move (turn 10) would cause a Game Over
    // message.


  }

  /**
   * This test ensures that the game ends when the maximum number of turns has been reached.
   * In this scenario, three human players, "player1," "player2," and "player3," are added.
   * The test allows each player to take multiple turns until the maximum limit (9) is reached.
   * After the maximum turn limit is reached, any additional action attempts result in the game
   * ending.
   *
   * @throws Exception if there's an exception during the test execution.
   */
  @Test(expected = IllegalStateException.class)
  public void testGameOverMaxTurnHumanPlayerLook() {
    ms2TestWorld.addOnePlayer("player1", 19, false, 1);
    ms2TestWorld.addOnePlayer("player2", 15, false, 1);
    ms2TestWorld.addOnePlayer("player3", 15, false, 1);

    // The first human player ("player1") takes an action (LOOK).
    assertEquals("player1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();

    // Then it's "player2's" turn, 2 takes an action (LOOK).
    assertEquals("player2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();

    // Then it's "player3's" turn, 3 takes an action (LOOK).
    assertEquals("player3", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();

    // Then back to 1's turn, 1 takes an action (LOOK).
    assertEquals("player1", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();

    // Then 2's turn, after 2, it's 3's turn, and the cycle continues.
    assertEquals("player2", ms2TestWorld.getCurrentPlayerName());
    ms2TestWorld.cmdPlayerLook();
    assertEquals("player3", ms2TestWorld.getCurrentPlayerName());

    // Turns 6-9 (LOOK actions):
    ms2TestWorld.cmdPlayerLook(); // turn 6
    ms2TestWorld.cmdPlayerLook(); // turn 7
    ms2TestWorld.cmdPlayerLook(); // turn 8

    // The total number of turns reaches the maximum limit (9).
    // After this, any additional action attempt should result in the game ending.
    assertEquals(ms2TestWorld.getTotalAllowedTurns(), ms2TestWorld.getCurrentTurnNumber());

    // Attempting one more action after reaching the maximum turn limit should end the game.
    ms2TestWorld.cmdPlayerLook(); // turn 9

    // Verify that the game has ended.
    assertEquals(true, ms2TestWorld.checkGameOver());

    // Attempting one more action after the game has ended should throw a game over error.
    ms2TestWorld.cmdPlayerLook(); // one more move (turn 10) would cause a Game Over message.


  }


}
