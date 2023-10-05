package model.room;

import java.util.Map;
import model.item.Item;

public interface Room {
  String getRoomName();

  int[] getRoomLocation();

  Map<String, Integer> getAllItemsWithDamage();

  Item getOneItem(String itemName) throws IllegalArgumentException, NullPointerException;
  void addOneItem(String itemName) throws IllegalArgumentException, NullPointerException;
  void removeOneItem(Item itemObj) throws IllegalArgumentException, NullPointerException;


}
