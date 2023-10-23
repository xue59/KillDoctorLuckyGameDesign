package model.player;

import model.item.Item;
import java.util.Map;

/**
 * The {@code Player} interface represents a player in the game world. Players can interact with
 * their surroundings, pick up items, and manage their inventory.
 */
public interface Player {

  /**
   * Get the name of the player.
   *
   * @return The name of the player.
   */
  String getPlayerName();

  /**
   * Check if the player is controlled by a computer (AI).
   *
   * @return {@code true} if the player is controlled by a computer; {@code false} if it's a
   * human player.
   */
  boolean checkComputer();

  /**
   * Get the current room number where the player is located.
   *
   * @return The room number where the player is currently located.
   */
  int getCurrentRoomNumber();

  /**
   * Move the player to a specified room number.
   *
   * @param destinationRoomNum The room number to which the player should move.
   */
  void moveToRoomNumber(int destinationRoomNum);

  /**
   * Pick up an item and add it to the player's inventory.
   *
   * @param item The item to be picked up and added to the player's inventory.
   * @throws NullPointerException     If the provided item is null.
   * @throws IllegalArgumentException If the item cannot be picked up for any reason.
   * @throws IllegalAccessException   If the player's inventory is full and cannot pick up more
   * items.
   */
  void pickUpOneItem(Item item)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException;

  /**
   * Get the current capacity of the player's inventory.
   *
   * @return The maximum number of items the player can carry in their inventory.
   */
  int getCurrentCapacity();

  /**
   * Delete an item from the player's inventory.
   *
   * @param item The item to be removed from the player's inventory.
   */
  void deleteOneItem(Item item);

  /**
   * Get a map containing information about the items in the player's inventory. The map associates
   * item names with their respective quantities.
   *
   * @return A map where keys are item names, and values are the quantities of those items in the
   * player's inventory.
   */
  Map<String, Integer> getItemListMapInfo();
}

