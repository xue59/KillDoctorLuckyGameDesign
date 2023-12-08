import controller.Controller;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Mock implementation of the Controller interface for GUI testing purposes.
 */
public class GuiControllerMockModel implements Controller {

  private final StringBuilder log;

  /**
   * Constructs a GuiControllerMockModel with a StringBuilder to log method calls.
   *
   * @param log StringBuilder to log method calls.
   */
  public GuiControllerMockModel(StringBuilder log) {
    this.log = log;
  }

  /**
   * Starts the game loop, allowing the user to interact with the game world. Displays a menu with
   * options to create a world map, add players, find a room, find a player, and start game turns.
   * The user can also execute Order 66 to quit the program.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  @Override
  public void startGame() throws IOException {
    this.log.append("startGame executed!");
  }

  /**
   * Restarts the game, resetting the world and player states.
   *
   * @throws FileNotFoundException if a required file is not found.
   */
  public void restartGame() throws FileNotFoundException {
    this.log.append("restartGame executed!");
  }

  /**
   * Displays the new world setup screen for GUI interaction.
   */
  public void guiNewWorldSetupScreen() {
    this.log.append("guiNewWorldSetupScreen executed!");
  }

  /**
   * Starts a new world based on user input.
   */
  public void startNewWorld() {
    this.log.append("startNewWorld executed!");
  }

  /**
   * Displays the quit game screen for GUI interaction.
   */
  public void guiQuitGame() {
    this.log.append("guiQuitGame executed!");
  }

  /**
   * Displays the game over screen for GUI interaction.
   */
  public void guiGameOverScreen() {
    this.log.append("guiGameOverScreen executed!");
  }

  /**
   * Executes the pet move action in response to user interaction.
   */
  public void guiExePetMove() {
    this.log.append("guiExePetMove executed!");
  }

  /**
   * Executes the move action in response to user interaction.
   */
  public void guiExeMove() {
    this.log.append("guiExeMove executed!");
  }

  /**
   * Executes the look action in response to user interaction.
   */
  public void guiExeLook() {
    this.log.append("guiExeLook executed!");
  }

  /**
   * Executes the pick action in response to user interaction.
   */
  public void guiExePick() {
    this.log.append("guiExePick executed!");
  }

  /**
   * Executes the attack action in response to user interaction.
   */
  public void guiExeAttack() {
    this.log.append("guiExeAttack executed!");
  }

  /**
   * Displays the start turn screen for GUI interaction.
   */
  public void startTurnScreen() {
    this.log.append("startTurnScreen executed!");
  }

  /**
   * Adds a new player to the game in response to user interaction.
   */
  public void addOnePlayer() {
    this.log.append("addOnePlayer executed!");
  }

  /**
   * Displays the add player screen for GUI interaction.
   */
  public void addPlayerScreen() {
    this.log.append("addPlayerScreen executed!");
  }

  /**
   * Displays the current player info window for GUI interaction.
   */
  public void showCurrentPlayerInfoWindow() {
    this.log.append("showCurrentPlayerInfoWindow executed!");
  }

  /**
   * Retrieves the current turn's begin info for testing purposes.
   *
   * @return Current turn's begin info.
   */
  public String getCurTurnBeginInfo() {
    this.log.append("getCurTurnBeginInfo executed!");
    return null;
  }

  /**
   * Retrieves the result of looking around for testing purposes.
   *
   * @return Result of looking around.
   */
  public String getLookAroundResult() {
    this.log.append("getLookAroundResult executed!");
    return null;
  }

  /**
   * Retrieves the result of picking an item for testing purposes.
   *
   * @return Result of picking an item.
   * @throws IllegalAccessException if picking an item is not allowed.
   */
  public String getPickResult() throws IllegalAccessException {
    this.log.append("getPickResult executed!");
    return null;
  }

  /**
   * Retrieves the result of moving for testing purposes.
   *
   * @return Result of moving.
   */
  public String getMoveResult() {
    this.log.append("getMoveResult executed!");
    return null;
  }

  /**
   * Retrieves the result of a pet move for testing purposes.
   *
   * @return Result of a pet move.
   */
  public String getPetMoveResult() {
    this.log.append("getPetMoveResult executed!");
    return null;
  }

  /**
   * Retrieves the current player's information for testing purposes.
   *
   * @return Current player's information.
   */
  public String getCurrentPlayerInfo() {
    this.log.append("getCurrentPlayerInfo executed!");
    return null;
  }
}
