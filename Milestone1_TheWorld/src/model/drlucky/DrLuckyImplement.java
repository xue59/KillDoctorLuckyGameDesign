package model.drlucky;

public class DrLuckyImplement implements DrLucky{
  private final String name;
  private int curHp;
  private final int maxRoomIndex; // reflect the total amount of rooms, index-0 based room numbers.
  private int curRoomNum;

  public DrLuckyImplement(String name, int hp, int maxRoomIndex){
    //check dr lucky name cannot be empty
    if (name.isEmpty()){
      throw new IllegalArgumentException("Error: name can not be Empty!!");
    }
    //check staring hp must be postive
    if (hp<=0){
      throw new IllegalArgumentException("Error: Starting Hp cannot be negative or Zero!!");
    }
    // check max room index must be larger or equal 0, need at lease 1 room
    if (maxRoomIndex <0 ){
      throw new IllegalArgumentException("Error: need 1 room min, room index cannot be negative!!");
    }
    this.name = name;
    this.curHp = hp;
    this.maxRoomIndex = maxRoomIndex;
    this.curRoomNum = 0;
  }
  @Override
  public String getName() {
    return null;
  }

  @Override
  public int getCurrentHp() {
    return 0;
  }

  @Override
  public int getCurrentRoomNumber() {
    return 0;
  }

  @Override
  public void moveDrLucky() {

  }

  @Override
  public void decreaseHp() {

  }
}
