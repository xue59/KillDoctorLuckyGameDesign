package command;

import model.world.World;

/**
 * Represents a command to move the pet in the game world to a specified room.
 * This command implements the WorldCommand interface and is executed on a provided world model.
 * The class includes a constructor for initializing the input room name and an execute method
 * to perform the pet move operation.
 */
public class PetMoveCmd implements WorldCommand {
  private final String inputRoomName;

  /**
   * Constructs a PetMoveCmd object with the specified input room name.
   *
   * @param inputRoomName the name of the room to which the pet will be moved.
   * @throws NullPointerException if the input room name is blank or null.
   */
  public PetMoveCmd(String inputRoomName) throws NullPointerException {
    if (inputRoomName.isEmpty() || inputRoomName == null) {
      throw new NullPointerException("Error: Input room name cannot be Blank or Null!");
    }
    this.inputRoomName = inputRoomName;
  }

  /**
   * Executes the pet move command on the provided world model.
   *
   * @param model The world on which the command will be executed.
   * @return A message or information related to the execution of the command.
   * @throws IllegalAccessException   If the command execution encounters an illegal access.
   * @throws IllegalStateException    If the command execution encounters an illegal state.
   * @throws IllegalArgumentException If the command execution encounters an illegal argument.
   */
  @Override
  public String execute(World model)
      throws IllegalAccessException, IllegalStateException, IllegalArgumentException {
    try {
      model.cmdPetMove(this.inputRoomName);
      return "Player PET_MOVE execute success!\n";
    } catch (IllegalAccessException | IllegalStateException | IllegalArgumentException e) {
      throw e;
    }
  }
}
