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
    String symbolsFilename;
    ArrayList<Room> rooms;
    ArrayList<Item> items;

    public void setPlayer(Player thePlayer){
      player = thePlayer;
    }


    public void setSymbols(String filename){
      symbolsFilename = filename;
    }

    public ArrayList<Room> getRooms(){
        return rooms;

    }

    public ArrayList<Item> getItems(){
        return items;

    }
    public Player getPlayer(){
        return player;

    }

    public void createRooms(String filename){

    }
    public String displayAll(){
        //creates a string that displays all the rooms in the dungeon
        return null;
    }
}
