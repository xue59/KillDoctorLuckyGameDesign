import static org.junit.Assert.assertEquals;

import model.item.Item;
import model.item.ItemImplement;
import org.junit.Test;

/**
 * This class contains unit tests for the ItemImplementTest class, which is an implementation of
 * the Item interface. It thoroughly tests all the initiate different Item and property.
 */
public class ItemImplementTest {

  /**
   * Test case for verifying the getName() method of the ItemImplement class.
   */
  @Test
  public void testGetName() {
    Item item = new ItemImplement("Sword", 10);
    assertEquals("Sword", item.getName());
  }

  /**
   * Test case for verifying the getDamage() method of the ItemImplement class.
   */
  @Test
  public void testGetDamage() {
    Item item = new ItemImplement("Axe", 15);
    assertEquals(15, item.getDamage());
  }

  /**
   * Test case for verifying the toString() method of the ItemImplement class.
   */
  @Test
  public void testToString() {
    Item item = new ItemImplement("Shield", 5);
    assertEquals("Shield Damage=5 ", item.toString());
  }

  /**
   * Test case for verifying the hashCode() method of the ItemImplement class.
   */
  @Test
  public void testHashCode() {
    Item item1 = new ItemImplement("Potion", 3);
    Item item2 = new ItemImplement("Potion", 3);

    assertEquals(item1.hashCode(), item2.hashCode());
  }

  /**
   * Test case for verifying the equals() method of the ItemImplement class.
   */
  @Test
  public void testEquals() {
    Item item1 = new ItemImplement("Ring", 3);
    Item item2 = new ItemImplement("Ring", 3);

    assertEquals(item1, item2);
  }

  /**
   * Test case for validating the creation of an item with an empty name, which should throw an
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateItemWithEmptyName() {
    new ItemImplement("", 10);
  }

  /**
   * Test case for validating the creation of an item with negative damage, which should throw an
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateItemWithNegativeDamage() {
    new ItemImplement("Dagger", -5);
  }

  /**
   * Test case for validating the creation of an item with zero damage, which should throw an
   * IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreateItemWithZeroDamage() {
    new ItemImplement("Gloves", 0);
  }
}
