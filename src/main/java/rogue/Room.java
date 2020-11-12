package rogue;
import java.util.ArrayList;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room  {
  private int width;
  private int height;
  private int id;
  private boolean start;
  private ArrayList<Item> roomItems;
  private Map<String, Door> door;
  private HashMap<String, Character> symbols;
  private Player player;
  private ArrayList<Door> returningDoors;


/**
*find a empty tile.
*@return a point to move our item to
*/
public Point findEmptyTile() {
  int x = 0;
  int y = 0;
  Point ret = new Point(1, 1);
  Point play = player.getXyLocation();
  int blank = 0;
  for (y = 1; y < height - 1; y++) {
    for (x = 1; x < width - 1; x++) {
      Point temp = new Point(x, y);
      if (!roomItems.isEmpty()) {
          for (Item e : roomItems) {
            if (!(temp.equals(e.getXyLocation()))) {
              if (!(temp.equals(play))) {

              ret = temp;
              }
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
* Default room constructor.
*/
 public Room() {
  roomItems = new ArrayList<Item>();
  door = new HashMap<String, Door>();
  returningDoors = new ArrayList<Door>();
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

   if (p == null) {
     throw new NoSuchItemException();
   }
   if (toAdd.getCurrentRoom() == null) {
     throw new NoSuchItemException();
   }
   if (p.getX() > width - 2 || p.getX() < 1) {
     throw new ImpossiblePositionException();
   }
   if (p.getY() > height - 2 || p.getY() < 1) {
     throw new ImpossiblePositionException();
   }
   for (Item e : roomItems) {
     Point p2 = e.getXyLocation();
     Point p3 = toAdd.getXyLocation();
     if (p2.equals(p3)) {
       throw new ImpossiblePositionException();
     }
   }
   p = player.getXyLocation();
   if (p.equals(toAdd.getXyLocation())) {
     throw new ImpossiblePositionException();
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

/**
* Debug method that prints json parsed info about a room.
*/
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
    // this is our output string its + 1 width to fit a newline char
    char[] output = new char[(width + 1) * height];
    // getting all of our symbols for printing
    char tb = symbols.get("NS_WALL");
    char lr = symbols.get("EW_WALL");
    char itemZ = symbols.get("ITEM");
    char plyr = symbols.get("PLAYER");
    char flr = symbols.get("FLOOR");
    char dr = symbols.get("DOOR");

    output = printBaseRoom(output, tb, lr, flr);
    // setting our item locations
   output = printItemsPlayer(output, itemZ, flr, plyr);
// printing our door locations
   output = printDoors(output, dr);
// putting our head and string together
  String str = String.valueOf(output);
  str = head + str;
  return str;
   }

  private char[] printItemsPlayer(char[] output, char itemZ, char flr, char plyr) {
    int x = 0;
    int p = 0;
    for (p = 0; p < roomItems.size(); p++) {
      Item item = roomItems.get(p);
      Point point = item.getXyLocation();
      int h = (int) point.getX();
      int j = (int) point.getY();
      int location = ((width + 1) * j) + h;
      // item character
      output[location] = itemZ;
    }
    // used in loop for placing character
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

  private char[] printBaseRoom(char[] output, char tb, char lr, char flr) {
    int x = 0;
    int z = 0;
    int p = 0;

    for (z = 0; z < width; z++) {
        output[x] = tb;
        x++;
    }
    // newline
      output[x] = '\n';
      x++;
    for (z = 0; z < height - 2; z++) {
      // left wall character
      output[x] = lr;
      x++;
      for (p = 0; p < width - 2; p++) {
        // floor character
        output[x] = flr;
        x++;
      }
      // right wall character
      output[x] = lr;
      x++;
      output[x] = '\n';
      x++;
    }
    for (z = 0; z < width; z++) {
      // bottom wall character
        output[x] = tb;
        x++;
    }
      output[x] = '\n';
      x++;

    return output;
  }


  private char[] printDoors(char[] output, char dr) {
  int k = 0;
  for (Door d : door.values()) {
    int val = d.getLocation();
    if (d.getWall().equals("N")) {
      if (val > 0) {
        output[val] = dr;
      }
    }
    if (d.getWall().equals("E")) {
      if (val > 0) {
        if (val > 0) {
          k = (width + 1) * (val + 1) - 2;
          output[k] = dr;
        }
      }
    }
    if (d.getWall().equals("S")) {
      if (val > 0) {
        if (val > 0) {
          k = (width + 1) * (height - 1) + val;
          output[k] = dr;
        }
      }
    }
    if (d.getWall().equals("W")) {
      if (val > 0) {
        if (val > 0) {
          k = ((width + 1) * (val));
          output[k] = dr;
        }
      }
    }
}

    return output;
  }

}
