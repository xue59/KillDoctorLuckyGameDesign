package model.room;

import java.util.Map;
import model.item.Item;

public interface Room {
  String getRoomName();

  int[] getRoomCoordinate();

  Map<String, Integer> getAllItemsWithDamage();

  Item getOneItem(String itemName) throws IllegalArgumentException;
  void addOneItem(Item addedItem) throws IllegalArgumentException, NullPointerException;
  void removeOneItem(Item removedItem) throws IllegalArgumentException, NullPointerException;


}
