package command;

import model.world.World;

/**
 * A command class that represents a player's action to attack with using item in the game world.
 */
public class KillCmd implements WorldCommand {
  private final String inputItemName;

  /**
   * Constructs a new KillCmd object with the specified input item name.
   *
   * @param inputItemName The name of the item the player wants use.
   * @throws NullPointerException If the input item name is blank or null.
   */
  public KillCmd(String inputItemName) throws NullPointerException {
    if (inputItemName.isEmpty() || inputItemName == null) {
      throw new NullPointerException("Error: killCmd Item name cannot be Blank or Null!");
    }
    this.inputItemName = inputItemName;
  }

  /**
   * Executes the command on the provided world model.
   *
   * @param model The world on which the command will be executed.
   * @return A message or information related to the execution of the command.
   * @throws IllegalAccessException If the command execution encounters an illegal access or
   *                                operation.
   */
  @Override
  public String execute(World model)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException,
      IllegalStateException {
    try {
      return model.cmdPlayerKill(this.inputItemName);
    } catch (IllegalAccessException e) {
      throw e;
    } catch (IllegalStateException e) {
      throw e;
    } catch (NullPointerException e) {
      throw e;
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }
}
