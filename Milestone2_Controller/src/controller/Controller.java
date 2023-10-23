package controller;

import java.io.IOException;

/**
 * The CmdControllerImplement class is responsible for controlling and managing the command-line
 * interface (CLI) for interacting with a game world.
 * It provides methods for processing user input and generating output to the specified Appendable.
 * This controller handles commands to move players, perform actions, look around, and pick up
 * items in the game world.
 * <p>
 * The controller communicates with the provided World model to execute commands and retrieve game
 * state information.
 *
 * @see Controller
 */
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
