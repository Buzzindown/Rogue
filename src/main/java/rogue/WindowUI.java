package rogue;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TerminalPosition;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileInputStream;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JScrollPane;

import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.Dimension;
import javax.swing.JFileChooser;


public class WindowUI extends JFrame {


    private SwingTerminal terminal;
    private TerminalScreen screen;
    public static final int WIDTH = 700;
    public static final int HEIGHT = 800;
    // Screen buffer dimensions are different than terminal dimensions.
    public static final int COLS = 80;
    public static final int ROWS = 24;
    private static final int THICK = 5;
    private static final int ROWSTD = 22;
    private static final int RWIDTH = 200;
   private final char startCol = 0;
   private final char msgRow = 1;
   private final char roomRow = 3;
   private Container contentPane;
   private JScrollPane sp;
   private JLabel namelbl;
   private JLabel msgBoard;
   private DefaultListModel listModel;
   private JList jl;
   private static Rogue theGame;
   private static int pastInvSize = 0;
/**
Constructor.
**/

    public WindowUI() {
        super("my awesome game");
        contentPane = getContentPane();
        contentPane.setBackground(Color.black);
        this.setResizable(false);
        setWindowDefaults(getContentPane());
        setUpPanels();
        pack();
        start();
    }

    private void setWindowDefaults(Container cntntpane) {
        setTitle("Rogue!");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        cntntpane.setLayout(new BorderLayout());

    }

// setup the terminal
    private void setTerminal() {
        JPanel terminalPanel = new JPanel();
        terminal = new SwingTerminal();
        terminalPanel.add(terminal);
        contentPane.add(terminalPanel, BorderLayout.WEST);
    }

// setting up various panels
    private void setUpPanels() {
        JPanel labelPanel = new JPanel();
        labelPanel.setBackground(Color.black);
        setUpLabelPanel(labelPanel);
        makeMenuBar();
        addInvPanel();
        addNamePanel();
        setTerminal();
    }

// creating menu bar  loadnewjson has no functionality atm
    private void makeMenuBar() {
      JMenuBar menubar = new JMenuBar();
      setJMenuBar(menubar);
      menubar.setLayout(new FlowLayout());
      JMenu fileMenu = new JMenu("GAME MENU");
      menubar.add(fileMenu);
      JMenuItem loadjson = new JMenuItem("load json");
      fileMenu.add(loadjson);
      JMenuItem loadSG = new JMenuItem("Load saved game");
      loadSG.addActionListener(eevt -> loadGame());
      fileMenu.add(loadSG);
      JMenuItem saveGame = new JMenuItem("Save Game");
      saveGame.addActionListener(evt -> saveGame());
      fileMenu.add(saveGame);
      JMenuItem chngPN = new JMenuItem("Change player name");
      chngPN.addActionListener(ev -> changeText());
      fileMenu.add(chngPN);
    }

// load a game file
    private void loadGame() {
      JFileChooser fc = new JFileChooser(".");
      fc.setApproveButtonText("Load File");
      int x = fc.showOpenDialog(null);
      if (x == JFileChooser.APPROVE_OPTION) {
        String filename = fc.getSelectedFile().getName();
        String absPath =  fc.getSelectedFile().getAbsolutePath();
        tryToUnSerializeData(absPath);
      }
    }
// attempt to load a saved game file
    private void tryToUnSerializeData(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));) {
          Rogue loadG = (Rogue) in.readObject();
          theGame = loadG;
          JOptionPane.showMessageDialog(null, "Loaded file at: " + path, "", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
              JFrame rent = new JFrame();
            JOptionPane.showMessageDialog(rent, "Failed to Load Game", "", JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException ex) {
              JFrame rent = new JFrame();
            JOptionPane.showMessageDialog(rent, "Failed to Load Game", "", JOptionPane.WARNING_MESSAGE);
          System.out.println(ex);
        }
    }

// save the game in a serialized file
    private void saveGame() {
      JFileChooser fc = new JFileChooser(".");
    //  fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int x = fc.showSaveDialog(null);
      if (x == JFileChooser.APPROVE_OPTION) {
        String filename = fc.getSelectedFile().getName();
        String absPath =  fc.getSelectedFile().getAbsolutePath();
        tryToSerializeData(absPath);
      }
    }

// attemp to save the game
    private void tryToSerializeData(String path) {
      try {
        FileOutputStream outputStr = new FileOutputStream(path);
        Rogue saveG = new Rogue();
        saveG = theGame;
        ObjectOutputStream outputdest = new ObjectOutputStream(outputStr);
        outputdest.writeObject(saveG);
        outputdest.close();
        outputStr.close();
        JOptionPane.showMessageDialog(null, "Saved file at: " + path, "", JOptionPane.INFORMATION_MESSAGE);
      } catch (IOException ex) {
        JFrame rent = new JFrame();
        JOptionPane.showMessageDialog(rent, "Failed to Save Game", "", JOptionPane.WARNING_MESSAGE);
        System.out.println(ex);
      }

    }

// change the players name
    private void changeText() {
      String namePlz;

      namePlz = JOptionPane.showInputDialog("Enter new player name");
      if (namePlz != null) {
        namelbl.setText(namePlz);
        JOptionPane.showMessageDialog(null, "Name set: " + namePlz, "", JOptionPane.INFORMATION_MESSAGE);
        Player theP = theGame.getPlayer();
        theP.setName(namePlz);
      } else {
        JOptionPane.showMessageDialog(null, "Name not set", "", JOptionPane.INFORMATION_MESSAGE);
      }
    }
// setting up our inventory panel
    private void addInvPanel() {
        JPanel np = new JPanel();
        int thickness = THICK;
        np.setBorder(BorderFactory.createLineBorder(Color.white, thickness));
        np.setLayout(new BorderLayout());
        np.setVisible(true);
        listModel = new DefaultListModel();
        jl = new JList(listModel);
        jl.setBackground(Color.black);
        jl.setForeground(Color.white);
        jl.setVisibleRowCount(ROWSTD);
        sp = new JScrollPane(jl);
        Dimension d = jl.getPreferredSize();
        d.width = RWIDTH;
        sp.setPreferredSize(d);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        np.add(sp, BorderLayout.CENTER);
        contentPane.add(np, BorderLayout.EAST);
    }
// adjsuting our inventory panel
    private void addInventoryItem(ArrayList<Item> items) {
     listModel.clear();
      for (Item e : items) {
          listModel.addElement(e.getName());
      }
    }

    private void addNamePanel() {
        JPanel namePanel = new JPanel();
        namePanel.setBackground(Color.black);
        String str = "Player Name";
        Player p = theGame.getPlayer();
        if (p.getName() != null) {
          str = p.getName();
        }
        namelbl = new JLabel(str);
        namelbl.setForeground(Color.white);
        namePanel.add(namelbl);
        contentPane.add(namePanel, BorderLayout.NORTH);
    }

    private void setUpLabelPanel(JPanel thePanel) {
      //  Border prettyLine = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        msgBoard = new JLabel("Message board");
        msgBoard.setForeground(Color.white);
        thePanel.add(msgBoard);
        contentPane.add(thePanel, BorderLayout.SOUTH);
    }

    private void start() {
        try {
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(TerminalPosition.TOP_LEFT_CORNER);
            screen.startScreen();
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
Prints a string to the screen starting at the indicated column and row.
@param toDisplay the string to be printed
@param column the column in which to start the display
@param row the row in which to start the display
**/
        public void putString(String toDisplay, int column, int row) {

            Terminal t = screen.getTerminal();
            try {
                t.setCursorPosition(column, row);
            for (char ch: toDisplay.toCharArray()) {
                t.putCharacter(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

/**
Changes the message at the top of the screen for the user.
@param msg the message to be displayed
**/
            public void setMessage(String msg) {
                putString("                                                ", 1, 1);
                putString(msg, startCol, msgRow);
            }

/**
Redraws the whole screen including the room and the message.
@param message the message to be displayed at the top of the room
@param room the room map to be drawn
**/
            public void draw(String message, String room) {

                try {
                    setMessage(message);
                    putString(room, startCol, roomRow);
                    screen.refresh();
                } catch (IOException e) {

                }

        }

/**
Obtains input from the user and returns it as a char.  Converts arrow
keys to the equivalent movement keys in rogue.
@return the ascii value of the key pressed by the user
**/
public char getInput() {
    KeyStroke keyStroke = null;
    char returnChar;
    while (keyStroke == null) {
    try {
        keyStroke = screen.pollInput();

    } catch (IOException e) {
        e.printStackTrace();
    }

}
returnChar = checkInput(keyStroke);
return returnChar;
}

private char checkInput(KeyStroke keyStroke) {
char returnChar;
if (keyStroke.getKeyType() == KeyType.ArrowDown) {
 returnChar = Rogue.DOWN;  //constant defined in rogue
} else if (keyStroke.getKeyType() == KeyType.ArrowUp) {
 returnChar = Rogue.UP;
} else if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
 returnChar = Rogue.LEFT;
} else if (keyStroke.getKeyType() == KeyType.ArrowRight) {
 returnChar = Rogue.RIGHT;
} else {
 returnChar = keyStroke.getCharacter();
}
return returnChar;
}

/**
The controller method for making the game logic work.
@param args command line parameters
**/
public static void main(String[] args) {
  char userInput = 'd';
  String message = "Welcome to my rogue game";
  String configurationFileLocation = "fileLocations.json";
  RogueParser parser = new RogueParser(configurationFileLocation);
  theGame = new Rogue(parser);
  Player thePlayer = new Player();
  theGame.setPlayer(thePlayer);
  WindowUI theGameUI = new WindowUI();
  theGameUI.draw(message, theGame.getNextDisplay());
  theGameUI.setVisible(true);
  pastInvSize = (theGame.getPlayer()).returnInvSize();
    while (userInput != 'q') {
      theGameUI.mainLoopControl(userInput, theGameUI);
    }
    JFrame rent = new JFrame();
    JOptionPane.showMessageDialog(rent, "Pressed Q: game exited");

    System.exit(0);
}

private void mainLoopControl(char userInput, WindowUI theGameUI) {
  if (pastInvSize != ((theGame.getPlayer()).returnInvSize())) {
      theGameUI.makeInventory(theGame.getPlayer());
      pastInvSize = (theGame.getPlayer()).returnInvSize();
  }
  userInput = theGameUI.getInput();
  theGameUI.tryToMakeMove(theGameUI, theGame, userInput);
}

private void updateMessage(String msg) {
    msgBoard.setText(msg);
}

private void tryToMakeMove(WindowUI theGameUI, Rogue game, char userInput) {
String message;
try {
  String str = (String) jl.getSelectedValue();
  message = game.makeMove(userInput, str);
  theGameUI.draw(" ", game.getBlankDisplay());
  theGameUI.draw("", game.getNextDisplay());
    updateMessage(message);
} catch (InvalidMoveException badMove) {
  message = "Silly goose, thats a wall!";
  theGameUI.setMessage(message);
  theGameUI.draw(" ", game.getNextDisplay());
  updateMessage(message);
}
}

private void makeInventory(Player thePlayer) {
  ArrayList<Item> items = thePlayer.getInvList();
  addInventoryItem(items);
}

}
