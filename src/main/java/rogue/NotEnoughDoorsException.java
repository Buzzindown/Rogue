package rogue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
*not enoguh doors exception.
*/
public class NotEnoughDoorsException extends Exception {

  /**
  *course not found.
  */
  public NotEnoughDoorsException() {
    super("not enough doors exception.");
    JFrame rent = new JFrame();
    JOptionPane.showMessageDialog(rent, "NotEnoughDoorsException");
  }
}
