package controller;

import java.io.IOException;

public interface Controller {
  /**
   * Starts the game loop, allowing the user to interact with the game world.
   * Displays a menu with options to create a world map, add players, find a room,
   * find a player, and start game turns. The user can also execute Order 66 to quit the program.
   *
   * @throws IOException if an I/O error occurs while interacting with the game.
   */
  void startGame() throws IOException;
}
