package sideeffects;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BadStream {
  public static void main(String[] args) {
//    final int [] count = {0};
    final AtomicLong count = new AtomicLong();
//    long total = Stream.of(1,2,3,4,5,6,7,8,9,10)
    long total = IntStream.range(0, 5_000_000)
        .parallel()
//        .peek(v -> count[0]++)
        .peek(v -> count.incrementAndGet())
        .count();
//        .forEach(System.out::println);
//    System.out.println("count is " + count[0]);
    System.out.println("count is " + count);
    System.out.println("total is " + total);
  }
}
