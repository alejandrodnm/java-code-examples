package codeexamples.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.SupplierUtils;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.retry.IntervalFunction;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.vavr.control.Try;
import java.util.function.Supplier;

/** Resilience */
public class Resilience {
  public static void resilience() {
    // Create a CircuitBreaker with default configuration
    final var circuitBreaker = CircuitBreaker.ofDefaults("backendService");

    final var intervalWithExponentialBackoff = IntervalFunction.ofExponentialBackoff();
    // Overwrite the default intervalFunction with your custom one
    final var retryConfig =
        RetryConfig.custom().intervalFunction(intervalWithExponentialBackoff).build();
    final var registry = RetryRegistry.of(retryConfig);
    final var retry = registry.retry("my-retry");

    Supplier<String> supplier =
        () -> {
          return "something";
        };

    // Decorate your call to backendService.doSomething()
    // with a Bulkhead, CircuitBreaker and Retry
    // **note: you will need the resilience4j-all dependency for this
    Supplier<String> decoratedSupplier =
        Decorators.ofSupplier(supplier)
            .withCircuitBreaker(circuitBreaker)
            .withRetry(retry)
            .decorate();

    // Recover before recording it as failure in the decorators
    Supplier<String> supplierWithRecovery =
        SupplierUtils.recover(supplier, (exception) -> "Hello Recovery");

    // Post processing of the results
    Supplier<String> supplierWithResultHandling =
        SupplierUtils.andThen(
            supplierWithRecovery,
            result -> {
              if (result.equals("nothing")) {
                throw new RuntimeException("error");
              }
              return "nothing";
            });

    circuitBreaker.executeSupplier(supplierWithResultHandling);

    Try.ofSupplier(supplierWithResultHandling)
        // Recover from exception after it was recorded as failure
        .recover(throwable -> "Hello from Recovery")
        .get();
  }
}
