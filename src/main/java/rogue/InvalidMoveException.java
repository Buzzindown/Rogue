package rogue;

/**
*not enoguh doors exception
*/
public class InvalidMoveException extends Exception{

  /**
  *course not found
  */
  public InvalidMoveException(){
    super("bad move.");
  }
}
