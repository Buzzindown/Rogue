package rogue;
import java.util.ArrayList;
/**
 * The player character
 */
public class Symbol {

  private char symbol;
  private String name;

  public Symbol(String namez, char symbolz){
    symbol = symbolz;
    name = namez;
  }

  public String getSymbolName(){
    return name;
  }

  public char getSymbol(){
    return symbol;
  }
}
