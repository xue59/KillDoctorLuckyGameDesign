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
    return this.name;
  }

  @Override
  public int getCurrentHp() {
    return this.curHp;
  }

  @Override
  public int getCurrentRoomNumber() {
    return this.curRoomNum;
  }

  @Override
  public void moveDrLucky() {
    this.curRoomNum = (this.curRoomNum+1) % this.maxRoomIndex;
  }

  @Override
  public void decreaseHp(int decreaseBy) {
    if (decreaseBy <=0){
      throw new IllegalArgumentException("Error: the decreased amount must be 1 or larger!");
    }
    if (this.curHp - decreaseBy <=0){
      this.curHp = 0; // if health is less than 0 then set to 0, dr lucky is dead! game over!
    }else{
      this.curHp -=decreaseBy;
    }
  }

  @Override
  public String toString(){
    String returnedString;
    returnedString = String.format("Target name: %s, Cur Hp: %d, Cur room index: %d\n",this.name, this.curHp, this.curRoomNum);
    return returnedString;
  }
}
