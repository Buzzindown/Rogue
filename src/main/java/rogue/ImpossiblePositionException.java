package rogue;
/**
*Exception for impossible possitions, specifically for items placed outside of rooms..
*/
public class ImpossiblePositionException extends Exception {

  /**
  *course not found.
  */
  public ImpossiblePositionException() {
    super("impossible location exception.");
  }
}
