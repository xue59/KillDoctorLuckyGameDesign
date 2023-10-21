package command;

import model.world.World;

public class LookAroundCmd implements WorldCommand{
  /**
   * @param model
   * @return
   */
  @Override
  public String execute(World model)
      throws IllegalStateException, IllegalArgumentException {
    try{
      return model.cmdPlayerLook();
    } catch (IllegalStateException e){
      throw e;
    } catch (IllegalArgumentException e){
      throw e;
    }
  }

}
