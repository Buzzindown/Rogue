package rogue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
*no such item exception.
*/
public class NoSuchItemException extends Exception {

  /**
  *course not found.
  */
  public NoSuchItemException() {
    super("No such item exists.");
    JFrame rent = new JFrame();
    JOptionPane.showMessageDialog(rent, "NoSuchItemException");
  }
}
