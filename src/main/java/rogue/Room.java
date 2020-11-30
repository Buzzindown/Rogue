package rogue;
import java.util.ArrayList;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room implements Serializable {
  private static final long serialVersionUID = 2426928157109291125L;
  private int width;
  private int height;
  private int id;
  private boolean start;
  private ArrayList<Item> roomItems;
  private Map<String, Door> door;
  private HashMap<String, Character> symbols;
  private Player player;
  private boolean isPiNR;
  private ArrayList<Door> returningDoors;
  private int xx = 0;
  private int yy = 0;
  private char tb;
  private char lr;
  private char itemZ;
  private char plyr;
  private char flr;
  private char dr;
  private char gold;
  private char ptn;
  private char scroll;
  private char clthing;
  private char food;
  private char ring;
  private char smallf;
  private int zz;
/**
*find a empty tile.
*@return a point to move our item to
*/
public Point findEmptyTile() {
  Point ret = new Point(1, 1);
  Point play = player.getXyLocation();
  for (yy = 1; yy < height - 1; yy++) {
    for (xx = 1; xx < width - 1; xx++) {
      Point temp = new Point(xx, yy);
      if (!roomItems.isEmpty()) {
          for (Item e : roomItems) {
            if ((!(temp.equals(e.getXyLocation()))) && (!(temp.equals(play)))) {
              ret = temp;
            }
          }
        } else {
                ret = temp;
        }
    }
  }
  return ret;
}

/**
*@return if play is in room.
*/
public Boolean isPlayerInRoom() {
  return isPiNR;
}

/**
*set if player is in room.
*@param b
*/
public void setPlayerInRoom(Boolean b) {
  isPiNR = b;
}

/**
* Default room constructor.
*/
 public Room() {
  roomItems = new ArrayList<Item>();
  door = new HashMap<String, Door>();
  returningDoors = new ArrayList<Door>();
  isPiNR = false;
 }

 /**
 * add door to arraylist.
 *@param toAdd new door
 */
 public void addDoor(Door toAdd) {
   if ((toAdd.getWall()).equals("N")) {
     door.put("N", toAdd);
   } else if ((toAdd.getWall()).equals("E")) {
     door.put("E", toAdd);
   } else if ((toAdd.getWall()).equals("S")) {
     door.put("S", toAdd);
   } else if ((toAdd.getWall()).equals("W")) {
     door.put("W", toAdd);
   }
 }

 /**
 *check if rooms r good.
 *@return if the room is good
 *@throws NotEnoughDoorsException except
 */
 public Boolean verifyRoom() throws NotEnoughDoorsException {
   if (door.isEmpty()) {
     throw new NotEnoughDoorsException();
   } else {
     return true;
   }
 };

 /**
 *get door arraylist.
 *@return door arraylist
 */
 public ArrayList<Door> retDoor() {
   returningDoors.clear();
   for (Door d : door.values()) {
     returningDoors.add(d);
   }
   return returningDoors;
 }

 /**
 *get door arraylist.
 *@return door arraylist
 */
 public ArrayList<Door> retDoorNE() {
   returningDoors.clear();
   for (Door d : door.values()) {
     returningDoors.add(d);
   }
   return returningDoors;
 }

 /**
 * Symbol ArrayList setter.
 *@param symbs arraylist of symbols
 */
 public void setSymbols(HashMap<String, Character> symbs) {
   symbols = symbs;
 }

 /**
 *add items.
 *@param toAdd
 *@throws ImpossiblePositionException except;
 *@throws NoSuchItemException except;
 */
 public void addItem(Item toAdd) throws ImpossiblePositionException, NoSuchItemException {
   Point p = toAdd.getXyLocation();
   if ((p == null) || (toAdd.getCurrentRoom() == null)) {
     throw new NoSuchItemException();
   }
   if ((p.getX() > width - 2 || p.getX() < 1) || (p.getY() > height - 2 || p.getY() < 1)) {
     throw new ImpossiblePositionException();
   }
   for (Item e : roomItems) {
     Point p2 = e.getXyLocation();
     Point p3 = toAdd.getXyLocation();
        p = player.getXyLocation();
     if ((p2.equals(p3)) || (p.equals(toAdd.getXyLocation()))) {
       throw new ImpossiblePositionException();
     }
   }
   roomItems.add(toAdd);
 }

 /**
 *add item no excep.
 *@param toAdd item
 */

 public void addItemNE(Item toAdd) {
   roomItems.add(toAdd);
 }

 /**
 * Set whether the player starts in this room or not.
 *@param bool start boolean
 */
public void setStart(String bool) {
  if (bool.equals("true")) {
    start = true;
  } else {
    start = false;
  }
}

/**
* Player start boolean getter.
*@return start
*/
public boolean getStartBool() {
  return start;
}

/**
* Width getter.
*@return width
*/
 public int getWidth() {
  return width;
}

/**
* Width setter.
*@param newWidth new width
*/
 public void setWidth(int newWidth) {
  width = newWidth;
 }

 /**
 * height getter.
 * @return height
 */
 public int getHeight() {
  return height;
 }

 /**
 * Height setter.
 * @param newHeight new height
 */
 public void setHeight(int newHeight) {
   height = newHeight;
 }

 /**
 * Room number getter.
 * @return id
 */
 public int getId() {
    return id;
 }

 /**
 * Room number setter.
 * @param newId
 */
 public void setId(int newId) {
   id = newId;
 }

 /**
 * Arraylist of items getter.
 * @return roomItems
 */
 public ArrayList<Item> getRoomItems() {
    return roomItems;
 }

 /**
 * Arraylist of items setter.
 * @param newRoomItems arraylist of room items
 */
 public void setRoomItems(ArrayList<Item> newRoomItems) {
   roomItems = newRoomItems;
 }

 /**
 * Room's player getter.
 * @return the player
 */
 public Player getPlayer() {
    return player;
 }

 /**
 * Room player setter.
 * @param newPlayer
 */
 public void setPlayer(Player newPlayer) {
   player = newPlayer;
 }

/*
* Debug method that prints json parsed info about a room.

 public void printInfo() {
   int i = 0;
   System.out.println("Room id: " + id);
   System.out.println("height: " + height);
   System.out.println("width: " + width);
   System.out.println("start: " + getStartBool());
   System.out.println("Doors ... ");
   for (Door d : door.values()) {
     System.out.println("Wall: " + d.getWall());
     ArrayList<Integer> tempRN = d.getRoomNums();
     for (Integer r : tempRN) {
     System.out.println("Connected room: " + r);
    }
    System.out.println("Location: " + d.getLocation());
   }
   int sizeB = roomItems.size();
   if (sizeB > 0) {
     System.out.println("Loot ...");
     for (i = 0; i < sizeB; i++) {
       Item temp = roomItems.get(i);
       Point p = temp.getXyLocation();
       System.out.println("Item id: " + temp.getId() + " name: " + temp.getName()
       + " type: " + temp.getType() + " at position " + p.getX() + "," + p.getY());
       System.out.println("Item description: " + temp.getDescription());
     }
   } else {
     System.out.println("No loot in this room");
   }
   System.out.println();
 }
*/


/**
* Produces a string that can be printed to produce an ascii rendering of the room and all of its contents.
* @return (String) String representation of how the room looks.
*/
   public String displayRoom() {
// printing header
    String head = "<---- [Room " + id + "] ---->\n";
    if (start) {
      String head2 = "- Starting Room\n";
      head = head + head2;
    }
    char[] output = new char[(width + 1) * height];
    setSymbolsforRooms();
    output = printBaseRoom(output);
   output = printItemsPlayer(output);
   output = printDoors(output);
  String str = String.valueOf(output);
  str = head + str;
  return str;
   }

   private void setSymbolsforRooms() {
      tb = symbols.get("NS_WALL");
      lr = symbols.get("EW_WALL");
      plyr = symbols.get("PLAYER");
      flr = symbols.get("FLOOR");
      dr = symbols.get("DOOR");
      gold = symbols.get("GOLD");
      ptn = symbols.get("POTION");
      scroll = symbols.get("SCROLL");
      clthing = symbols.get("CLOTHING");
      food = symbols.get("FOOD");
      ring = symbols.get("RING");
      smallf = symbols.get("SMALLFOOD");

   }

  private char[] printItemsPlayer(char[] output) {
    int x = 0;
    setItemSpots(output);
    boolean playerNSet = true;
    x = 0;
    int hightMod = 0;
    int widMod = 0;
    Room r = player.getCurrentRoom();
    if (r.getId() == id) {
      int h = width + 1;
      Point temP = player.getXyLocation();
      h += (int) temP.getY() * (width + 1);
      h = h - (width + 1 - (int) temP.getX());
      output[h] = plyr;
    }
    return output;
  }

  private void setItemSpots(char[] output) {
    int p = 0;
    for (p = 0; p < roomItems.size(); p++) {
      Item item = roomItems.get(p);
      Point point = item.getXyLocation();
      int h = (int) point.getX();
      int j = (int) point.getY();
      int location = ((width + 1) * j) + h;
      char symb = setItemSymb(item);
      output[location] = symb;
    }
  }

  private char setItemSymb(Item item) {
    String type = item.getType();
    type = type.toUpperCase();
    char symbol = 'F';
    symbol = symbols.get(type);
    return symbol;
  }

  private char[] printBaseRoom(char[] output) {
    int x = 0;
    int p = 0;
    buildRoomTopFlr(x, output);
    x = width + 1;
    for (zz = 0; zz < height - 2; zz++) {
      output[x] = lr;
      x++;
      for (p = 0; p < width - 2; p++) {
        output[x] = flr;
        x++;
      }
      output[x] = lr;
      x++;
      output[x] = '\n';
      x++;
    }
    buildRoomBotFlr(x, output);
    return output;
  }

  private void buildRoomTopFlr(int x, char[] output) {
    for (zz = 0; zz < width; zz++) {
        output[x] = tb;
        x++;
    }
      output[x] = '\n';
      x++;
  }

  private void buildRoomBotFlr(int x, char[] output) {
    for (zz = 0; zz < width; zz++) {
        output[x] = tb;
        x++;
    }
      output[x] = '\n';
      x++;
  }


  private char[] printDoors(char[] output) {
  int k = 0;
  for (Door d : door.values()) {
    setDoorNorth(d, output);
    setDoorEast(d, output);
    setDoorSouth(d, output);
    setDoorWest(d, output);

}

    return output;
  }

  private void setDoorNorth(Door d, char[] output) {
    int val = d.getLocation();
    if (d.getWall().equals("N")) {
      if (val > 0) {
        output[val] = dr;
      }
    }
  }

  private void setDoorEast(Door d, char[] output) {
    int val = d.getLocation();
    int k = 0;
    if (d.getWall().equals("E")) {
      if (val > 0) {
          k = (width + 1) * (val + 1) - 2;
          output[k] = dr;
      }
    }
  }

  private void setDoorSouth(Door d, char[] output) {
    int val = d.getLocation();
    int k = 0;
    if (d.getWall().equals("S")) {
      if (val > 0) {
          k = (width + 1) * (height - 1) + val;
          output[k] = dr;
      }
    }
  }

  private void setDoorWest(Door d, char[] output) {
    int val = d.getLocation();
    int k = 0;
    if (d.getWall().equals("W")) {
      if (val > 0) {
          k = ((width + 1) * (val));
          output[k] = dr;
      }
    }
  }

}
