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
    assertEquals("\nWelcome to the Game World of Doctor Lucky's Mansion:\n"
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
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n hu2\n 1\n 0\n n\n m\n 2\n Armory\n "
            + "m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Which room do you want to check? (Enter the exact room"
        + " name from above list.)\n"
        + "#0 Room: Armory, has items: [Revolver(Damage=99)]\n"
        + "**DrLucky(Doctor Lucky HP:50) in this room.\n"
        + "**Pet(Fortune Cat Pet) in this room.\n"
        + "Players in this room: hu1, hu2, \n"
        + "Room neighbors: Billiard Room,Dining Hall,Drawing Room,"));
  }

  /**
   * Test to add 2 human player success.
   */
  @Test
  public void testAddTwoHumanPlayersSuccess() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n hu2\n 1\n 0\n n\n m\n 2\n Armory\n "
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
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n y\n hu2\n 1\n 0\n n\n m\n 2\n Armory\n "
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
  public void testHumanPlayerInfoKnowTarget() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Target name: Doctor Lucky(HP:50)currently "
        + "in room #0-Armory\n"
        + "Current Turn #1 for player: hu1. (Available commands: [Move, Look, Pick, PetMove, "
        + "Attack])\n"
        + "Reachable Rooms: [Billiard Room, Dining Hall, Drawing Room]\n"
        + "To Player: hu1, Which room do you want to move to?\n"
        + "Player: hu1 moved to room: Dining Hall SUCCESS!"));
  }

  /**
   * Test human player know info before their turn.
   */
  @Test
  public void testHumanPlayerBeginTurn() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains("Turn #3 Current Player Status: \n"
        + "Player type: Human Player\n"
        + "Player's Name: hu1 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] \n"
        + "Current Room: Dining Hall\n"
        + "#3 Room: Dining Hall, has items: []"));
  }

  /**
   * Test pet info before player's turn.
   */
  @Test
  public void testPetInfoBeginTurn() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n y\n m\n 4\n move\n Dining Hall\n"
            + "move\n Kitchen\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains("Turn #1 Current Player Status: \n"
        + "Player type: Human Player\n"
        + "Player's Name: hu1 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] \n"
        + "Current Room: Armory (**Dr.Lucky**(Doctor Lucky HP=50) is in this #0 room.)"
        + "(**Pet**(Fortune Cat Pet) in this room.)"));
  }

  /**
   * Test PetMove by player success.
   */
  @Test
  public void testPetMoveByPlayer() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 1\n n\n m\n 4\n petmove\n "
            + "Billiard Room\n look\n move\n Billiard Room\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Room: Armory "
        + "(**Dr.Lucky**(Doctor Lucky HP=50) is in this #0 room.)"
        + "(**Pet**(Fortune Cat Pet) in this room.)"));
    assertTrue(output.toString().contains("Player: hu1 moved Pet(Fortune Cat Pet) "
        + "to: Billiard Room SUCCESS!"));
    assertTrue(output.toString().contains("Current Turn #4 for player: com2. "
        + "(Available commands: [Move, Look, Pick, PetMove] (Can "
        + "not Attack, due to no Dr.Lucky))\n"
        + "You (player: com2) are currently in room #1 Billiard Room and neighboring rooms: "
        + "[Armory, Trophy Room, Dining Hall]\n"
        + "Your current #1 Room: Billiard Room, has items: [Billiard Cue(Damage=3)]\n"
        + "**Pet(Fortune Cat Pet) in this room(Billiard Room)."));
  }

  /**
   * Test player lookaround current room with others.
   */
  @Test
  public void testPlayerLookAroundSameRoomWithOthers() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n n\n m\n 4\n petmove\n "
            + "Billiard Room\n look\n move\n Billiard Room\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Target name: Doctor Lucky(HP:50)currently "
        + "in room #1-Billiard Room\n"
        + "Current Turn #2 for player: com2. (Available commands: [Move, Look, Pick, PetMove] "
        + "(Can not Attack, due to no Dr.Lucky))\n"
        + "You (player: com2) are currently in room #0 Armory and neighboring rooms: "
        + "[Billiard Room, Dining Hall, Drawing Room]\n"
        + "Your current #0 Room: Armory, has items: [Revolver(Damage=99)]\n"
        + "Other players in the same room: hu1, \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "**Invisible inside/out Room** Pet(Fortune Cat Pet) in this room: Billiard Room\n"
        + "2. Neighbor:\n"
        + "#3 Room: Dining Hall, has items: []\n"
        + "Players in Dining Hall: \n"
        + "3. Neighbor:\n"
        + "#4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
        + "Players in Drawing Room: \n"
        + "Neighboring room info end:"
        + "------------------------------------------------------------"));
  }

  /**
   * Test player lookaround current room with no others.
   */
  @Test
  public void testPlayerLookAroundNoPlayerTogether() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 0\n n\n m\n 4\n petmove\n "
            + "Billiard Room\n look\n move\n Billiard Room\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains("Current Turn #4 for player: com2. "
        + "(Available commands:"
        + " [Move, Look, Pick, PetMove] (Can not Attack, due to no Dr.Lucky))\n"
        + "You (player: com2) are currently in room #0 Armory and neighboring rooms: [Billiard "
        + "Room, Dining Hall, Drawing Room]\n"
        + "Your current #0 Room: Armory, has items: [Revolver(Damage=99)]\n"
        + "Other players in the same room: \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "**Invisible inside/out Room** Pet(Fortune Cat Pet) in this room: Billiard Room\n"
        + "2. Neighbor:\n"
        + "#3 Room: Dining Hall, has items: []\n"
        + "**DrLucky(Doctor Lucky HP:50) in this room(Dining Hall).\n"
        + "Players in Dining Hall: \n"
        + "3. Neighbor:\n"
        + "#4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
        + "Players in Drawing Room: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------\n"
        + "Game Over: Max 4 turns finished!"));
  }

  /**
   * Test player lookaround current room with no others in neighbor.
   */
  @Test
  public void testPlayerLookNoPlayerNeighbor() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 11\n n\n m\n 4\n petmove\n "
            + "Billiard Room\n look\n move\n Billiard Room\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Other players in the same room: \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#16 Room: Servants' Quarters, has items: [Broom Stick(Damage=2)]\n"
        + "Players in Servants' Quarters: \n"
        + "2. Neighbor:\n"
        + "#17 Room: Tennessee Room, has items: []\n"
        + "Players in Tennessee Room: \n"
        + "3. Neighbor:\n"
        + "#9 Room: Lancaster Room, has items: [Silken Cord(Damage=3)]\n"
        + "Players in Lancaster Room: \n"
        + "4. Neighbor:\n"
        + "#12 Room: Master Suite, has items: [Shoe Horn(Damage=2)]\n"
        + "Players in Master Suite: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------"));
    //System.out.println(output.toString());
  }

  /**
   * Test player lookaround current room with players in neighbor.
   */
  @Test
  public void testPlayerLookPlayersInNeighbor() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 3\n n\n m\n 4\n petmove\n "
            + "Library\n look\n move\n Billiard Room\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #2 for player: com2. "
        + "(Available commands: "
        + "[Move, Look, Pick, PetMove] (Can not Attack, due to no Dr.Lucky))\n"
        + "You (player: com2) are currently in room #3 Dining Hall and neighboring rooms: "
        + "[Armory, Tennessee Room, Billiard Room, Trophy Room, Wine Cellar, Drawing Room, "
        + "Kitchen, Parlor]\n"
        + "Your current #3 Room: Dining Hall, has items: []\n"
        + "Other players in the same room: \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#0 Room: Armory, has items: [Revolver(Damage=99)]\n"
        + "Players in Armory: hu1, "));
    //System.out.println(output.toString());
  }

  /**
   * Test player lookaround current room with no item in same room.
   */
  @Test
  public void testPlayerLookNoItemInSameRoom() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 3\n n\n m\n 4\n petmove\n "
            + "Library\n look\n move\n Billiard Room\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #4 for player: com2. "
        + "(Available commands: [Move, Look, Pick, PetMove, "
        + "Attack])\n"
        + "You (player: com2) are currently in room #3 Dining Hall and neighboring rooms: "
        + "[Armory, Tennessee Room, Billiard Room, Trophy Room, Wine Cellar, Drawing Room, "
        + "Kitchen, Parlor]\n"
        + "Your current #3 Room: Dining Hall, has items: []\n"
        + "**Dr. Lucky is in your room(Dining Hall): Target name: "
        + "Doctor Lucky, Current HP: 50,"
        + " Current room index: 3\n"
        + "Other players in the same room: \n"
        + "Neighboring room info begin:--------"));
    //System.out.println(output.toString());
  }

  /**
   * Test player lookaround current room with item in same room and neighbor.
   */
  @Test
  public void testPlayerLookItem() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 4\n n\n com2\n 1\n 4\n n\n m\n 4\n petmove\n "
            + "Library\n look\n move\n Armory\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #2 for player: com2. "
        + "(Available commands: "
        + "[Move, Look, Pick, PetMove] (Can not Attack, due to no Dr.Lucky))\n"
        + "You (player: com2) are currently in room #4 Drawing Room and neighboring rooms: "
        + "[Armory, Dining Hall, Wine Cellar, Foyer]\n"
        + "Your current #4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
        + "Other players in the same room: hu1, \n"
        + "Neighboring room info begin:-----"));
    //System.out.println(output.toString());

    assertTrue(output.toString().contains("You (player: com2) are currently in room #4 "
        + "Drawing Room and neighboring rooms: "
        + "[Armory, Dining Hall, Wine Cellar, Foyer]\n"
        + "Your current #4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
        + "Other players in the same room: hu1, \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#0 Room: Armory, has items: [Revolver(Damage=99)]\n"
        + "Players in Armory: \n"
        + "2. Neighbor:\n"
        + "#3 Room: Dining Hall, has items: []\n"
        + "Players in Dining Hall: \n"
        + "3. Neighbor:\n"
        + "#19 Room: Wine Cellar, has items: [Rat Poison(Damage=2), Piece of Rope(Damage=2)]\n"
        + "Players in Wine Cellar: \n"
        + "4. Neighbor:\n"
        + "#5 Room: Foyer, has items: []\n"
        + "Players in Foyer: \n"
        + "Neighboring room info end:"
        + "------------------------------------------------------------"));
  }

  /**
   * Test player lookaround current room and neighbor for the DrLucky location.
   */
  @Test
  public void testPlayerLookTargetLocation() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 1\n n\n m\n 4\n look\n "
            + "look\n move\n Billiard Room\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Current Turn #1 for player: hu1. "
        + "(Available commands: [Move, Look, Pick, PetMove, "
        + "Attack])\n"
        + "You (player: hu1) are currently in room #0 Armory and neighboring rooms: [Billiard "
        + "Room, Dining Hall, Drawing Room]\n"
        + "Your current #0 Room: Armory, has items: [Revolver(Damage=99)]\n"
        + "**Dr. Lucky is in your room(Armory): Target name: Doctor Lucky, Current HP: 50, "
        + "Current room index: 0\n"
        + "**Pet(Fortune Cat Pet) in this room(Armory).\n"
        + "Other players in the same room: \n"
        + "Neighboring room info begin:--------"));
    //System.out.println(output.toString());

    assertTrue(output.toString().contains("Neighboring room info begin:"
        + "----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "**Invisible inside/out Room** Pet(Fortune Cat Pet) in this room: Armory\n"
        + "2. Neighbor:\n"
        + "#18 Room: Trophy Room, has items: [Duck Decoy(Damage=3), Monkey Hand(Damage=2)]\n"
        + "Players in Trophy Room: \n"
        + "3. Neighbor:\n"
        + "#3 Room: Dining Hall, has items: []\n"
        + "**DrLucky(Doctor Lucky HP:50) in this room(Dining Hall).\n"
        + "Players in Dining Hall: \n"
        + "Neighboring room info "
        + "end:------------------------------------------------------------\n"
        + "Game Over: Max 4 turns finished!"));
  }

  /**
   * Test player lookaround can not find target in any room or nearby.
   */
  @Test
  public void testPlayerLookNoTarget() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 19\n n\n com2\n 1\n 11\n n\n m\n 4\n look\n "
            + "look\n look\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    assertTrue(output.toString().contains("Turn #1 Current Player Status: \n"
        + "Player type: Human Player\n"
        + "Player's Name: hu1 \n"
        + "Player's limit: 1, can still carry: 1\n"
        + "Carrying: [] \n"
        + "Current Room: Wine Cellar\n"
        + "#19 Room: Wine Cellar, has items: [Rat Poison(Damage=2), Piece of Rope(Damage=2)]\n"
        + "Neighbor Rooms: Dining Hall, Drawing Room, Kitchen\n"
        + "Target name: Doctor Lucky(HP:50)currently in room #0-Armory\n"
        + "Current Turn #1 for player: hu1. (Available commands: [Move, Look, Pick, PetMove] "
        + "(Can not Attack, due to no Dr.Lucky))\n"
        + "You (player: hu1) are currently in room #19 Wine Cellar and neighboring rooms: "
        + "[Dining Hall, Drawing Room, Kitchen]\n"
        + "Your current #19 Room: Wine Cellar, has items: [Rat Poison(Damage=2), Piece of Rope"
        + "(Damage=2)]\n"
        + "Other players in the same room: \n"
        + "Neighboring room info "
        + "begin:----------------------------------------------------------\n"
        + "1. Neighbor:\n"
        + "#3 Room: Dining Hall, has items: []\n"
        + "Players in Dining Hall: \n"
        + "2. Neighbor:\n"
        + "#4 Room: Drawing Room, has items: [Letter Opener(Damage=77)]\n"
        + "Players in Drawing Room: \n"
        + "3. Neighbor:\n"
        + "#8 Room: Kitchen, has items: [Crepe Pan(Damage=3), Sharp Knife(Damage=3)]\n"
        + "Players in Kitchen: \n"
        + "Neighboring room info end:"
        + "------------------------------------------------------------"));
    //System.out.println(output.toString());
  }

  /**
   * Test human player can success poking DrLucky causing hp -1.
   */
  @Test
  public void testHumanPokingSuccess() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 1\n 0\n n\n com2\n 1\n 11\n n\n m\n 4\n attack\n poking\n"
            + "look\n look\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains("Choose your carrying item to attack:\n"
        + "[]\n"
        + "**Basic attack item: Poking(Damage=1)\n"
        + " Player(hu1) attack Dr.Lucky SUCCESS with item: Poking(Damage=1)\n"
        + "Dr.Lucky(Doctor Lucky) HP: -1\n"
        + "Dr.Lucky(Doctor Lucky) Current HP=49"));
  }

  /**
   * Test human player can success attack DrLucky with item and reflect its damage.
   */
  @Test
  public void testHumanItemAttackSuccessHumanWin() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 22\n 2\n n\n com2\n 1\n 11\n n\n m\n 4\n pick\n Chain Saw\n"
            + "look\n attack\n Chain Saw\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains("You are in #2 Room: Carriage House, "
        + "has items: [Chain Saw(Damage=88), Big Red Hammer"
        + "(Damage=4)]\n"
        + "(To Player) hu1: What do you want to pick? (Enter the exact name.):\n"
        + "Player PICK execute success!\n"
        + "Player: hu1 picked up item: Chain Saw SUCCESS!"));
    assertTrue(output.toString().contains("Target name: Doctor Lucky(HP:50)currently in room "
        + "#2-Carriage House.\n"
        + "Choose your carrying item to attack:\n"
        + "[Chain Saw(Damage=88)]\n"
        + "**Basic attack item: Poking(Damage=1)\n"
        + " Player(hu1) attack Dr.Lucky SUCCESS with item: Chain Saw(Damage=88)\n"
        + "Dr.Lucky(Doctor Lucky) was attacked by hp:-88\n"
        + "Dr.Lucky(Doctor Lucky) Current HP=0"));
  }

  /**
   * Test human player try attack with wrong item name.
   * In this case the system will stop and waring,then let customer using proper item name to
   * attack.
   */
  @Test
  public void testHumanItemAttackFailWrongItemName() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 22\n 2\n n\n com2\n 1\n 11\n n\n m\n 4\n pick\n "
            + "Chain Saw\n look\n attack\n WrongItem\n Chain Saw\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains("Choose your carrying item to attack:\n"
        + "[Chain Saw(Damage=88)]\n"
        + "**Basic attack item: Poking(Damage=1)\n"
        + " Error PlayerImpl: Item with name 'WrongItem' not found in player's inventory!\n"
        + "Check your item name for typos and case sensitivity!"));
  }

  /**
   * Test human player try attack with used item name.
   * In this case the system will stop and waring,then let customer using proper item name to
   * attack.
   * In the game, every item after success attack will be deleted. Thus, the item will not be
   * present in the player's inventory.
   */
  @Test
  public void testHumanItemAttackFailUsedItem() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 22\n 2\n n\n com2\n 1\n 11\n n\n m\n 4\n pick\n "
            + "Chain Saw\n look\n attack\n Chain saw\n Chain Saw\n look\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains(" Error PlayerImpl: "
        + "Item with name 'Chain saw' not found in player's inventory!\n"
        + "Check your item name for typos and case sensitivity!"));
  }

  /**
   * Test the game will end when DrLucky is killed and hp is 0.
   */
  @Test
  public void testEndDrLuckyDead() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 22\n 2\n n\n com2\n 1\n 11\n n\n m\n 4\n pick\n "
            + "Chain Saw\n look\n attack\n Chain Saw\n m\n 4\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());
    assertTrue(output.toString().contains("Choose your carrying item to attack:\n"
        + "[Chain Saw(Damage=88)]\n"
        + "**Basic attack item: Poking(Damage=1)\n"
        + " Player(hu1) attack Dr.Lucky SUCCESS with item: Chain Saw(Damage=88)\n"
        + "Dr.Lucky(Doctor Lucky) was attacked by hp:-88\n"
        + "Dr.Lucky(Doctor Lucky) Current HP=0"));
    assertTrue(output.toString().contains("Congratulation!!!!!!!!!\n"
        + "Game Winner: hu1 \n\n"
        + "Game finished, back to main menu to 66-quit & re-start a New Game.\n"
        + "Enter 'm' or 'M' to go back to Main Menu: "));
  }

  /**
   * Test the game will end when max turn reach.(In this case total 4 turns).
   * This mean DrLucky is escaped and hp is not 0.
   */
  @Test
  public void testEndDrLuckyEscaped() throws IOException {
    Readable input =
        new StringReader("1\n hu1\n 22\n 2\n n\n com2\n 1\n 11\n n\n m\n 4\n pick\n "
            + "Chain Saw\n look\n attack\n Poking\n look\n m\n 4\n m\n 66\n");
    Appendable output = new StringBuilder();
    Controller testConsole = new CmdControllerImplement(input, output, this.realWorld);
    testConsole.startGame();
    // Check the output is correct or not by compare string from manually running game result
    // The correct return string should contain correct command result info
    //System.out.println(output.toString());

    assertTrue(output.toString().contains("Neighboring room info "
        + "end:------------------------------------------------------------\n"
        + "Game Over: Max 4 turns finished!\n"
        + "Whoops, Dr.Lucky(Doctor Lucky HP=49) escaped!!!\n"
        + "Game finished, back to main menu to 66-quit & re-start a New Game.\n"
        + "Enter 'm' or 'M' to go back to Main Menu: "));

    assertTrue(output.toString().contains("Last game already finished, "
        + "back to main menu to re-start a New Game.\n"
        + "Whoops, last game Dr.Lucky(Doctor Lucky HP=49) escaped!!!"));
    //check make sure dr lucky health is not zero.
    assertTrue(output.toString().contains("Dr.Lucky(Doctor Lucky HP=49) escaped!!!"));
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
