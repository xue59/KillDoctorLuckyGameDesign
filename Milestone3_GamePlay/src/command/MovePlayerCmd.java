package command;

import model.world.World;

/**
 * A command class that represents a player's move action in the game world.
 */
public class MovePlayerCmd implements WorldCommand {
  private final String inputRoomName;

  /**
   * Constructs a new MovePlayerCmd object with the specified input room name.
   *
   * @param inputRoomName The name of the target room the player wants to move to.
   * @throws NullPointerException If the input room name is blank or null.
   */
  public MovePlayerCmd(String inputRoomName) throws NullPointerException {
    if (inputRoomName.isEmpty() || inputRoomName == null) {
      throw new NullPointerException("Error: Input room name cannot be Blank or Null!");
    }
    this.inputRoomName = inputRoomName;
  }

  /**
   * Executes the player's move action in the game world.
   *
   * @param model The game world in which the player is to perform the move action.
   * @return A message indicating the success of the move action.
   * @throws IllegalAccessException   If there's a security or access violation during the move.
   * @throws IllegalStateException    If the game world is in an invalid state for the move action.
   * @throws IllegalArgumentException If the input room name is not valid.
   */
  @Override
  public String execute(World model)
      throws IllegalAccessException, IllegalStateException, IllegalArgumentException {
    try {
      model.cmdPlayerMove(this.inputRoomName);
      return "Player MOVE execute success!\n";
    } catch (IllegalAccessException e) {
      throw e;
    } catch (IllegalStateException e) {
      throw e;
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }
}
