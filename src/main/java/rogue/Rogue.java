package rogue;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.Map;
import java.util.HashMap;
/**
 * The rogue game class.
 */
public class Rogue {
    private RogueParser parser;
    private ArrayList<Room> roomZ = new ArrayList<Room>();
    private ArrayList<Map<String,String>> items;
    private ArrayList<Map<String,String>> itemLocs;
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
*adds a room to roomZ arraylist
*@param toAdd the map of stuff
*/

public void addRoom(Map<String, String> toAdd) {
  Room room = new Room();
  room.setPlayer(player);
  room.setId(Integer.parseInt(toAdd.get("id")));
  room.setStart(toAdd.get("start"));
  room.setHeight(Integer.parseInt(toAdd.get("height")));
  room.setWidth(Integer.parseInt(toAdd.get("width")));
  room.setSymbols(parser.retSymbols());
  for(Door d : doors){
    if(d.getRoomID() == room.getId()){
      room.addDoor(d);
    }
  }
  roomZ.add(room);
}

/**
*returns the new display of the room
*@return string of the new room
*/
public String getNextDisplay(){
  Room r = player.getCurrentRoom();
  return r.displayRoom();
}

/**
*returns the new display of the room
*@return string of the new room
*/
public String getBlankDisplay(){
  int x =0;
  char[] temp = new char[1920];
  for(x = 0; x < 1920; x++) {
    temp[x] = ' ';
  }
  String str = new String(temp);
  return str;
}

private void moveDir(char input){
  if(input == UP){
    y--;
    msg = "moved N";
  }else if(input == DOWN){
    y++;
    msg = "moved E";
  }
  else if(input == LEFT){
    x--;
    msg = "moved W";
  }else if(input == RIGHT){
    x++;
    msg = "moved S";
  }
}

private void moveDoorWest(){
  for(Door h : doorss){
    if((h.getWall()).equals("W")){
      nextDoor = h;
      validDoor = true;
    }
  }
  if(validDoor == true){
    if(p.getY() == nextDoor.getLocation()){
      Room newRoom = nextDoor.getConRoom();
      ArrayList<Door> m = newRoom.retDoor();
      for(Door f : m){
        if((f.getWall()).equals("E")){
          player.setCurrentRoom(newRoom);
          p.setLocation(newRoom.getWidth()-2,f.getLocation());
          player.setXyLocation(p);
          thruDoor = true;
          msg = msg + "...entered room: " + newRoom.getId();

        }
      }
    }
  }
  goodMove = false;
}

private void moveDoorEast(){
  //if we go into east wall
  for(Door h : doorss){
    if((h.getWall()).equals("E")){
      nextDoor = h;
      validDoor = true;
    }
  }
  if(validDoor == true){
      if(p.getY() == nextDoor.getLocation()){
        Room newRoom = nextDoor.getConRoom();
        ArrayList<Door> m = newRoom.retDoor();
        for(Door f : m){
          if((f.getWall()).equals("W")){
            player.setCurrentRoom(newRoom);
            p.setLocation(1,f.getLocation());
            player.setXyLocation(p);
            thruDoor = true;
            msg = msg + "...entered room: " + newRoom.getId();
          }
        }
      }
    }
  goodMove = false;
}

private void moveDoorNorth(){
  for(Door h : doorss){
    if((h.getWall()).equals("N")){
      nextDoor = h;
      validDoor = true;
    }
  }
  if(validDoor == true){
    if(p.getX() == nextDoor.getLocation()){
      Room newRoom = nextDoor.getConRoom();
      ArrayList<Door> m = newRoom.retDoor();
      for(Door f : m){
        if((f.getWall()).equals("S")){
          player.setCurrentRoom(newRoom);
          p.setLocation(f.getLocation(),newRoom.getHeight()-2);
          player.setXyLocation(p);
          thruDoor = true;
          msg = msg + "...entered room: " + newRoom.getId();
        }
      }
    }
  }
  goodMove = false;
}

private void moveDoorSouth(){
  for(Door h : doorss){
    if((h.getWall()).equals("S")){
      nextDoor = h;
      validDoor = true;
    }
  }
  if(validDoor == true){
      if(p.getX() == nextDoor.getLocation()){
        Room newRoom = nextDoor.getConRoom();
        ArrayList<Door> m = newRoom.retDoor();
        for(Door f : m){
          if((f.getWall()).equals("N")){
            player.setCurrentRoom(newRoom);
            p.setLocation(f.getLocation(),1);
            player.setXyLocation(p);
            thruDoor = true;
            msg = msg + "...entered room: " + newRoom.getId();
          }
        }
      }
    }
  goodMove = false;
}
/**
* moves the character
*@param input userinput char
*@return a string for the player
*/
public String makeMove(char input) throws InvalidMoveException{
  Room r = player.getCurrentRoom();
  Point oldLoc = player.getXyLocation();
  nextDoor = null;
  doorss = r.retDoor();
   p = new Point();
   x = (int)oldLoc.getX();
   y = (int)oldLoc.getY();
  goodMove = true;
  thruDoor = false;
  validDoor = false;
  pickedItem = false;
   msg = "";
  moveDir(input);

  p.setLocation(x,y);

  if(p.getX() <= 0){
    moveDoorWest();
  }
  if(p.getX() > r.getWidth()-2){
    moveDoorEast();
  }
  if(p.getY() <= 0){
    moveDoorNorth();
  }
  // south door
  if(p.getY() > r.getHeight() - 2){
    moveDoorSouth();
  }
if(thruDoor == false){
  if(p.getX() > 0 && p.getX() < r.getWidth() - 1){
    if(p.getY() > 0 && p.getY() < r.getHeight() -1){
      goodMove = true;
      ArrayList<Item> roomIts= r.getRoomItems();
      int itemIndex = -1;
      for(Item remIt : roomIts){
        Point itemPoint = remIt.getXyLocation();
        if(itemPoint.equals(player.getXyLocation())){
          itemIndex = roomIts.indexOf(remIt);
          msg = "picked up: " + remIt.getName();
          pickedItem = true;
        }
      }
      if(itemIndex >= 0){
        roomIts.remove(itemIndex);
      }
    }
  }
}
  if(goodMove == true && thruDoor == false){
    player.setXyLocation(p);
    if(pickedItem == false){
    msg = msg + ".....( " + p.getX() + " , " + p.getY() + " )";
    }
  }else if(goodMove == false && thruDoor == false){
    player.setXyLocation(oldLoc);
    throw new InvalidMoveException();
  }
  return msg;
}


/**
*adds a item to items arraylist
*@param toAdd the map of stuff
*/
public void addItem(Map<String, String> toAdd) {
    if(toAdd.containsKey("x") && toAdd.containsKey("y")){
    Item item = new Item();
    Room room = roomZ.get(Integer.parseInt(toAdd.get("room"))-1);
    item.setCurrentRoom(room);
    item.setId(Integer.parseInt(toAdd.get("id")));
    int x = Integer.parseInt(toAdd.get("x"));
    int y = Integer.parseInt(toAdd.get("y"));
    Point p = new Point(x,y);
    item.setXyLocation(p);

    item.setDescription(toAdd.get("description"));
    item.setName(toAdd.get("name"));
    item.setType(toAdd.get("type"));
  // try and add item
  try{
    room.addItem(item);
  }catch (NoSuchItemException d){
    System.out.println("No such item with id: " + item.getId());
    items.remove(toAdd);
  }catch (ImpossiblePositionException e) {
    item.setXyLocation(room.findEmptyTile());
    room.addItemNE(item);
  }
  }
}

/**
* parser caller
*@param theDungeonInfo parser
*/
    public Rogue(RogueParser theDungeonInfo) {
      parser = theDungeonInfo;
      // getting our arraylist of doors
      doors = parser.retDoor();
      items = parser.getItems();
      itemLocs = parser.getItemLocs();
      ArrayList<Map<String, String>> temp = parser.retRooms();
      for(Map<String, String> roomMap : temp){
        addRoom(roomMap);
      }
      for(Room r : roomZ){
        ArrayList<Door> d = r.retDoor();
        for(Door door : d){
          ArrayList<Integer> in = door.getRoomNums();
          for(Integer x : in){
            for(Room z : roomZ){
              if(z.getId() == x){
                door.connectRoom(z);
              }
            }
          }
        }
      }

      for(Room r : roomZ){
        doTheDoors(r);
            if(r.getStartBool()){
              player.setCurrentRoom(r);
            }
      }
      for(Map<String, String> itemsMap : items) {
        addItem(itemsMap);
      }
      Room x = player.getCurrentRoom();

    }
/**
 * Sets the games player.
 * @param thePlayer the player
 */
    public void setPlayer(Player thePlayer) {
      player.setName(thePlayer.getName());

    }

