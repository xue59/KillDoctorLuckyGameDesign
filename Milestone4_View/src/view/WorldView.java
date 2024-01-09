package view;

import java.util.List;

/**
 * The {WorldView} interface defines methods for interacting with the game world from the GUI.
 */
public interface WorldView {

  /**
   * Displays a general message in the game world.
   *
   * @param msg The message to be displayed.
   */
  void showMsg(String msg);

  /**
   * Displays an error message in the game world.
   *
   * @param errorMsg The error message to be displayed.
   */
  void showError(String errorMsg);

  /**
   * Adds action listeners to all buttons to the GUI.
   *
   * @param btnAction The action associated with the button.
   */
  void addActionButton(ButtonAction btnAction);

  /**
   * Adds a keyboard action to the GUI.
   *
   * @param keyboardAction The action associated with the keyboard input.
   */
  void addActionKeyboard(KeyboardAction keyboardAction);

  /**
   * Displays the welcome GUI.
   */
  void showWelcomeGui();

  /**
   * Displays the GUI for adding players to the game.
   *
   * @param totalRoomNumber The total number of rooms in the game.
   * @param totalPlayer     The total number of players in the game.
   * @param totalTurns      The total number of turns in the game.
   */
  void showAddPlayerGui(Integer totalRoomNumber, Integer totalPlayer, Integer totalTurns);

  /**
   * Enables the button for starting turns in the GUI.
   */
  void enableStartTurnsButton();

  /**
   * Disables or enables the "Add One Player" button in the GUI.
   *
   * @param input If true, enables the button; if false, disables the button.
   */
  void disableEnableAddOnePlayerButton(boolean input);

  /**
   * Gets input for adding a player from the GUI.
   *
   * @param totalRoomNumber   The total number of rooms in the game.
   * @param allPlayerNameList The list of all player names in the game.
   * @return An array of strings containing player input.
   */
  String[] getAddPlayerInputString(Integer totalRoomNumber, List<String> allPlayerNameList);

  /**
   * Displays the screen for starting a turn in the GUI.
   *
   * @param turnBeginInfo Information about the beginning of the turn.
   */
  void showStartTurnScreen(String turnBeginInfo);

  /**
   * Displays information about the player's surroundings in the GUI.
   *
   * @param lookResult The result of the "look around" action.
   */
  void showLookAroundInfo(String lookResult);

  /**
   * Displays information about picking up an item in the GUI.
   *
   * @param pickResult The result of the "pick" action.
   */
  void showPickInfo(String pickResult);

  /**
   * Displays information about attacking in the GUI.
   *
   * @param attackRes The result of the attack action.
   */
  void showAttackInfo(String attackRes);

  /**
   * Displays information about moving in the GUI.
   *
   * @param moveResult The result of the move action.
   */
  void showMoveInfo(String moveResult);

  /**
   * Displays information about a pet's movement in the GUI.
   *
   * @param petResult The result of the pet's movement action.
   */
  void showPetMoveInfo(String petResult);

  /**
   * Disables or enables all action buttons in the GUI.
   *
   * @param input If true, enables the buttons; if false, disables the buttons.
   */
  void disableEnableAllActionButtons(Boolean input);

  /**
   * Gets the status of a specific button in the GUI.
   *
   * @param inputButtonName The name of the button.
   * @return True if the button is active, false otherwise.
   */
  boolean getBtnStatus(String inputButtonName);

  /**
   * Updates the screen in the GUI.
   */
  void updateScreen();

  /**
   * Displays the game over screen in the GUI.
   *
   * @param winnerName The name of the winner.
   */
  void showGameOverScreen(String winnerName);

  /**
   * Displays the screen for entering a new world input file in the GUI.
   */
  void showNewWorldInputFileScreen();

  /**
   * Gets information about the new world from the input file in the GUI.
   *
   * @return An array of strings containing information about the new world.
   */
  String[] getNewWorldFileTurnPlayerInfo();
}
