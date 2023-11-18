import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
public class Ms2Ms3WorldImplementTest {

  private WorldImplement ms2TestWorld;

  /**
   * Sets up the test environment by creating a test world with specific properties.
   */
  @Before
  public void setUp() throws FileNotFoundException {
    FileReader fileReader = new FileReader("res/testRes/mansion2023Pet.txt");
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
        + "#4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
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
        + "Carrying: [Letter Opener(Damage=77)] \n"
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
    assertEquals("You (player: human1) are currently in room "
        + "#3 Dining Hall and neighboring rooms: "
        + "[Armory, Tennessee Room, Billiard Room, Trophy Room, Wine Cellar, Drawing Room, "
        + "Kitchen, Parlor]\n"
        + "Your current #3 Room: Dining Hall, has items: []\n"
        + "Other players in the same room: computer2, \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "**Invisible inside/out Room** Pet(Fortune Cat Pet) in this room: Armory\n"
        + "2. Neighbor:\n"
        + "#17 Room: Tennessee Room, has items: []\n"
        + "Players in Tennessee Room: \n"
        + "3. Neighbor:\n"
        + "#1 Room: Billiard Room, has items: [Billiard Cue(Damage=3)]\n"
        + "Players in Billiard Room: \n"
        + "4. Neighbor:\n"
        + "#18 Room: Trophy Room, has items: [Duck Decoy(Damage=3), Monkey Hand(Damage=2)]\n"
        + "Players in Trophy Room: \n"
        + "5. Neighbor:\n"
        + "#19 Room: Wine Cellar, has items: [Rat Poison(Damage=2), Piece of Rope(Damage=2)]\n"
        + "Players in Wine Cellar: \n"
        + "6. Neighbor:\n"
        + "#4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
        + "Players in Drawing Room: \n"
        + "7. Neighbor:\n"
        + "#8 Room: Kitchen, has items: [Crepe Pan(Damage=3), Sharp Knife(Damage=3)]\n"
        + "Players in Kitchen: \n"
        + "8. Neighbor:\n"
        + "#14 Room: Parlor, has items: []\n"
        + "Players in Parlor: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------\n", human1LookRes);
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
    assertEquals("You (player: human1) are currently in room "
        + "#2 Carriage House and neighboring rooms: "
        + "[Winter Garden]\n"
        + "Your current #2 Room: Carriage House, has items: [Chain Saw(Damage=88), Big Red "
        + "Hammer(Damage=4)]\n"
        + "Other players in the same room: computer2, \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#20 Room: Winter Garden, has items: []\n"
        + "Players in Winter Garden: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------\n", human1LookRes);
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
    assertEquals("You (player: human1) are currently in room "
        + "#15 Piazza and neighboring rooms: [Winter "
        + "Garden, Foyer, Hedge Maze]\n"
        + "Your current #15 Room: Piazza, has items: [Civil War Cannon(Damage=3)]\n"
        + "Other players in the same room: computer2, \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#20 Room: Winter Garden, has items: []\n"
        + "Players in Winter Garden: \n"
        + "2. Neighbor:\n"
        + "#5 Room: Foyer, has items: []\n"
        + "Players in Foyer: \n"
        + "3. Neighbor:\n"
        + "#7 Room: Hedge Maze, has items: [Loud Noise(Damage=2)]\n"
        + "Players in Hedge Maze: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------\n", human1LookRes);
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
    assertEquals("You (player: human1) are currently in room "
        + "#15 Piazza and neighboring rooms: [Winter "
        + "Garden, Foyer, Hedge Maze]\n"
        + "Your current #15 Room: Piazza, has items: [Civil War Cannon(Damage=3)]\n"
        + "Other players in the same room: computer2, \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#20 Room: Winter Garden, has items: []\n"
        + "Players in Winter Garden: \n"
        + "2. Neighbor:\n"
        + "#5 Room: Foyer, has items: []\n"
        + "Players in Foyer: \n"
        + "3. Neighbor:\n"
        + "#7 Room: Hedge Maze, has items: [Loud Noise(Damage=2)]\n"
        + "Players in Hedge Maze: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------\n", human1LookRes);
  }

