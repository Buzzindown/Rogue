package rogue;
import java.awt.Point;

/**
 * A basic Item class; basic functionality for both consumables and equipment
 */
public class Item  {

  private Integer identif;
  private Room curRoom;
  private String name;
  private String type;
  private Point xyLocation;
  private Character displayCharacter;
  private String description;


    //Constructors
    public Item() {

    }

    public Item(int id, String namez, String typez, Point xyLocationz) {
      identif = id;
      name = namez;
      type = typez;
      xyLocation = xyLocationz;
    }

    // Getters and setters

    // get our id num
    public int getId() {
        return identif;
    }


    public void setId(int id) {
        identif = id;
    }

    // get our name
    public String getName() {
        return name;
    }


    public void setName(String name) {
      name = name;
    }

    // get our type
    public String getType() {
        return type;

    }


    public void setType(String type) {
      type = type;
    }

    // get display charcter
    public Character getDisplayCharacter() {
        return displayCharacter;

    }


    public void setDisplayCharacter(Character newDisplayCharacter) {
      displayCharacter = newDisplayCharacter;
    }


    // get our description
    public String getDescription() {
        return description;

    }


    public void setDescription(String newDescription) {
      description = newDescription;
    }

    // get our point
    public Point getXyLocation() {
        return xyLocation;

    }


    public void setXyLocation(Point newXyLocation) {
      xyLocation = newXyLocation;
    }

    // get current roo
    public Room getCurrentRoom() {
        return curRoom;

    }


    public void setCurrentRoom(Room newCurrentRoom) {
      curRoom = newCurrentRoom;
    }
}
