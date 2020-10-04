package rogue;
import java.util.ArrayList;
import java.util.Map;
import java.awt.Point;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * A room within the dungeon - contains monsters, treasure,
 * doors out, etc.
 */
public class Room  {
  private int width;
  private int height;
  private int id;
  private boolean start;
  private boolean isPinR;
  private ArrayList<Item> RoomItems;
  private ArrayList<String> DoorDir;
  private ArrayList<Integer> DoorPos;
  private ArrayList<Symbol> symbols;
  private Player player;
  private int DN;
  private int DE;
  private int DS;
  private int DW;



    // Default constructor
 public Room() {

 }


 public void setSymbols(ArrayList<Symbol> symbs){
   symbols = symbs;
 }


 public char getSymbolbyName(String name){
   int x = 0;
   for(x = 0; x < symbols.size();x++){
     Symbol symb = symbols.get(x);
     if(symb.getSymbolName().equals(name)){
       return symb.getSymbol();
     }
   }
   return '!';
 }

  // Setting whether this is our start room
public void setStart(String bool){
  if(bool.equals("true")){
    start = true;
  }else{
    start = false;
  }
}
  //gets start bool as string
public String getStart(){
  if(start == true){
    return "true";
  }else{
    return "false";
  }
}
  // gets start bool
public boolean getStartBool(){
  return start;
}


   // Required getter and setters below
 public int getWidth() {
  return width;
}

// set width of room
 public void setWidth(int newWidth) {
  width = newWidth;
 }


 public int getHeight() {
  return height;
 }

//set height of room
 public void setHeight(int newHeight) {
   height = newHeight;
 }

//get room id
 public int getId() {
    return id;
 }


 public void setId(int newId) {
   id = newId;
 }

// Arraylist of our room items
 public ArrayList<Item> getRoomItems() {
    return RoomItems;

 }

 public void setRoomItems(ArrayList<Item> newRoomItems) {
   RoomItems = newRoomItems;
 }


 public Player getPlayer() {
    return player;
 }

// set our rooms player
 public void setPlayer(Player newPlayer) {
   player = newPlayer;
 }

// return door position by direction
 public int getDoor(String direction){

  if(direction.equals("N")){
    return DN;
  }else if(direction.equals("E")){
    return DE;
  }else if(direction.equals("S")){
    return DS;
  }else if(direction.equals("W")){
    return DW;
  }else{
    return 0;
  }

}
// helpful for debugging, essentially spits out all the objects to sysout
 public void printInfo(){
   int i = 0;
   System.out.println("Room id: " + id);
   System.out.println("height: " + height);
   System.out.println("width: " + width);
   System.out.println("start: " + getStart());
   System.out.println("Doors ... ");
   if(DN > 0){
     System.out.println("North door @ pos : " + DN);
   }
   if(DE > 0){
     System.out.println("East door @ pos : " + DE);
   }
   if(DS > 0){
     System.out.println("South door @ pos : " + DS);
   }
   if(DW > 0){
     System.out.println("West door @ pos : " + DW);
   }

   int sizeB = RoomItems.size();
   if(sizeB > 0){
     System.out.println("Loot ...");
     for(i=0; i < sizeB; i++){
       Item temp = RoomItems.get(i);
       Point p = temp.getXyLocation();
       System.out.println("Item id: " + temp.getId() + " name: " + temp.getName() + " type: " + temp.getType() + " at position " + p.getX() + "," + p.getY());
     }
   }else{
     System.out.println("No loot in this room");
   }

   System.out.println();
 }


// setting our doors and their locations
public void setDoor(String direction, int location){
  if(direction.equals("N")){
    DN = location;
  }else if(direction.equals("E")){
    DE = location;
  }else if(direction.equals("S")){
    DS = location;
  }else if(direction.equals("W")){
    DW = location;
  }
}

// check if the player is actually in the room
public boolean isPlayerInRoom() {
  return isPinR;
}

// lets us know the player is in the room
public void setPlayerinRoom(){
  isPinR = true;
}





   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
    * @return (String) String representation of how the room looks
    */
   public String displayRoom() {
// printing header
    String head = "<---- [Room " + id + "] ---->\n";
    if(start == true){
      String head2 = "- Starting Room\n";
      head = head + head2;
    }
    // this is our output string its + 1 width to fit a newline char
    char[] output = new char[(width+1)*height];
    // just counter vars
    int x = 0;
    int z = 0;
    int p = 0;
    // top wall
    char tb = getSymbolbyName("NS_WALL");
    char lr = getSymbolbyName("EW_WALL");
    char itemZ = getSymbolbyName("ITEM");
    char plyr = getSymbolbyName("PLAYER");
    char flr = getSymbolbyName("FLOOR");
    char dr = getSymbolbyName("DOOR");
    for(z = 0; z < width; z++){
      // top wall character
        output[x] = tb ;
        x++;
    }
    // newline
      output[x] = 10;
      x++;
    for(z = 0; z < height-2;z++){
      // left wall character
      output[x] = lr;
      x++;
      for(p =0; p <width-2;p++){
        // floor character
        output[x] = flr;
        x++;
      }
      // right wall character
      output[x] = lr;
      x++;
      output[x] = 10;
      x++;
    }
    for(z = 0; z < width; z++){
      // bottom wall character
        output[x] = tb;
        x++;
    }
      output[x] = 10;
      x++;
    // setting our item locations
    for(p =0; p< RoomItems.size();p++){
      Item item = RoomItems.get(p);
      Point point = item.getXyLocation();
      int h = (int)point.getX();
      int j = (int)point.getY();
      int location = ((width+1) * j) + h;
      // item character
      output[location] = itemZ;
    }
    // used in loop for placing character
    boolean playerNSet = true;
    x = 0;
    int hightMod = 0;
    int widMod = 0;
    // finding a spot for our character where there is floor
    if(start == true){
    while(playerNSet){
      if(output[x] == flr){
        output[x] = plyr;
        // setting our players location
        Point playLoc = new Point(hightMod, widMod);
        player.setXyLocation(playLoc);
        playerNSet = false;
      }
      x++;
      widMod++;
      if(x%(width+1) == 0){
        hightMod++;
        widMod = 0;
      }
    }
}
// printing our door locations
// door char is '+' rn
      int k = 0;
      if(DN > 0){
        output[DN] = dr;
      }
      if(DE > 0){
        k = (width+1)*(DE+1);
        output[k] = dr;
      }
      if(DS > 0){
        k = (width+1)*(height-1) + DS;
        output[k] = dr;

      }
      if(DW > 0){
        k = ((width+1)*(DW-1))+1;
        output[k] = dr;
      }
// putting our head and string together
  String str = String.valueOf(output);
  str = head + str;
  return str;
   }
}
