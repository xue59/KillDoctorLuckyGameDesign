package model.pet;

public class PetImplement implements Pet{
  private final String name;
  private int curRoomNum;

  public PetImplement(String name) throws IllegalArgumentException{
    if (name.isEmpty()){
      throw new IllegalArgumentException("Error in PetImplement: Pet name cannot be empty/null\n!");
    }
    this.name = name;
    this.curRoomNum = 0; // set initial room number as 0
  }

  /**
   * Gets the name of the pet.
   *
   * @return The name of the pet.
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Gets the current room number where the pet is located.
   *
   * @return The current room number.
   */
  @Override
  public int getCurrentRoomNumber() {
    return this.curRoomNum;
  }

  /**
   * Moves the pet to the specified room number.
   *
   * @param roomNumber The room number to which the pet should be moved.
   * @throws IllegalArgumentException If the provided room number is invalid or out of bounds.
   */
  @Override
  public void movePet(int roomNumber) throws IllegalArgumentException {
    if (roomNumber >=0){
      this.curRoomNum = roomNumber;
    } else {
      throw new IllegalArgumentException("Error in Pet movePet: input roomNum cannot be negative!");
    }

  }
}
