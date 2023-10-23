import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.CmdControllerImplement;
import controller.Controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.NoSuchElementException;
import model.world.CreateWorldHelper;
import model.world.World;
import model.world.WorldImplement;
import org.junit.Before;
import org.junit.Test;

public class CmdControllerImplementTest {
  private World realWorld;
  private World mockWorld;
  private Appendable outGameLog;
  private StringBuilder outMockLog;

  @Before
  public void setup() throws IOException {
    // Create a new World instance for testing
    FileReader fileReader = new FileReader("res/testRes/mansion2023.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    this.realWorld = (WorldImplement) createHelper.createWorld();
    this.realWorld.setTotalAllowedPlayers(2);
    this.realWorld.setTotalAllowedTurns(4);
    this.outGameLog = new StringBuilder(); // Initialize gameLog with a StringBuilder
    this.outMockLog = new StringBuilder();
    this.mockWorld = new WorldMockModel(this.outMockLog);
  }

  /**
   * This method test the controller can start create a graph representation.
   * @throws IOException
   */
  @Test
  public void testCreateGameMapPngCommand() throws IOException {
    Readable input = new StringReader("0\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // can check the output contain create world map result!
    assertTrue(output.toString().contains("The world map png created in above directory.\n"));
  }

  /**
   * Test quit command can successfully quit.
   * @throws IOException
   */
  @Test
  public void testQuitCommandOrder66() throws IOException {
    Readable input = new StringReader("66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // can check the output contain quit command!
    assertTrue(output.toString().contains("Executed Order 66 to kill and eliminate ALL controller"
        + " program and Quit!\n"));
  }


  /**
   * This method test the controller can start successfully and enter welcome screen.
   * @throws IOException
   */
  @Test
  public void starGameWelcomeScreen() throws IOException {
    try {
      Readable input = new StringReader(" \n");
      Controller cmdController = new CmdControllerImplement(input, this.outGameLog, this.realWorld);
      cmdController.startGame();
    } catch (NoSuchElementException e){
      // End for waiting user input by catching no user input Exception error
      assertEquals("No line found", e.getMessage());
    }
    assertEquals("Welcome to the Game World of Doctor Lucky's Mansion:\n" +
        "(To quit: Ctrl + C) \n" +
        "Main Menu: \n" +
        "(Select following operation (integer only)!)\n" +
        "0-Create a graphical representation of the world map PNG.\n" +
        "1-Setup game by adding all 2 players.\n" +
        "2-Find a Room (Display information about specified room in the world).\n" +
        "3-Find a Player (Display information about specified player in the world).\n" +
        "4-Start game turns to play (Must setup all the players before play!)\n" +
        "66-Quit and kill the program by using Order 66.\n" +
        "Error: please only enter an integer.\n" +
        "The world map png created in above directory.\n" +
        "Enter 'm' or 'M' to go back to Main Menu: ", this.outGameLog.toString());
  }

  /**
   * Test check one room command return correct room info with one item in the room.
   * @throws IOException
   */
  @Test
  public void testCheckOneRoomInfoCommandWithOneItem() throws IOException {
    Readable input = new StringReader("2\n Armory\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    assertTrue(output.toString().contains("#0 Room: Armory, has items: [Revolver(Damage=3)]\n"));
  }

  /**
   * Test check one room command return correct room info with one item in the room.
   * @throws IOException
   */
  @Test
  public void testCheckOneRoomInfoCommandWithTwoItem() throws IOException {
    Readable input = new StringReader("2\n Carriage House\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
//    assertEquals("exp",output.toString());
    assertTrue(output.toString().contains("#2 Room: Carriage House, has items: [Chain Saw"
        +"(Damage=4), Big Red Hammer(Damage=4)]\n"));
  }

  /**
   * Test check one room command return correct room info.
   * This room contain other players and target, this case we checked room Armory.
   * @throws IOException
   */
  @Test
  public void testCheckOneRoomInfoCommandWithPlayersTargets() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n hu2\n 1\n 0\n n\n m\n 2\n Armory\n "
        + "m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Which room do you want to check? (Enter the exact room" +
        " name from above list.)\n" +
        "#0 Room: Armory, has items: [Revolver(Damage=3)]\n" +
        "DrLucky(Doctor Lucky HP:50) in this room.\n" +
        "Players in this room: hu1, hu2, \n" +
        "Room neighbors: Billiard Room,Dining Hall,Drawing Room,"));
  }

  /**
   * Test to add 2 human player success.
   * @throws IOException
   */
  @Test
  public void testAddTwoHumanPlayersSuccess() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n hu2\n 1\n 0\n n\n m\n 2\n Armory\n "
        + "m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("All 2 players added successfully! They are:\n" +
        "Player type: Human Player\n" +
        "Player's Name: hu1 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] \n" +
        "\n" +
        "Player type: Human Player\n" +
        "Player's Name: hu2 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] "));
  }
  /**
   * Test add human player & computer player mixed success.
   * @throws IOException
   */
  @Test
  public void testAddMixedHumanComputerPlayerSuccess() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n y\n hu2\n 1\n 0\n n\n m\n 2\n Armory\n "
        + "m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("All 2 players added successfully! They are:\n" +
        "Player type: **Computer Player**\n" +
        "Player's Name: hu1 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] \n" +
        "\n" +
        "Player type: Human Player\n" +
        "Player's Name: hu2 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] "));
  }

  /**
   * Test add 2 computer player mixed success.
   * @throws IOException
   */
  @Test
  public void testAddTwoComputerPlayerSuccess() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n y\n hu2\n 1\n 0\n y\n m\n 2\n Armory\n "
        + "m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("All 2 players added successfully! They are:\n" +
        "Player type: **Computer Player**\n" +
        "Player's Name: hu1 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] \n" +
        "\n" +
        "Player type: **Computer Player**\n" +
        "Player's Name: hu2 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] "));
  }

  /**
   * Test human player can move by 1 success with Move command.
   * @throws IOException
   */
  @Test
  public void testHumanPlayerMove1() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n" +
        "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
