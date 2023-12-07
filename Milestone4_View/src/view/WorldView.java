package view;

import java.util.List;
import java.util.Map;

public interface WorldView {

  String showItems(Map<String, Integer> items);

  void showMsg(String msg);
  void showError(String errorMsg);

  void setupNewGameWindow();
  void addActionButton(ButtonAction btnAction);
  void addActionKeyboard(KeyboardAction keyboardAction);
  void showWelcomeGui();
  void showAddPlayerGui(Integer totalRoomNumber);
  void enableStartTurnsButton();
  void disableAddOnePlayerButton();
  String[] getAddPlayerInputString(Integer totalRoomNumber, List<String> allPlayerNameList);

  void showStartTurnScreen(String turnBeginInfo);
  void showLookAroundInfo(String lookResult);
  void showPickInfo(String pickResult);
  void showAttackInfo(String attackRes);
  void showMoveInfo(String moveResult);
  void showPetMoveInfo(String petResult);
  void disableEnableAllActionButtons(Boolean input);
  boolean getBtnStatus(String inputButtonName);


}
