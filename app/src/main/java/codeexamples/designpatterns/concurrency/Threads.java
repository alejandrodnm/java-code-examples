package codeexamples.designpatterns.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Threads {
  public static void main() {
    final var invoker = new Invoker();
    invoker.execute(() -> System.out.println("with custom executor"));
    final var executorService = Executors.newFixedThreadPool(2);
    executorService.submit(new Task());
    final var future =
        executorService.submit(
            () -> {
              System.out.println("future from executor from Lambda");
              return Integer.valueOf(1);
            });
    final var futureToCancel =
        executorService.submit(
            () -> {
              System.out.println("future to cancel");
              Thread.sleep(1000L);
              System.out.println("never prints");
              return Integer.valueOf(1);
            });
    futureToCancel.cancel(true);
    System.out.println("The future is cancelled: " + String.valueOf(futureToCancel.isCancelled()));
    try {
      if (future.isDone() && !future.isCancelled()) {
        final var futureResult = future.get();
        System.out.println(futureResult);
      }
    } catch (InterruptedException | ExecutionException ex) {
      ex.printStackTrace();
    }
    final var cyclicBarrier =
        new CyclicBarrier(2, () -> System.out.println("cyclic barrier finished"));
    executorService.submit(
        () -> {
          System.out.println("waiting for barrier");
          try {
            cyclicBarrier.await();
          } catch (InterruptedException | BrokenBarrierException ex) {
            ex.printStackTrace();
          }
          System.out.println("barrier passed");
          return;
        });
    executorService.submit(
        () -> {
          System.out.println("waiting for barrier");
          try {
            cyclicBarrier.await();
          } catch (InterruptedException | BrokenBarrierException ex) {
            ex.printStackTrace();
          }
          System.out.println("barrier passed");
          return;
        });
    final var phaser = new Phaser(1);
    phaser.register();
    executorService.submit(
        () -> {
          System.out.println("thread 1 wait");
          phaser.arriveAndAwaitAdvance();
          System.out.println("thread 1 advanced");
          try {
            Thread.sleep(100L);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
          phaser.arriveAndDeregister();
          return;
        });
    phaser.register();
    executorService.submit(
        () -> {
          System.out.println("thread 2 wait");
          phaser.arriveAndAwaitAdvance();
          System.out.println("thread 2 advanced");
          try {
            Thread.sleep(100L);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
          phaser.arriveAndDeregister();
          return;
        });
    phaser.arriveAndAwaitAdvance();
    phaser.register();
    executorService.submit(
        () -> {
          System.out.println("thread 3 wait");
          phaser.arriveAndAwaitAdvance();
          System.out.println("thread 3 advanced");
          try {
            Thread.sleep(100L);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
          phaser.arriveAndDeregister();
          return;
        });
    phaser.arriveAndDeregister();
    executorService.shutdown();
    try {
      if (!executorService.awaitTermination(800L, TimeUnit.MILLISECONDS)) {
        executorService.shutdownNow();
      }
    } catch (InterruptedException ex) {
      executorService.shutdownNow();
    }
    try {
      final var hello =
          CompletableFuture.supplyAsync(() -> "Hello")
              .thenApply(w -> w + " World")
              .thenAccept(w -> System.out.println(w));
      hello.get();
    } catch (InterruptedException | ExecutionException ex) {
      ex.printStackTrace();
    }
    System.out.println("THREADS END");
  }

  public static class Invoker implements Executor {
    @Override
    public void execute(final Runnable command) {
      command.run();
    }
  }

  public static class Task implements Runnable {
    @Override
    public void run() {
      System.out.println("Task running");
    }
  }
}
