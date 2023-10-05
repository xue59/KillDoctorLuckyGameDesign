package model.item;


public class ItemImplement implements Item {
  private final int damage;
  private final String itemName;

  public ItemImplement(String itemName, int damage){
    if (itemName.isEmpty()){
      throw new IllegalArgumentException("Error: Item Name cannot be empty!");
    }
    if (damage <= 0){
      throw new IllegalArgumentException("Error: Item Damage cannot be Negative or Zero!");
    }
    this.damage = damage;
    this.itemName = itemName;
  }
  @Override
  public String getName() {
    return this.itemName;
  }

  @Override
  public int getDamage() {
    return this.damage;
  }

  @Override
  public String toString(){
    String newString;
    newString = String.format("Item: %s Damage=%s", this.getName(), this.getDamage());
    return newString;
  }

  @Override
  public int hashCode(){
    String toBeHashed = this.toString();
    return(toBeHashed.hashCode());
  }

  @Override
  public boolean equals(Object obj){
    if (obj instanceof Item){
      Item comparedItem = (Item) obj;
      if(this.hashCode() == comparedItem.hashCode()){
        return true;
      } else{
        return false;
      }
    }else {
      return false;
    }
  }
}
