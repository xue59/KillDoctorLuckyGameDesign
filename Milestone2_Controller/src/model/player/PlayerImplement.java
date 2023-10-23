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

  /**
   * Constructs a player with the specified name, initial room number, computer status, and item
   * limit.
   *
   * @param name           The name of the player.
   * @param initialRoomNum The initial room number where the player is located.
   * @param checkComputer  A flag indicating whether the player is controlled by a computer.
   * @param limit          The maximum number of items the player can carry in their inventory.
   * @throws IllegalArgumentException If the name is null, empty, or blank; if the initial room
   *                                  number is negative;
   *                                  if the item limit is non-positive.
   */
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
      throw new IllegalArgumentException("PlayerImplement Error: player:" + name + " item " +
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
   * Get the name of the player.
   *
   * @return The name of the player.
   */
  @Override
  public String getPlayerName() {
    return this.name;
  }

  /**
   * Get the current room number where the player is located.
   *
   * @return The room number where the player is currently located.
   */
  @Override
  public int getCurrentRoomNumber() {
    return this.curRoomNum;
  }

  /**
   * Check if the player is controlled by a computer (AI).
   *
   * @return {@code true} if the player is controlled by a computer; {@code false} if it's a
   * human player.
   */
  @Override
  public boolean checkComputer() {
    return this.checkComputer;
  }

  /**
   * Pick up an item and add it to the player's inventory.
   *
   * @param item The item to be picked up and added to the player's inventory.
   * @throws NullPointerException     If the provided item is null.
   * @throws IllegalArgumentException If the item cannot be picked up for any reason.
   * @throws IllegalAccessException   If the player's inventory is full and cannot pick up more
   *                                  items.
   */
  @Override
  public void pickUpOneItem(Item item)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException {
    // check item cannot null
    if (item == null) {
      throw new NullPointerException("Error pickUpOneItem: item to pickup cannot be null!");
    }

    //check if the item player already duplicated, player cannot have two same item
    for (Item existItem : this.itemList) {
      if (existItem.equals(item)) {
        throw new IllegalArgumentException(
            "Error pickUpOneItem: player-" + this.name + " already " +
                "have" + existItem.getName() + ", can't pickup!");
      }
    }

    // check new picked item cannot over player item limit
    if ((this.itemList.size() + 1) > this.limit) {
      throw new IllegalAccessException(
          "Error pickUpOneItem: player-" + this.name + " item list is " +
              "full, can't pickup: " + item.getName());
    }
    // pass all the check then add to item list
    this.itemList.add(item);

  }

  /**
   * Get the current capacity of the player's inventory.
   *
   * @return The maximum number of items the player can carry in their inventory.
   */
  @Override
  public int getCurrentCapacity() {
    int curCapacity;
    curCapacity = this.limit - this.itemList.size();
    return curCapacity;
  }


  /**
   * Get a map containing information about the items in the player's inventory. The map associates
   * item names with their respective quantities.
   *
   * @return A map where keys are item names, and values are the quantities of those items in the
   * player's inventory.
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
   * Get a map containing information about items in the player's inventory. The map associates
   * item names with their respective quantities.
   *
   * @return A map where keys are item names, and values are the quantities of those items in the
   * player's inventory.
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
   * Delete an item from the player's inventory.
   *
   * @param item The item to be removed from the player's inventory.
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
            item.getName())));
  }

  /**
   * Returns a string representation of the player, including their type, name, limit, remaining
   * capacity, and the items they are carrying.
   *
   * @return A string representation of the player.
   */

  @Override
  public String toString() {
    if (this.checkComputer) {
      // is computer
      return String.format("Player type: **Computer Player**\n"
              + "Player's Name: %s \n"
              + "Player's limit: %d, can still carry: %d\n"
              + "Carrying: %s \n",
          this.name, this.limit, (this.limit - this.itemList.size()), itemList);
    } else {
      // is human player
      return String.format("Player type: Human Player\n"
              + "Player's Name: %s \n"
              + "Player's limit: %d, can still carry: %d\n"
              + "Carrying: %s \n",
          this.name, this.limit, (this.limit - this.itemList.size()), itemList);
    }
  }

  /**
   * Computes the hash code for the player based on their name.
   *
   * @return The hash code of the player.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

  /**
   * Checks if this player is equal to another object by comparing their hash codes.
   *
   * @param obj The object to compare with.
   * @return {@code true} if the player is equal to the provided object based on their hash codes;
   * {@code false} otherwise.
   */
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
