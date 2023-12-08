package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The ButtonAction class implements the ActionListener interface and provides a mechanism to
 * map button actions to specific actions.
 * It allows associating action command strings with corresponding Runnable actions using a Map.
 */
public class ButtonAction implements ActionListener {

  private Map<String, Runnable> map;

  /**
   * Constructs a new ButtonAction with an empty action map.
   */
  public ButtonAction() {
    this.map = new HashMap<>();
  }

  /**
   * Sets the button action map to the provided map.
   *
   * @param inputMap A Map associating action command strings with Runnable actions.
   */
  public void setMap(Map<String, Runnable> inputMap) {
    this.map = inputMap;
  }

  /**
   * Invoked when an action occurs.
   * Executes the action associated with the action command, if available in the action map.
   *
   * @param e The ActionEvent associated with the button action.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (map.containsKey(e.getActionCommand())) {
      map.get(e.getActionCommand()).run();
    }
  }
}
