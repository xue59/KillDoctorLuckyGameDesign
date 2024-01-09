package controller;

import command.LookAroundCmd;
import command.MovePlayerCmd;
import command.PetMoveCmd;
import command.PlayerPickCmd;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.world.CreateWorldHelper;
import model.world.World;
import view.ButtonAction;
import view.KeyboardAction;
import view.WorldView;


/**
 * Constructs a GuiControllerImplement instance with Controller interface.
 * This is the concrete class for the GUI controller class which control the Kill Dr Lucky GUI.
 */
public class GuiControllerImplement implements Controller {
  private final WorldView view;
  private final StringBuilder log;
  private World world;
  private int totalAllowedPlayers;
  private int totalAllowedTurns;
  private int curTurnNum;
  private String curFileName;

  /**
   * Constructs a GuiControllerImplement instance with following input.
   *
   * @param world       The world model.
   * @param worldView   The associated view.
   * @param curFileName The current file name.
   * @param log         The log for recording events.
   * @throws NullPointerException If the worldView is null.
   */
  public GuiControllerImplement(World world, WorldView worldView, String curFileName,
                                StringBuilder log)
      throws NullPointerException {
    this.world = world;
    Objects.requireNonNull(worldView);
    this.view = worldView;
    this.totalAllowedPlayers = world.getTotalAllowedPlayers();
    this.totalAllowedTurns = world.getTotalAllowedTurns();
    this.curTurnNum = 0;
    this.addKeyboardAction();
    this.curFileName = curFileName;
    this.log = log;
  }

  /**
   * Starts the game, initializes the view, and sets up button actions.
   */
  @Override
  public void startGame() {
    this.log.append("startGame executed!");
    this.view.showWelcomeGui();
    Map<String, Runnable> buttonActionMap = new HashMap<>();

    buttonActionMap.put("ADD_PLAYER_SCREEN", () -> {
      this.addPlayerScreen();
    });

    buttonActionMap.put("ADD_ONE_PLAYER", () -> {
      this.addOnePlayer();
    });

    buttonActionMap.put("START_TURNS", () -> {
      this.startTurnScreen();
      this.view.updateScreen();
    });

    buttonActionMap.put("LOOK", () -> {
      this.guiExeLook();
    });

    buttonActionMap.put("PICK", () -> {
      this.guiExePick();
    });

    buttonActionMap.put("ATTACK", () -> {
      this.guiExeAttack();
    });

    buttonActionMap.put("MOVE", () -> {
      this.guiExeMove();
    });

    buttonActionMap.put("PETMOVE", () -> {
      this.guiExePetMove();
    });

    buttonActionMap.put("SHOW_PLAYER_INFO", () -> {
      this.showCurrentPlayerInfoWindow();
    });

    buttonActionMap.put("QUIT", () -> {
      this.guiQuitGame();
    });

    buttonActionMap.put("RESTART", () -> {
      try {
        this.restartGame();
      } catch (FileNotFoundException e) {
        this.view.showError(e.getMessage());
      }
    });

    buttonActionMap.put("NEW_WORLD_SETUP", () -> {
      this.guiNewWorldSetupScreen();
    });

    buttonActionMap.put("START_NEW_WORLD", () -> {
      try {
        this.startNewWorld();
      } catch (FileNotFoundException e) {
        this.view.showError(e.getMessage());
      }
    });

    ButtonAction buttonAction = new ButtonAction();
    buttonAction.setMap(buttonActionMap);
    this.view.addActionButton(buttonAction);

  }

  /**
   * Adds keyboard actions for specific key events.
   */
  private void addKeyboardAction() {
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    keyPresses.put(KeyEvent.VK_L, () -> {
      this.guiExeLook();
    });

    keyPresses.put(KeyEvent.VK_M, () -> {
      this.guiExeMove();
    });

    keyPresses.put(KeyEvent.VK_A, () -> {
      this.guiExeAttack();
    });

    keyPresses.put(KeyEvent.VK_P, () -> {
      this.guiExePick();
    });

    keyPresses.put(KeyEvent.VK_T, () -> {
      this.guiExePetMove();
    });

    KeyboardAction keyboardAction = new KeyboardAction();
    keyboardAction.setMap(keyPresses);
    this.view.addActionKeyboard(keyboardAction);
  }

