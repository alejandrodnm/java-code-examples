package codeexamples.designpatterns;

import codeexamples.designpatterns.behavioural.chainofresponsibility.NotBlankMiddleware;
import codeexamples.designpatterns.behavioural.chainofresponsibility.NotEmptyMiddleware;
import codeexamples.designpatterns.behavioural.command.CommandHandler;
import codeexamples.designpatterns.behavioural.command.GetCommand;
import codeexamples.designpatterns.behavioural.command.PutCommand;
import codeexamples.designpatterns.behavioural.mediator.Mediator;
import codeexamples.designpatterns.behavioural.observer.Observable;
import codeexamples.designpatterns.behavioural.observer.Observer;
import codeexamples.designpatterns.behavioural.state.State;
import codeexamples.designpatterns.behavioural.visitor.Visitor;
import codeexamples.designpatterns.creational.abstractfactory.AbstractFactory;
import codeexamples.designpatterns.creational.builder.SQL;
import codeexamples.designpatterns.creational.factorymethod.Encryptor;
import codeexamples.designpatterns.creational.prototype.ExpensiveObjectPrototype.ExpensiveObjectSupplier;
import codeexamples.designpatterns.adapter.Sorter;

public class DesignPatterns {
  public static void main() {
    final var colorFactory = new AbstractFactory.ColorFactory();
    final var blue = colorFactory.getColor("BLUE");
    System.out.println("- AbstractFactory:");
    System.out.println(blue);

    System.out.println("- Factory method:");
    final var md5Encryptor = new Encryptor.MD5Encryptor();
    System.out.println(md5Encryptor.encryptAndStuff("SOMETHING TO ENCRYPT"));

    System.out.println("- Builder:");
    System.out.println(
        new SQL.SQLBuilder().select("name").from("user").limit(1).build().getQuery());

    System.out.println("- Prototype:");
    System.out.println("original");
    System.out.println(ExpensiveObjectSupplier.supplieOriginal.get());
    System.out.println(ExpensiveObjectSupplier.supplieOriginal.get());
    System.out.println("clones");
    System.out.println(ExpensiveObjectSupplier.supplier.get());
    System.out.println(ExpensiveObjectSupplier.supplier.get());

    System.out.println("- Adapter:");
    System.out.println(new Sorter.NumberSorterAdapter().sort(new int[] {1, 2, 3})[0]);
    System.out.println(new Sorter.NumberSorterAdapter().sort(new int[] {1, 2, 3})[1]);
    System.out.println(new Sorter.NumberSorterAdapter().sort(new int[] {1, 2, 3})[2]);

    System.out.println("- Chain of responsibility:");
    System.out.println(new NotEmptyMiddleware().linkWith(new NotBlankMiddleware()).check("asd"));
    System.out.println(new NotEmptyMiddleware().linkWith(new NotBlankMiddleware()).check("   "));
    System.out.println(new NotEmptyMiddleware().linkWith(new NotBlankMiddleware()).check(""));

    System.out.println("- Command:");
    final CommandHandler handler = new CommandHandler();
    System.out.println(handler.executeCommand(new PutCommand("A", "B")));
    System.out.println(handler.executeCommand(new GetCommand("A")));

    System.out.println("- Mediator:");
    final Mediator mediator = new Mediator();
    mediator.mediated1.notify("something");
    System.out.println(mediator.mediated2.msgs.get(0));

    System.out.println("- Observer:");
    final Observable.MyObservable observable = new Observable.MyObservable();
    final Observer.MyObserver observer = new Observer.MyObserver(observable);
    System.out.println(observer.observedSomething);
    observable.onMessage("something to observe");
    System.out.println(observer.observedSomething);

    System.out.println("- Observer:");
    final State.Machine machine = new State.Machine();
    System.out.println(machine.getState());
    machine.somethingChangeTheState();
    System.out.println(machine.getState());
    machine.somethingChangeTheState();
    System.out.println(machine.getState());
    machine.somethingChangeTheState();
    System.out.println(machine.getState());

    System.out.println("- Visitor:");
    final Visitor.MyVisitor visitor = new Visitor.MyVisitor();
    new Visitor.HtmlDocument().visit(visitor);
    new Visitor.MdDocument().visit(visitor);
  }
}
