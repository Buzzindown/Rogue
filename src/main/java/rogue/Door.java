package rogue;
import java.util.ArrayList;
/**
 * A door in the dunegon walls.
 */
public class Door  {
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
*sets newLoc.
*@param newLoc newloc.
*/
public void setLocation(Integer newLoc) {
  location = newLoc;
}

/**
*sets newLoc.
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
*sets newLoc.
*@return doors room id.
*/
public Integer getRoomID() {
  return room;
}

/**
*get roomsNums.
*@return gets location.
*/
public ArrayList<Integer> getRoomNums() {
  return connRoomNums;
}

/**
*sets newwall.
*@param newWall newwall.
*/
public void setWall(String newWall) {
  wall = newWall;
}

/**
*get location.
*@return gets location.
*/
public Integer getLocation() {
  return location;
}

/**
*get Wall.
*@return get wall.
*/
public String getWall() {
  return wall;
}

/**
*specifies one of the two rooms that can be attached to a door.
public void connectRoom(Room r).
*@param r r
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
*get first con room.
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
