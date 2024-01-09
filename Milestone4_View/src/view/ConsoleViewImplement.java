package view;

import java.io.IOException;
import java.util.List;

/**
 * This concrete class is the View for Console controller.
 * It is used to display Kill Dr Lucky Game info on a Console command line interface or text
 * based game. Other non-defined method are not used for this console interface class.
 */
public class ConsoleViewImplement implements WorldView {
  private final Appendable output; // output appendable

  /**
   * Construct the console view output appendable.
   *
   * @param output Appendable output to the screen for display.
   */
  public ConsoleViewImplement(Appendable output) {
    this.output = output;
  }

  /**
   * Displays a general message in the game world for console view.
   *
   * @param msg The message to be displayed.
   */
  @Override
  public void showMsg(String msg) {
    try {
      this.output.append(msg);
    } catch (IOException e) {
      System.out.println("Error in console view: ");
      System.out.println(e.getMessage());
    }
  }

  /**
   * Displays an error message in the game world.
   *
   * @param errorMsg The error message to be displayed.
   */
  @Override
  public void showError(String errorMsg) {

  }

  /**
   * Adds an action button to the GUI.
   *
   * @param btnAction The action associated with the button.
   */
  @Override
  public void addActionButton(ButtonAction btnAction) {

  }

  /**
   * Adds a keyboard action to the GUI.
   *
   * @param keyboardAction The action associated with the keyboard input.
   */
  @Override
  public void addActionKeyboard(KeyboardAction keyboardAction) {

  }

  /**
   * Displays the welcome GUI.
   */
  @Override
  public void showWelcomeGui() {

  }

  /**
   * Displays the GUI for adding players to the game.
   *
   * @param totalRoomNumber The total number of rooms in the game.
   * @param totalPlayer     The total number of players in the game.
   * @param totalTurns      The total number of turns in the game.
   */
  @Override
  public void showAddPlayerGui(Integer totalRoomNumber, Integer totalPlayer, Integer totalTurns) {

  }

  /**
   * Enables the button for starting turns in the GUI.
   */
  @Override
  public void enableStartTurnsButton() {

  }

  /**
   * Disables or enables the "Add One Player" button in the GUI.
   *
   * @param input If true, enables the button; if false, disables the button.
   */
  @Override
  public void disableEnableAddOnePlayerButton(boolean input) {

  }

  /**
   * Gets input for adding a player from the GUI.
   *
   * @param totalRoomNumber   The total number of rooms in the game.
   * @param allPlayerNameList The list of all player names in the game.
   * @return An array of strings containing player input.
   */
  @Override
  public String[] getAddPlayerInputString(Integer totalRoomNumber, List<String> allPlayerNameList) {
    return new String[0];
  }

  /**
   * Displays the screen for starting a turn in the GUI.
   *
   * @param turnBeginInfo Information about the beginning of the turn.
   */
  @Override
  public void showStartTurnScreen(String turnBeginInfo) {

  }

  /**
   * Displays information about the player's surroundings in the GUI.
   *
   * @param lookResult The result of the "look around" action.
   */
  @Override
  public void showLookAroundInfo(String lookResult) {

  }

  /**
   * Displays information about picking up an item in the GUI.
   *
   * @param pickResult The result of the "pick" action.
   */
  @Override
  public void showPickInfo(String pickResult) {

  }

  /**
   * Displays information about attacking in the GUI.
   *
   * @param attackRes The result of the attack action.
   */
  @Override
  public void showAttackInfo(String attackRes) {

  }

  /**
   * Displays information about moving in the GUI.
   *
   * @param moveResult The result of the move action.
   */
  @Override
  public void showMoveInfo(String moveResult) {

  }

  /**
   * Displays information about a pet's movement in the GUI.
   *
   * @param petResult The result of the pet's movement action.
   */
  @Override
  public void showPetMoveInfo(String petResult) {

  }

  /**
   * Disables or enables all action buttons in the GUI.
   *
   * @param input If true, enables the buttons; if false, disables the buttons.
   */
  @Override
  public void disableEnableAllActionButtons(Boolean input) {

  }

  /**
   * Gets the status of a specific button in the GUI.
   *
   * @param inputButtonName The name of the button.
   * @return True if the button is active, false otherwise.
   */
  @Override
  public boolean getBtnStatus(String inputButtonName) {
    return false;
  }

  /**
   * Updates the screen in the GUI.
   */
  @Override
  public void updateScreen() {

  }

  /**
   * Displays the game over screen in the GUI.
   *
   * @param winnerName The name of the winner.
   */
  @Override
  public void showGameOverScreen(String winnerName) {

  }

  /**
   * Displays the screen for entering a new world input file in the GUI.
   */
  @Override
  public void showNewWorldInputFileScreen() {

  }

  /**
   * Gets information about the new world from the input file in the GUI.
   *
   * @return An array of strings containing information about the new world.
   */
  @Override
  public String[] getNewWorldFileTurnPlayerInfo() {
    return new String[0];
  }
}
