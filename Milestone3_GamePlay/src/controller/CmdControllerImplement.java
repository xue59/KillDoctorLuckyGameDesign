package controller;

import command.ComputerActionCmd;
import command.LookAroundCmd;
import command.MovePlayerCmd;
import command.PetMoveCmd;
import command.PlayerPickCmd;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.world.World;

/**
 * The CmdControllerImplement class is responsible for controlling and managing the command-line
 * interface (CLI) for interacting with a game world.
 * It provides methods for processing user input and generating output to the specified Appendable.
 * This controller handles commands to move players, perform actions, look around, and pick up
 * items in the game world.
 * <p>
 * The controller communicates with the provided World model to execute commands and retrieve game
 * state information.
 *
 * @see Controller
 */
public class CmdControllerImplement implements Controller {
  private final World world;
  private final Scanner scanner;
  private final Appendable output;
  private final int totalAllowedPlayers;
  private final int totalAllowedTurns;
  private boolean quitFlag;

  /**
   * Constructs a CmdControllerImplement with the specified input, output, and game world.
   *
   * @param input  A Readable input source, typically a user input stream, for receiving commands.
   * @param output An Appendable output destination, typically a console or a text-based interface.
   * @param world  The game World model that provides methods for executing commands interactions.
   */
  public CmdControllerImplement(Readable input, Appendable output, World world) {
    this.scanner = new Scanner(input);
    this.output = output;
    this.world = world;
    this.totalAllowedPlayers = world.getTotalAllowedPlayers();
    this.totalAllowedTurns = world.getTotalAllowedTurns();
    this.quitFlag = false;
  }

