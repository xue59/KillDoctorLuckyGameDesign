import static org.junit.Assert.assertEquals;

import model.drlucky.DrLucky;
import model.drlucky.DrLuckyImplement;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests for the DrLuckyImplement class, which is an
 * implementation of the DrLucky interface.
 */
public class DrLuckyImplementTest {
  private DrLucky drLucky;

  /**
   * Setting up the drLucky class with valid name and maxRoomIndex = 10.
   */
  @Before
  public void setUp() {
    // Initialize Dr. Lucky instance (you can use a concrete implementation for testing)
    drLucky = new DrLuckyImplement("TestLuckyName", 60, 10);
  }

  /**
   * Test the getName method to ensure it returns the correct name.
   */
  @Test
  public void testGetName() {
    assertEquals("TestLuckyName", drLucky.getName());
  }

  /**
   * Test that creating a Dr. Lucky with a null, which should throw NullPointerException.
   */
  @Test(expected = NullPointerException.class)
  public void testCreateDrLuckyWithNull() {
    // Try to create Dr. Lucky with a null name
    new DrLuckyImplement(null, 60, 10);
  }

  /**
   * Test that creating a Dr. Lucky with an empty name which should throw IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateDrLuckyWithEmptyName() {
    // Try to create Dr. Lucky with an empty name
    new DrLuckyImplement("", 50, 5);
  }

  /**
   * Test that creating a Dr. Lucky with an initial HP of 0 throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateDrLuckyWithZeroInitialHp() {
    // Try to create Dr. Lucky with an initial Hp of 0
    new DrLuckyImplement("Test Dr. Lucky", 0, 10);
  }

  /**
   * Test that creating a Dr. Lucky with a maximum room index of negative throws an
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateDrLuckyWithNegativeMaxRoomIndex() {
    // Try to create Dr. Lucky with a maximum room index of negative
    new DrLuckyImplement("Test Dr. Lucky", 60, -1);
  }

  /**
   * Test the getCurrentHp method to ensure it returns the correct current health points (Hp).
   */
  @Test
  public void testGetCurrentHp() {
    assertEquals(60, drLucky.getCurrentHp());
  }

  /**
   * Test the getCurrentRoomNumber method to ensure it returns the correct current room number.
   */
  @Test
  public void testGetCurrentRoomNumber() {
    assertEquals(0, drLucky.getCurrentRoomNumber());
  }

  /**
   * Test the moveDrLucky method by moving Dr. Lucky by 1 room and checking if the room number
   * changes accordingly.
   */
  @Test
  public void testMoveDrLuckyBy1() {
    // Store the initial room number
    int initialRoomNumber = drLucky.getCurrentRoomNumber();
    // Move Dr. Lucky
    drLucky.moveDrLucky();
    // Ensure Dr. Lucky's room number has changed
    assertEquals(initialRoomNumber + 1, drLucky.getCurrentRoomNumber());
  }

  /**
   * Test the moveDrLucky method by moving Dr. Lucky from the last room to the first room in
   * a cyclic manner.
   */
  @Test
  public void testMoveDrLuckyEndToStart() {
    // Store the initial room number dr lucky should be in index 0 room
    int initialRoomNumber = drLucky.getCurrentRoomNumber();
    assertEquals(0, initialRoomNumber);
    // Prepare Dr. Lucky to the last room number Room index #10.
    for (int i = 1; i <= 10; i++) {
      drLucky.moveDrLucky();
    }
    // Now he is at room index 10
    assertEquals(10, drLucky.getCurrentRoomNumber());
    drLucky.moveDrLucky(); // Move one more time.
    assertEquals(0, drLucky.getCurrentRoomNumber());
  }

  /**
   * Test the decreaseHp method by decreasing Dr. Lucky's Hp by 20 and checking if it decreases
   * by the correct amount.
   */
  @Test
  public void testDecreaseHp() {
    // Store the initial Hp
    int initialHp = drLucky.getCurrentHp();

    // Decrease Hp by 20
    drLucky.decreaseHp(20);

    // Ensure Hp has decreased by the correct amount
    assertEquals(initialHp - 20, drLucky.getCurrentHp());
  }
}
