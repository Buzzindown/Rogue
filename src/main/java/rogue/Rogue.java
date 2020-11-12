package rogue;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Map;
/**
 * The rogue game class.
 */
public class Rogue {
    private RogueParser parser;
    private ArrayList<Room> roomZ = new ArrayList<Room>();
    private ArrayList<Map<String, String>> items;
    private ArrayList<Map<String, String>> itemLocs;
    private ArrayList<Door> doors = new ArrayList<Door>();
    private Player player = new Player();
    private String msg;
    private int x;
    private int y;
    private ArrayList<Door> doorss;
    private Boolean goodMove;
    private Boolean thruDoor;
    private Boolean validDoor;
    private Boolean pickedItem;
    private Point p;
    private Door nextDoor;
    public static final char UP = 'w';
    public static final char DOWN = 's';
    public static final char LEFT = 'a';
    public static final char RIGHT = 'd';

/**
*adds a room to roomZ arraylist.
*@param toAdd the map of stuff.
*/

public void addRoom(Map<String, String> toAdd) {
  Room room = new Room();
  room.setPlayer(player);
  room.setId(Integer.parseInt(toAdd.get("id")));
  room.setStart(toAdd.get("start"));
  room.setHeight(Integer.parseInt(toAdd.get("height")));
  room.setWidth(Integer.parseInt(toAdd.get("width")));
  room.setSymbols(parser.retSymbols());
  for (Door d : doors) {
    if (d.getRoomID() == room.getId()) {
      room.addDoor(d);
    }
  }
  roomZ.add(room);
}

/**
*returns the new display of the room.
*@return string of the new room.
*/
public String getNextDisplay() {
  Room r = player.getCurrentRoom();
  return r.displayRoom();
}

/**
*returns the new display of the room.
*@return string of the new room.
*/
public String getBlankDisplay() {
  int s = 0;
  final int screenSize = 1920;
  char[] temp = new char[screenSize];
  for (s = 0; s < screenSize; s++) {
    temp[s] = ' ';
  }
  String str = new String(temp);
  return str;
}

private void moveDir(char input) {
  if (input == UP) {
    y--;
    msg = "moved N";
  } else if (input == DOWN) {
    y++;
    msg = "moved E";
  } else if (input == LEFT) {
    x--;
    msg = "moved W";
  } else if (input == RIGHT) {
    x++;
    msg = "moved S";
  }
}

private void moveDoorWest() {
  for (Door h : doorss) {
    if ((h.getWall()).equals("W")) {
      nextDoor = h;
      validDoor = true;
    }
  }
  if (validDoor) {
    if (p.getY() == nextDoor.getLocation()) {
      Room newRoom = nextDoor.getConRoom();
      ArrayList<Door> m = newRoom.retDoor();
      for (Door f : m) {
        if ((f.getWall()).equals("E")) {
          player.setCurrentRoom(newRoom);
          p.setLocation(newRoom.getWidth() - 2, f.getLocation());
          player.setXyLocation(p);
          thruDoor = true;
          msg = msg + "...entered room: " + newRoom.getId();
        }
      }
    }
  }
  goodMove = false;
}

private void moveDoorEast() {
  //if we go into east wall
  for (Door h : doorss) {
    if ((h.getWall()).equals("E")) {
      nextDoor = h;
      validDoor = true;
    }
  }
  if (validDoor) {
      if (p.getY() == nextDoor.getLocation()) {
        Room newRoom = nextDoor.getConRoom();
        ArrayList<Door> m = newRoom.retDoor();
        for (Door f : m) {
          if ((f.getWall()).equals("W")) {
            player.setCurrentRoom(newRoom);
            p.setLocation(1, f.getLocation());
            player.setXyLocation(p);
            thruDoor = true;
            msg = msg + "...entered room: " + newRoom.getId();
          }
        }
      }
    }
  goodMove = false;
}

private void moveDoorNorth() {
  for (Door h : doorss) {
    if ((h.getWall()).equals("N")) {
      nextDoor = h;
      validDoor = true;
    }
  }
  if (validDoor) {
    if (p.getX() == nextDoor.getLocation()) {
      Room newRoom = nextDoor.getConRoom();
      ArrayList<Door> m = newRoom.retDoor();
      for (Door f : m) {
        if ((f.getWall()).equals("S")) {
          player.setCurrentRoom(newRoom);
          p.setLocation(f.getLocation(), newRoom.getHeight() - 2);
          player.setXyLocation(p);
          thruDoor = true;
          msg = msg + "...entered room: " + newRoom.getId();
        }
      }
    }
  }
  goodMove = false;
}

private void moveDoorSouth() {
  for (Door h : doorss) {
    if ((h.getWall()).equals("S")) {
      nextDoor = h;
      validDoor = true;
    }
  }
  if (validDoor) {
      if (p.getX() == nextDoor.getLocation()) {
        Room newRoom = nextDoor.getConRoom();
        ArrayList<Door> m = newRoom.retDoor();
        for (Door f : m) {
          if ((f.getWall()).equals("N")) {
            player.setCurrentRoom(newRoom);
            p.setLocation(f.getLocation(), 1);
            player.setXyLocation(p);
            thruDoor = true;
            msg = msg + "...entered room: " + newRoom.getId();
          }
        }
      }
    }
  goodMove = false;
}

private void pickupItem(Room r) {
  if (!thruDoor) {
    if (p.getX() > 0 && p.getX() < r.getWidth() - 1) {
      if (p.getY() > 0 && p.getY() < r.getHeight() - 1) {
        goodMove = true;
        ArrayList<Item> roomIts = r.getRoomItems();
        int itemIndex = -1;
        for (Item remIt : roomIts) {
          Point itemPoint = remIt.getXyLocation();
          if (itemPoint.equals(player.getXyLocation())) {
            itemIndex = roomIts.indexOf(remIt);
            msg = "picked up: " + remIt.getName();
            pickedItem = true;
          }
        }
        if (itemIndex >= 0) {
          roomIts.remove(itemIndex);
        }
      }
    }
  }
}
/**
* moves the character.
*@param input userinput char
*@throws InvalidMoveException except
*@return a string for the player
*/
public String makeMove(char input) throws InvalidMoveException {
  Room r = player.getCurrentRoom();
  Point oldLoc = player.getXyLocation();
  nextDoor = null;
  doorss = r.retDoor();
   p = new Point();
   x = (int) oldLoc.getX();
   y = (int) oldLoc.getY();
  goodMove = true;
  thruDoor = false;
  validDoor = false;
  pickedItem = false;
   msg = "";
  moveDir(input);
  p.setLocation(x, y);
  if (p.getX() <= 0) {
    moveDoorWest();
  }
  if (p.getX() > r.getWidth() - 2) {
    moveDoorEast();
  }
  if (p.getY() <= 0) {
    moveDoorNorth();
  }
  // south door
  if (p.getY() > r.getHeight() - 2) {
    moveDoorSouth();
  }
  pickupItem(r);
  if (goodMove && !thruDoor) {
    player.setXyLocation(p);
    if (!pickedItem) {
    msg = msg + ".....( " + p.getX() + " , " + p.getY() + " )";
    }
  } else if (!goodMove && !thruDoor) {
    player.setXyLocation(oldLoc);
    throw new InvalidMoveException();
  }
  return msg;
}


/**
*adds a item to items arraylist.
*@param toAdd the map of stuff
*/
public void addItem(Map<String, String> toAdd) {
    if (toAdd.containsKey("x") && toAdd.containsKey("y")) {
    Item item = new Item();
    Room room = roomZ.get(Integer.parseInt(toAdd.get("room")) - 1);
    item.setCurrentRoom(room);
    item.setId(Integer.parseInt(toAdd.get("id")));
    int xpos = Integer.parseInt(toAdd.get("x"));
    int ypos = Integer.parseInt(toAdd.get("y"));
    Point point = new Point(xpos, ypos);
    item.setXyLocation(point);

    item.setDescription(toAdd.get("description"));
    item.setName(toAdd.get("name"));
    item.setType(toAdd.get("type"));
  // try and add item
    try {
      room.addItem(item);
    } catch (NoSuchItemException d) {
      System.out.println("No such item with id: " + item.getId());
      items.remove(toAdd);
    } catch (ImpossiblePositionException e) {
      item.setXyLocation(room.findEmptyTile());
      room.addItemNE(item);
    }
  }
}

/**
* parser caller.
*@param theDungeonInfo parser
*/
    public Rogue(RogueParser theDungeonInfo) {
      parser = theDungeonInfo;
      // getting our arraylist of doors
      doors = parser.retDoor();
      items = parser.getItems();
      itemLocs = parser.getItemLocs();
      ArrayList<Map<String, String>> temp = parser.retRooms();
      for (Map<String, String> roomMap : temp) {
        addRoom(roomMap);
      }
      for (Room r : roomZ) {
        ArrayList<Door> d = r.retDoor();
        for (Door door : d) {
          ArrayList<Integer> in = door.getRoomNums();
          for (Integer s : in) {
            for (Room z : roomZ) {
              if (z.getId() == s) {
                door.connectRoom(z);
              }
            }
          }
        }
      }

      for (Room r : roomZ) {
        doTheDoors(r);
            if (r.getStartBool()) {
              player.setCurrentRoom(r);
            }
      }
      for (Map<String, String> itemsMap : items) {
        addItem(itemsMap);
      }
      Room s = player.getCurrentRoom();
    }
/**
 * Sets the games player.
 * @param thePlayer the player
 */
    public void setPlayer(Player thePlayer) {
      player.setName(thePlayer.getName());

    }

