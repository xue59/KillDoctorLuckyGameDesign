package controller;

import command.LookAroundCmd;
import command.MovePlayerCmd;
import command.PetMoveCmd;
import command.PlayerPickCmd;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import model.world.World;
import view.ButtonAction;
import view.KeyboardAction;
import view.WorldView;


public class GuiControllerImplement implements Controller{
  private final World world;
  private final WorldView view;
  private final int totalAllowedPlayers;
  private final int totalAllowedTurns;
  private boolean quitFlag;
  private int curTurnNum;

  public GuiControllerImplement(World world, WorldView worldView) throws NullPointerException{
    this.world = world;
    Objects.requireNonNull(worldView);
    this.view = worldView;
    this.totalAllowedPlayers = world.getTotalAllowedPlayers();
    this.totalAllowedTurns = world.getTotalAllowedTurns();
    this.quitFlag = false;
    this.curTurnNum = 0;
    this.addKeyboardAction();
  }

  @Override
  public void startGame() throws IOException {
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

    ButtonAction buttonAction = new ButtonAction();
    buttonAction.setMap(buttonActionMap);
    this.view.addActionButton(buttonAction);

  }

  private void addKeyboardAction(){
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    keyPresses.put(KeyEvent.VK_L, () -> {
      System.out.println("L key pressed");
      this.guiExeLook();
    });

    KeyboardAction keyboardAction = new KeyboardAction();
    keyboardAction.setMap(keyPresses);
    System.out.println("this.view.addActionKeyboard(keyboardAction); executed");
    this.view.addActionKeyboard(keyboardAction);
  }

  private void guiExePetMove(){

    if(world.checkGameOver()){
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      System.out.println("Now PetMove clicked! \n");
      this.view.showPetMoveInfo(this.getPetMoveResult());
      this.view.disableEnableAllActionButtons(false);
    }
  }

  private void guiExeMove(){
    if(world.checkGameOver()){
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      System.out.println("Now Move clicked! \n");
      this.view.showMoveInfo(this.getMoveResult());
      this.view.disableEnableAllActionButtons(false);
    }
  }

  private void guiExeLook(){
    if (!this.view.getBtnStatus("LOOK")){
      this.view.showError("Cannot perform Action at this turn!\n");
    }
    if(world.checkGameOver()){
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      System.out.println("Now look around clicked! \n");
      this.view.showLookAroundInfo(this.getLookAroundResult());
      this.view.disableEnableAllActionButtons(false);
    }
  }

  private void guiExePick() {
    try{
      if(world.checkGameOver()){
        this.view.showError("Game Over! Cannot play!\n");
      } else {
        System.out.println("Now pick clicked! \n");
        this.view.showPickInfo(this.getPickResult());
        this.view.disableEnableAllActionButtons(false);
      }
    } catch (IllegalAccessException e ){
      this.view.showError(e.getMessage());
    }
  }

  private void guiExeAttack(){
    try{
      if(world.checkGameOver()){
        this.view.showError("Game Over! Cannot play!\n");
      } else {
        System.out.println("Now attack clicked! \n");
        this.view.showAttackInfo(this.world.getCurPlayerBestKillResult());
        this.view.disableEnableAllActionButtons(false);
      }
    } catch (IllegalAccessException e){
      this.view.showError(e.getMessage());
    }

  }

  private void startTurnScreen(){
    if(world.checkGameOver()){
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      System.out.println("Now start turns clicked! \n");
      this.view.disableEnableAllActionButtons(true);
      this.world.createGraphBufferedImage();
      this.view.showStartTurnScreen(this.getCurTurnBeginInfo());
      this.curTurnNum++;
    }

  }
  private void addOnePlayer(){
    String[] inputString =
        this.view.getAddPlayerInputString(world.getTotalOfRoom(), world.getAllPlayerNames());
    System.out.println("Add one player clicked: " + inputString);
    if (Objects.nonNull(inputString)){
      try{
        String playerName = inputString[0];
        int playerLimit = Integer.parseInt(inputString[1]);
        int playerInitialRoom = Integer.parseInt(inputString[2]);
        boolean isComputer = "true".equals(inputString[3]);
        this.world.addOnePlayer(playerName, playerInitialRoom, isComputer, playerLimit);

        this.view.showMsg(String.format("Player: %s (limit: %d) successfully added into Room #%d\n",
            playerName, playerLimit, playerInitialRoom));

        //after adding player success check if can start play turns
        // disable add new player button
        if (world.getTotalAllowedPlayers() == world.getAllPlayerNames().size()){
          this.view.enableStartTurnsButton();
          this.view.disableAddOnePlayerButton();
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

  private void addPlayerScreen() {
    //timer.stop();

    if(world.checkGameOver()){
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      System.out.println("Now addPlayerScreen clicked! \n");
      this.view.showAddPlayerGui(this.world.getTotalOfRoom());
    }
  }

  private String getCurTurnBeginInfo(){
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

  private String getLookAroundResult(){
      // using command design pattern to execute the Look command
      StringBuilder output = new StringBuilder();
      LookAroundCmd cmdLook = new LookAroundCmd();
      output.append(cmdLook.execute(this.world));
      return output.toString();
  }

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

  private String getMoveResult(){
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

  private String getPetMoveResult(){
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

}
