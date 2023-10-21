package model.item;

/**
 * The {@code ItemImplement} class represents an item in a game, which has a name and damage value.
 * It implements the {@link Item} interface.
 */
public class ItemImplement implements Item {
  private final int damage;
  private final String itemName;

  /**
   * Creates a new instance of an item with the given name and damage value.
   *
   * @param itemName The name of the item.
   * @param damage   The damage value associated with the item.
   * @throws IllegalArgumentException If the item name is empty or if the damage value is negative
   *                                  or zero.
   */
  public ItemImplement(String itemName, int damage) {
    if (itemName.isEmpty()) {
      throw new IllegalArgumentException("Error: Item Name cannot be empty!");
    }
    if (damage <= 0) {
      throw new IllegalArgumentException("Error: Item Damage cannot be Negative or Zero!");
    }
    this.damage = damage;
    this.itemName = itemName;
  }

  /**
   * Gets the name of the item.
   *
   * @return The name of the item.
   */
  @Override
  public String getName() {
    return this.itemName;
  }

  /**
   * Gets the damage value associated with the item.
   *
   * @return The damage value of the item.
   */
  @Override
  public int getDamage() {
    return this.damage;
  }

  /**
   * Returns a string representation of the item, including its name and damage value.
   *
   * @return A string representation of the item.
   */
  @Override
  public String toString() {
    String newString;
    newString = String.format("%s(Damage=%s)", this.getName(), this.getDamage());
    return newString;
  }

  /**
   * Computes the hash code of the item based on its string representation.
   *
   * @return The hash code of the item.
   */
  @Override
  public int hashCode() {
    String toBeHashed = this.toString();
    return (toBeHashed.hashCode());
  }

  /**
   * Checks whether this item is equal to another object by comparing their hash codes.
   *
   * @param obj The object to compare to.
   * @return {@code true} if the items are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Item) {
      Item comparedItem = (Item) obj;
      if (this.hashCode() == comparedItem.hashCode()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
