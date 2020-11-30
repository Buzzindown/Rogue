Mackenzie Starkebaum
1094207
mstarkeb@uoguelph.ca
I did this work on my own.
to run: java -jar build/libs/A3.jar

IMPORTANT NOTES REGARDING FUNCTIONALITY
Description was a little vague so I will describe some of the behaviors I implemented.

MARKDOWN ANALYSIS CHARTS
I used a website to create my charts, I've copied the raw md and that's is whats contained in the .md files
if that works(may need to zoomout to get table to look proper) and looks clean then cool. Otherwise I've also include the .TGN files that the site uses.
to view these go to https://www.tablesgenerator.com/markdown_tables > file > load file > select the TGN file and hit preview
it creates a nice little pdf sort of chart that is easy to read.

Drop Down Menu
  Created a drop down menu that utilizes the FlowLayout layout manager, the rest of the frame
  utilizes the BoxLayout layoutmanager. The four drop downs are, change name, save game, load save, and load json

    Change Player name
        Creates a popup to enter the players name, this name will stay on the
        terminal until it is changed, regardless of new saves / loads.

    Save Game
        Save game implements serialization to save the rogue object as a binary file. It also
        uses a file explorer. Choose a file to overwrite or type in a new filename in the path and hit save.
        It will then save the file to that directory. (This can and will overwrite other (possibly important) files, just a warning)

    Load Saved Game
        Load Saved game implements serialization to deserialize a binary file into a rogue object.
        The game will provide a popup if something goes wrong with loading the game, at which point you
        would have to try and load a new file. Once a file has been loaded, you must press a key on the keyboard
        for the actual game to recognize the new file and refresh the terminal.

    Load Json file
        Game will use A3_Rooms.json as room file. Only added the menu button, did not add any functionality to open a new json file.

Item Subclasses
  I implemented all of the item subclasses and they have full functionality, the new items do instantiate the right classes.
  Items will display the full description when picked up (must move onto and off of an item to display picked up message).

eat/throw/wear interfaces
  I implemented the three interfaces eat/throw/wear. They work as they should. Throwing an item that is throwable
  will result in the item landing somewhere in the room (think that our character has a really strong and inaccurate throwing arm).
  That item can then be picked up and brought into a new room etc.

Selecting items to eat/throw wear
  The players current inventory is described in a scroll pane on the right side of the gui, the player is able to use the mouse
  to select an item in the list. An item is selected when a blue square highlights it. From there, the player must click their mouse
  on the terminal to change the focus, and then press one of j,k,m to perform an action on the selected item.

Dialog Box
  I implemented a dialog box at the bottom of the GUI to read out messages as they game produced them.

Error messages
  Certain actions will create warning dialog boxes, such as a NoSuchItemException or NotEnoughDoorsException, ImpossiblePositionException
  is still caught however it does not produce a dialog box as it would create one anytime you threw an object and that would be annoying.

!!CONTROLS!!
MOVEMENT
  UP: W
  DOWN: S
  LEFT: A
  RIGHT: D

OTHER
  EAT: J
  WEAR: K
  TOSS: M

SELECTING ITEMS/MENUES
  MOUSE
