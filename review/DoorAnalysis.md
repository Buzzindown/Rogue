| Method Sig                                 | Responsibility                      | instance vars used    | other class methods | objects used with method calls | lines of code |
|--------------------------------------------|-------------------------------------|-----------------------|---------------------|--------------------------------|---------------|
| public Door()                              | default constructor                 | conRooms connRoomNums |                     | ArrayList Room                 | 4             |
| public void setLocation(Integer newLoc)    | set door location                   | location              |                     |                                | 3             |
| public void setCRN(Integer rN)             | set connecting room number          | connRoomNums          | add                 | ArrayList                      | 3             |
| public void setRoomId(Integer rI)          | set doors room id                   | room                  |                     |                                | 3             |
| public Integer getRoomID()                 | get doors room id                   | room                  |                     |                                | 3             |
| public ArrayList<Integer> getRoomNums()    | get arraylist of conn. room numbers | connRoomNums          |                     |                                | 3             |
| public void setWall(String newWall)        | set the wall the door is on         | wall                  |                     |                                | 3             |
| public Integer getLocation()               | get location on the wall            | location              |                     |                                | 3             |
| public String getWall()                    | get the wall the door is on         | wall                  |                     |                                | 3             |
| public void connectRoom(Room r)            | connect a room to a door            | conRooms              | add                 | ArrayList Room                 |               |
| public ArrayList<Room> getConnectedRooms() | get conn. room arrayList            | conRooms              |                     | ArrayList Room                 | 3             |
| public Room getConRoom()                   | get a connected room                | conRooms              | get                 | ArrayList Room                 | 3             |
| public Room getOtherRoom(Room currentRoom) | get alternative connected room      | conRooms              | getId get           | ArrayList Room                 | 9             |