  /**
   * Restarts the game using the current file name and updates the view.
   *
   * @throws FileNotFoundException If the file is not found.
   */
  private void restartGame() throws FileNotFoundException {
    this.log.append("restartGame executed!");
    FileReader fileReader = new FileReader(this.curFileName);
    CreateWorldHelper createHelper = new CreateWorldHelper().readBuildTxtFile(fileReader);
    World restartWorld = createHelper.createWorld();
    //mainWorld.printWorld2dArray(); // print 2d world
    restartWorld.setTotalAllowedPlayers(this.totalAllowedPlayers); //set total players
    restartWorld.setTotalAllowedTurns(this.totalAllowedTurns); //set total turns
    System.out.println("Restart Game & rebuild world model GUI View >>>>>>>>>>:");
    this.world = restartWorld;
    this.view.showWelcomeGui();
    this.view.showMsg(String.format("Restart Game Word(%s) Success!\n", world.getWorldName()));
    this.view.disableEnableAllActionButtons(true);
    this.view.disableEnableAddOnePlayerButton(true);
  }

  /**
   * Displays the screen for setting up a new world using a file.
   */
  private void guiNewWorldSetupScreen() {
    this.view.showNewWorldInputFileScreen();
  }

  /**
   * Starts a new world using the selected file and restarts the game.
   *
   * @throws FileNotFoundException If the file is not found.
   */
  private void startNewWorld() throws FileNotFoundException {
    String[] res = this.view.getNewWorldFileTurnPlayerInfo();
    this.curFileName = res[0];
    this.totalAllowedTurns = Integer.parseInt(res[1]);
    this.totalAllowedPlayers = Integer.parseInt(res[2]);
    this.restartGame();
  }

  /**
   * Displays the quit game screen and exits the program.
   */
  private void guiQuitGame() {
    System.exit(0);
  }

  /**
   * Displays the game over screen.
   */
  private void guiGameOverScreen() {
    this.view.showGameOverScreen(world.getWinnerName());
  }

  /**
   * Executes the PETMOVE action and updates the view.
   */
  private void guiExePetMove() {
    if (!this.view.getBtnStatus("PETMOVE")) {
      this.view.showError("Cannot perform PETMOVE at this turn!\n");
      return;
    }
    if (world.checkGameOver()) {
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      //System.out.println("Now PetMove clicked! \n");
      this.view.showPetMoveInfo(this.getPetMoveResult());
      this.view.disableEnableAllActionButtons(false);
    }
  }

  /**
   * Executes the MOVE action and updates the view.
   */
  private void guiExeMove() {
    if (!this.view.getBtnStatus("MOVE")) {
      this.view.showError("Cannot perform MOVE at this turn!\n");
      return;
    }
    if (world.checkGameOver()) {
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      //System.out.println("Now Move clicked! \n");
      this.view.showMoveInfo(this.getMoveResult());
      this.view.disableEnableAllActionButtons(false);
    }
  }

  /**
   * Executes the LOOK action and updates the view.
   */
  private void guiExeLook() {
    if (!this.view.getBtnStatus("LOOK")) {
      this.view.showError("Cannot perform LOOK at this turn!\n");
      return;
    }
    if (world.checkGameOver()) {
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      //System.out.println("Now look around clicked! \n");
      this.view.showLookAroundInfo(this.getLookAroundResult());
      this.view.disableEnableAllActionButtons(false);
    }
  }

  /**
   * Executes the PICK action and updates the view.
   */
  private void guiExePick() {
    if (!this.view.getBtnStatus("PICK")) {
      this.view.showError("Cannot perform PICK at this turn!\n");
      return;
    }
    try {
      if (world.checkGameOver()) {
        this.view.showError("Game Over! Cannot play!\n");
      } else {
        //System.out.println("Now pick clicked! \n");
        this.view.showPickInfo(this.getPickResult());
        this.view.disableEnableAllActionButtons(false);
      }
    } catch (IllegalAccessException e) {
      this.view.showError(e.getMessage());
    }
  }

