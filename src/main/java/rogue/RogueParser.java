package rogue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RogueParser {

    private ArrayList<Map<String, String>> rooms = new ArrayList<>();
    private ArrayList<Map<String, String>> items = new ArrayList<>();
    private ArrayList<Map<String, String>> itemLocations = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>();
    private HashMap<String, Character> symbols = new HashMap<>();

    private Iterator<Map<String, String>> roomIterator;
    private Iterator<Map<String, String>> itemIterator;

    private int numOfRooms = -1;
    private int numOfItems = -1;

    private JSONObject roomsJSON;
    private JSONObject symbolsJSON;

    /**
     * Default constructor..
     */
    public RogueParser() {
      System.out.println("call again with a filename");
    }

    /**
     * Constructor that takes filename and sets up the object.
     * @param filename  (String) name of file that contains file location for rooms and symbols
     */
    public RogueParser(String filename) {
        parse(filename);
    }

    /**
     * Return the next room.
     * @return (Map) Information about a room
     */
    public Map nextRoom() {
        if (roomIterator.hasNext()) {
            return roomIterator.next();
        } else {
            return null;
        }
    }

    /**
     * Returns the next item.
     * @return (Map) Information about an item
     */
    public Map nextItem() {
        if (itemIterator.hasNext()) {
            return itemIterator.next();
        } else {
            return null;
        }
    }

    /**
     * rooms map.
     * @return (Map) Information about an item
     */
    public ArrayList<Map<String, String>> retRooms() {
      return rooms;
    }

    /**
     * Returns the symbols arrlist.
     * @return symbolsm
     */
    public HashMap<String, Character> retSymbols() {
      return symbols;
    }

    /**
     * Returns the doors arraylist.
     * @return symbolsm
     */
    public ArrayList<Door> retDoor() {
      return doors;
    }

    /**
     * Returns the itemlocs arrlist.
     * @return itemlocs
     */
     public ArrayList<Map<String, String>> getItemLocs() {
       return itemLocations;
     }

     /**
      * Returns the item arrlist.
      * @return itemlocs
      */
      public ArrayList<Map<String, String>> getItems() {
        return items;
      }

    /**
     * Get the character for a symbol.
     * @param symbolName (String) Symbol Name
     * @return (Character) Display character for the symbol
     */
    public Character getSymbol(String symbolName) {
        if (symbols.containsKey(symbolName)) {
            return symbols.get(symbolName);
        }
        return null;
    }

    /**
     * Get the number of items.
     * @return (int) Number of items
     */
    public int getNumOfItems() {
        return numOfItems;
    }

    /**
     * Get the number of rooms.
     * @return (int) Number of rooms
     */
    public int getNumOfRooms() {
        return numOfRooms;
    }

    /**
     * Read the file containing the file locations.
     * @param filename (String) Name of the file
     */
    private void parse(String filename) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject configurationJSON = (JSONObject) obj;
            String roomsFileLocation = (String) configurationJSON.get("Rooms");
            String symbolsFileLocation = (String) configurationJSON.get("Symbols");
            Object roomsObj = parser.parse(new FileReader(roomsFileLocation));
            roomsJSON = (JSONObject) roomsObj;
            Object symbolsObj = parser.parse(new FileReader(symbolsFileLocation));
            symbolsJSON = (JSONObject) symbolsObj;
            extractAndIterate();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file named: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            System.out.println("Error parsing JSON file");
        }
    }

    private void extractAndIterate() {
      extractRoomInfo();
      extractItemInfo();
      extractSymbolInfo();
      roomIterator = rooms.iterator();
      itemIterator = items.iterator();
    }

    /**
     * Get the symbol information.
     */
    private void extractSymbolInfo() {
        JSONArray symbolsJSONArray = (JSONArray) symbolsJSON.get("symbols");

        // Make an array list of room information as maps
        for (int i = 0; i < symbolsJSONArray.size(); i++) {
            JSONObject symbolObj = (JSONObject) symbolsJSONArray.get(i);
            symbols.put(symbolObj.get("name").toString(), String.valueOf(symbolObj.get("symbol")).charAt(0));
        }
    }

    /**
     * Get the room information.
     */
    private void extractRoomInfo() {


        JSONArray roomsJSONArray = (JSONArray) roomsJSON.get("room");

        // Make an array list of room information as maps
        for (int i = 0; i < roomsJSONArray.size(); i++) {
            rooms.add(singleRoom((JSONObject) roomsJSONArray.get(i)));
            numOfRooms += 1;
        }
    }



    /**
     * Get a room's information.
     * @param roomJSON (JSONObject) Contains information about one room
     * @return (Map<String, String>) Contains key/values that has information about the room
     */
    private Map<String, String> singleRoom(JSONObject roomJSON) {
        HashMap<String, String> room = new HashMap<>();
        putRoomTogether(roomJSON, room);
        JSONArray doorArray = (JSONArray) roomJSON.get("doors");
        for (int j = 0; j < doorArray.size(); j++) {
            JSONObject doorObj = (JSONObject) doorArray.get(j);
            Door d = new Door();
            String dir = String.valueOf(doorObj.get("dir"));
            int loc = Integer.parseInt(String.valueOf(doorObj.get("wall_pos")));
            int conRoom = Integer.parseInt(String.valueOf(doorObj.get("con_room")));
            setRoomQualities(dir, loc, conRoom, d);
            d.setRoomId(Integer.parseInt(room.get("id")));
            doors.add(d);
        }
        JSONArray lootArray = (JSONArray) roomJSON.get("loot");
        for (int j = 0; j < lootArray.size(); j++) {
            itemLocations.add(itemPosition((JSONObject) lootArray.get(j), roomJSON.get("id").toString()));
        }
        return room;
    }

    private void setRoomQualities(String dir, int loc, int conRoom, Door d) {
      d.setWall(dir);
      d.setLocation(loc);
      d.setCRN(conRoom);
    }

    private void putRoomTogether(JSONObject roomJSON, HashMap<String, String> room) {
      String x = roomJSON.get("id").toString();
      room.put("id", x);
      room.put("start", roomJSON.get("start").toString());
      room.put("height", roomJSON.get("height").toString());
      room.put("width", roomJSON.get("width").toString());
    }

    /**
     * Create a map for information about an item in a room.
     * @param lootJSON (JSONObject) Loot key from the rooms file
     * @param roomID (String) Room id value
     * @return (Map<String, String>) Contains information about the item, where it is and what room
     */
    private Map<String, String>  itemPosition(JSONObject lootJSON, String roomID) {

        HashMap<String, String> loot = new HashMap<>();

        loot.put("room", roomID);
        loot.put("id", lootJSON.get("id").toString());
        loot.put("x", lootJSON.get("x").toString());
        loot.put("y", lootJSON.get("y").toString());

        return loot;
    }

    /**
     * Get the Item information from the Item key.
     */
    private void extractItemInfo() {

        JSONArray itemsJSONArray = (JSONArray) roomsJSON.get("items");

        for (int i = 0; i < itemsJSONArray.size(); i++) {
            items.add(singleItem((JSONObject) itemsJSONArray.get(i)));
            numOfItems += 1;
        }
    }

    /**
     * Get a single item from its JSON object.
     * @param itemsJSON (JSONObject) JSON version of an item
     * @return (Map<String, String>) Contains information about a single item
     */
    private Map<String, String>  singleItem(JSONObject itemsJSON) {
        HashMap<String, String> item = new HashMap<>();
        item.put("id", itemsJSON.get("id").toString());
        item.put("name", itemsJSON.get("name").toString());
        item.put("type", itemsJSON.get("type").toString());
        item.put("description", itemsJSON.get("description").toString());
        // parse a specific set of name/type/getDescription
        // see if there are any items that match the id
        // if so add them, e
        for (Map<String, String> itemLocation : itemLocations) {
            if (itemLocation.get("id").toString().equals(item.get("id").toString())) {
                item.put("room", itemLocation.get("room"));
                item.put("x", itemLocation.get("x"));
                item.put("y", itemLocation.get("y"));
                break;
            }
        }
        return item;
    }
}
