package codeexamples.designpatterns.creational.abstractfactory;

public abstract class AbstractFactory {
  abstract Color getColor(final String p0);

  public static class ColorFactory extends AbstractFactory {
    public Color getColor(final String color) {
      final String lowerCase = color.toLowerCase();
      switch (lowerCase) {
        case "blue":
          {
            return new Color.Blue();
          }
        case "red":
          {
            return new Color.Red();
          }
        default:
          {
            return null;
          }
      }
    }
  }
}
