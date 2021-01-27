package rogue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
*exception for when an item exists in a room with no legitimate id.
*/
public class NoSuchItemException extends Exception {

  /**
  *course not found..
  */
  public NoSuchItemException() {
    super("No such item exists.");
    JFrame rent = new JFrame();
    JOptionPane.showMessageDialog(rent, "NoSuchItemException");
  }
}
