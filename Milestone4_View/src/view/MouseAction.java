package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MouseAction extends MouseAdapter{
  private Map<Integer, Consumer<String>> map;
  public MouseAction() {
    this.map = new HashMap<>();
  }


  public void setMouse(Map<Integer, Consumer<String>> inputMap) {
    this.map = inputMap;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    map.get(e.getButton()).accept(e.getComponent().getName());
  }
}
