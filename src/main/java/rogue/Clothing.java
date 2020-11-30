package rogue;

public class Clothing extends Item implements Wearable {

    /**
    *clothing constructor.
    */
    public Clothing() {
      super.setWearable();
    }

    @Override
    public String wear() {
          return super.getDescription();
    }
}
