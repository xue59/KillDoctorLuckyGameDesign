package command;

import model.world.World;

/**
 * The {@code WorldCommand} interface represents a command that can be executed on a
 * {@link model.world.World}.
 * Implementing classes define specific actions to be performed on the world model when executed.
 *
 * @see model.world.World
 */
public interface WorldCommand {

  /**
   * Executes the command on the provided world model.
   *
   * @param model The world on which the command will be executed.
   * @return A message or information related to the execution of the command.
   * @throws IllegalAccessException If the command execution encounters an illegal access or
   *                                operation.
   */
  String execute(World model) throws IllegalAccessException;
}
