package model.room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.item.Item;


/**
 * The RoomImplement class represents a room in a mansion. It implements the Room interface,
 * providing methods to interact with the room's attributes and items.
 */
public class RoomImplement implements Room {
  private final String roomName;
  private final int roomNumber;
  private final int topRowY;
  private final int topColX;
  private final int botRowY;
  private final int botColX;
  private final List<Item> itemList;

  /**
   * Constructs a RoomImplement object with the specified attributes.
   *
   * @param roomName   The name of the room.
   * @param roomNumber The room number.
   * @param topRowY    The top row Y-coordinate of the room.
   * @param topColX    The top column X-coordinate of the room.
   * @param botRowY    The bottom row Y-coordinate of the room.
   * @param botColX    The bottom column X-coordinate of the room.
   * @throws IllegalArgumentException If the room name is empty or null, the room number is
   *                                  negative, or the coordinates are invalid.
   */
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

  /**
   * Gets the name of the room.
   *
   * @return The name of the room.
   */
  @Override
  public String getRoomName() {
    return this.roomName;
  }

  /**
   * Gets the room number.
   *
   * @return The room number.
   */
  @Override
  public int getRoomNumber() {
    return this.roomNumber;
  }

  /**
   * Gets the coordinates of the room.
   *
   * @return An array of integers coordinates in the format [topColX, topRowY, botColX, botRowY].
   */
  @Override
  public int[] getRoomCoordinate() {
    int[] returnCoordinate = new int[4];
    returnCoordinate[0] = this.topColX;
    returnCoordinate[1] = this.topRowY;
    returnCoordinate[2] = this.botColX;
    returnCoordinate[3] = this.botRowY;
    return returnCoordinate;
  }

  /**
   * Gets the top row Y-coordinate of the room.
   *
   * @return The top row Y-coordinate.
   */
  public int getTopRowY() {
    return topRowY;
  }

  /**
   * Gets the top column X-coordinate of the room.
   *
   * @return The top column X-coordinate.
   */
  public int getTopColX() {
    return topColX;
  }

  /**
   * Gets the bottom column X-coordinate of the room.
   *
   * @return The bottom column X-coordinate.
   */
  public int getBotColX() {
    return botColX;
  }

  /**
   * Gets the bottom row Y-coordinate of the room.
   *
   * @return The bottom row Y-coordinate.
   */
  public int getBotRowY() {
    return botRowY;
  }

  /**
   * Gets a map of item names to their corresponding damage values for all items in the room.
   *
   * @return A map containing item names as keys and damage values as values.
   */
  @Override
  public Map<String, Integer> getAllItemsWithDamage() {
    Map<String, Integer> itemNameDamageMap = new HashMap<>();
    for (Item item : this.itemList) {
      itemNameDamageMap.put(item.getName(), item.getDamage());
    }
    return itemNameDamageMap;
  }

  /**
   * Gets an item with the specified name from the room.
   *
   * @param itemName The name of the item to retrieve.
   * @return The item with the specified name.
   * @throws IllegalArgumentException If the item name is empty or null, or if the item is not in
   *                                  the room.
   */
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

  /**
   * Adds an item to the room.
   *
   * @param addedItem The item to add.
   * @throws IllegalArgumentException If the item already exists in the room or the item is null.
   * @throws NullPointerException     If the added item is null.
   */
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

  /**
   * Removes an item from the room.
   *
   * @param removedItem The item to remove.
   * @throws IllegalArgumentException If the item does not exist in the room or the item is null.
   * @throws NullPointerException     If the removed item is null.
   */
  @Override
  public void removeOneItem(Item removedItem)
      throws IllegalArgumentException, NullPointerException {
    //check item cannot be Null
    if (removedItem == null) {
      throw new NullPointerException("Error: try to remove item cannot be empty or Null!");
    }

    //check if item is not in the room:
    if (!(this.itemList.contains(removedItem))) {
      throw new IllegalArgumentException("Error: Item is not in the room item list.");
    }
    //pass all validation:
    this.itemList.remove(removedItem);
  }

  /**
   * Computes the hash code of the room based on its name.
   *
   * @return The hash code of the room.
   */
  @Override
  public int hashCode() {
    return this.roomName.hashCode();
  }

  /**
   * Checks if the room is equal to another object.
   *
   * @param obj The object to compare.
   * @return True if the room is equal to the other object, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Room) {
      Room comparedRoom = (Room) obj;
      if (this.hashCode() == comparedRoom.hashCode()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * Returns a string representation of the room, including its name, number, and list of items.
   *
   * @return A string representation of the room.
   */
  @Override
  public String toString() {
    return String.format("#%d Room: %s, has items: %s", this.roomNumber, this.roomName,
        this.itemList);
  }
}
