package rogue;
import java.util.ArrayList;
import java.awt.Point;
/**
 * The player character
 */
public class Player {

    String name;
    Point xyLocation;
    Room currentRoom;

    // Default constructor
    public Player() {

    }


    public Player(String name) {
      setName(name);
    }


    public String getName() {

        return name;
    }


    public void setName(String newName) {
      name = newName;
    }

    public Point getXyLocation() {
        return xyLocation;

    }


    public void setXyLocation(Point newXyLocation) {
      xyLocation = newXyLocation;
    }


    public Room getCurrentRoom() {
        return currentRoom;

    }


    public void setCurrentRoom(Room newRoom) {
      currentRoom = newRoom;
    }
}
