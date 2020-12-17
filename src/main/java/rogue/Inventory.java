package rogue;
import java.util.ArrayList;
import java.io.Serializable;

/**
* This class is used to implement a player's inventory.
*/
public class Inventory implements Serializable {
  private static final long serialVersionUID = 2123804758704638867L;
  private ArrayList<Item> items;

  /**
  *default constructor.
  */
  public Inventory() {
    items = new ArrayList<Item>();
  }

  /**
  *add item to inventory.
  *@param item to add
  */
  public void addItem(Item item) {
    items.add(item);
  }

  /**
  *remove item from inventory.
  *@param item to remove
  */
  public void removeItem(Item item) {
    if (items.contains(item)) {
      items.remove(item);
    }
  }

  /**
  *return inventory arraylist.
  *@return inventory
  */
  public ArrayList<Item> getInventory() {
    return items;
  }
}
