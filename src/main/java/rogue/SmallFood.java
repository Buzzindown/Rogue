package rogue;

public class SmallFood extends Food implements Tossable {

    /**
    *Default constructor.
    */
    public SmallFood() {

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
        return "Do not try and eat that";
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
        return "Do not try and toss that";
      }
    }

}
