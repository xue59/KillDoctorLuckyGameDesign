package command;

import model.world.World;

/**
 * The {@code LookAroundCmd} class implements {@link WorldCommand} interface to represent a command
 * for a player to look around the game world. This command allows a player to obtain info about
 * their current location and surroundings.
 *
 * @see WorldCommand
 * @see model.world.World
 */
public class LookAroundCmd implements WorldCommand {

  /**
   * Executes the command for a player to look around the game world using the provided world model.
   * This command provides information about the player's current location and the neighboring
   * rooms.
   *
   * @param model The world on which the look around command will be executed.
   * @return A message or information about the player's surroundings.
   * @throws IllegalStateException    If the command execution encounters an illegal state or
   *                                  game over
   *                                  condition.
   * @throws IllegalArgumentException If the command execution encounters an invalid argument or
   *                                  operation.
   */
  @Override
  public String execute(World model)
      throws IllegalStateException, IllegalArgumentException {
    try {
      return model.cmdPlayerLook();
    } catch (IllegalStateException e) {
      throw e;
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

}