  /**
   * Executes the ATTACK action and updates the view.
   */
  private void guiExeAttack() {
    if (!this.view.getBtnStatus("ATTACK")) {
      this.view.showError("Cannot perform ATTACK at this turn!\n");
      return;
    }
    try {
      if (world.checkGameOver()) {
        this.view.showError("Game Over! Cannot play!\n");
      } else {
        //System.out.println("Now attack clicked! \n");
        this.view.showAttackInfo(this.world.getCurPlayerBestKillResult());
        this.view.disableEnableAllActionButtons(false);
      }
    } catch (IllegalAccessException e) {
      this.view.showError(e.getMessage());
    }
  }

  /**
   * Displays the start turn screen, updates the view, and checks for game over.
   */
  private void startTurnScreen() {
    this.view.updateScreen();
    if (world.checkGameOver()) {
      this.view.showMsg("Game Over! Cannot play!\nClick finish to see game result!");
      this.guiGameOverScreen(); // jump to game over screen
    } else {
      //System.out.println("Now start turns clicked! \n");
      this.view.disableEnableAllActionButtons(true);
      this.world.createGraphBufferedImage();
      this.view.showStartTurnScreen(this.getCurTurnBeginInfo());
      this.curTurnNum++;
    }
  }

  /**
   * Show adding a new player GUI to the world and updates the view.
   */
  private void addOnePlayer() {
    String[] inputString =
        this.view.getAddPlayerInputString(world.getTotalOfRoom(), world.getAllPlayerNames());
    //System.out.println("Add one player clicked: " + inputString);
    if (Objects.nonNull(inputString)) {
      try {
        String playerName = inputString[0];
        int playerLimit = Integer.parseInt(inputString[1]);
        int playerInitialRoom = Integer.parseInt(inputString[2]);
        boolean isComputer = "true".equals(inputString[3]);
        this.world.addOnePlayer(playerName, playerInitialRoom, isComputer, playerLimit);

        this.view.showMsg(String.format("Player: %s (limit: %d) successfully added into Room #%d\n",
            playerName, playerLimit, playerInitialRoom));

        //after adding player success check if it can start play turns
        // disable add new player button
        if (world.getTotalAllowedPlayers() == world.getAllPlayerNames().size()) {
          this.view.enableStartTurnsButton();
          this.view.disableEnableAddOnePlayerButton(false); //人满了就不能添加人 button了
        }

      } catch (NumberFormatException e) {
        this.view.showError("Please enter an integer for limit and room index! \n");
        this.view.showMsg(String.format("\nNow, re-add No.%d player from beginning: \n",
            this.world.getAllPlayerNames().size() + 1));
      } catch (IllegalArgumentException e) {
        this.view.showError(e.getMessage());
        this.view.showMsg(String.format("\nNow, re-add No.%d player from beginning: \n",
            this.world.getAllPlayerNames().size() + 1));
      }
    } else {
      //this.view.showError("Input player info cannot be Null!\n");
    }
  }

  /**
   * Displays the add player screen.
   */
  private void addPlayerScreen() {
    if (world.checkGameOver()) {
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      this.view.showAddPlayerGui(this.world.getTotalOfRoom(), totalAllowedPlayers,
          totalAllowedTurns);
    }
  }

  /**
   * Displays the current player information window.
   */
  private void showCurrentPlayerInfoWindow() {
    this.view.showMsg(this.getCurrentPlayerInfo());
  }

  /**
   * Retrieves the information about the beginning of the current turn.
   *
   * @return Information about the beginning of the current turn.
   */
  private String getCurTurnBeginInfo() {
    String curTurnPlayerName = world.getCurrentPlayerName();
    boolean isCurPlayerComputer = world.isCurrentPlayerComputer();
    StringBuilder output = new StringBuilder();
    output.append(String.format("\nTurn #%d Current Player Status: \n%s",
        curTurnNum, world.getOnePlayerAndRoomInfo(curTurnPlayerName)));
    output.append(String.format("%s\n", world.getDrLuckyInfo()));
    if (this.world.checkCurPlayerSameRoomWithDrLucky()) {
      output.append(
          String.format("Current Turn #%d for player: %s. (Available commands: "
                  + "[Move, Look, Pick, PetMove, Attack])\n",
              curTurnNum, curTurnPlayerName));
    } else {
      output.append(
          String.format("Current Turn #%d for player: %s. (Available commands: "
                  + "[Move, Look, Pick, PetMove] (Can not Attack, due to no Dr.Lucky))\n",
              curTurnNum, curTurnPlayerName));
    }
    return output.toString();
  }

