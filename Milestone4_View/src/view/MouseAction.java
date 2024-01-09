package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * The MouseAction class extends MouseAdapter and provides a mechanism to map mouse clicks to
 * specific actions.
 * It allows associating mouse button clicks with corresponding actions using a Map.
 */
public class MouseAction extends MouseAdapter {

  private Map<Integer, Consumer<String>> map;

  /**
   * Constructs a new {@code MouseAction} with an empty action map.
   */
  public MouseAction() {
    this.map = new HashMap<>();
  }

  /**
   * Sets the mouse action map to the provided map.
   *
   * @param inputMap A Map associating mouse button codes with  Consumer String actions.
   */
  public void setMouse(Map<Integer, Consumer<String>> inputMap) {
    this.map = inputMap;
  }

  /**
   * Invoked when a mouse button has been clicked.
   * Executes the action associated with the clicked mouse button.
   *
   * @param e The {@code MouseEvent} associated with the click.
   */
  @Override
  public void mouseClicked(MouseEvent e) {
    map.get(e.getButton()).accept(e.getComponent().getName());
  }
}
