package model.room;

import model.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomImplement implements Room {
  private final String roomName;
  private final int roomNumber;
  private final int topRowY;
  private final int topColX;
  private final int botRowY;
  private final int botColX;
  private final List<Item> itemList;

  public RoomImplement(String roomName, int roomNumber, int topRowY, int topColX, int botRowY,
      int botColX) throws IllegalArgumentException {
    //check room name cannot be empty or Null
    if (roomName.isEmpty() || roomName == null) {
      throw new IllegalArgumentException("Error: room name can not be empty or null.");
    }
    //check room number index must larger or equal to 0
    if (roomNumber < 0) {
      throw new IllegalArgumentException("Error: room number can not be negative!");
    }
    //check the room coordinate must be valid of top left & bot right
    // coordinate cannot be negative, top must < bot; left must <right
    if (topColX >= botColX) {
      throw new IllegalArgumentException("Error: top left col X must be less than right bot X!");
    }
    if (topRowY >= botRowY) {
      throw new IllegalArgumentException("Error: top row Y must be less than right bot row Y!");
    }
    if (topColX < 0 || topRowY < 0 || botColX < 0 || botRowY < 0) {
      throw new IllegalArgumentException("Error: Room coordinate cannot be negative!");
    }
    //After passing all validation assign local private var:
    this.roomName = roomName;
    this.roomNumber = roomNumber;
    this.topRowY = topRowY;
    this.topColX = topColX;
    this.botRowY = botRowY;
    this.botColX = botColX;
    this.itemList = new ArrayList<>();
  }

  @Override
  public String getRoomName() {
    return this.roomName;
  }

  @Override
  public int getRoomNumber(){
    return this.roomNumber;
  }

  @Override
  public int[] getRoomCoordinate() {
    int[] returnCoordinate = new int[4];
    returnCoordinate[0] = this.topColX;
    returnCoordinate[1] = this.topRowY;
    returnCoordinate[2] = this.botColX;
    returnCoordinate[3] = this.botRowY;
    return returnCoordinate;
  }

  public int getTopRowY() {
    return topRowY;
  }

  public int getTopColX() {
    return topColX;
  }

  public int getBotColX() {
    return botColX;
  }

  public int getBotRowY() {
    return botRowY;
  }

  @Override
  public Map<String, Integer> getAllItemsWithDamage() {
    Map<String, Integer> itemNameDamageMap = new HashMap<>();
    for (Item item : this.itemList) {
      itemNameDamageMap.put(item.getName(), item.getDamage());
    }
    return itemNameDamageMap;
  }

  @Override
  public Item getOneItem(String itemName) throws IllegalArgumentException {
    if (itemName.isEmpty() || itemName == null) {
      throw new IllegalArgumentException("Error: cannot input get a empty itemName");
    }

    for (Item item : this.itemList) {
      if (itemName.equals(item.getName()) && item != null) {
        return item;
      }
    }
    throw new IllegalArgumentException(
        String.format("Error: %s not in Room %d %s", itemName, this.roomNumber, this.roomName));
  }

  @Override
  public void addOneItem(Item addedItem) throws IllegalArgumentException, NullPointerException {
    //check item cannot be Null
    if (addedItem == null) {
      throw new NullPointerException("Error: input item cannot be empty or Null!");
    }
    //check the addedItem cannot exist in the current room.
    if (this.itemList.contains(addedItem)) {
      throw new IllegalArgumentException(
          String.format("Error: %s already in the room %s cannot add again!", addedItem.getName(),
              this.roomName));
    }
    // after checking can be added to the room
    this.itemList.add(addedItem);
  }

  @Override
  public void removeOneItem(Item removedItem) throws IllegalArgumentException, NullPointerException {
    //check item cannot be Null
    if (removedItem == null) {
      throw new NullPointerException("Error: try to remove item cannot be empty or Null!");
    }

    //check if item is not in the room:
    if (!(this.itemList.contains(removedItem))){
      throw new IllegalArgumentException("Error: Item is not in the room item list.");
    }
    //pass all validation:
    this.itemList.remove(removedItem);
  }

  @Override
  public int hashCode(){
    return this.roomName.hashCode();
  }

  @Override
  public boolean equals(Object obj){
    if (obj instanceof Room){
      Room comparedRoom = (Room) obj;
      if(this.hashCode() == comparedRoom.hashCode()){
        return true;
      } else{
        return false;
      }
    }else {
      return false;
    }
  }
  @Override
  public String toString(){
    return String.format(
        "Room Info Name= %s \nItems in this Room and damage= %s \n", this.roomName, this.itemList);
  }
}
