package model.room;

import java.util.Map;
import model.item.Item;

public interface Room {
  String getRoomName();
  int getRoomNumber();

  int[] getRoomCoordinate();
  public int getTopRowY();
  public int getTopColX();
  public int getBotColX();
  public int getBotRowY();

    Map<String, Integer> getAllItemsWithDamage();

  Item getOneItem(String itemName) throws IllegalArgumentException;
  void addOneItem(Item addedItem) throws IllegalArgumentException, NullPointerException;
  void removeOneItem(Item removedItem) throws IllegalArgumentException, NullPointerException;


}
