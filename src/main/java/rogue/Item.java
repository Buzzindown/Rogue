package rogue;
import java.awt.Point;

/**
 * A basic Item class; basic functionality for both consumables and equipment
 */
public class Item  {

  Integer id;
  Room curRoom;
  String name;
  String type;
  Point xyLocation;
  Character displayCharacter;
  String description;


    //Constructors
    public Item() {

    }

    public Item(int id, String name, String type, Point xyLocation) {
      setId(id);
      setName(name);
      setType(type);
      setXyLocation(xyLocation);
    }

    // Getters and setters


    public int getId() {
        return id;

    }


    public void setId(int id) {
        id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
      name = name;
    }


    public String getType() {
        return type;

    }


    public void setType(String type) {
      type = type;
    }


    public Character getDisplayCharacter() {
        return displayCharacter;

    }


    public void setDisplayCharacter(Character newDisplayCharacter) {
      displayCharacter = newDisplayCharacter;
    }


    public String getDescription() {
        return description;

    }


    public void setDescription(String newDescription) {
      description = newDescription;
    }


    public Point getXyLocation() {
        return xyLocation;

    }


    public void setXyLocation(Point newXyLocation) {
      xyLocation = newXyLocation;
    }


    public Room getCurrentRoom() {
        return curRoom;

    }


    public void setCurrentRoom(Room newCurrentRoom) {
      curRoom = newCurrentRoom;
    }
}
