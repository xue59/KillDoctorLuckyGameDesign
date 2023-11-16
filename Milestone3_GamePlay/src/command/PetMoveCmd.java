package command;

import model.world.World;

public class PetMoveCmd implements WorldCommand{
  private String inputRoomName;

  public PetMoveCmd(String inputRoomName) throws NullPointerException {
    if (inputRoomName.isEmpty() || inputRoomName == null){
      throw new NullPointerException("Error: Input room name cannot be Blank or Null!");
    }
    this.inputRoomName = inputRoomName;
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
      throws IllegalAccessException, IllegalStateException, IllegalArgumentException {
    try {
      model.cmdPetMove(this.inputRoomName);
      return "Player PET_MOVE execute success!\n";
    } catch (IllegalAccessException e) {
      throw e;
    } catch (IllegalStateException e) {
      throw e;
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }
}
