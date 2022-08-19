package averagerone;

import java.util.concurrent.ThreadLocalRandom;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
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
    ThreadLocalRandom.current().doubles(6_000_000_000L, -1, 1)
        .parallel()
        .mapToObj(v -> new Average(v, 1))
//        .reduce((a1, a2) -> a1.merge(a2))
        .reduce(Average::merge)
        .ifPresent(a -> System.out.println("Average is " + a.get()));
    long time = System.nanoTime() - start;
    System.out.printf("Time taken was %7.3f\n", (time / 1_000_000_000.0));
  }
}
