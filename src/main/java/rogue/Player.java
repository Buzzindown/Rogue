package rogue;
import java.awt.Point;
/**
 * The player character class.
 */
public class Player {
    private String name;
    private Point xyLocation;
    private Room currentRoom;

/**
*default constructor.
*/
    public Player() {
      Point p = new Point(1, 1);
      xyLocation = p;
    }

/**
*overloaded constructor.
*@param namez players new name.
*/
    public Player(String namez) {
      name = namez;
      Point p = new Point(1, 1);
      xyLocation = p;
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
      currentRoom = newRoom;
    }
}
