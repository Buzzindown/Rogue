package rogue;
import java.awt.Point;

/**
 * A basic Item class; basic functionality for both consumables and equipment.
 */
public class Item  {

  private Integer identif;
  private Room curRoom;
  private String name;
  private String type;
  private Point xyLocation;
  private Character displayCharacter;
  private String description;
  private boolean doSomething;
  /**
  * default constructor.
  */
    public Item() {
      doSomething = true;
    }

  /**
  * overlaoded item constructor.
  @param id the items identificaiton MagicNumber
  @param namez the items name
  @param typez the items typez
  @param xyLocationz the items xylocation
  */
    public Item(int id, String namez, String typez, Point xyLocationz) {
      identif = id;
      name = namez;
      type = typez;
      xyLocation = xyLocationz;
    }

/**
*id getter for item class.
* @return identity
*/
    public int getId() {
        return identif;
    }

/**
*id setter for item class.
*@param id item id
*/
    public void setId(int id) {
        identif = id;
    }

/**
*name getter for item class.
* @return item name
*/
    public String getName() {
        return name;
    }

/**
*id setter for item class.
* @param namez new name
*/
    public void setName(String namez) {
      name = namez;
    }

/**
*type getter for item class.
* @return item type
*/
    public String getType() {
        return type;

    }

/**
*type setter for item class.
* @param typez item type
*/
    public void setType(String typez) {
      type = typez;
    }

/**
*display char setter for item class.
* @return item display char
*/
    public Character getDisplayCharacter() {
        return displayCharacter;

    }

/**
*dispaly character setter for item class.
* @param newDisplayCharacter new display character
*/
    public void setDisplayCharacter(Character newDisplayCharacter) {
      displayCharacter = newDisplayCharacter;
    }


/**
*description getter for item class.
* @return description
*/
    public String getDescription() {
        return description;

    }

/**
*description setter for item class.
* @param newDescription new description
*/
    public void setDescription(String newDescription) {
      description = newDescription;
    }

/**
*point getter for item class.
* @return item location
*/
    public Point getXyLocation() {
        return xyLocation;

    }

/**
*point setter for item class.
* @param newXyLocation new item location
*/
    public void setXyLocation(Point newXyLocation) {
      xyLocation = newXyLocation;
    }

/**
*room getter for item class.
* @return items room
*/
    public Room getCurrentRoom() {
        return curRoom;

    }

/**
*room setter for item class.
* @param newCurrentRoom items new room.
*/
    public void setCurrentRoom(Room newCurrentRoom) {
      curRoom = newCurrentRoom;
    }
}
