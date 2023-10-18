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
   * @param name
   * @param initialRoomNum
   * @param limit
   * @param checkComputer
   * @throws IllegalArgumentException
   * @throws NullPointerException
   */
  void addOnePlayer(String name, int initialRoomNum, boolean checkComputer, int limit)
      throws IllegalArgumentException, NullPointerException;

  void setTotalAllowedTurns(int totalAllowedTurns);
  void setTotalAllowedPlayers(int totalAllowedPlayers);

  void cmdPlayerMove(String roomName)
      throws IllegalAccessException, IllegalArgumentException, IllegalAccessException;
  boolean checkGameOver();
  int getCurrentTurn();
}