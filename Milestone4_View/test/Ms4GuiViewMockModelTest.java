import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for GuiViewMockModel.
 */
public class Ms4GuiViewMockModelTest {

  // Log to track method calls
  private StringBuilder log;

  // Instance of GuiViewMockModel for testing
  private GuiViewMockModel guiViewMockModel;

  /**
   * Initializes the log and GuiViewMockModel before each test.
   */
  @Before
  public void setUp() {
    log = new StringBuilder();
    guiViewMockModel = new GuiViewMockModel(log);
  }

  /**
   * Test method for {@link GuiViewMockModel#showMsg(String)}.
   */
  @Test
  public void testShowMsg() {
    guiViewMockModel.showMsg("Test Message");
    assertEquals("showMsg executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showError(String)}.
   */
  @Test
  public void testShowError() {
    guiViewMockModel.showError("Test Error");
    assertEquals("showError executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showWelcomeGui()}.
   */
  @Test
  public void testShowWelcomeGui() {
    guiViewMockModel.showWelcomeGui();
    assertEquals("showWelcomeGui executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showAddPlayerGui(Integer, Integer, Integer)}.
   */
  @Test
  public void testShowAddPlayerGui() {
    guiViewMockModel.showAddPlayerGui(5, 3, 10);
    assertEquals("showAddPlayerGui executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#enableStartTurnsButton()}.
   */
  @Test
  public void testEnableStartTurnsButton() {
    guiViewMockModel.enableStartTurnsButton();
    assertEquals("enableStartTurnsButton executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#disableEnableAddOnePlayerButton(boolean)}.
   */
  @Test
  public void testDisableEnableAddOnePlayerButton() {
    guiViewMockModel.disableEnableAddOnePlayerButton(true);
    assertEquals("disableEnableAddOnePlayerButton executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#getAddPlayerInputString(Integer, List)}.
   */
  @Test
  public void testGetAddPlayerInputString() {
    List<String> playerNames = Arrays.asList("Player1", "Player2", "Player3");
    guiViewMockModel.getAddPlayerInputString(10, playerNames);
    assertEquals("getAddPlayerInputString executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showStartTurnScreen(String)}.
   */
  @Test
  public void testShowStartTurnScreen() {
    guiViewMockModel.showStartTurnScreen("Turn 1 begins!");
    assertEquals("showStartTurnScreen executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showLookAroundInfo(String)}.
   */
  @Test
  public void testShowLookAroundInfo() {
    guiViewMockModel.showLookAroundInfo("You see a room.");
    assertEquals("showLookAroundInfo executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showPickInfo(String)}.
   */
  @Test
  public void testShowPickInfo() {
    guiViewMockModel.showPickInfo("Picked up an item.");
    assertEquals("showPickInfo executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showAttackInfo(String)}.
   */
  @Test
  public void testShowAttackInfo() {
    guiViewMockModel.showAttackInfo("Attacked successfully!");
    assertEquals("showAttackInfo executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showMoveInfo(String)}.
   */
  @Test
  public void testShowMoveInfo() {
    guiViewMockModel.showMoveInfo("Moved to a new room.");
    assertEquals("showMoveInfo executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showPetMoveInfo(String)}.
   */
  @Test
  public void testShowPetMoveInfo() {
    guiViewMockModel.showPetMoveInfo("Your pet moved.");
    assertEquals("showPetMoveInfo executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#disableEnableAllActionButtons(Boolean)}.
   */
  @Test
  public void testDisableEnableAllActionButtons() {
    guiViewMockModel.disableEnableAllActionButtons(true);
    assertEquals("disableEnableAllActionButtons executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#getBtnStatus(String)}.
   */
  @Test
  public void testGetBtnStatus() {
    guiViewMockModel.getBtnStatus("TestButton");
    assertEquals("getBtnStatus executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#updateScreen()}.
   */
  @Test
  public void testUpdateScreen() {
    guiViewMockModel.updateScreen();
    assertEquals("updateScreen executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showGameOverScreen(String)}.
   */
  @Test
  public void testShowGameOverScreen() {
    guiViewMockModel.showGameOverScreen("Winner");
    assertEquals("showGameOverScreen executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#showNewWorldInputFileScreen()}.
   */
  @Test
  public void testShowNewWorldInputFileScreen() {
    guiViewMockModel.showNewWorldInputFileScreen();
    assertEquals("showNewWorldInputFileScreen executed!", log.toString());
  }

  /**
   * Test method for {@link GuiViewMockModel#getNewWorldFileTurnPlayerInfo()}.
   */
  @Test
  public void testGetNewWorldFileTurnPlayerInfo() {
    guiViewMockModel.getNewWorldFileTurnPlayerInfo();
    assertEquals("getNewWorldFileTurnPlayerInfo executed!", log.toString());
  }

}