  /**
   * Tests the "Look" command when there are no other players in the room.
   */
  @Test
  public void testCmdLookWithDrLuckyAppeared() {
    ms2TestWorld.addOnePlayer("human1", 0, false, 10);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals("You (player: human1) are currently in room #0 Armory "
        + "and neighboring rooms: [Billiard "
        + "Room, Dining Hall, Drawing Room]\n"
        + "Your current #0 Room: Armory, has items: [Revolver(Damage=99)]\n"
        + "**Dr. Lucky is in your room(Armory): Target name: Doctor Lucky, Current HP: 50, "
        + "Current room index: 0\n"
        + "**Pet(Fortune Cat Pet) in this room(Armory).\n"
        + "Other players in the same room: \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#1 Room: Billiard Room, has items: [Billiard Cue(Damage=3)]\n"
        + "Players in Billiard Room: \n"
        + "2. Neighbor:\n"
        + "#3 Room: Dining Hall, has items: []\n"
        + "Players in Dining Hall: \n"
        + "3. Neighbor:\n"
        + "#4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
        + "Players in Drawing Room: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------\n", human1LookRes);
  }

  /**
   * Tests the "Look" command when there are no other players in the room.
   */
  @Test
  public void testCmdLookWithNoOtherPlayer() {
    ms2TestWorld.addOnePlayer("human1", 2, false, 10);
    String human1LookRes = ms2TestWorld.cmdPlayerLook();
    assertNotNull(human1LookRes);
    assertEquals("You (player: human1) are currently in room #2 Carriage House "
            + "and neighboring "
            + "rooms: [Winter Garden]\n"
            + "Your current #2 Room: Carriage House, has items: [Chain Saw(Damage=88), Big Red "
            + "Hammer"
            + "(Damage=4)]\n"
            + "Other players in the same room: \n"
            + "Neighboring room info "
            + "begin:----------------------------------------------------------\n"
            + "1. Neighbor:\n"
            + "#20 Room: Winter Garden, has items: []\n"
            + "Players in Winter Garden: \n"
            + "Neighboring room info "
            + "end:------------------------------------------------------------\n",
        human1LookRes);

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
    assertEquals("You (player: human1) are currently in room #2 Carriage House and "
            + "neighboring "
            + "rooms: [Winter Garden]\n"
            + "Your current #2 Room: Carriage House, has items: [Chain Saw(Damage=88), Big Red "
            + "Hammer"
            + "(Damage=4)]\n"
            + "Other players in the same room: human2, \n"
            + "Neighboring room "
            + "info begin:----------------------------------------------------------\n"
            + "1. Neighbor:\n"
            + "#20 Room: Winter Garden, has items: []\n"
            + "Players in Winter Garden: \n"
            + "Neighboring room info "
            + "end:------------------------------------------------------------\n",
        human1LookRes);

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
    assertEquals("You (player: human1) are currently in room "
            + "#2 Carriage House and neighboring rooms: "
            + "[Winter Garden]\n"
            + "Your current #2 Room: Carriage House, has items: [Chain Saw(Damage=88), Big Red "
            + "Hammer(Damage=4)]\n"
            + "Other players in the same room: human2, human3, \n"
            + "Neighboring room info "
            + "begin:----------------------------------------------------------\n"
            + "1. Neighbor:\n"
            + "#20 Room: Winter Garden, has items: []\n"
            + "Players in Winter Garden: \n"
            + "Neighboring room info "
            + "end:------------------------------------------------------------\n",
        human1LookRes);

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
            + "Current Room: Armory (**Dr.Lucky**(Doctor Lucky HP=50) is in this #0 room.)(**Pet**"
            + "(Fortune Cat Pet) in this room.)\n"
            + "#0 Room: Armory, has items: [Revolver(Damage=99)]\n"
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
    assertNull(ms2TestWorld.getPlayerWhatCanPickInfo("Com1"));


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
    assertNull(ms2TestWorld.getPlayerWhatCanPickInfo("Com1"));
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
    assertTrue(ms2TestWorld.checkGameOver());

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
    assertTrue(ms2TestWorld.checkGameOver());

    // Attempting one more action after the game has ended should throw a game over error.
    ms2TestWorld.cmdPlayerLook(); // one more move (turn 10) would cause a Game Over message.

  }

