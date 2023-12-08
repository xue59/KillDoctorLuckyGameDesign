import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for GuiControllerMockModel.
 */
public class GuiControllerMockModelTest {

  private GuiControllerMockModel guiControllerMockModel;
  private StringBuilder log;

  /**
   * Set up the test with String builder log.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    guiControllerMockModel = new GuiControllerMockModel(log);
  }

  /**
   * This method tests the {@link GuiControllerMockModel#startGame()} method.
   * It utilizes the Mock Model testing approach, which is built based on real
   * methods of the GuiController class. Each function call is logged in the
   * mock model to test and ensure the expected function is executed correctly.
   * For example, starting the game loop, creating a world map, adding players,
   * finding a room, finding a player, and starting game turns are all tested
   * through this method.
   *
   * @throws Exception if an exception occurs during the test.
   */
  @Test
  public void testStartGame() throws IOException {
    guiControllerMockModel.startGame();
    assertEquals("startGame executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#restartGame()} method.
   * It utilizes the Mock Model testing approach, which is built based on real
   * methods of the GuiController class. Each function call is logged in the
   * mock model to test and ensure the expected function is executed correctly.
   * For example, restarting the game is tested through this method.
   *
   * @throws Exception if an exception occurs during the test.
   */
  @Test
  public void testRestartGame() throws FileNotFoundException {
    guiControllerMockModel.restartGame();
    assertEquals("restartGame executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiNewWorldSetupScreen()} method.
   * It ensures that the GUI new world setup screen is executed correctly and logs the function
   * call.
   */
  @Test
  public void testGuiNewWorldSetupScreen() {
    guiControllerMockModel.guiNewWorldSetupScreen();
    assertEquals("guiNewWorldSetupScreen executed!", log.toString());
  }


  /**
   * This method tests the {@link GuiControllerMockModel#startNewWorld()} method.
   * It ensures that starting a new world is executed correctly and logs the function call.
   */
  @Test
  public void testStartNewWorld() {
    guiControllerMockModel.startNewWorld();
    assertEquals("startNewWorld executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiQuitGame()} method.
   * It ensures that quitting the game through the GUI is executed correctly and logs the
   * function call.
   */
  @Test
  public void testGuiQuitGame() {
    guiControllerMockModel.guiQuitGame();
    assertEquals("guiQuitGame executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiGameOverScreen()} method.
   * It ensures that displaying the GUI game over screen is executed correctly and logs the
   * function call.
   */
  @Test
  public void testGuiGameOverScreen() {
    guiControllerMockModel.guiGameOverScreen();
    assertEquals("guiGameOverScreen executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiExePetMove()} method.
   * It ensures that executing the GUI pet move is executed correctly and logs the function call.
   */
  @Test
  public void testGuiExePetMove() {
    guiControllerMockModel.guiExePetMove();
    assertEquals("guiExePetMove executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiExeMove()} method.
   * It ensures that executing the GUI move is executed correctly and logs the function call.
   */
  @Test
  public void testGuiExeMove() {
    guiControllerMockModel.guiExeMove();
    assertEquals("guiExeMove executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiExeLook()} method.
   * It ensures that executing the GUI look is executed correctly and logs the function call.
   */
  @Test
  public void testGuiExeLook() {
    guiControllerMockModel.guiExeLook();
    assertEquals("guiExeLook executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiExePick()} method.
   * It ensures that executing the GUI pick is executed correctly and logs the function call.
   */
  @Test
  public void testGuiExePick() {
    guiControllerMockModel.guiExePick();
    assertEquals("guiExePick executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#guiExeAttack()} method.
   * It ensures that executing the GUI attack is executed correctly and logs the function call.
   */
  @Test
  public void testGuiExeAttack() {
    guiControllerMockModel.guiExeAttack();
    assertEquals("guiExeAttack executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#startTurnScreen()} method.
   * It ensures that starting the turn screen is executed correctly and logs the function call.
   */
  @Test
  public void testStartTurnScreen() {
    guiControllerMockModel.startTurnScreen();
    assertEquals("startTurnScreen executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#addOnePlayer()} method.
   * It ensures that adding one player is executed correctly and logs the function call.
   */
  @Test
  public void testAddOnePlayer() {
    guiControllerMockModel.addOnePlayer();
    assertEquals("addOnePlayer executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#addPlayerScreen()} method.
   * It ensures that displaying the add player screen is executed correctly and logs the function
   * call.
   */
  @Test
  public void testAddPlayerScreen() {
    guiControllerMockModel.addPlayerScreen();
    assertEquals("addPlayerScreen executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#showCurrentPlayerInfoWindow()} method.
   * It ensures that showing the current player info window is executed correctly and logs the
   * function call.
   */
  @Test
  public void testShowCurrentPlayerInfoWindow() {
    guiControllerMockModel.showCurrentPlayerInfoWindow();
    assertEquals("showCurrentPlayerInfoWindow executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#getCurTurnBeginInfo()} method.
   * It ensures that retrieving the current turn begin information is executed correctly and logs
   * the function call.
   */
  @Test
  public void testGetCurTurnBeginInfo() {
    guiControllerMockModel.getCurTurnBeginInfo();
    assertEquals("getCurTurnBeginInfo executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#getLookAroundResult()} method.
   * It ensures that retrieving the look around result is executed correctly and logs the
   * function call.
   */
  @Test
  public void testGetLookAroundResult() {
    guiControllerMockModel.getLookAroundResult();
    assertEquals("getLookAroundResult executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#getPickResult()} method.
   * It ensures that retrieving the pick result is executed correctly and logs the function call.
   *
   * @throws IllegalAccessException if there is an illegal access exception during the test.
   */
  @Test
  public void testGetPickResult() throws IllegalAccessException {
    guiControllerMockModel.getPickResult();
    assertEquals("getPickResult executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#getMoveResult()} method.
   * It ensures that retrieving the move result is executed correctly and logs the function call.
   */
  @Test
  public void testGetMoveResult() {
    guiControllerMockModel.getMoveResult();
    assertEquals("getMoveResult executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#getPetMoveResult()} method.
   * It ensures that retrieving the pet move result is executed correctly and logs the function
   * call.
   */
  @Test
  public void testGetPetMoveResult() {
    guiControllerMockModel.getPetMoveResult();
    assertEquals("getPetMoveResult executed!", log.toString());
  }

  /**
   * This method tests the {@link GuiControllerMockModel#getCurrentPlayerInfo()} method.
   * It ensures that retrieving the current player information is executed correctly and logs the
   * function call.
   */
  @Test
  public void testGetCurrentPlayerInfo() {
    guiControllerMockModel.getCurrentPlayerInfo();
    assertEquals("getCurrentPlayerInfo executed!", log.toString());
  }
}