  /**
   * Starts the game loop, allowing the user to interact with the game world.
   * Displays a menu with options to create a world map, add players, find a room,
   * find a player, and start game turns. The user can also execute Order 66 to quit the program.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  @Override
  public void startGame() throws IOException {

    while (!quitFlag) {
      this.output.append(String.format("Welcome to the Game World of %s:\n",
          world.getWorldName()));
      this.output.append("(To quit: Ctrl + C) \n");
      this.displayMainMenuOptions();
      int select = 0;
      try {
        select = Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException exception) {
        this.output.append("Error: please only enter an integer.\n");
      }

      switch (select) {
        case 0: // 0-Create a graphical representation of the world map PNG.
          this.createWorldMapPng();
          break;
        case 1: // 1-Setup game by adding all " + totalAllowedPlayers + " players.
          this.loopToAddAllPlayers();
          break;
        case 2: //2-Find a Room
          this.loopToDisplayOneRoomInfo();
          break;
        case 3: //3-Find a Player (Display information about specified player in the world)
          this.loopToDisplayOnePlayerInfo();
          break;
        case 4: //4-Start game turns to play (Must setup all the players before play!)
          this.loopToStartTurns();
          break;
        case 66:
          //Execute order 66 to kill the program and Quit. Lol
          this.output.append("Executed Order 66 to kill and eliminate ALL controller program "
              + "and Quit!\n");
          quitFlag = true;
          return;

        default:
          this.output.append("Please select from Main Menu:(0-4)\n");
      }
    }
  }

  /**
   * Displays the main menu options to the user, including creating a world map, adding players,
   * finding a room,
   * finding a player, starting game turns, and quitting the program.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void displayMainMenuOptions() throws IOException {
    this.output.append("Main Menu: \n");
    this.output.append("(Select following operation (integer only)!)\n");
    this.output.append(String.format("0-Create a graphical representation of the world map PNG.\n"
        + "1-Setup game by adding all %d players.\n"
        + "2-Find a Room (Display information about specified room in the world).\n"
        + "3-Find a Player (Display information about specified player in the world).\n"
        + "4-Start game turns to play (Must setup all the players before play!)\n"
        + "66-Quit and kill the program by using Order 66.\n", totalAllowedPlayers));
  }

  /**
   * Loops to select an option from the main menu or return to the main menu.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void loopToSelectMainMenu() throws IOException {
    try {
      output.append("Enter 'm' or 'M' to go back to Main Menu: ");
      String userInput = scanner.nextLine().trim();

      if ("m".equalsIgnoreCase(userInput)) {
        // Go back to the main menu
        startGame();
      } else {
        // Invalid input, ask again
        output.append("Invalid input. Please enter 'm' or 'M' to go back to the main menu.\n");
        loopToSelectMainMenu(); // Recursive call to ask again
      }
    } catch (IOException e) {
      // Handle IOException if needed
      e.printStackTrace();
    }
  }

  /**
   * Loops to display information about a specific room in the world.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void loopToDisplayOneRoomInfo() throws IOException {
    String inputName = null;
    try {
      this.output.append(String.format("All %d room in the %s:\n%s", world.getTotalOfRoom(),
          world.getWorldName(), world.getAllRoomNames()));
      this.output.append("\nWhich room do you want to check? "
          + "(Enter the exact room name from above list.)\n");
      inputName = this.scanner.nextLine().trim();
      this.output.append(world.getOneRoomInfo(inputName));
      this.output.append("\n");
      loopToSelectMainMenu();
    } catch (IllegalArgumentException e) {
      this.output.append(String.format("No room name found for: %s\n", inputName));
      this.output.append("Check your room name typo again, room names are case sensitive.\n");
      loopToDisplayOneRoomInfo();
    } catch (NullPointerException e) {
      this.output.append("Room name cannot be Null.\n");
      loopToDisplayOneRoomInfo();
    }
  }

  /**
   * Loops to display information about a specific player in the world.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void loopToDisplayOnePlayerInfo() throws IOException {
    //if no players added advise user go back to main menu to add players.
    if (world.getAllPlayerNames().size() == 0) {
      this.output.append("No players added, back to Main menu and add players first!\n");
      loopToSelectMainMenu();
      return;
    }

    String inputName = null;
    List<String> playerNameList = world.getAllPlayerNames();
    try {
      this.output.append(String.format("All %d players in the %s:\n%s", playerNameList.size(),
          world.getWorldName(), playerNameList));
      this.output.append("\nWhich player do you want to check? "
          + "(Enter the exact player name from the above list.)\n");
      inputName = this.scanner.nextLine().trim();
      this.output.append(world.getOnePlayerAndRoomInfo(inputName));
      this.output.append("\n");
      loopToSelectMainMenu();
    } catch (IllegalArgumentException e) {
      this.output.append(String.format("No player found for: %s\n", inputName));
      this.output.append("Check your player name typo again. Player names are case sensitive.\n");
      loopToDisplayOnePlayerInfo();
    } catch (NullPointerException e) {
      this.output.append("Player name cannot be null.\n");
      loopToDisplayOnePlayerInfo();
    }
  }

  /**
   * Creates a graphical representation of the world map in PNG format.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void createWorldMapPng() throws IOException {
    this.world.createGraphBufferedImage();
    this.output.append("The world map png created in above directory.\n");
    loopToSelectMainMenu();
  }

  /**
   * Loops through the game turns and player actions.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void loopToStartTurns() throws IOException {

    // if not enough player advise user to main to add more players
    if (world.getAllPlayerNames().size() < this.totalAllowedPlayers) {
      this.output.append(
          String.format("Not enough players! Back to Main Menu to add %d players to " + "play.\n",
              this.totalAllowedPlayers));
      loopToSelectMainMenu();
      return;
    }
    // if game over then should not start the game, let user go back re-start the game
    if (world.checkGameOver()) {
      // True game is over
      this.output.append(
          String.format("Last game already finished, back to main menu to re-start a New Game.\n"));
      this.output.append(String.format("Last game winner: %s!!!\n",world.getWinnerName()));
      loopToSelectMainMenu();
      return;
    }

    int curTurnNum = 1;

    this.output.append(String.format("Game turns starting in %s, total %d players: %s\n",
        world.getWorldName(), world.getAllPlayerNames().size(), world.getAllPlayerNames()));

    // For MS2 game only end when max turns reached, no Dr.Lucky Health involved now!
    // Turn starting loop curTurnNum starting with 1
    while (curTurnNum <= world.getTotalAllowedTurns()) {
      this.loopToOnePlayerTurn(curTurnNum);
      //last curTurnNum ++
      curTurnNum++;
    }
    if (curTurnNum > world.getTotalAllowedTurns()) {
      output.append(String.format("Game Over: Max %d turns finished!\n", curTurnNum - 1));
      loopToSelectMainMenu();
    }

  }

  /**
   * Loops through a single player's turn during the game.
   *
   * @param curTurnNum the current turn number.
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void loopToOnePlayerTurn(int curTurnNum) throws IOException {
    // ms3 if game over when palyer killed dr lucky!
    if (world.checkGameOver()){
      output.append(String.format("Game Over, Winner: %s!!!\n", world.getWinnerName()));
      loopToSelectMainMenu();
    }
    String curTurnPlayerName = world.getCurrentPlayerName();
    boolean isCurPlayerComputer = world.isCurrentPlayerComputer();
    this.output.append(String.format("\nTurn #%d Current Player Status: \n%s",
        curTurnNum, world.getOnePlayerAndRoomInfo(curTurnPlayerName)));
    if (this.world.checkCurPlayerSameRoomWithDrLucky()){
      this.output.append(
          String.format("Current Turn #%d for player: %s. (Available commands: "
                  + "[Move, Look, Pick, PetMove, Attack])\n",
              curTurnNum, curTurnPlayerName));
    } else {
      this.output.append(
          String.format("Current Turn #%d for player: %s. (Available commands: "
                  + "[Move, Look, Pick, PetMove] (Can not Attack, due to no Dr.Lucky))\n",
              curTurnNum, curTurnPlayerName));
    }


    //call to computer player logic
    if (isCurPlayerComputer) {
      this.computerPlayerTakeOneTurn(curTurnPlayerName);
      return;
    }

    //following loop let a HUMAN player make a selection of their actions
    //true and current player is not a Computer
    while (!isCurPlayerComputer) {
      String command;
      // Convert the input to ALL UPPER CASE for case-insensitivity
      command = scanner.nextLine().trim().toUpperCase();
      try {
        if ("MOVE".equals(command)) {
          this.consolePlayerMove(curTurnPlayerName);
          break;
        } else if ("LOOK".equals(command)) {
          this.consolePlayerLook();
          break;
        } else if ("PICK".equals(command)) {
          this.consolePlayerPick(curTurnPlayerName);
          break;
        } else if ("PETMOVE".equals(command) || "PET MOVE".equals(command)) {
          this.consolePetMove(curTurnPlayerName);
          break;
        } else if ("ATTACK".equals(command) && this.world.checkCurPlayerSameRoomWithDrLucky()){
          this.consolePlayerKill(curTurnPlayerName);
          break;
        } else { // Default base case nothing match input & loop back to let user input selection
          this.output.append("No match command found! Please enter exact command:\n");
          if (this.world.checkCurPlayerSameRoomWithDrLucky()){
            this.output.append(
                String.format("Turn#%d player(%s). (Available commands: "
                        + "[Move, Look, Pick, PetMove, Attack])\n",
                    curTurnNum, curTurnPlayerName));
          } else {
            this.output.append(
                String.format("Turn#%d player(%s). (Available commands: "
                        + "[Move, Look, Pick, PetMove] "
                        + "(Can not Attack, due to no Dr.Lucky in room.))\n",
                    curTurnNum, curTurnPlayerName));
          }
        }
      } catch (IllegalStateException e) {    // Turn max reached & Game over!
        this.output.append(e.getMessage());
        loopToSelectMainMenu();
        return;
      } catch (IllegalAccessException e) {
        this.output.append(e.getMessage());
        this.output.append("Try a different command!\n");
      }
    }
  }

  /**
   * Handles the actions of a computer player during their turn.
   *
   * @param curTurnPlayerName the name of the current player.
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void computerPlayerTakeOneTurn(String curTurnPlayerName) throws IOException {
    this.output.append(String.format("**Computer player**: %s is taking action...\n",
        curTurnPlayerName));

    String computerActionResult = null;
    ComputerActionCmd computerActionCmd = new ComputerActionCmd();
    try {
      computerActionResult = computerActionCmd.execute(this.world);
      this.output.append(computerActionResult); // display computer action run
    } catch (IllegalAccessException e) {
      this.output.append(e.getMessage());
      loopToSelectMainMenu();
    } catch (IllegalStateException e) { // Turn max reached Game over!
      this.output.append(e.getMessage());
      loopToSelectMainMenu();
    }
  }

  private void consolePlayerKill(String curTurnPlayerName)
      throws IOException, IllegalAccessException {
    String curPlayerCarrying =
        this.world.getPlayerAllCarryingItemStringWithDamage(curTurnPlayerName);
    output.append(String.format(
        "%s.\nChoose your carrying item to attack:\n%s ",
        world.getDrLuckyInfo(), curPlayerCarrying));

    while (true) {
      String inputItemName;
      String cmdKillResult;
      inputItemName = scanner.nextLine().trim();
      try{
        cmdKillResult = world.cmdPlayerKill(inputItemName);
        if (cmdKillResult == null || cmdKillResult.isEmpty() ){
          output.append(String.format("Player(%s) Attack failed due to be seen!\n",
              curTurnPlayerName));
        } else {
          output.append(cmdKillResult);
        }
        return;
      } catch (IllegalStateException e){// Game Over state!
        throw new IllegalStateException(String.format("Game Over! "
            + "Player:%s Cannot Attack with item! %s\n", curTurnPlayerName, inputItemName));
      } catch (IllegalAccessException e){
        throw e; // player and drLucky are not in the same room
      } catch (IllegalArgumentException e){
        output.append(String.format("%s",e.getMessage()));
        output.append("Check your item name for typos and case sensitivity!\n");
      }

    }

  }

  /**
   * Allows a console player to pick up an item in the game.
   *
   * @param curTurnPlayerName the name of the current player.
   * @throws IOException            if an I/O error occurs while interacting with the game.
   * @throws IllegalAccessException if the player's bag is full.
   * @throws IllegalStateException  if the game is over.
   */
  private void consolePlayerPick(String curTurnPlayerName)
      throws IOException, IllegalAccessException, IllegalStateException {
    String whatCanPickInfo = world.getPlayerWhatCanPickInfo(curTurnPlayerName);
    if (whatCanPickInfo != null) {
      output.append(
          String.format("You are in %s\n", whatCanPickInfo)); // display what can be picked info
      output.append(
          String.format("(To Player) %s: What do you want to pick? (Enter the exact name.):\n",
              curTurnPlayerName));
    } else {
      throw new IllegalAccessException("No items can be picked in current room.\n");
    }

    while (true) {
      String inputItemName;
      String cmdPickResult;
      inputItemName = scanner.nextLine().trim();
      PlayerPickCmd cmdPick = new PlayerPickCmd(inputItemName);
      try {
        cmdPickResult = cmdPick.execute(this.world);
        if (!cmdPickResult.isEmpty()) {
          output.append(cmdPickResult);
          output.append(String.format("Player: %s picked up item: %s SUCCESS!\n",
              curTurnPlayerName, inputItemName));
        }
        return;
      } catch (IllegalAccessException e) {
        output.append(e.getMessage());
        throw new IllegalAccessException("Can't PICK, your bag is Full, try other commands!\n");
      } catch (IllegalStateException e) { // Game Over state!
        throw new IllegalStateException(String.format("Game Over! Player:%s Cannot pick up item!"
                + " %s\n", curTurnPlayerName, inputItemName));
      } catch (IllegalArgumentException e) {
        output.append(e.getMessage());
        output.append("\nCheck the item name for typos and case sensitivity!\n");
      } catch (NullPointerException e) {
        output.append("Item name cannot be null! Try a valid Item name.\n");
      }
    }
  }

