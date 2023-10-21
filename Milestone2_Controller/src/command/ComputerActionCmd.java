package command;

import model.world.World;

public class ComputerActionCmd implements WorldCommand{
  /**
   * @param model
   * @return
   * @throws IllegalAccessException
   */
  @Override
  public String execute(World model) throws IllegalStateException, IllegalAccessException {
    try{
      return model.cmdComputerPlayerAction();
    } catch (IllegalStateException e){
      throw e;
    } catch (IllegalAccessException e){
      throw e;
    }
  }
}
