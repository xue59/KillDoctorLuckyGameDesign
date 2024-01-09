package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * The KeyboardAction class extends {KeyAdapter} and provides a mechanism to
 * map key presses to specific actions.
 * It allows associating key codes with corresponding actions using a {@code Map}.
 */
public class KeyboardAction extends KeyAdapter {

  private Map<Integer, Runnable> map;

  /**
   * Constructs a new {KeyboardAction} with an empty action map.
   */
  public KeyboardAction() {
    this.map = new HashMap<>();
  }

  /**
   * Sets the keyboard action map to the provided map.
   *
   * @param inputMap A {Map} associating key codes with {Runnable} actions.
   */
  public void setMap(Map<Integer, Runnable> inputMap) {
    this.map = inputMap;
  }

  /**
   * Invoked when a key has been pressed.
   * Executes the action associated with the pressed key, if available in the action map.
   *
   * @param e The {KeyEvent} associated with the key press.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (map.containsKey(e.getKeyCode())) {
      map.get(e.getKeyCode()).run();
    }
  }
}
