package model.drlucky;

/**
 * The DrLucky interface represents a character named Dr. Lucky in a game. Dr. Lucky has a name,
 * current health points (HP), current room number, and can perform actions like moving and
 * decreasing HP.
 */
public interface DrLucky {

  /**
   * Gets the name of Dr. Lucky.
   *
   * @return The name of Dr. Lucky.
   */
  String getName();

  /**
   * Gets the current health points (HP) of Dr. Lucky.
   *
   * @return The current HP of Dr. Lucky.
   */
  int getCurrentHp();

  /**
   * Gets the current room number where Dr. Lucky is located.
   *
   * @return The current room number.
   */
  int getCurrentRoomNumber();

  /**
   * Moves Dr. Lucky to a new location or room.
   */
  void moveDrLucky();

  /**
   * Decreases Dr. Lucky's health points (HP) by a specified amount.
   *
   * @param decreaseBy The amount by which HP should be decreased.
   */
  void decreaseHp(int decreaseBy);
}