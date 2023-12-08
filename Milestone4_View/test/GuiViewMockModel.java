import java.util.List;
import view.ButtonAction;
import view.KeyboardAction;
import view.WorldView;

/**
 * Mock implementation of the WorldView interface for testing purposes.
 * This class appends method call names to a log StringBuilder for tracking execution.
 */
public class GuiViewMockModel implements WorldView {
  /**
   * The log to store method call names.
   */
  private final StringBuilder log;

  /**
   * Constructs a new {@code GuiViewMockModel} with the specified log.
   *
   * @param outputLog The {@code StringBuilder} log to store method call names.
   */
  public GuiViewMockModel(StringBuilder outputLog) {
    this.log = outputLog;
  }

  /**
   * Appends "showMsg executed!" to the log.
   *
   * @param msg The message to be shown.
   */
  @Override
  public void showMsg(String msg) {
    this.log.append("showMsg executed!");
  }

  /**
   * Appends "showError executed!" to the log.
   *
   * @param errorMsg The error message to be shown.
   */
  @Override
  public void showError(String errorMsg) {
    this.log.append("showError executed!");
  }

  /**
   * Appends "addActionButton executed!" to the log.
   *
   * @param btnAction The {@link ButtonAction} to be added.
   */
  @Override
  public void addActionButton(ButtonAction btnAction) {
    this.log.append("addActionButton executed!");
  }

  /**
   * Appends "addActionKeyboard executed!" to the log.
   *
   * @param keyboardAction The {@link KeyboardAction} to be added.
   */
  @Override
  public void addActionKeyboard(KeyboardAction keyboardAction) {
    this.log.append("addActionKeyboard executed!");
  }

  /**
   * Appends "showWelcomeGui executed!" to the log.
   */
  @Override
  public void showWelcomeGui() {
    this.log.append("showWelcomeGui executed!");
  }

  /**
   * Appends "showAddPlayerGui executed!" to the log.
   *
   * @param totalRoomNumber Total room number in the game.
   * @param totalPlayer     Total player count in the game.
   * @param totalTurns      Total turns in the game.
   */
  @Override
  public void showAddPlayerGui(Integer totalRoomNumber, Integer totalPlayer, Integer totalTurns) {
    this.log.append("showAddPlayerGui executed!");
  }

  /**
   * Appends "enableStartTurnsButton executed!" to the log.
   */
  @Override
  public void enableStartTurnsButton() {
    this.log.append("enableStartTurnsButton executed!");
  }

  /**
   * Appends "disableEnableAddOnePlayerButton executed!" to the log.
   *
   * @param input The input boolean value.
   */
  @Override
  public void disableEnableAddOnePlayerButton(boolean input) {
    this.log.append("disableEnableAddOnePlayerButton executed!");
  }

  /**
   * Appends "getAddPlayerInputString executed!" to the log.
   *
   * @param totalRoomNumber   Total room number in the game.
   * @param allPlayerNameList List of all player names.
   * @return An array of strings representing player input.
   */
  @Override
  public String[] getAddPlayerInputString(Integer totalRoomNumber, List<String> allPlayerNameList) {
    this.log.append("getAddPlayerInputString executed!");
    return new String[0];
  }

  /**
   * Appends "showStartTurnScreen executed!" to the log.
   *
   * @param turnBeginInfo Information about the beginning of a turn.
   */
  @Override
  public void showStartTurnScreen(String turnBeginInfo) {
    this.log.append("showStartTurnScreen executed!");
  }

  /**
   * Appends "showLookAroundInfo executed!" to the log.
   *
   * @param lookResult The result of looking around in the game.
   */
  @Override
  public void showLookAroundInfo(String lookResult) {
    this.log.append("showLookAroundInfo executed!");
  }

  /**
   * Appends "showPickInfo executed!" to the log.
   *
   * @param pickResult The result of picking an item in the game.
   */
  @Override
  public void showPickInfo(String pickResult) {
    this.log.append("showPickInfo executed!");
  }

  /**
   * Appends "showAttackInfo executed!" to the log.
   *
   * @param attackRes The result of an attack in the game.
   */
  @Override
  public void showAttackInfo(String attackRes) {
    this.log.append("showAttackInfo executed!");
  }

  /**
   * Appends "showMoveInfo executed!" to the log.
   *
   * @param moveResult The result of moving in the game.
   */
  @Override
  public void showMoveInfo(String moveResult) {
    this.log.append("showMoveInfo executed!");
  }

  /**
   * Appends "showPetMoveInfo executed!" to the log.
   *
   * @param petResult The result of a pet's movement in the game.
   */
  @Override
  public void showPetMoveInfo(String petResult) {
    this.log.append("showPetMoveInfo executed!");
  }

  /**
   * Appends "disableEnableAllActionButtons executed!" to the log.
   *
   * @param input The input boolean value.
   */
  @Override
  public void disableEnableAllActionButtons(Boolean input) {
    this.log.append("disableEnableAllActionButtons executed!");
  }

  /**
   * Appends "getBtnStatus executed!" to the log.
   *
   * @param inputButtonName The name of the button.
   * @return The status of the button.
   */
  @Override
  public boolean getBtnStatus(String inputButtonName) {
    this.log.append("getBtnStatus executed!");
    return false;
  }

  /**
   * Appends "updateScreen executed!" to the log.
   */
  @Override
  public void updateScreen() {
    this.log.append("updateScreen executed!");
  }

  /**
   * Appends "showGameOverScreen executed!" to the log.
   *
   * @param winnerName The name of the winner.
   */
  @Override
  public void showGameOverScreen(String winnerName) {
    this.log.append("showGameOverScreen executed!");
  }

  /**
   * Appends "showNewWorldInputFileScreen executed!" to the log.
   */
  @Override
  public void showNewWorldInputFileScreen() {
    this.log.append("showNewWorldInputFileScreen executed!");
  }

  /**
   * Appends "getNewWorldFileTurnPlayerInfo executed!" to the log.
   *
   * @return An array of strings representing information about a new world file.
   */
  @Override
  public String[] getNewWorldFileTurnPlayerInfo() {
    this.log.append("getNewWorldFileTurnPlayerInfo executed!");
    return new String[0];
  }
}

