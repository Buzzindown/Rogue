package rogue;

public class Potion extends Magic implements Edible, Tossable {

    /**
    *default constructor..
    */
    public Potion() {

    }
    @Override
    public void setAbilities() {
      String x = super.getDescription();
      if (x.contains(":")) {
        super.setEdible();
        super.setTossable();
      } else {
        super.setEdible();
      }
    }

    @Override
    public String eat() {
      String x = super.getDescription();
      if (x.contains(":")) {
        String[] arroStr = x.split(":", 2);
        return arroStr[0];
      }
      if (super.getEdible()) {
          return super.getDescription();
      } else {
          return "Do not try and eat that silly";
      }
    }

    @Override
    public String toss() {
      String x = super.getDescription();
      if (x.contains(":")) {
        String[] arroStr = x.split(":", 2);
        return arroStr[1];
      }
      if (super.getTossable()) {
        return super.getDescription();
      } else {
        return "don't try to toss that!";
      }
    }
}
