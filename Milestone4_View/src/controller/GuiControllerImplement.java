package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import command.LookAroundCmd;
import model.world.World;
import view.ButtonAction;
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
      this.lookAroundTextBoxUpdate();
    });

    ButtonAction buttonAction = new ButtonAction();
    buttonAction.setMap(buttonActionMap);
    this.view.addActionButton(buttonAction);
  }

  private void lookAroundTextBoxUpdate(){
    if(world.checkGameOver()){
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      System.out.println("Now look around clicked! \n");
      this.view.showLookAroundInfo(this.getLookAroundResult());
    }
  }

  private void startTurnScreen(){
    if(world.checkGameOver()){
      this.view.showError("Game Over! Cannot play!\n");
    } else {
      System.out.println("Now start turns clicked! \n");
      this.world.createGraphBufferedImage();
      this.view.showStartTurnScreen(this.getCurTurnBeginInfo());
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

}