  /**
   * Allows a console player to look around the room.
   *
   * @throws IOException            if an I/O error occurs while interacting with the game.
   * @throws IllegalAccessException if the action is not allowed.
   */
  private void consolePlayerLook()
      throws IOException, IllegalAccessException {
    // using command design pattern to execute the Look command
    LookAroundCmd cmdLook = new LookAroundCmd();
    output.append(cmdLook.execute(this.world));
  }

  private void consolePetMove(String curTurnPlayerName) throws IOException {
    String petName = world.getPetName();
    output.append(
        String.format("Available rooms for Pet(%s): \n ", petName));
    output.append(String.format("%s",world.getAllRoomNames()));
    output.append(String.format("To Player: %s, Which room do you want Pet to move to?\n",
        curTurnPlayerName));
    while (true) {
      String inputRoomName;
      inputRoomName = scanner.nextLine().trim();
      try {
        PetMoveCmd cmdPetMove = new PetMoveCmd(inputRoomName);
        String moveResult;
        moveResult = cmdPetMove.execute(this.world);
        if (!moveResult.isEmpty()) {
          output.append(String.format("Player: %s moved Pet(%s) to: %s SUCCESS!\n",
              curTurnPlayerName, petName,inputRoomName));
        }
        return;
      } catch (IllegalAccessException e) {
        output.append(e.getMessage());
        output.append("Check your room name for typo and case sensitivity!\n");
      } catch (NullPointerException e) {
        output.append(e.getMessage());
        output.append("Enter a valid room name again!\n");
      } catch (IllegalStateException e) {
        // Game Over state!
        throw new IllegalStateException(
            String.format("Game Over! Player:%s Cannot move! %s\n", curTurnPlayerName,
                e.getMessage()));
      } catch (IllegalArgumentException e) {
        output.append(e.getMessage());
        output.append("\nCheck your room name for typo and case sensitivity!\n");
      }
    }
  }

