package rogue;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * A door in the dunegon walls.
 * doors are designed to be able to connect to more than one room,
 * that functionality isn't built in yet though..
 */
public class Door implements Serializable {
  private static final long serialVersionUID = -2655797264737116798L;
  private ArrayList<Room> conRooms;
  private ArrayList<Integer> connRoomNums;
  private Integer location;
  private String wall;
  private Integer room;

/**
*default door Constructor.
*/
public Door() {
  conRooms = new ArrayList<Room>();
  connRoomNums = new ArrayList<Integer>();
}

/**
*sets new location.
*@param newLoc Integer position on a wall.
*/
public void setLocation(Integer newLoc) {
  location = newLoc;
}

/**
*sets the room number (room id) that the door is in.
*@param rN newloc.
*/
public void setCRN(Integer rN) {
  connRoomNums.add(rN);
}

/**
*sets newLoc.
*@param rI newloc.
*/
public void setRoomId(Integer rI) {
  room = rI;
}

/**
*sets the id of the room this door is in.
*@return doors room id.
*/
public Integer getRoomID() {
  return room;
}

/**
*get get the roomnumbers.
*@return gets location.
*/
public ArrayList<Integer> getRoomNums() {
  return connRoomNums;
}

/**
*sets the NESW wall that the door is on.
*@param newWall newwall.
*/
public void setWall(String newWall) {
  wall = newWall;
}

/**
*get location on hte wall.
*@return gets location.
*/
public Integer getLocation() {
  return location;
}

/**
*gets the NESW wall that the door is on.
*@return NESW wall.
*/
public String getWall() {
  return wall;
}

/**
*specifies one of the two rooms that can be attached to a door.
public void connectRoom(Room r).
*@param r room to be added.
*/
public void connectRoom(Room r) {
  conRooms.add(r);
}

/**
*get an arraylist that contains boht rooms connected by this door.
*@return conRooms connected rooms.
*/
public ArrayList<Room> getConnectedRooms() {
  return conRooms;
}

/**
*get first connected room.
*@return a con rooms.
*/
public Room getConRoom() {
  return conRooms.get(0);
}

/**
*gets the connected room by passing in the current room.
*@param currentRoom the current room.
*@return other connected room.
*/
public Room getOtherRoom(Room currentRoom) {
  Room r = conRooms.get(0);
  Room r2 = conRooms.get(1);
  if (r.getId() == currentRoom.getId()) {
    return r2;
  } else {
    return r;
  }
}

}
