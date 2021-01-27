package rogue;
import java.awt.Point;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * The player character class.
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 5426668594188040447L;
    private String name;
    private Point xyLocation;
    private Room currentRoom;
    private Inventory inventory;

/**
*default constructor..
*/
    public Player() {
      Point p = new Point(1, 1);
      inventory = new Inventory();
      xyLocation = p;
    }

/**
*overloaded constructor.
*@param namez players new name.
*/
    public Player(String namez) {
      name = namez;
      Point p = new Point(1, 1);
      inventory = new Inventory();
      xyLocation = p;
    }

/**
*adding an item to inventory.
*@param item the item being added
*/
    public void addToInv(Item item) {
      inventory.addItem(item);
    }

/**
*removing an item from inventory.
*@param item the item to be removed
*/

    public void remFrominv(Item item) {
      inventory.removeItem(item);
    }

/**
*getting player inventory size.
*@return inventory size
*/
  public int returnInvSize() {
    ArrayList<Item> its = inventory.getInventory();
    int x = its.size();
    return x;
  }

/**
* returning our players inventory.
* @return players inventory
*/
    public ArrayList<Item> getInvList() {
      return inventory.getInventory();
    }

/**
*Name getter.
* @return name
*/
    public String getName() {
        return name;
    }


/**
*Name setter.
* @param newName new name
*/
    public void setName(String newName) {
      name = newName;
    }

/**
*Location getter.
*@return a point object.
*/
    public Point getXyLocation() {
        return xyLocation;
    }

/**
*Location setter.
* @param newXyLocation new player location
*/
    public void setXyLocation(Point newXyLocation) {
      xyLocation = newXyLocation;
    }

/**
*Room getter.
* @return current room.
*/
    public Room getCurrentRoom() {
        return currentRoom;

    }

/**
*Room setter.
* @param newRoom new room./
*/
    public void setCurrentRoom(Room newRoom) {
      newRoom.setPlayerInRoom(true);
      currentRoom = newRoom;
    }
}
