package model.world;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * The World interface represents the game world, which consists of rooms and entities.
 * It provides various methods for interacting with the game world.
 */
public interface World {

  /**
   * Gets a list of neighboring rooms for a given room name.
   *
   * @param roomName The name of the room.
   * @return A list of neighboring room names.
   * @throws IllegalArgumentException If the room name is not valid.
   * @throws NullPointerException     If the provided room name is null.
   */
  List<String> getNeighborsRoomList(String roomName)
      throws IllegalArgumentException, NullPointerException;

  /**
   * Gets the name of the world.
   *
   * @return The name of the world.
   */
  String getWorldName();

  /**
   * Gets the total number of rooms in the world.
   *
   * @return The total number of rooms.
   */
  int getTotalOfRoom();

  /**
   * Gets the total number of items in the world.
   *
   * @return The total number of items.
   */
  int getTotalOfItem();

  /**
   * Gets information about a specific room in the world.
   *
   * @param roomName The name of the room.
   * @return Information about the room.
   * @throws IllegalArgumentException If the room name is not valid.
   * @throws NullPointerException     If the provided room name is null.
   */
  String getOneRoomInfo(String roomName)
      throws IllegalArgumentException, NullPointerException;

  /**
   * Moves Dr. Lucky within the world.
   */
  void moveDrLucky();

  /**
   * Creates a graphical representation of the world as a BufferedImage.
   *
   * @return An image representation of the world.
   */
  BufferedImage createGraphBufferedImage();

  /**
   * Prints the neighbor map of the world to the console.
   */
  void printWorldNeighborMap();

  /**
   * Prints a 2D array representation of the world to the console.
   */
  void printWorld2dArray();

  /**
   * Gets information about Dr. Lucky within the world.
   *
   * @return Information about Dr. Lucky.
   */
  String getDrLuckyInfo();

  /**
   * Prints information about all rooms in the world to the console.
   */
  void printAllRoomInfo();

  /**
   * Add a human or computer player into the game world.
   *
   * @param name           String of the player name.
   * @param initialRoomNum Int of player's initial room number index base 0.
   * @param limit          Player's limit for carrying number of items.
   * @param checkComputer  Ture if it is a computer player, otherwise false.
   * @throws IllegalArgumentException Due to invalid input.
   * @throws NullPointerException     Due to invalid input.
   */
  void addOnePlayer(String name, int initialRoomNum, boolean checkComputer, int limit)
      throws IllegalArgumentException, NullPointerException;

  /**
   * Gets the total number of players allowed in the game.
   *
   * @return The total number of players allowed in the game.
   */
  int getTotalAllowedPlayers();

  /**
   * Sets the total allowed number of players in the game.
   *
   * @param totalAllowedPlayers The total number of players allowed in the game.
   */
  void setTotalAllowedPlayers(int totalAllowedPlayers);

  /**
   * Gets the total number of turns allowed in the game.
   *
   * @return The total number of turns allowed in the game.
   */
  int getTotalAllowedTurns();

  /**
   * Sets the total allowed number of turns in the game.
   *
   * @param totalAllowedTurns The total number of turns allowed in the game.
   */
  void setTotalAllowedTurns(int totalAllowedTurns);

  /**
   * Perform a computer player's action in the game and return the result.
   *
   * @return The result of the computer player's action.
   * @throws IllegalStateException  If the game is illegal state for the computer player's action.
   * @throws IllegalAccessException If there is an illegal access attempt during the action.
   */
  String cmdComputerPlayerAction()
      throws IllegalStateException, IllegalAccessException;

  /**
   * Move the current player to the specified room.
   *
   * @param roomName The name of the room to which the player should move.
   * @throws IllegalArgumentException If the provided room name is not valid.
   * @throws IllegalAccessException   If there is an illegal access attempt during the move.
   */
  void cmdPlayerMove(String roomName)
      throws IllegalArgumentException, IllegalAccessException;

  /**
   * Move the Pet to the specified room.
   *
   * @param roomName The name of the room to which the player should move.
   * @throws IllegalArgumentException If the provided room name is not valid.
   * @throws IllegalAccessException   If there is an illegal access attempt during the move.
   */
  void cmdPetMove(String roomName)
      throws IllegalArgumentException, IllegalAccessException;

  /**
   * Get information about the current player's current room.
   *
   * @return Information about the current player's current room.
   * @throws IllegalArgumentException If the current player's room information is not valid.
   */
  String cmdPlayerLook()
      throws IllegalArgumentException;

  /**
   * Allow the current player to pick up an item in the room.
   *
   * @param inputItemName The name of the item to be picked up.
   * @throws NullPointerException     If the item name is null.
   * @throws IllegalArgumentException If item name is not valid, or if picking up is not allowed.
   * @throws IllegalAccessException   If there is an illegal access attempt during item pickup.
   * @throws IllegalStateException    If item pickup is not allowed in the current state.
   */
  void cmdPlayerPick(String inputItemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException,
      IllegalStateException;

  /**
   * Get information about the items that a player can pick up in the current room.
   *
   * @param playerName The name of the player for which to retrieve item information.
   * @return Information about the items that the player can pick up in the room.
   */
  String getPlayerWhatCanPickInfo(String playerName);

  /**
   * Check if the game is over.
   *
   * @return True if the game is over, false otherwise.
   */
  boolean checkGameOver();

  /**
   * Gets a list of names of all players in the game world.
   *
   * @return A list of player names.
   */
  List<String> getAllPlayerNames();

  /**
   * Gets information about all players in the game world.
   *
   * @return Information about all players.
   */
  String getAllPlayerInfo();

  /**
   * Gets a list of names of all rooms in the game world.
   *
   * @return A list of room names.
   */
  List<String> getAllRoomNames();

  /**
   * Gets information about a specific player and their current room.
   *
   * @param playerName The name of the player for which to retrieve information.
   * @return Information about the specified player and their current room.
   */
  String getOnePlayerAndRoomInfo(String playerName);


  /**
   * Gets the room name of the current player in the game.
   *
   * @param playerName The name of the player for which to retrieve information.
   * @return The room name string of where the current player is in.
   */
  String getOnePlayerCurrentRoomName(String playerName);

  /**
   * Gets the name of the current player in the game.
   *
   * @return The name String of the current player.
   */
  String getCurrentPlayerName();

  /**
   * Checks if the current player is a computer player.
   *
   * @return True if the current player is a computer player, false otherwise.
   */
  boolean isCurrentPlayerComputer();

  /**
   * Gets the index of the current player.
   *
   * @return The index of the current player.
   */
  int getCurrentPlayerIndex();

  /**
   * Gets the current turn number in the game.
   *
   * @return The current turn number.
   */
  int getCurrentTurnNumber();

  String getPetName();

  /**
   * Checks if the current player is in the same room as Dr. Lucky.
   *
   * @return {@code true} if the current player is in the same room as Dr. Lucky, false otherwise.
   */
  boolean checkCurPlayerSameRoomWithDrLucky();
  boolean checkCurPlayerCanBeSeen();
  String cmdPlayerKill(String itemName)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException;

  String getPlayerAllCarryingItemStringWithDamage(String playerName);
  String getWinnerName();
}