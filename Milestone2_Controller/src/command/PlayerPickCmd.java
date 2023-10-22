package command;

import model.world.World;

public class PlayerPickCmd implements WorldCommand {
  private String inputItemName;

  public PlayerPickCmd(String inputItemName) throws NullPointerException {
    if (inputItemName.isEmpty() || inputItemName == null) {
      throw new NullPointerException("Error: input Item name cannot be Blank or Null!");
    }
    this.inputItemName = inputItemName;
  }

  /**
   * @param model
   * @return
   * @throws IllegalAccessException
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
