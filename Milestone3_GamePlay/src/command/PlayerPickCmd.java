package command;

import model.world.World;

/**
 * A command class that represents a player's action to pick up an item in the game world.
 */
public class PlayerPickCmd implements WorldCommand {
  private final String inputItemName;

  /**
   * Constructs a new PlayerPickCmd object with the specified input item name.
   *
   * @param inputItemName The name of the item the player wants to pick up.
   * @throws NullPointerException If the input item name is blank or null.
   */
  public PlayerPickCmd(String inputItemName) throws NullPointerException {
    if (inputItemName.isEmpty() || inputItemName == null) {
      throw new NullPointerException("Error: input Item name cannot be Blank or Null!");
    }
    this.inputItemName = inputItemName;
  }

  /**
   * Executes the player's action to pick up an item in the game world.
   *
   * @param model The game world in which the player is to perform the pick-up action.
   * @return A message indicating the success of the pick-up action.
   * @throws IllegalAccessException   If there's a security or access violation during the pick-up.
   * @throws IllegalStateException    If the game world is in an invalid state for the pick-up
   *                                  action.
   * @throws IllegalArgumentException If the input item name is not valid.
   * @throws NullPointerException     If the input item name is blank or null.
   */
  @Override
  public String execute(World model)
      throws NullPointerException, IllegalArgumentException, IllegalAccessException,
      IllegalStateException {
    try {
      model.cmdPlayerPick(this.inputItemName);
      return "Player PICK execute success!\n";
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
