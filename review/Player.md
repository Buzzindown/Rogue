| Method Sig                                     | Responsibility                | instance vars used          | other class methods | objects used with method calls | lines of code |
|------------------------------------------------|-------------------------------|-----------------------------|---------------------|--------------------------------|---------------|
| public Player()                                | player constructor            | p inventory xyLocation      |                     | Point Inventory                | 5             |
| public Player(String namez)                    | secondary player constructor  | name p inventory xyLocation |                     | Point Inventory                | 6             |
| public void addToInv(Item item)                | add an item to inventory      | inventory                   | addItem             | Item Inventory                 | 3             |
| public void remFrominv(Item item)              | remove an item from inventory | inventory                   | removeItem          | Item Inventory                 | 3             |
| public int returnInvSize()                     | return inventory size         | inventory                   | getInventory size   | ArrayList Item Inventory       | 5             |
| public ArrayList<Item> getInvList()            | return inventory arraylist    | inventory                   | getInventory        | Inventory                      | 3             |
| public String getName()                        | return player name            | name                        |                     |                                | 3             |
| public void setName(String newName)            | set player name               | name                        |                     |                                | 3             |
| public Point getXyLocation()                   | get player location           | xyLocation                  |                     | Point                          | 3             |
| public void setXyLocation(Point newXyLocation) | set xyLocation                | xyLocation                  |                     | Point                          | 3             |
| public Room getCurrentRoom()                   | get room player is in         | currentRoom                 |                     | Room                           | 3             |
| public void setCurrentRoom(Room newRoom)       | set room player is in         | currentRoom                 | setPlayerInRoom     | Room                           | 4             |
