package rogue;

public class Ring extends Magic implements Wearable {

  /**
  *default constructor..
  */
  public Ring() {
    super.setWearable();
  }
    @Override
    public String wear() {
        return super.getDescription();
    }
}
