package command;

import model.world.World;

/**
 * The {@code ComputerActionCmd} class implements the {@link WorldCommand} interface to represent
 * a command
 * for the computer player to take actions in the game world.
 * This command is typically executed by a computer-controlled player in the game.
 *
 * @see WorldCommand
 * @see model.world.World
 */
public class ComputerActionCmd implements WorldCommand {
  /**
   * Executes the computer player's action command on the provided world model.
   *
   * @param model The world on which the computer player's action command will be executed.
   * @return A message or information related to the computer player's action.
   * @throws IllegalAccessException If the command execution encounters an illegal access.
   * @throws IllegalStateException  If the command execution encounters an illegal state or game
   *                                over condition.
   */
  @Override
  public String execute(World model) throws IllegalStateException, IllegalAccessException {
    try {
      return model.cmdComputerPlayerAction();
    } catch (IllegalStateException e) {
      throw e;
    } catch (IllegalAccessException e) {
      throw e;
    }
  }
}
