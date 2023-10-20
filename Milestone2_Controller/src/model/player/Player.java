package model.player;

import model.item.Item;
import java.util.Map;

public interface Player {
  String getPlayerName();

  boolean checkComputer();

  int getCurrentRoomNumber();

  void moveToRoomNumber(int destinationRoomNum);

  void pickUpOneItem(Item item) throws NullPointerException, IllegalArgumentException,
      IllegalAccessException;

  void deleteOneItem(Item item);

  Map<String, Integer> getItemListMapInfo();

}