  /**
   * Retrieves the result of the LOOK action.
   *
   * @return The result of the LOOK action.
   */
  private String getLookAroundResult() {
    // using command design pattern to execute the Look command
    LookAroundCmd cmdLook = new LookAroundCmd();
    return String.valueOf(cmdLook.execute(this.world));
  }

  /**
   * Retrieves the result of the PICK action.
   *
   * @return The result of the PICK action.
   * @throws IllegalAccessException If Illegal access occurs during the action.
   */
  private String getPickResult() throws IllegalAccessException {
    String highItemInRoom = world.getCurPlayerRoomHighItemName();
    String curTurnPlayerName = world.getCurrentPlayerName();
    String cmdPickResult;
    StringBuilder output = new StringBuilder();

    PlayerPickCmd cmdPick = new PlayerPickCmd(highItemInRoom);
    try {
      cmdPickResult = cmdPick.execute(this.world);
      if (!cmdPickResult.isEmpty()) {
        output.append(cmdPickResult);
        output.append(String.format("Player: %s picked up item: %s SUCCESS!\n",
            curTurnPlayerName, highItemInRoom));
      }
      return output.toString();
    } catch (IllegalAccessException e) {
      output.append(e.getMessage());
      throw new IllegalAccessException("Can't PICK, your bag is Full, try other commands!\n");
    } catch (IllegalStateException e) { // Game Over state!
      throw new IllegalStateException(String.format("Game Over! Player:%s Cannot pick up item!"
          + " %s\n", curTurnPlayerName, highItemInRoom));
    } catch (IllegalArgumentException e) {
      output.append(e.getMessage());
      output.append("\nCheck the item name for typos and case sensitivity!\n");
    } catch (NullPointerException e) {
      output.append("Item name cannot be null! Try a valid Item name.\n");
    }
    return output.toString();
  }

  /**
   * Retrieves the result of the MOVE action.
   *
   * @return The result of the MOVE action.
   */
  private String getMoveResult() {
    String moveToRoomName = world.getCurrentPlayerOneNeighborRoom();
    String curTurnPlayerName = world.getCurrentPlayerName();
    StringBuilder output = new StringBuilder();
    try {
      MovePlayerCmd cmdMove = new MovePlayerCmd(moveToRoomName);
      String moveResult;
      moveResult = cmdMove.execute(this.world);
      if (!moveResult.isEmpty()) {
        output.append(String.format("Player: %s moved to room: %s SUCCESS!\n",
            curTurnPlayerName, moveToRoomName));
      }
      return output.toString();
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
    return output.toString();
  }

  /**
   * Retrieves the result of the PETMOVE action.
   *
   * @return The result of the PETMOVE action.
   */
  private String getPetMoveResult() {
    String curTurnPlayerName = world.getCurrentPlayerName();
    String curPlayerRoomName = world.getOnePlayerCurrentRoomName(curTurnPlayerName);
    String petName = world.getPetName();
    StringBuilder output = new StringBuilder();
    try {
      PetMoveCmd cmdPetMove = new PetMoveCmd(curPlayerRoomName);
      String moveResult;
      moveResult = cmdPetMove.execute(this.world);
      if (!moveResult.isEmpty()) {
        output.append(String.format("Player: %s moved Pet(%s) to their own room: %s SUCCESS!\n",
            curTurnPlayerName, petName, curPlayerRoomName));
      }
      return output.toString();
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
    return output.toString();
  }

  /**
   * Retrieves information about the current player.
   *
   * @return Information about the current player.
   */
  private String getCurrentPlayerInfo() {
    String inputName = world.getCurrentPlayerName();
    return world.getOnePlayerAndRoomInfo(inputName);
  }

}
