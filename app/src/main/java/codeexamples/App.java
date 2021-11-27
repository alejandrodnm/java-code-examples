package codeexamples;

import codeexamples.designpatterns.DesignPatterns;
import codeexamples.designpatterns.concurrency.Threads;
import codeexamples.streamapi.StreamExamples;

public class App {
  public static void main(final String[] args) {

    System.out.println("- Design Patterns");
    DesignPatterns.main();

    System.out.println("- Threads");
    Threads.main();

    System.out.println("- Streams");
    StreamExamples.main();
  }
}
