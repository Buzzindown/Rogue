package rogue;

/**
*not enoguh doors exception
*/
public class NotEnoughDoorsException extends Exception{

  /**
  *course not found
  */
  public NotEnoughDoorsException(){
    super("not enough doors exception.");
  }
}
