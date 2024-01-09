package model.item;

/**
 * The Item interface represents an item that can be used or carried by characters in a game.
 * Items have a name and can potentially cause damage when used.
 */
public interface Item {

  /**
   * Gets the name of the item.
   *
   * @return The name of the item.
   */
  String getName();

  /**
   * Gets the damage value associated with the item.
   *
   * @return The damage value of the item.
   */
  int getDamage();
}