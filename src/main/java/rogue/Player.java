package rogue;
import java.util.ArrayList;
import java.awt.Point;
/**
 * The player character
 */
public class Player {
  // player needs a name, location and room
    String name;
    Point xyLocation;
    Room currentRoom;

    // Default constructor
    // our default location is -1,-1 for the char
    public Player() {
      Point p = new Point(-1,-1);
      xyLocation = p;
    }


    public Player(String namez) {
      name = namez;
      Point p = new Point(-1,-1);
      xyLocation = p;
    }

    // get player name
    public String getName() {

        return name;
    }


    public void setName(String newName) {
      name = newName;
    }
    // get our players location as a point
    public Point getXyLocation() {
        return xyLocation;

    }


    public void setXyLocation(Point newXyLocation) {
      xyLocation = newXyLocation;
    }

    // get the players current room
    public Room getCurrentRoom() {
        return currentRoom;

    }


    public void setCurrentRoom(Room newRoom) {
      currentRoom = newRoom;
    }
}
