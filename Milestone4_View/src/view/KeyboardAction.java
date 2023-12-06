package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class KeyboardAction extends KeyAdapter {
  private Map<Integer, Runnable> map;

  public KeyboardAction() {
    this.map = new HashMap<>();
  }

  public void setMap(Map<Integer, Runnable> inputMap) {
    this.map = inputMap;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if (map.containsKey(e.getKeyCode())) {
      map.get(e.getKeyCode()).run();
    }
  }
}
