package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ButtonAction  implements ActionListener {
  private Map<String, Runnable> map;

  public ButtonAction() {
    this.map = new HashMap<>();
  }

  public void setMap(Map<String, Runnable> inputMap) {
    this.map = inputMap;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (map.containsKey(e.getActionCommand())) {
      map.get(e.getActionCommand()).run();
    }
  }
}
