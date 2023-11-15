package model.drlucky;

/**
 * The DrLuckyImplement class represents the character "Dr. Lucky" in the game. It implements the
 * DrLucky interface, providing methods to interact with Dr. Lucky's attributes.
 */
public class DrLuckyImplement implements DrLucky {
  private final String name;        // The name of Dr. Lucky.
  private final int maxRoomIndex;   // The maximum room index reflecting the total number of rooms.
  private int curHp;                // The current health points of Dr. Lucky.
  private int curRoomNum;           // The current room number where Dr. Lucky is located.

  /**
   * Constructs a DrLuckyImplement object with the specified name, initial health points, and
   * maximum room index.
   *
   * @param name         The name of Dr. Lucky.
   * @param hp           The initial health points of Dr. Lucky.
   * @param maxRoomIndex The maximum room index reflecting the total number of rooms.
   * @throws IllegalArgumentException If the name is empty, starting health points are non-positive,
   *                                  or the maximum room index is negative.
   */
  public DrLuckyImplement(String name, int hp, int maxRoomIndex) {
    // Check if Dr. Lucky's name is empty.
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Error: Name cannot be empty.");
    }
    // Check if starting health points are positive.
    if (hp <= 0) {
      throw new IllegalArgumentException("Error: Starting health points must be positive.");
    }
    // Check if the maximum room index is non-negative (at least 1 room required).
    if (maxRoomIndex < 0) {
      throw new IllegalArgumentException("Error: Maximum room index cannot be negative.");
    }

    this.name = name;
    this.curHp = hp;
    this.maxRoomIndex = maxRoomIndex;
    this.curRoomNum = 0;
  }

  /**
   * Gets the name of Dr. Lucky.
   *
   * @return The name of Dr. Lucky.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the current health points of Dr. Lucky.
   *
   * @return The current health points of Dr. Lucky.
   */
  @Override
  public int getCurrentHp() {
    return this.curHp;
  }

  /**
   * Gets the current room number where Dr. Lucky is located.
   *
   * @return The current room number.
   */
  @Override
  public int getCurrentRoomNumber() {
    return this.curRoomNum;
  }

  /**
   * Moves Dr. Lucky to the next room in a circular manner.
   */
  @Override
  public void moveDrLucky() {
    this.curRoomNum = (this.curRoomNum + 1) % (this.maxRoomIndex + 1);
  }

  /**
   * Decreases Dr. Lucky's health points by the specified amount.
   *
   * @param decreaseBy The amount to decrease Dr. Lucky's health points.
   * @throws IllegalArgumentException If the decrease amount is non-positive.
   */
  @Override
  public void decreaseHp(int decreaseBy) {
    if (decreaseBy <= 0) {
      throw new IllegalArgumentException("Error: The decrease amount must be greater than zero.");
    }
    if (this.curHp - decreaseBy <= 0) {
      this.curHp = 0; // Health cannot be negative; Dr. Lucky is dead.
    } else {
      this.curHp -= decreaseBy;
    }
  }

  /**
   * Returns a string representation of Dr. Lucky's attributes.
   *
   * @return A string containing Dr. Lucky's name, current health points, and current room number.
   */
  @Override
  public String toString() {
    return String.format("Target name: %s, Current HP: %d, Current room index: %d\n", this.name,
        this.curHp, this.curRoomNum);
  }
}