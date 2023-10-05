package model.drlucky;

public interface DrLucky {

  String getName();

  int getCurrentHp();

  int getCurrentRoomNumber();

  void moveDrLucky();

  void decreaseHp(int decreaseBy);

}