//    assertEquals("exp",output.toString());
    assertTrue(output.toString().contains("Turn #1 Current Player Status: \n" +
        "Player type: Human Player\n" +
        "Player's Name: hu1 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] \n" +
        "Current Room: Armory (**Dr.Lucky**(Doctor Lucky HP=50) is in this #0 room.)\n" +
        "#0 Room: Armory, has items: [Revolver(Damage=3)]\n" +
        "Neighbor Rooms: Billiard Room, Dining Hall, Drawing Room\n" +
        "Current Turn #1 for player: hu1. (Available commands: [Move, Look, Pick])\n" +
        "Reachable Rooms: [Billiard Room, Dining Hall, Drawing Room]\n" +
        "To Player: hu1, Which room do you want to move to?\n" +
        "Player: hu1 moved to room: Dining Hall SUCCESS!"));
  }

  /**
   * Test human player can move continuously in two turns.
   * @throws IOException
   */
  @Test
  public void testHumanPlayerMove2() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n" +
        "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Player: hu1 moved to room: Dining Hall SUCCESS!"));
    assertTrue(output.toString().contains("Player: hu1 moved to room: Kitchen SUCCESS!"));
  }

  /**
   * Test computer player can move 1 turn.
   * @throws IOException
   */
  @Test
  public void testComputerPlayerMove() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n" +
        "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #4 for player: com2. (Available commands:" +
        " [Move, Look, Pick])\n" +
        "**Computer player**: com2 is taking action...\n" +
        "**Computer player**: com2 MOVE to room: "));
  }

  /**
   * Test computer player can Pick Item when needed.
   * @throws IOException
   */
  @Test
  public void testComputerPlayerPick() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n" +
        "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result

    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #2 for player: com2. (Available commands:" +
        " [Move, Look, Pick])\n" +
        "**Computer player**: com2 is taking action...\n" +
        "**Computer player**: com2 PICK up Revolver with 3 damage.\n"));
  }

  /**
   * Test human player can Pick Item when needed.
   * @throws IOException
   */
  @Test
  public void testHumanPlayerPick() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n Pick\n " +
        "Revolver\n" + "move\n Dining Hall\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
//        assertEquals("exp",output.toString());

    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #1 for player: hu1. (Available commands: " +
        "[Move, Look, Pick])\n" +
        "You are in #0 Room: Armory, has items: [Revolver(Damage=3)]\n" +
        "(To Player) hu1: What do you want to pick? (Enter the exact name.):\n" +
        "Player PICK execute success!\n" +
        "Player: hu1 picked up item: Revolver SUCCESS!"));
  }

  /**
   * Test human player can use Look around command.
   * @throws IOException
   */
  @Test
  public void testPlayerLook() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n " +
        "Look\n move\n Dining Hall\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result

    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #1 for player: hu1. (Available commands: " +
        "[Move, Look, Pick])\n" +
        "You (player: hu1) are currently in room #0 Armory and can be seen from rooms: [Billiard " +
        "Room, Dining Hall, Drawing Room]\n" +
        "#0 Room: Armory, has items: [Revolver(Damage=3)]\n" +
        "**Dr. Lucky is in the room: Target name: Doctor Lucky, Current HP: 50, Current room " +
        "index: 0\n" +
        "Players in the same room: com2, "));
  }

  /**
   * Test check info about one computer player.
   * @throws IOException
   */
  @Test
  public void testCheckOnePlayerInfoComputer() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n " +
        "Look\n move\n Dining Hall\n m\n 3\n com2\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
//            assertEquals("exp",output.toString());

    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Which player do you want to check? (Enter the exact " +
        "player name from the above list.)\n" +
        "Player type: **Computer Player**\n" +
        "Player's Name: com2 \n" +
        "Player's limit: 1, can still carry: 0\n" +
        "Carrying: [Revolver(Damage=3)] "));
  }

  /**
   * Test check info about one Human player.
   * @throws IOException
   */
  @Test
  public void testCheckOnePlayerInfoHuman() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n " +
        "Look\n move\n Dining Hall\n m\n 3\n hu1\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
//        assertEquals("exp",output.toString());

    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("All 2 players in the Doctor Lucky's Mansion:\n" +
        "[hu1, com2]\n" +
        "Which player do you want to check? (Enter the exact player name from the above list.)\n" +
        "Player type: Human Player\n" +
        "Player's Name: hu1 \n" +
        "Player's limit: 1, can still carry: 1\n" +
        "Carrying: [] \n" +
        "Current Room: Dining Hall\n" +
        "#3 Room: Dining Hall, has items: []\n" +
        "Neighbor Rooms: Armory, Tennessee Room, Billiard Room, Trophy Room, Wine Cellar, Drawing" +
        " Room, Kitchen, Parlor\n"));
  }



  /**
   * Test method to ensure that an IllegalStateException is thrown when the Appendable fails.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    // Testing when something goes wrong with the Appendable
    // Here we are passing it of an Appendable that always fails
    StringReader input = new StringReader("0876 2");
    Appendable gameLog = new FailingAppendable();
    Controller c = new CmdControllerImplement(input,gameLog, this.realWorld);
    try {
      c.startGame();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
  //End test file.
}

