package averagertwo;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.DoubleStream;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public Average include(double d) {
    return new Average(this.sum + d, this.count + 1);
  }

  public Average merge(Average other) {
    return new Average(this.sum + other.sum, this.count + other.count);
  }

  public double get() {
    return sum / count;
  }
}
public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
//    double average = ThreadLocalRandom.current().doubles(4_000_000_000L, -1, 1)
    double average = DoubleStream.iterate(0,
        x -> ThreadLocalRandom.current().nextDouble(-1, 1))
        .limit (4_000_000_000L)
//        .parallel() // watch out for out of memory with ordered stream
        .boxed()
        .reduce(new Average(0, 0),
            (a, d) -> a.include(d), // Average::include
            (a1, a2) -> a1.merge(a2)) // Average::merge
        .get();
    long time = System.nanoTime() - start;
    System.out.printf("Time taken was %7.3f\n", (time / 1_000_000_000.0));
  }
}
