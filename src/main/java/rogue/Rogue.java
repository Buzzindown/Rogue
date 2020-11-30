package rogue;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.io.Serializable;
/**
 * The rogue game class.
 */
public class Rogue implements Serializable {
    private static final long serialVersionUID = 7424074459531017606L;
    private transient RogueParser parser;
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
    private Point p = new Point();
    private Door nextDoor;
    private boolean cN;
    private boolean cE;
    private boolean cS;
    private boolean cW;
    private boolean foundSpace;
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
  moveDoorWestFinder();
  if (validDoor) {
    if (p.getY() == nextDoor.getLocation()) {
      Room newRoom = nextDoor.getConRoom();
      ArrayList<Door> m = newRoom.retDoor();
      for (Door f : m) {
        if ((f.getWall()).equals("E")) {
          (player.getCurrentRoom()).setPlayerInRoom(false);
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

private void moveDoorWestFinder() {
  for (Door h : doorss) {
    if ((h.getWall()).equals("W")) {
      nextDoor = h;
      validDoor = true;
    }
  }
}

private void moveDoorEast() {
  moveDoorEastFinder();
  if (validDoor) {
      if (p.getY() == nextDoor.getLocation()) {
        Room newRoom = nextDoor.getConRoom();
        ArrayList<Door> m = newRoom.retDoor();
        for (Door f : m) {
          if ((f.getWall()).equals("W")) {
            (player.getCurrentRoom()).setPlayerInRoom(false);
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

private void moveDoorEastFinder() {
  //if we go into east wall
  for (Door h : doorss) {
    if ((h.getWall()).equals("E")) {
      nextDoor = h;
      validDoor = true;
    }
  }
}

private void moveDoorNorth() {
moveDoorNorthFinder();
  if (validDoor) {
    if (p.getX() == nextDoor.getLocation()) {
      Room newRoom = nextDoor.getConRoom();
      ArrayList<Door> m = newRoom.retDoor();
      for (Door f : m) {
        if ((f.getWall()).equals("S")) {
          (player.getCurrentRoom()).setPlayerInRoom(false);
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

private void moveDoorNorthFinder() {
  for (Door h : doorss) {
    if ((h.getWall()).equals("N")) {
      nextDoor = h;
      validDoor = true;
    }
  }
}

private void moveDoorSouth() {
  moveDoorSouthFinder();
  if (validDoor) {
      if (p.getX() == nextDoor.getLocation()) {
        Room newRoom = nextDoor.getConRoom();
        ArrayList<Door> m = newRoom.retDoor();
        for (Door f : m) {
          if ((f.getWall()).equals("N")) {
            (player.getCurrentRoom()).setPlayerInRoom(false);
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

private void moveDoorSouthFinder() {
  for (Door h : doorss) {
    if ((h.getWall()).equals("S")) {
      nextDoor = h;
      validDoor = true;
    }
  }
}

private void pickupItem(Room r) {
  if (!thruDoor) {
    if ((p.getX() > 0 && p.getX() < r.getWidth() - 1)  && (p.getY() > 0 && p.getY() < r.getHeight() - 1)) {
        goodMove = true;
        ArrayList<Item> roomIts = r.getRoomItems();
        int itemIndex = -1;
        for (Item remIt : roomIts) {
          Point itemPoint = remIt.getXyLocation();
          if (itemPoint.equals(player.getXyLocation())) {
            itemIndex = roomIts.indexOf(remIt);
            msg = "picked up: " + remIt.getName() + " (" + remIt.getDescription() + ")";
            pickItemAndSet(remIt);
          }
        }
        if (itemIndex >= 0) {
          roomIts.remove(itemIndex);
        }
      }
  }
}

private void pickItemAndSet(Item item) {
    pickedItem = true;
    player.addToInv(item);
}
/**
* moves the character.
*@param input userinput char
*@param itemName name of an item to eat/wear/toss
*@throws InvalidMoveException except
*@return a string for the player
*/
public String makeMove(char input, String itemName) throws InvalidMoveException {
  if (input == 'w' || input == 'a' || input == 's' || input == 'd') {
  Room r = player.getCurrentRoom();
  Point oldLoc = player.getXyLocation();
  doorss = r.retDoor();
  setUpToMakeMove(oldLoc, r, input);
  if (goodMove && !thruDoor) {
    player.setXyLocation(p);
    if (!pickedItem) {
    msg = msg + ".....( " + p.getX() + " , " + p.getY() + " )";
    }
  } else if (!goodMove && !thruDoor) {
    player.setXyLocation(oldLoc);
    throw new InvalidMoveException();
  }
} else {
  msg = eatWearThrow(itemName, input);
}
  return msg;
}

private void setUpToMakeMove(Point oldLoc, Room r, char input) {
  initializeMoveVals(oldLoc);
  moveDir(input);
  p.setLocation(x, y);
  moveAllDoors(r);
  pickupItem(r);
}

private String eatWearThrow(String string, char inputchar) {
  String str = " ";
  // eat
  if (inputchar == 'j') {
    str = tryToEatItem(string);
  } else if (inputchar == 'k') {
    str = tryToWearItem(string);
  } else if (inputchar == 'm') {
    str = tryToTossItem(string);
  }
  return str;
}

private String tryToTossItem(String string) {
  String str;
  ArrayList<Item> plyrItems = player.getInvList();
  for (Item it : plyrItems) {
    if (it.getName().equals(string)) {
      str = it.toss();
      if (it.getTossable()) {
      player.remFrominv(it);
      putItemBackinRoom(it);
      }
      return str;
    }
  }
  return " ";
}

private void putItemBackinRoom(Item e) {
  Room room = player.getCurrentRoom();
  try {
    room.addItem(e);
  } catch (ImpossiblePositionException exep) {
    e.setXyLocation(room.findEmptyTile());
    room.addItemNE(e);
  } catch (NoSuchItemException excep) {
    System.out.println(excep);
  }
}

private String tryToWearItem(String string) {
  String str;
  ArrayList<Item> plyrItems = player.getInvList();
  for (Item it : plyrItems) {
    if (it.getName().equals(string)) {
      str = it.wear();
      if (it.getWearable()) {
      player.remFrominv(it);
      }
      return str;
    }
  }
    return " ";
}

private String tryToEatItem(String string) {
  String str;
  ArrayList<Item> plyrItems = player.getInvList();
  for (Item it : plyrItems) {
    if (it.getName().equals(string)) {
      str = it.eat();
      if (it.getEdible()) {
      player.remFrominv(it);
      }
      return str;
    }
  }
    return " ";
}

private void initializeMoveVals(Point oldLoc) {
    p = new Point();
  nextDoor = null;
   x = (int) oldLoc.getX();
   y = (int) oldLoc.getY();
  goodMove = true;
  thruDoor = false;
  validDoor = false;
  pickedItem = false;
   msg = "";
}

private void moveAllDoors(Room r) {
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
}


/**
*adds a item to items arraylist.
*@param toAdd the map of stuff
*/
public void addItem(Map<String, String> toAdd) {
    if (toAdd.containsKey("x") && toAdd.containsKey("y")) {
      Item item = settingItem(toAdd);
      Room room = roomZ.get(Integer.parseInt(toAdd.get("room")) - 1);

      item.setCurrentRoom(room);

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



private Item settingItem(Map<String, String> toAdd) {
  Item item = createRightItem(toAdd.get("type"));
  item.setId(Integer.parseInt(toAdd.get("id")));
  int xpos = Integer.parseInt(toAdd.get("x"));
  int ypos = Integer.parseInt(toAdd.get("y"));
  Point point = new Point(xpos, ypos);
  item.setXyLocation(point);
  item.setDescription(toAdd.get("description"));
  item.setName(toAdd.get("name"));
  item.setType(toAdd.get("type"));
  if (((item.getType()).equalsIgnoreCase("Potion")) || ((item.getType()).equalsIgnoreCase("SmallFood"))) {
    item.setAbilities();
  }
  return item;
}

private Item createRightItem(String type) {
  if (type.equalsIgnoreCase("Food")) {
    return new Food();
  } else if (type.equalsIgnoreCase("SmallFood")) {
    return new SmallFood();
  } else if (type.equalsIgnoreCase("Clothing")) {
    return new Clothing();
  } else if (type.equalsIgnoreCase("Potion")) {
    return new Potion();
  } else if (type.equalsIgnoreCase("Ring")) {
    return new Ring();
  } else {
    return new Item();
  }

}
/**
*public rogue.
*/
    public Rogue() {
      System.out.println("make a new rogue with parser");
    }

/**
* parser caller.
*@param theDungeonInfo parser
*/
    public Rogue(RogueParser theDungeonInfo) {
      startRogueSetup(theDungeonInfo);
      ArrayList<Map<String, String>> temp = parser.retRooms();
      for (Map<String, String> roomMap : temp) {
        addRoom(roomMap);
      }
      checkAllDoorConnects();
      for (Room r : roomZ) {
        doTheDoors(r);
            if (r.getStartBool()) {
              player.setCurrentRoom(r);
              r.setPlayerInRoom(true);
            }
      }
      for (Map<String, String> itemsMap : items) {
        addItem(itemsMap);
      }
      Room s = player.getCurrentRoom();
    }

    private void startRogueSetup(RogueParser theDungeonInfo) {
      parser = theDungeonInfo;
      doors = parser.retDoor();
      items = parser.getItems();
      itemLocs = parser.getItemLocs();
    }

    private void checkAllDoorConnects() {
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
    }
/**
 * Sets the games player.
 * @param thePlayer the player
 */
    public void setPlayer(Player thePlayer) {
      player.setName(thePlayer.getName());

    }

    private void setDirsFalse() {
       cN = false;
       cE = false;
       cS = false;
       cW = false;
    }

    private void doTheDoors(Room r) {
      try {
          r.verifyRoom();
      } catch (NotEnoughDoorsException b) {
        foundSpace = false;
        for (Room z : roomZ) {
          if (!foundSpace) {
            if (z.getId() != r.getId()) {
                  addDoorsToRooms(z, r);
                }
              }
            }
            if (!foundSpace) {
              System.out.println("This dungeon file cannot be used");
              JFrame rent = new JFrame();
              JOptionPane.showMessageDialog(rent, "This file cannot be used");
              System.exit(1);
            }
          }
    }

private void addDoorsToRooms(Room z, Room r) {
  ArrayList<Door> doorH = z.retDoor();
  final int four = 4;
  if (doorH.size() < four) {
    setDirsFalse();
    for (Door d : doorH) {
      String wall = d.getWall();
      setWallStatus(wall);
    }
    Door d = new Door();
    Door j = new Door();
    ArrayList<Door> doorList = new ArrayList<>();
    adjDoors(d, j, r, z, doorList);
  }
}

private void adjDoors(Door d, Door j, Room r, Room z, ArrayList<Door> doorList) {
  doorList.add(d);
  doorList.add(j);
  j.setCRN(z.getId());
  d.setCRN(r.getId());
  d.connectRoom(r);
  j.connectRoom(z);
  cDoor(doorList, z, r);
  foundSpace = true;
  z.addDoor(d);
  r.addDoor(j);
}

private void setWallStatus(String wall) {
      if (wall.equals("N")) {
        cN = true;
      }
      if (wall.equals("E")) {
        cE = true;
      }
      if (wall.equals("S")) {
        cS = true;
      }
      if (wall.equals("W")) {
        cW = true;
      }
    }

private void cDoor(ArrayList<Door> doorList, Room z, Room r) {
  Door d = doorList.get(0);
  Door j = doorList.get(1);
    if (!cN) {
      setNewWallNS(d, j, r, z);
    } else if (!cE) {
      setNewWallEW(d, j, r, z);
    } else if (!cS) {
      setNewWallSN(d, j, r, z);
    } else if (!cW) {
      setNewWallWE(d, j, r, z);
  }
}

private void setNewWallNS(Door d, Door j, Room r, Room z) {
  d.setWall("N");
  j.setWall("S");
  d.setLocation(z.getWidth() / 2);
  j.setLocation(r.getWidth() / 2);
}

private void setNewWallEW(Door d, Door j, Room r, Room z) {
  d.setWall("E");
  j.setWall("W");
  d.setLocation(z.getHeight() / 2);
  j.setLocation(r.getHeight() / 2);
}

private void setNewWallSN(Door d, Door j, Room r, Room z) {
  d.setWall("S");
  j.setWall("N");
  d.setLocation(z.getWidth() / 2);
  j.setLocation(r.getWidth() / 2);
}

private void setNewWallWE(Door d, Door j, Room r, Room z) {
  d.setWall("W");
  j.setWall("E");
  d.setLocation(z.getHeight() / 2);
  j.setLocation(r.getHeight() / 2);
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

    /*
    public void printAllInfo() {
      for (Room r : roomZ) {
        r.printInfo();
      }
    }
  */

}
