import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import model.pet.PetImplement;
import org.junit.Test;

/**
 * JUnit test for the PetImplement class.
 */
public class PetImplementTest {

  /**
   * Tests the construction of a PetImplement object with a valid name.
   * Verifies that the initial room number is set to 0.
   */
  @Test
  public void testPetImplementConstruction() {
    PetImplement pet = new PetImplement("Fluffy");
    assertEquals("Fluffy", pet.getName());
    assertEquals(0, pet.getCurrentRoomNumber());
  }

  /**
   * Tests the movePet(int) method with a valid room number.
   * Verifies that the pet's current room number is updated accordingly.
   */
  @Test
  public void testPetMoveValidRoomNumber() {
    PetImplement pet = new PetImplement("Buddy");
    pet.movePet(2);
    assertEquals(2, pet.getCurrentRoomNumber());
  }

  /**
   * Tests the movePet(int) method with an invalid (negative) room number.
   * Expects an IllegalArgumentException to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPetMoveInvalidRoomNumber() {
    PetImplement pet = new PetImplement("Mittens");
    pet.movePet(-1);
  }

  /**
   * Tests the getName() method to ensure it returns the correct pet name.
   */
  @Test
  public void testPetGetName() {
    PetImplement pet = new PetImplement("Spike");
    assertEquals("Spike", pet.getName());
  }

  /**
   * Tests the movePet(int) method with a different valid room number.
   * Verifies that the pet's current room number is updated accordingly.
   */
  @Test
  public void testPetMoveDifferentValidRoomNumber() {
    PetImplement pet = new PetImplement("Max");
    pet.movePet(3);
    assertEquals(3, pet.getCurrentRoomNumber());
    assertNotEquals(2, pet.getCurrentRoomNumber());
  }
}
