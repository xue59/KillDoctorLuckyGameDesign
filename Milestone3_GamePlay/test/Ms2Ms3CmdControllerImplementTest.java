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
import org.junit.Before;
import org.junit.Test;

/**
 * This is a test method for testing the command line controller.
 */
public class Ms2Ms3CmdControllerImplementTest {
  private World realWorld;
  private World mockWorld;
  private Appendable outGameLog;
  private StringBuilder outMockLog;

  /**
   * Set up the testing model world with DrLucky's mansion.
   * <p>
   * if there is an in/out error, throw exception.
   */
  @Before
  public void setup() throws IOException {
    // Create a new World instance for testing
    FileReader fileReader = new FileReader("res/testRes/mansion2023Pet.txt");
    BufferedReader br = new BufferedReader(fileReader);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(br);
    this.realWorld = createHelper.createWorld();
    this.realWorld.setTotalAllowedPlayers(2);
    this.realWorld.setTotalAllowedTurns(4);
    this.outGameLog = new StringBuilder(); // Initialize gameLog with a StringBuilder
    this.outMockLog = new StringBuilder();
    this.mockWorld = new WorldMockModel(this.outMockLog);
  }

  /**
   * This method test the controller can start create a graph representation.
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
   * This method tests the controller's ability to start successfully and enter the welcome screen.
   */
  @Test
  public void starGameWelcomeScreen() throws IOException {
    try {
      Readable input = new StringReader(" \n");
      Controller cmdController = new CmdControllerImplement(input, this.outGameLog, this.realWorld);
      cmdController.startGame();
    } catch (NoSuchElementException e) {
      // End for waiting user input by catching no user input Exception error
      assertEquals("No line found", e.getMessage());
    }
    assertEquals("Welcome to the Game World of Doctor Lucky's Mansion:\n"
        + "Main Menu: \n"
        + "(Select following operation (integer only)!)\n"
        + "0-Create a graphical representation of the world map PNG.\n"
        + "1-Setup game by adding all 2 players.\n"
        + "2-Find a Room (Display information about specified room in the world).\n"
        + "3-Find a Player (Display information about specified player in the world).\n"
        + "4-Start game turns to play (Must setup all the players before play!)\n"
        + "66-Quit and kill the program by using Order 66.\n"
        + "Error: please only enter an integer.\n"
        + "The world map png created in above directory.\n"
        + "Enter 'm' or 'M' to go back to Main Menu: ", this.outGameLog.toString());
  }

  /**
   * Test check one room command return correct room info with one item in the room.
   */
  @Test
  public void testCheckOneRoomInfoCommandWithOneItem() throws IOException {
    Readable input = new StringReader("2\n Armory\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    assertTrue(output.toString().contains("#0 Room: Armory, has items: [Revolver(Damage=99)]\n"));
  }

  /**
   * Test check one room command return correct room info with one item in the room.
   */
  @Test
  public void testCheckOneRoomInfoCommandWithTwoItem() throws IOException {
    Readable input = new StringReader("2\n Carriage House\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("#2 Room: Carriage House, has items: [Chain Saw"
        + "(Damage=88), Big Red Hammer(Damage=4)]\n"));
  }

  /**
   * Test check one room command return correct room info.
   * This room contain other players and target, this case we checked room Armory.
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
        "#0 Room: Armory, has items: [Revolver(Damage=99)]\n" +
        "**DrLucky(Doctor Lucky HP:50) in this room.\n" +
        "**Pet(Fortune Cat Pet) in this room.\n" +
        "Players in this room: hu1, hu2, \n" +
        "Room neighbors: Billiard Room,Dining Hall,Drawing Room,"));
  }

  /**
   * Test to add 2 human player success.
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
    assertTrue(output.toString().contains("All 2 players added successfully! They are:\n"
        + "Player type: Human Player\n"
        + "Player's Name: hu1 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] \n"
        + "\n"
        + "Player type: Human Player\n"
        + "Player's Name: hu2 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] "));
  }

  /**
   * Test add human player & computer player mixed success.
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
    assertTrue(output.toString().contains("All 2 players added successfully! They are:\n"
        + "Player type: **Computer Player**\n"
        + "Player's Name: hu1 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] \n"
        + "\n"
        + "Player type: Human Player\n"
        + "Player's Name: hu2 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] "));
  }

  /**
   * Test add 2 computer player mixed success.
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
    assertTrue(output.toString().contains("All 2 players added successfully! They are:\n"
        + "Player type: **Computer Player**\n"
        + "Player's Name: hu1 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] \n"
        + "\n"
        + "Player type: **Computer Player**\n"
        + "Player's Name: hu2 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] "));
  }

  /**
   * Test human player can move by 1 success with Move command.
   */
  @Test
  public void testHumanPlayerMove1() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("To Player: hu1, Which room do you want to move to?\n"
        + "Player: hu1 moved to room: Dining Hall SUCCESS!"));
  }

  /**
   * Test human player can move continuously in two turns.
   */
  @Test
  public void testHumanPlayerMove2() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
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
   */
  @Test
  public void testComputerPlayerMove() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Game Over: Max 4 turns finished!"));
  }

  /**
   * Test computer player can Pick Item when needed.
   */
  @Test
  public void testComputerPlayerPick() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("**Computer player**: com2 PICK up Revolver with 99 "
        + "damage.\n"));
  }

  /**
   * Test human player can Pick Item when needed.
   */
  @Test
  public void testHumanPlayerPick() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n Pick\n "
        + "Revolver\n"
        + "move\n Dining Hall\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Player: hu1 picked up item: Revolver SUCCESS!"));
  }

  /**
   * Test human player can use Look around command.
   */
  @Test
  public void testPlayerLook() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n "
        + "Look\n move\n Dining Hall\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Neighboring room info begin:-----"));
  }

  /**
   * Test check info about one computer player.
   */
  @Test
  public void testCheckOnePlayerInfoComputer() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n"
        + " Look\n move\n Dining Hall\n m\n 3\n com2\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Which player do you want to check? (Enter the exact "
        + "player name from the above list.)\n"
        + "Player type: **Computer Player**\n"));
  }

  /**
   * Test check info about one Human player.
   */
  @Test
  public void testCheckOnePlayerInfoHuman() throws IOException {
    Readable input = new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n "
        + "Look\n move\n Dining Hall\n m\n 3\n hu1\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("All 2 players in the Doctor Lucky's Mansion:\n"
        + "[hu1, com2]\n"
        + "Which player do you want to check? (Enter the exact player name from the above list.)\n"
        + "Player type: Human Player\n"
        + "Player's Name: hu1 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] \n"
        + "Current Room: Dining Hall\n"
        + "#3 Room: Dining Hall, has items: []\n"
        + "Neighbor Rooms: Armory, Tennessee Room, Billiard Room, Trophy Room, Wine Cellar, Drawing"
        + " Room, Kitchen, Parlor\n"));
  }

  //san testing start from here:

  /**
   * Test human player know target location from display info.
   */
  @Test
  public void testHumanPlayerInfo() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info

    assertTrue(output.toString().contains("Current Turn #1 for player: hu1. (Available commands: " +
        "[Move, Look, Pick, PetMove, Attack])\n" +
        "Reachable Rooms: [Billiard Room, Dining Hall, Drawing Room]\n" +
        "To Player: hu1, Which room do you want to move to?\n" +
        "Player: hu1 moved to room: Dining Hall SUCCESS!"));
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
    Controller c = new CmdControllerImplement(input, gameLog, this.realWorld);
    try {
      c.startGame();
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }
  //End test file.
}