    private void doTheDoors(Room r) {
      try {
          r.verifyRoom();
      } catch (NotEnoughDoorsException b) {
        Boolean foundSpace = false;
        for (Room z : roomZ) {
          if (!foundSpace) {
            if (z.getId() != r.getId()) {
                  ArrayList<Door> doorH = z.retDoor();
                  final int four = 4;
                  if (doorH.size() < four) {
                    Boolean n = false;
                    Boolean e = false;
                    Boolean s = false;
                    Boolean w = false;
                    for (Door d : doorH) {
                      String wall = d.getWall();
                      setWallStatus(n,s,e,w,wall);
                    }
                    Door d = new Door();
                    Door j = new Door();
                    ArrayList<Door> doorList = new ArrayList<>();
                    doorList.add(d);
                    doorList.add(j);
                    j.setCRN(z.getId());
                    d.setCRN(r.getId());
                    d.connectRoom(r);
                    j.connectRoom(z);
                    cDoor(n, e, s, w, doorList, z, r);
                    foundSpace = true;
                    z.addDoor(d);
                    r.addDoor(j);
                  }
                }
              }
            }
            if (!foundSpace) {
              System.out.println("This dungeon file cannot be used");
              System.exit(1);
            }
          }
    }

private void setWallStatus(boolean n, boolean s, boolean e, boolean w,
                                                    String wall) {
      if (wall.equals("N")) {
        n = true;
      }
      if (wall.equals("E")) {
        e = true;
      }
      if (wall.equals("S")) {
        s = true;
      }
      if (wall.equals("W")) {
        w = true;
      }
    }

private void cDoor(boolean n, boolean e, boolean s, boolean w,
                              ArrayList<Door> doorList, Room z, Room r) {
  Door d = doorList.get(0);
  Door j = doorList.get(1);
  if (!n) {
    d.setWall("N");
    j.setWall("S");
    d.setLocation(z.getWidth() / 2);
    j.setLocation(r.getWidth() / 2);

  } else if (!e) {
    d.setWall("E");
    j.setWall("W");
    d.setLocation(z.getHeight() / 2);
    j.setLocation(r.getHeight() / 2);
  } else if (!s) {
    d.setWall("S");
    j.setWall("N");
    d.setLocation(z.getWidth() / 2);
    j.setLocation(r.getWidth() / 2);
  } else if (!w) {
    d.setWall("W");
    j.setWall("E");
    d.setLocation(z.getHeight() / 2);
    j.setLocation(r.getHeight() / 2);
  }
}
/**
 * Rooms ArrayList getter.
 * @return ararylist of rooms
 */
    public ArrayList<Room> getRooms() {
        return roomZ;

    }

/**
 * Items ArrayList getter.
 * @return arraylist of items
 */
    public ArrayList<Map<String, String>> getItems() {
        return items;
    }

/**
 * Player getter.
 * @return player
 */
    public Player getPlayer() {
        return player;

    }


/**
 * Rooms ArrayList setter.
 *@param roomies arraylist of rooms
 */
    public void setRoomList(ArrayList<Room> roomies) {
      roomZ = roomies;
    }

/**
 * Display all rooms one after the other.
 *@return string of all the rooms.
*/
    public String displayAll() {
        String str = "";
        String temp;
        for (Room r : roomZ) {
          temp = r.displayRoom();
          str = str + "\n\n";
          str = str + temp;
        }
        return str;
    }

    /**
    *print all the room info.
    */
    public void printAllInfo() {
      for (Room r : roomZ) {
        r.printInfo();
      }
    }


}
