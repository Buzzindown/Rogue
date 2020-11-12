package rogue;

/**
*no such item exception.
*/
public class NoSuchItemException extends Exception {

  /**
  *course not found.
  */
  public NoSuchItemException() {
    super("No such item exists.");
  }
}
