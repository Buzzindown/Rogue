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

        JSONParser parser = new JSONParser();
        try {

            Object obj = parser.parse(new FileReader(configurationFileLocation));
            JSONObject configurationJSON = (JSONObject) obj;

            // getting our room filename and making it a jsonobject
            String roomLocs = (configurationJSON.get("Rooms").toString());

            String symbolsLoc = (configurationJSON.get("Symbols").toString());


            Rogue rogue = new Rogue();
            Player thePlayer = new Player();
            thePlayer.setName("thePlayer");
            rogue.setPlayer(thePlayer);

            rogue.setSymbols(symbolsLoc);
            rogue.createRooms(roomLocs);
            rogue.giveRoomSymbol();
            System.out.println(rogue.displayAll());




        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

}
