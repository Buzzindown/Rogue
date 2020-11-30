package rogue;

public class Food extends Item implements Edible {

    /**
    *default food constructor.
    */
    public Food() {
      super.setEdible();
    }

    @Override
    public String eat() {
      if (super.getEdible()) {
        return super.getDescription();
      } else {
        return "why don't eat that";
      }

    }

}
