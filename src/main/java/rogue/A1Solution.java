package rogue;

import java.util.Scanner;
import java.util.ArrayList;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Point;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class A1Solution{


    public static void main(String[] args) {
        // Hardcoded configuration file location/name
        String configurationFileLocation = "fileLocations.json";  //please don't change this for this version of the assignment

        ArrayList<Room> roomsList = new ArrayList<>();
 // reading the input file locations using the configuration file
        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(configurationFileLocation));
            JSONObject configurationJSON = (JSONObject) obj;

            // getting our room filename and making it a jsonobject
            Object obj2 = parser.parse(new FileReader(configurationJSON.get("Rooms").toString()));
            JSONObject rooms = (JSONObject) obj2;

            JSONArray roomsArr = (JSONArray) rooms.get("room");
            int x = 0;
            for(Object o : roomsArr){
              JSONObject oneRoom = (JSONObject)o;
              Room newRoom = new Room(oneRoom);
              roomsList.add(x,newRoom);
              x++;
            }

            for(x = 0; x<roomsList.size();x++){
              roomsList.get(x).printInfo();
            }





        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

// instantiate a new Rogue object and call methods to do the required things
      //  Rogue rogue = new Rogue();
      //  Room testRoom = new Room();
      //  testRoom.setWidth(10);
      //  System.out.println(testRoom.getWidth());
    }

}