  @Test
  public void testPetDfsMoveStartWithPlayerMovePet() throws IllegalAccessException {

    ms2TestWorld.addOnePlayer("human0", 0, false, 10);
    ms2TestWorld.addOnePlayer("human1", 1, true, 11);
    assertEquals(0, ms2TestWorld.getPetRoomNumber()); // pet in room 0 same as human0
    ms2TestWorld.cmdPetMove("Billiard Room"); // Confirm pet moved by player
    String petDfsMoveResult = ms2TestWorld.getPetDfsMoveResult();
    assertEquals(1, ms2TestWorld.getPetRoomNumber()); // pet in room 1 same as human0
    assertEquals("human1", ms2TestWorld.getCurrentPlayerName());
    // current player human 1 cannot been seen since turn change, and human 1 has pet buffer.
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen()); // human1 cannot be seen.
    //The above code check and make sure Pet move executed correctly.
    assertTrue(petDfsMoveResult.contains("1. Pet moved from (Room#1)Billiard Room to (Room#0)"
        + "Armory, now pet in (Room#0)Armory\n"
        + "2. Pet moved from (Room#0)Armory to (Room#3)Dining Hall, now pet in (Room#3)Dining "
        + "Hall\n"
        + "3. Pet moved from (Room#3)Dining Hall to (Room#17)Tennessee Room, now pet in (Room#17)"
        + "Tennessee Room\n"
        + "4. Pet moved from (Room#17)Tennessee Room to (Room#18)Trophy Room, now pet in (Room#18)"
        + "Trophy Room\n"
        + "5. Pet moved from (Room#18)Trophy Room to (Room#10)Library, now pet in (Room#10)"
        + "Library\n"
        + "6. Pet moved from (Room#10)Library to (Room#12)Master Suite, now pet in (Room#12)Master "
        + "Suite\n"
        + "7. Pet moved from (Room#12)Master Suite to (Room#11)Lilac Room, now pet in (Room#11)"
        + "Lilac Room\n"
        + "8. Pet moved from (Room#11)Lilac Room to (Room#16)Servants' Quarters, now pet in "
        + "(Room#16)Servants' Quarters\n"
        + "9. Pet moved from (Room#16)Servants' Quarters to (Room#9)Lancaster Room, now pet in "
        + "(Room#9)Lancaster Room\n"
        + "10. Pet moved from (Room#9)Lancaster Room to (Room#14)Parlor, now pet in (Room#14)"
        + "Parlor\n"
        + "11. Pet moved from (Room#14)Parlor to (Room#8)Kitchen, now pet in (Room#8)Kitchen\n"
        + "12. Pet moved from (Room#8)Kitchen to (Room#19)Wine Cellar, now pet in (Room#19)Wine "
        + "Cellar\n"
        + "13. Pet moved from (Room#19)Wine Cellar to (Room#4)Drawing Room, now pet in (Room#4)"
        + "Drawing Room\n"
        + "14. Pet moved from (Room#4)Drawing Room to (Room#5)Foyer, now pet in (Room#5)Foyer\n"
        + "15. Pet moved from (Room#5)Foyer to (Room#15)Piazza, now pet in (Room#15)Piazza\n"
        + "16. Pet moved from (Room#15)Piazza to (Room#20)Winter Garden, now pet in (Room#20)Winter"
        + " Garden\n"
        + "17. Pet moved from (Room#20)Winter Garden to (Room#2)Carriage House, now pet in (Room#2)"
        + "Carriage House\n"
        + "18. Pet moved from (Room#2)Carriage House to (Room#7)Hedge Maze, now pet in (Room#7)"
        + "Hedge Maze\n"
        + "19. Pet moved from (Room#7)Hedge Maze to (Room#6)Green House, now pet in (Room#6)Green "
        + "House\n"
        + "20. Pet moved from (Room#6)Green House to (Room#13)Nursery, now pet in (Room#13)"
        + "Nursery"));
  }

  @Test
  public void testPetDfsMoveStartWhenReachingEnd() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("p1", 20, false, 10);
    ms2TestWorld.addOnePlayer("p2", 2, false, 10);
    String petDfsMoveResult = ms2TestWorld.getPetDfsMoveResult(); // recording DFS move result
    ms2TestWorld.cmdPetMove("Winter Garden"); // move pet to neighbor room 20

    //check player can be seen by each other.
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen());
    ms2TestWorld.moveDrLucky(); // move dr to the testing room
    String res = ms2TestWorld.cmdPlayerKill("poking");
    assertNull(res);
    //check drLucky's hp is still 50 at full thus attack failed
    assertEquals(50, ms2TestWorld.getDrLuckyHp());

    //check pet is in effect, player in room 20 human1 cannot be seen due to pet in.
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen());
    assertEquals(20, ms2TestWorld.getPetRoomNumber());
    assertTrue(petDfsMoveResult.contains("20. Pet moved from (Room#10)Library to (Room#13)Nursery, "
        + "now pet in (Room#13)Nursery\n"));
    assertTrue(petDfsMoveResult.contains("1. Pet moved from (Room#0)Armory to (Room#1)Billiard "
        + "Room, now pet in (Room#1)Billiard Room\n"
        + "2. Pet moved from (Room#1)Billiard Room to (Room#18)Trophy Room, now pet in (Room#18)"
        + "Trophy Room\n"));

  }

  /**
   * Tests two players in same room can be seen without pet.
   */
  @Test
  public void testPlayerSeenSameRoomNoPet() {
    ms2TestWorld.addOnePlayer("human1", 11, false, 10);
    ms2TestWorld.addOnePlayer("human2", 11, true, 11);
    assertEquals(0, ms2TestWorld.getPetRoomNumber()); // pet in room 0 not 11
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen());
  }

  /**
   * Tests two players in same room can be seen even with pet.
   */
  @Test
  public void testPlayerSeenSameRoomWithPet() {
    ms2TestWorld.addOnePlayer("human1", 0, false, 10);
    ms2TestWorld.addOnePlayer("human2", 0, true, 11);
    assertEquals(0, ms2TestWorld.getPetRoomNumber()); // pet in room 0 same as players
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen());
  }

  /**
   * Tests two players in two neighbor room with pet.
   * The one with pet cannot been seen from the outside.
   * In this case human0 have the pet, thus its room cannot been seen from outside.
   */
  @Test
  public void testPlayerNotBeSeenWithPet1() {
    ms2TestWorld.addOnePlayer("human0", 0, false, 10);
    ms2TestWorld.addOnePlayer("human1", 1, true, 11);
    assertEquals(0, ms2TestWorld.getPetRoomNumber()); // pet in room 0 same as human0
    // current player: human0 cannot been seen, visibility check is false.
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen());
  }

  /**
   * Tests two players in two neighbor room with pet.
   * The one with pet cannot been seen from the outside.
   * In this case pet is moved to room#1 Billiard Room, thus player human0 can be seen again.
   * But player human1 cannot be seen.
   */
  @Test
  public void testPlayerNotBeSeenWithPet2() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human0", 0, false, 10);
    ms2TestWorld.addOnePlayer("human1", 1, true, 11);
    assertEquals(0, ms2TestWorld.getPetRoomNumber()); // pet in room 0 same as human0
    ms2TestWorld.cmdPetMove("Billiard Room"); // pet moved to room #1 & turn change
    assertEquals(1, ms2TestWorld.getPetRoomNumber()); // pet in room 1 same as human0
    assertEquals("human1", ms2TestWorld.getCurrentPlayerName());
    // current player human 1 cannot been seen since turn change, and human 1 has pet buffer.
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen()); // human1 cannot be seen.
  }

  /**
   * Tests two players in two neighbor room without pet can always be seen.
   * In this case human0 and human1 in two neighbor room 11 & 12, and pet are away.
   * Thus, the two players can always be seen.
   */
  @Test
  public void testPlayerBeSeenNeighborNoPet() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human0", 11, false, 10);
    ms2TestWorld.addOnePlayer("human1", 12, true, 11);
    assertEquals(0, ms2TestWorld.getPetRoomNumber()); // pet in room0, away from 2 players
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen()); // human0 can be seen.
    ms2TestWorld.cmdPetMove("Foyer"); //move pet change turn, change player
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen()); // human1 can be seen.
  }

  /**
   * Test case for validating the behavior of player attacks with an item in a game scenario.
   * Scenario:
   * - Two players, "human1" and "human2," are present in the game world.
   * - Both players have a "Billiard Cue" item, which is used for attacking.
   * - The initial health of the players and Dr. Lucky is set.
   * - The first player attempts to attack Dr. Lucky using the "Billiard Cue,"
   * - resulting in a successful attack.
   * - Dr. Lucky's health is reduced, and he moves to the next room.
   * - The second player attempts to use the "Billiard Cue" for a second time,
   * triggering an expected IllegalAccessException.
   * <p>
   * Expected Outcome:
   * - The first attack is expected to be successful, reducing Dr. Lucky's health.
   * - Dr. Lucky moves to the next room.
   * - The second player's attempt to use the "Billiard Cue" for the second time is expected to
   * throw an IllegalAccessException, as per the game rules that prohibit using the same item
   * twice.
   */
  @Test(expected = IllegalAccessException.class)
  public void testPlayerUsedItemAttackFail() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human1", 1, false, 10);
    ms2TestWorld.addOnePlayer("human2", 5, false, 10);
    ms2TestWorld.cmdPlayerPick("Billiard Cue");
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen()); //check cannot be seen.
    String cmdResult = ms2TestWorld.cmdPlayerKill("Billiard Cue");
    assertTrue(cmdResult.contains("Player(human1) attack Dr.Lucky SUCCESS with item: "
        + "Billiard Cue(Damage=3)\n"
        + "Dr.Lucky(Doctor Lucky) was attacked by hp:-3\n"
        + "Dr.Lucky(Doctor Lucky) Current HP=47")); // 1st attack success
    assertEquals(47, ms2TestWorld.getDrLuckyHp()); // check drLucky hp reduced by -3 from 50
    ms2TestWorld.moveDrLucky(); // move Dr lucky to next room
    ms2TestWorld.moveDrLucky();
    ms2TestWorld.cmdPlayerLook();
    // When 2nd player use the same item it will throw an exception error
    // Due to the rule, no item can be used for second time.
    ms2TestWorld.cmdPlayerKill("Billiard Cue");
  }

  /**
   * Test case for when DrLucky is not in the room with the player, they player not suppose to
   * execute attack command, if its attacked, it will throw an Exception error.
   * In following case, player try poking but target not in room, it will fail.
   */
  @Test(expected = IllegalAccessException.class)
  public void testPlayerAttackFailNoDrLuckyInRoom() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human1", 11, false, 10);
    ms2TestWorld.cmdPlayerKill("poking");
  }

  /**
   * Test case for attack fail when 2 players in the same room with no pet.
   * In following case, 2 players in same room #2, human1 try attack but failed, return null string.
   * Validated the attack failed with Dr. hp remain unchanged.
   */
  @Test
  public void testPlayerAttackFailOtherPlayerInSameRoom() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human1", 2, false, 10);
    ms2TestWorld.addOnePlayer("human2", 2, false, 10);
    //current player are in the same room with human 1 & 2 thus attack failed.
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen());
    ms2TestWorld.moveDrLucky();
    ms2TestWorld.moveDrLucky(); // move dr to the testing room
    String res = ms2TestWorld.cmdPlayerKill("poking");
    //System.out.println(res);
    assertNull(res);
    //check drLucky's hp is still 50 at full thus attack failed
    assertEquals(50, ms2TestWorld.getDrLuckyHp());
  }

  /**
   * Test case for when 2 players in neighbor room, with no pet, attack will fail due to be seen.
   * In Dr Lucky's mansion room 2 & 20 are neighbors.
   * In following case, player try poking target in room, it will fail, return null string.
   * Validated the attack failed with Dr. hp remain unchanged.
   */
  @Test
  public void testPlayerAttackFailOtherPlayerInNeighborNoPet() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human1", 2, false, 10);
    ms2TestWorld.addOnePlayer("human2", 20, false, 10);
    //check player can be seen by each other.
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen());
    ms2TestWorld.moveDrLucky();
    ms2TestWorld.moveDrLucky(); // move dr to the testing room
    String res = ms2TestWorld.cmdPlayerKill("poking");
    //System.out.println(res);
    assertNull(res);
    //check drLucky's hp is still 50 at full thus attack failed
    assertEquals(50, ms2TestWorld.getDrLuckyHp());
  }

  /**
   * Test case for when 2 players in neighbor room, with pet in other room , attack fail due to
   * be seen from room 20 where pet in there but inside can see others outside.
   * In Dr Lucky's mansion room 2 & 20 are neighbors, pet is in other room.(20)
   * In following case, player try poking target in room, it will fail, return null string.
   * Validated the attack failed with Dr. hp remain unchanged.
   * Validate Pet in effect.
   */
  @Test
  public void testPlayerAttackFailOtherPlayerInNeighborWithPet() throws IllegalAccessException {
    ms2TestWorld.addOnePlayer("human1", 20, false, 10);
    ms2TestWorld.addOnePlayer("human2", 2, false, 10);
    ms2TestWorld.cmdPetMove("Winter Garden"); // move pet to neighbor room 20
    //check player can be seen by each other.
    assertTrue(ms2TestWorld.checkCurPlayerCanBeSeen());
    ms2TestWorld.moveDrLucky(); // move dr to the testing room
    String res = ms2TestWorld.cmdPlayerKill("poking");
    //System.out.println(res);
    assertNull(res);
    //check drLucky's hp is still 50 at full thus attack failed
    assertEquals(50, ms2TestWorld.getDrLuckyHp());

    //check pet is in effect, player in room 20 human1 cannot be seen due to pet in.
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen());
    assertEquals(20, ms2TestWorld.getPetRoomNumber());
  }

  /**
   * Test case to validate a player's successful attack on Dr. Lucky when there is no pet present.
   * <p>
   * Scenario:
   * - Two players, "human1" and "human2," are in the game world.
   * - Player "human1" has a health level of 20, and Player "human2" has a "Poking" item with
   * - damage 1.
   * - The pet is moved to a faraway location using the "Winter Garden" command.
   * - Dr. Lucky's initial health is verified to be 50.
   * - Players cannot see each other initially.
   * - Player "human2" successfully attacks Dr. Lucky with the "Poking" item, resulting in Dr.
   * Lucky's health reduction.
   * <p>
   * Expected Outcome:
   * - Dr. Lucky's health is reduced by the damage caused by the successful attack.
   * - The attack message indicates success, and Dr. Lucky's updated health is provided.
   *
   * @throws IllegalAccessException if there is an issue with the player's attack.
   */
  @Test
  public void testPlayerAttackSuccessWithNoPet() throws IllegalAccessException {
    // Setup: Create two players, set initial conditions, and move the pet to a faraway location.
    ms2TestWorld.addOnePlayer("human1", 20, false, 10);
    ms2TestWorld.addOnePlayer("human2", 1, false, 10);
    ms2TestWorld.cmdPetMove("Winter Garden"); // move pet to far away

    // Assertion: Verify Dr. Lucky's initial health is 50.
    assertEquals(50, ms2TestWorld.getDrLuckyHp());

    // Assertion: Verify players cannot see each other initially.
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen());

    // Action: Attempt the player attack and verify the outcome.
    String res = ms2TestWorld.cmdPlayerKill("poking");

    // Assertion: Verify the success of the attack and Dr. Lucky's updated health.
    assertTrue(res.contains("Player(human2) attack Dr.Lucky SUCCESS with item: Poking(Damage=1)\n"
        + "Dr.Lucky(Doctor Lucky) HP: -1\n"
        + "Dr.Lucky(Doctor Lucky) Current HP=49\n"));
    assertEquals(49, ms2TestWorld.getDrLuckyHp()); // check dr lucky hp reduced
  }

  /**
   * This case test player can attempt attack Dr Lucky with other player in the neighbor room1,
   * and the current player have pet to block the visibility to attack success.
   *
   * @throws IllegalAccessException if there is an issue with the player's attack.
   */
  @Test
  public void testPlayerAttackSuccessWithPet() throws IllegalAccessException {
    // Setup: Create two players, set initial conditions, and move the pet to a faraway location.
    ms2TestWorld.addOnePlayer("human1", 0, false, 10);
    ms2TestWorld.addOnePlayer("human2", 1, false, 10);

    //check pet is present in room 0 for human1 player
    assertEquals(0, ms2TestWorld.getPetRoomNumber());
    // Assertion: Verify Dr. Lucky's initial health is 50.
    assertEquals(50, ms2TestWorld.getDrLuckyHp());

    // Assertion: Verify players cannot see each other initially.
    assertFalse(ms2TestWorld.checkCurPlayerCanBeSeen());

    // Action: Attempt the player attack and verify the outcome.
    String res = ms2TestWorld.cmdPlayerKill("poking");

    // Assertion: Verify the success of the attack and Dr. Lucky's updated health.
    assertTrue(res.contains("Player(human1) attack Dr.Lucky SUCCESS with item: Poking(Damage=1)\n"
        + "Dr.Lucky(Doctor Lucky) HP: -1\n"
        + "Dr.Lucky(Doctor Lucky) Current HP=49\n"));
    assertEquals(49, ms2TestWorld.getDrLuckyHp()); // check dr lucky hp reduced
  }
}
