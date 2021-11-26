package codeexamples.designpatterns.creational.prototype;

import java.util.function.Supplier;

public interface ExpensiveObjectPrototype extends Cloneable {
  ExpensiveObject clone() throws CloneNotSupportedException;

  public static class ExpensiveObject implements ExpensiveObjectPrototype {
    @Override
    public ExpensiveObject clone() {
      try {
        return (ExpensiveObject) super.clone();
      } catch (CloneNotSupportedException e) {
        e.printStackTrace();
        return null;
      }
    }
  }

  public static class ExpensiveObjectSupplier {
    private static ExpensiveObject expensiveObject;
    public static Supplier<ExpensiveObject> supplieOriginal;
    public static Supplier<ExpensiveObject> supplier;

    static {
      ExpensiveObjectSupplier.expensiveObject = new ExpensiveObject();
      ExpensiveObjectSupplier.supplieOriginal = (() -> ExpensiveObjectSupplier.expensiveObject);
      ExpensiveObjectSupplier.supplier = (() -> ExpensiveObjectSupplier.expensiveObject.clone());
    }
  }
}
