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



    // Default constructor
 public Room() {

 }

 public Room(JSONObject jsonRooms){
    int i = 0;
    int c = 0;
    int z = 0;
    int u = 0;
   ArrayList<Point> tempLoc = new ArrayList<>();
   ArrayList<Integer> ItemID = new ArrayList<>();
   ArrayList<String> itemNames = new ArrayList<>();
   ArrayList<String> itemTypes = new ArrayList<>();
   ArrayList<Integer> barcode = new ArrayList<>();

   setId(Integer.decode(jsonRooms.get("id").toString()));
   setHeight(Integer.decode(jsonRooms.get("height").toString()));
   setWidth(Integer.decode(jsonRooms.get("width").toString()));
   for(Object doors : (JSONArray) jsonRooms.get("doors")){
     JSONObject jsonDoors = (JSONObject)doors;
     String dir = jsonDoors.get("dir").toString();
     Integer pos = Integer.decode(jsonDoors.get("id").toString());
  //   setDoors(dir, pos, i);
     i++;
   }

   for(Object loot : (JSONArray) jsonRooms.get("loot")){
     JSONObject jsonLoot = (JSONObject)loot;
     Point point = new Point();
     Integer x = Integer.decode(jsonLoot.get("x").toString());
     Integer y = Integer.decode(jsonLoot.get("y").toString());
                  System.out.println("fuck" + c);
     ItemID.add(c,Integer.decode(jsonLoot.get("id").toString()));

     point.setLocation(x, y);
     tempLoc.add(c,point);
     c++;
   }
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
public void setDoors(String direction, int position, int index){
    DoorDir.set(index,direction);
    DoorPos.set(index,position);
}

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

/*
direction is one of NSEW
location is a number between 0 and the length of the wall
*/

public void setDoor(String direction, int location){

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
