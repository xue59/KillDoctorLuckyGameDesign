package model.player;

import model.item.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PlayerImplement implements Player {
  private final String name;
  private int curRoomNum;
  private final boolean checkComputer;
  private final int limit;
  private final List<Item> itemList;

  public PlayerImplement(String name, int initialRoomNum, boolean checkComputer, int limit) {
    // check make sure the input are valid if not valid throw errors
    // check name is not null
    if (name == null || name.isEmpty() || name.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "PlayerImplement Error: Player name can not be null, empty or black!");
    }
    // check room number is not negative
    if (initialRoomNum < 0) {
      throw new IllegalArgumentException("PlayerImplement Error: initial room number cannot be " +
          "negative!");
    }
    // check limit cannot be negative
    if (limit < 1) {
      throw new IllegalArgumentException("PlayerImplement Error: player:"+ name +" item " +
          "limit cannot be negative or zero!!");
    }
    // assign to the local variable
    this.name = name;
    this.curRoomNum = initialRoomNum;
    this.checkComputer = checkComputer;
    this.limit = limit;
    this.itemList = new ArrayList<>();
  }

  /**
   * @return
   */
  @Override
  public String getPlayerName() {
    return this.name;
  }

  /**
   * @return
   */
  @Override
  public int getCurrentRoomNumber() {
    return this.curRoomNum;
  }

  /**
   * @return
   */
  @Override
  public boolean checkComputer() {
    return this.checkComputer;
  }

  /**
   * @param item
   */
  @Override
  public void pickUpOneItem(Item item) {
    // check item cannot null
    if (item == null) {
      throw new NullPointerException("Error pickUpOneItem: item to pickup cannot be null!");
    }

    //check if the item player already duplicated, player cannot have two same item
    for (Item existItem : this.itemList) {
      if (existItem.equals(item)) {
        throw new IllegalArgumentException("Error pickUpOneItem: player-"+this.name+" already " +
            "have" + existItem.getName()+", can't pickup!");
      }
    }

    // check new picked item cannot over player item limit
    if ((this.itemList.size() + 1) > this.limit) {
      throw new IllegalStateException("Error pickUpOneItem: player-"+this.name+" itemlist is " +
          "full, can't pickup: "+ item.getName());
    }
    // pass all the check then add to item list
    this.itemList.add(item);

  }


  /**
   * @param destinationRoomNum
   */
  @Override
  public void moveToRoomNumber(int destinationRoomNum) {
    if (destinationRoomNum >= 0) {
      this.curRoomNum = destinationRoomNum;
    } else {
      throw new IllegalArgumentException("Error moveToRoomNumber(): invalid room number!");
    }
  }

  /**
   * @return
   */
  @Override
  public Map<String, Integer> getItemListMapInfo() {
    Map<String, Integer> itemListMapInfo = new HashMap<>();

    for (Item item : this.itemList) {
      String itemName = item.getName();
      int itemDamage = item.getDamage();
      itemListMapInfo.put(itemName, itemDamage);
    }
    return itemListMapInfo;
  }

  /**
   *
   */
  @Override
  public void deleteOneItem(Item item) {
    for (Item existItem : this.itemList) {
      if (existItem.equals(item)) {
        this.itemList.remove(existItem);
        return;
      }
    }
    throw new IllegalArgumentException(
        ("Item delete error Error: Player: %s doesn't have item: %s".format(this.getPlayerName(),
            item.getName() )));
  }

  @Override
  public String toString(){
    if (this.checkComputer){
      // is computer
      return String.format("Player type: **Computer Player**\n"
              + "Player's Name: %s \n"
              + "Player's limit: %d, can still carry: %d\n"
              + "Carrying: %s \n",
          this.name, this.limit, (this.limit - this.itemList.size()), itemList);
    }else{
      // is human player
      return String.format("Player type: Human Player\n"
              + "Player's Name: %s \n"
              + "Player's limit: %d, can still carry: %d\n"
              + "Carrying: %s \n",
          this.name, this.limit, (this.limit - this.itemList.size()), itemList);
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Player) {
      Player comparedPlayer = (Player) obj;
      if (this.hashCode() == comparedPlayer.hashCode()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }


}
