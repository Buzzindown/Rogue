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


public class Rogue{
  // our game of rogue needs a player, symbol map, arry of rooms and array of items
    Player player;
    String SymbolsFilename;
    ArrayList<Room> roomZ;
    ArrayList<Item> items;
    // not added yet but this is where we'll store our symbols
    ArrayList<Symbol> symbols;
// setting our player
    public void setPlayer(Player thePlayer){
      player = thePlayer;
    }

// setting symbols (need to work on) this will be a parsing function
    public void setSymbols(String filename){
      JSONParser parser = new JSONParser();
      try {
        ArrayList<Symbol> symb = new ArrayList<>();

        Object obj2 = parser.parse(new FileReader(filename));
        JSONObject symbolZ = (JSONObject) obj2;

        JSONArray symbolZArr = (JSONArray) symbolZ.get("symbols");
        int x = 0;
        for(Object z : symbolZArr){
          JSONObject oneSymbol = (JSONObject)z;
          String name = oneSymbol.get("name").toString();
          String chara = oneSymbol.get("symbol").toString();

          Symbol syma = new Symbol(name, chara.charAt(0));
          symb.add(syma);
          x++;
        }
        symbols = symb;


      } catch(FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } catch (ParseException e) {
          e.printStackTrace();
      }
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

    public void giveRoomSymbol(){
      int x = 0;
      for(x = 0; x<roomZ.size();x++){
        Room r = roomZ.get(x);
        r.setSymbols(symbols);
    }
  }
// getting our rooms
    public ArrayList<Room> getRooms(){
        return roomZ;

    }
// getting our items
    public ArrayList<Item> getItems(){
        return items;

    }
    public Player getPlayer(){
        return player;

    }
// parsing our rooms!
    public void createRooms(String filename){
    JSONParser parser = new JSONParser();
      try {
            ArrayList<Room> tempor = new ArrayList<>();

            // taking our rooms and making them a jsonobject
            Object obj2 = parser.parse(new FileReader(filename));
            JSONObject rooms = (JSONObject) obj2;
            //making an array of these jsonobjects
            JSONArray roomsArr = (JSONArray) rooms.get("room");
            JSONArray itemsArr = (JSONArray) rooms.get("items");
            int x = 0;
            // parsing all of the room data and storing in array
            for(Object o : roomsArr){
              JSONObject b = (JSONObject)itemsArr.get(x);
              JSONObject oneRoom = (JSONObject)o;
              Room newRoom = new Room();
              newRoom.setPlayer(player);
              parseRoom(oneRoom,b, newRoom);
              tempor.add(x,newRoom);
              x++;
            }
            // printing out our room details
            setRoomList(tempor);

      } catch(FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } catch (ParseException e) {
          e.printStackTrace();
      }

    }

// assinging our rooms list
    public void setRoomList(ArrayList<Room> roomies){
      roomZ = roomies;
    }
// displays our rooms one after the other
    public String displayAll(){
        int x = 0;
        String str = "";
        String temp;
        for(x = 0; x<roomZ.size();x++){
          Room room = roomZ.get(x);
          temp = room.displayRoom();
          str = str + "\n\n";
          str = str + temp;
        }
        return str;
    }

    public void parseRoom(JSONObject jsonRooms, JSONObject jsonItems, Room room){
      // counter variables mostly
      int i = 0;
      int c = 0;
      int z = 0;
      int u = 0;
      // initializing all our doors
      room.setDoor("N",0);
      room.setDoor("E",0);
      room.setDoor("S",0);
      room.setDoor("W",0);
      //  bunch of arraylists to parse into and out of
     ArrayList<Item> tempItems = new ArrayList<>();
     ArrayList<Point> points = new ArrayList<>();
     ArrayList<Integer> identifs = new ArrayList<>();

     room.setId(Integer.decode(jsonRooms.get("id").toString()));
     room.setHeight(Integer.decode(jsonRooms.get("height").toString()));
     room.setStart(jsonRooms.get("start").toString());
     room.setWidth(Integer.decode(jsonRooms.get("width").toString()));
     for(Object doors : (JSONArray) jsonRooms.get("doors")){
       JSONObject jsonDoors = (JSONObject)doors;
       String dir = jsonDoors.get("dir").toString();
       Integer pos = Integer.decode(jsonDoors.get("id").toString());
       room.setDoor(dir, pos);
     }

     for(Object loot : (JSONArray) jsonRooms.get("loot")){
       JSONObject jsonLoot = (JSONObject)loot;

       Integer x = Integer.decode(jsonLoot.get("x").toString());
       Integer y = Integer.decode(jsonLoot.get("y").toString());
       Integer tempID = Integer.decode(jsonLoot.get("id").toString());
       Point point = new Point(x,y);
       points.add(point);
       identifs.add(tempID);
     }

      ArrayList<Integer> itemsID = new ArrayList<>();
      ArrayList<String> itemsName = new ArrayList<>();
      ArrayList<String> itemsType = new ArrayList<>();


       Integer temp1 = Integer.decode(jsonItems.get("id").toString());
       String temp2 = jsonItems.get("name").toString();
       String temp3 = jsonItems.get("type").toString();
       itemsID.add(temp1);
       itemsName.add(temp2);
       itemsType.add(temp3);


     for(c = 0;c < identifs.size();c++ ){
       Integer j = identifs.get(c);
       Item item = new Item(j, itemsName.get(j-1),itemsType.get(j-1),points.get(c));
       tempItems.add(item);
     }

     room.setRoomItems(tempItems);

     if(room.getStartBool() == true){
       room.setPlayerinRoom();
       player.setCurrentRoom(room);
     }
    }

    public void printAllRoomInfo(){
      int x = 0;
      for(x = 0; x<roomZ.size();x++){
        roomZ.get(x).printInfo();
      }
      Room temporR = player.getCurrentRoom();
      System.out.println("Player name: " + player.getName());
      System.out.println("Player in room: " + temporR.getId());
      Point temporP = player.getXyLocation();
      System.out.println("Player loc: " + temporP.getX() + "," + temporP.getY());
    }
}
