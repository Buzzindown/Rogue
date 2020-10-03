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
  private ArrayList<Item> RoomItems;
  private ArrayList<String> DoorDir;
  private ArrayList<Integer> DoorPos;
  private Player player;
  private int DN;
  private int DE;
  private int DS;
  private int DW;



    // Default constructor
 public Room() {

 }

 public Room(JSONObject jsonRooms){
    int i = 0;
    int c = 0;
    int z = 0;
    int u = 0;
    DN = 0;
    DE = 0;
    DS = 0;
    DW = 0;
   ArrayList<Point> tempLoc = new ArrayList<>();
   ArrayList<Integer> ItemID = new ArrayList<>();
   ArrayList<String> itemNames = new ArrayList<>();
   ArrayList<String> itemTypes = new ArrayList<>();
   ArrayList<Integer> barcode = new ArrayList<>();
   ArrayList<Item> tempItems = new ArrayList<>();

   setId(Integer.decode(jsonRooms.get("id").toString()));
   setHeight(Integer.decode(jsonRooms.get("height").toString()));
   setWidth(Integer.decode(jsonRooms.get("width").toString()));
   for(Object doors : (JSONArray) jsonRooms.get("doors")){
     JSONObject jsonDoors = (JSONObject)doors;
     String dir = jsonDoors.get("dir").toString();
     Integer pos = Integer.decode(jsonDoors.get("id").toString());
     setDoor(dir, pos);
     i++;
   }

   for(Object loot : (JSONArray) jsonRooms.get("loot")){
     JSONObject jsonLoot = (JSONObject)loot;

     Integer x = Integer.decode(jsonLoot.get("x").toString());
     Integer y = Integer.decode(jsonLoot.get("y").toString());
     Integer tempID = Integer.decode(jsonLoot.get("id").toString());
     Point point = new Point(x,y);
     Item temp = new Item(tempID, "null", "null", point );
     tempItems.add(temp);
     c++;
   }

   setRoomItems(tempItems);

/*   for(Object items : (JSONArray) jsonRooms.get("items")){
     JSONObject jsonItems = (JSONObject)items;
     barcode.set(z,Integer.decode(jsonItems.get("id").toString()));
     itemNames.set(z,jsonItems.get("name").toString());
     itemTypes.set(z,jsonItems.get("id").toString());
     z++;
   }

   ArrayList<Item> tempList = new ArrayList<>();
   for(u = 0; u < ItemID.size(); u++){
     Integer identification = ItemID.get(u);
     String name = itemNames.get(identification-1);
     String type = itemTypes.get(identification-1);
     Point trial = tempLoc.get(u);
     Item item = new Item(identification, name, type, trial);
     tempList.set(u,item);
   }
   setRoomItems(tempList);
*/
 }




   // Required getter and setters below
 public int getWidth() {

return width;
}


 public void setWidth(int newWidth) {
width = newWidth;
 }


 public int getHeight() {

  return height;
 }


 public void setHeight(int newHeight) {
   height = newHeight;
 }

 public int getId() {
    return id;

 }


 public void setId(int newId) {
   id = newId;
 }


 public ArrayList<Item> getRoomItems() {
    return RoomItems;

 }


 public void setRoomItems(ArrayList<Item> newRoomItems) {
   RoomItems = newRoomItems;
 }


 public Player getPlayer() {
    return player;
 }


 public void setPlayer(Player newPlayer) {
   player = newPlayer;
 }


 public int getDoor(String direction){

    return 0;

 }

 public void printInfo(){
   int i = 0;
   System.out.println("Room id: " + id);
   System.out.println("height: " + height);
   System.out.println("width: " + width);
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
     for(i=0; i < sizeB; i++){
       Item temp = RoomItems.get(i);
       Point p = temp.getXyLocation();

       System.out.println("Item id: " + temp.getId() + " at position " + p.getX() + "," + p.getY());
     }
   }else{
     System.out.println("No loot in this room");
   }

   System.out.println();
 }

/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/

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


public boolean isPlayerInRoom() {

return true;
}





   /**
    * Produces a string that can be printed to produce an ascii rendering of the room and all of its contents
    * @return (String) String representation of how the room looks
    */
   public String displayRoom() {
    return null;


   }
}
