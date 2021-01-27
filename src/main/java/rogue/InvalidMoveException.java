package rogue;

/**
*Exception for when a player performs an invalid move(ie into a wall).
*/
public class InvalidMoveException extends Exception {

  /**
  *course not found..
  */
  public InvalidMoveException() {
    super("bad move.");
  }
}
