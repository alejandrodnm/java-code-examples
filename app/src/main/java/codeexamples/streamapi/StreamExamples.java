package codeexamples.streamapi;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExamples {

  public static void main() {

    final var summary =
        getStream()
            .collect(
                Collectors.teeing(
                    Collectors.summingInt(ValueObject::value),
                    Collectors.averagingInt(ValueObject::value),
                    ValueObjectSummary::new));

    System.out.println(
        "The values are: "
            + getStream()
                .map(vo -> vo.value().toString())
                .collect(Collectors.joining(", ", "[ ", " ]")));
    System.out.println("The sum is: " + summary.sum());
    System.out.println("The average is: " + summary.avg());
    System.out.println(
        "The reduce to power is: "
            + getStream()
                .reduce(new ValueObject(0), (acc, n) -> new ValueObject(acc.value() + n.value())));
  }

  private static Stream<ValueObject> getStream() {
    return Stream.iterate(1, n -> n * 2)
        .limit(20)
        .map(
            n -> {
              if (n == 32) {
                // We need this to specify the concrete type Integer
                Optional<ValueObject> empty = Optional.empty();
                return empty;
              }
              return Optional.of(new ValueObject(n));
            })
        .peek(
            o -> {
              if (o.isEmpty()) {
                System.out.println("found empty value");
              }
            })
        .filter(Optional::isPresent)
        .map(Optional::get);
  }

  record ValueObject(Integer value) {}

  record ValueObjectSummary(Integer sum, Double avg) {}
}
