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

    Player player;
    String SymbolsFilename;
    ArrayList<Room> roomZ;
    ArrayList<Item> items;

    public void setPlayer(Player thePlayer){
      player = thePlayer;
    }


    public void setSymbols(String filename){
      SymbolsFilename = filename;
    }

    public ArrayList<Room> getRooms(){
        return roomZ;

    }

    public ArrayList<Item> getItems(){
        return items;

    }
    public Player getPlayer(){
        return player;

    }

    public void createRooms(String filename){
    JSONParser parser = new JSONParser();
      try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject configurationJSON = (JSONObject) obj;
            ArrayList<Room> tempor = new ArrayList<>();

            // getting our room filename and making it a jsonobject
            Object obj2 = parser.parse(new FileReader(configurationJSON.get("Rooms").toString()));
            JSONObject rooms = (JSONObject) obj2;

            JSONArray roomsArr = (JSONArray) rooms.get("room");
            int x = 0;

            for(Object o : roomsArr){
              JSONObject oneRoom = (JSONObject)o;
              Room newRoom = new Room(oneRoom);
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

    public void setRoomList(ArrayList<Room> roomies){
      roomZ = roomies;
    }
    public String displayAll(){
        //creates a string that displays all the rooms in the dungeon
        return null;
    }

    public void printAllRoomInfo(){
      int x = 0;
      for(x = 0; x<roomZ.size();x++){
        roomZ.get(x).printInfo();
      }
    }
}
