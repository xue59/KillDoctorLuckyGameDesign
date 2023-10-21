package command;

import model.world.World;

public class MovePlayerCmd implements WorldCommand{
  private String  inputRoomName;
  /**
   * @param  inputRoomName input room name string your want to move to.
   * @return
   */

  public MovePlayerCmd(String inputRoomName) throws NullPointerException{
    if(inputRoomName.isEmpty() || inputRoomName ==null){
      throw new NullPointerException("Error: Input room name cannot be Blank or Null!");
    }
    this.inputRoomName = inputRoomName;
  }

  @Override
  public String execute(World model)
      throws IllegalAccessException,IllegalStateException, IllegalArgumentException{
    try{
      model.cmdPlayerMove(this.inputRoomName);
      return "Player MOVE execute success!\n";
    } catch (IllegalAccessException e){
      throw e;
    } catch (IllegalStateException e){
      throw e;
    } catch (IllegalArgumentException e){
      throw e;
    }
  }
}