    private void doTheDoors(Room r){
      try{
          r.verifyRoom();
      }catch(NotEnoughDoorsException b){
        Boolean foundSpace = false;
        for(Room z : roomZ){
          if(z.getId() != r.getId()){
                ArrayList<Door> doorH = z.retDoor();

                if(doorH.size() < 4){
                  Boolean N = false;
                  Boolean E = false;
                  Boolean S = false;
                  Boolean W = false;
                  for(Door d : doorH){
                    String wall = d.getWall();
                    if(wall.equals("N")){
                      N = true;
                    }
                    if(wall.equals("E")){
                      E = true;
                    }
                    if(wall.equals("S")){
                      S = true;
                    }
                    if(wall.equals("W")){
                      W = true;
                    }
                  }
                  Door d = new Door();
                  Door j = new Door();
                  j.setCRN(z.getId());
                  d.setCRN(r.getId());
                  if(N == false){
                    d.setWall("N");
                    j.setWall("S");
                    d.setLocation(z.getWidth()/2);
                    j.setLocation(r.getWidth()/2);
                  }else if(E == false){
                    d.setWall("E");
                    j.setWall("W");
                    d.setLocation(z.getHeight()/2);
                    j.setLocation(r.getHeight()/2);
                  }else if(S == false){
                    d.setWall("S");
                    j.setWall("N");
                    d.setLocation(z.getWidth()/2);
                    j.setLocation(r.getWidth()/2);
                  }else if(W == false){
                    d.setWall("W");
                    j.setWall("E");
                    d.setLocation(z.getHeight()/2);
                    j.setLocation(r.getHeight()/2);
                  }
                  foundSpace = true;
                  z.addDoor(d);
                  r.addDoor(j);
                }
              }
            }
            if(foundSpace == false) {
              System.out.println("This dungeon file cannot be used");
              System.exit(1);
            }
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
    public ArrayList<Map<String,String>> getItems() {
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
        int x = 0;
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
    *print all the room info
    */
    public void printAllInfo() {
      for(Room r : roomZ) {
        r.printInfo();
      }
    }


}
