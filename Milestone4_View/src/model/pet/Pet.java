package model.pet;

/**
 * The Pet interface represents a general pet that can move between different rooms.
 * Implementing classes should provide the necessary functionality for retrieving the pet's name,
 * getting the current room number, and moving the pet to a new room.
 */
public interface Pet {

  /**
   * Gets the name of the pet.
   *
   * @return The name of the pet.
   */
  String getName();

  /**
   * Gets the current room number where the pet is located.
   *
   * @return The current room number.
   */
  int getCurrentRoomNumber();

  /**
   * Moves the pet to the specified room number.
   *
   * @param roomNumber The room number to which the pet should be moved.
   * @throws IllegalArgumentException If the provided room number is invalid or out of bounds.
   */
  void movePet(int roomNumber) throws IllegalArgumentException;
}