  /**
   * Allows a console player to move to a different room.
   *
   * @param curTurnPlayerName the name of the current player.
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void consolePlayerMove(String curTurnPlayerName) throws IOException {
    String playerCurRoom = world.getOnePlayerCurrentRoomName(curTurnPlayerName);
    output.append(
        String.format("Reachable Rooms: %s\n", world.getNeighborsRoomList(playerCurRoom)));
    output.append(String.format("To Player: %s, Which room do you want to move to?\n",
        curTurnPlayerName));

    while (true) {
      String inputRoomName;
      inputRoomName = scanner.nextLine().trim();
      try {
        MovePlayerCmd cmdMove = new MovePlayerCmd(inputRoomName);
        String moveResult;
        moveResult = cmdMove.execute(this.world);
        if (!moveResult.isEmpty()) {
          output.append(String.format("Player: %s moved to room: %s SUCCESS!\n",
              curTurnPlayerName, inputRoomName));
        }
        return;
      } catch (IllegalAccessException e) {
        output.append(e.getMessage());
        output.append("Check your room name for typo and case sensitivity!\n");
      } catch (NullPointerException e) {
        output.append(e.getMessage());
        output.append("Enter a valid room name again!\n");
      } catch (IllegalStateException e) {
        // Game Over state!
        throw new IllegalStateException(
            String.format("Game Over! Player:%s Cannot move! %s\n", curTurnPlayerName,
                e.getMessage()));
      } catch (IllegalArgumentException e) {
        output.append(e.getMessage());
        output.append("\nCheck your room name for typo and case sensitivity!\n");
      }
    }
  }

  /**
   * Loops to add all players to the game.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void loopToAddAllPlayers() throws IOException {
    int count = 1;
    while (world.getAllPlayerNames().size() < world.getTotalAllowedPlayers()) {
      this.output.append(String.format("Enter information for No.%d player: \n", count));
      addOnePlayer();
      count++;
    }
    if (world.getAllPlayerNames().size() == world.getTotalAllowedPlayers()) {
      this.world.createGraphBufferedImage();
      this.output.append(String.format("\nAll %d players added successfully! They are:\n",
          world.getTotalAllowedPlayers()));
      this.output.append(world.getAllPlayerInfo());
    }
    loopToSelectMainMenu();
  }

  /**
   * Adds a single player to the game.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  private void addOnePlayer() throws IOException {
    try {
      String playerName;
      this.output.append("Give a name for this Player:\n");
      playerName = scanner.nextLine().trim(); // get player name

      this.output.append(String.format("Set Player: %s's carrying limit(Integer only!):\n",
          playerName));
      int playerLimit;
      playerLimit = Integer.parseInt(scanner.nextLine().trim()); // get player limit

      this.output.append(String.format("Set Player: %s's initial room index (0 to %d):\n",
          playerName, world.getTotalOfRoom() - 1));
      int playerInitialRoom;
      playerInitialRoom = Integer.parseInt(scanner.nextLine().trim()); // get player limit

      this.output.append(String.format("Set Player: is this %s a computer player?:(Y/N)\n",
          playerName));
      // if user input Y for isComputer True; N for isComputer False.
      boolean isComputer;
      isComputer = this.checkInputStringTrueFalse(scanner.nextLine().trim());

      // call world method to create the player & add into game world
      this.world.addOnePlayer(playerName, playerInitialRoom, isComputer, playerLimit);

      this.output.append(String.format("Player: %s (limit: %d) successfully added into Room #%d\n",
          playerName, playerLimit, playerInitialRoom));
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (NumberFormatException e) {
      output.append("Please enter an integer for limit and room index! \n");
      output.append(String.format("\nNow, re-add No.%d player from beginning: \n",
          this.world.getAllPlayerNames().size() + 1));
      this.addOnePlayer();
    } catch (IllegalArgumentException e) {
      output.append(e.getMessage());
      output.append(String.format("\nNow, re-add No.%d player from beginning: \n",
          this.world.getAllPlayerNames().size() + 1));
      this.addOnePlayer();
    }
  }

  /**
   * Checks if the user input string represents "true" or "false" and returns a boolean value.
   *
   * @param in the user input string.
   * @return true if the input represents "true" or "yes," false if it represents "false" or "no."
   * @throws IllegalArgumentException if the input is not recognized as a valid boolean value.
   */
  private boolean checkInputStringTrueFalse(String in) {
    if (in != null) {
      in = in.toLowerCase(); // Convert the input to lowercase for case-insensitivity
      if ("yes".equals(in) || "y".equals(in)) {
        return true;
      } else if ("no".equals(in) || "n".equals(in)) {
        return false;
      }
    }
    throw new IllegalArgumentException("Only enter yes or no, y/n!!\n");
  }

}
