package model.room;

import java.util.Map;
import model.item.Item;

/**
 * The Room interface represents a room or location within a game world.
 * Rooms have various attributes and can contain items.
 */
public interface Room {

  /**
   * Gets the name of the room.
   *
   * @return The name of the room.
   */
  String getRoomName();

  /**
   * Gets the room number, which uniquely identifies the room.
   *
   * @return The room number.
   */
  int getRoomNumber();

  /**
   * Gets the coordinates of the room in the game world.
   *
   * @return An array of integers representing the room's coordinates.
   */
  int[] getRoomCoordinate();

  /**
   * Gets the Y-coordinate of the top row of the room.
   *
   * @return The Y-coordinate of the top row.
   */
  int getTopRowY();

  /**
   * Gets the X-coordinate of the top column of the room.
   *
   * @return The X-coordinate of the top column.
   */
  int getTopColX();

  /**
   * Gets the X-coordinate of the bottom column of the room.
   *
   * @return The X-coordinate of the bottom column.
   */
  int getBotColX();

  /**
   * Gets the Y-coordinate of the bottom row of the room.
   *
   * @return The Y-coordinate of the bottom row.
   */
  int getBotRowY();

  /**
   * Gets a map of all items in the room along with their associated damage values.
   *
   * @return A map of item names to their damage values.
   */
  Map<String, Integer> getAllItemsWithDamage();

  /**
   * Gets a specific item from the room by its name.
   *
   * @param itemName The name of the item to retrieve.
   * @return The item object if found, or throws an exception if not found.
   * @throws IllegalArgumentException If the item name is not valid.
   */
  Item getOneItem(String itemName) throws IllegalArgumentException;

  /**
   * Adds an item to the room.
   *
   * @param addedItem The item to be added.
   * @throws IllegalArgumentException If the item cannot be added to the room.
   * @throws NullPointerException     If the provided item is null.
   */
  void addOneItem(Item addedItem) throws IllegalArgumentException, NullPointerException;

  /**
   * Removes an item from the room.
   *
   * @param removedItem The item to be removed.
   * @throws IllegalArgumentException If the item cannot be removed from the room.
   * @throws NullPointerException     If the provided item is null.
   */
  void removeOneItem(Item removedItem) throws IllegalArgumentException, NullPointerException;
}