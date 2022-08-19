package averagerone.averagecollect;

import java.util.concurrent.ThreadLocalRandom;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public void include(double d) {
    this.sum += d;
    this.count++;
  }

  public void merge(Average other) {
    this.sum += other.sum;
    this.count += other.count;
  }

  public double get() {
    return sum / count;
  }
}
public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    double average = ThreadLocalRandom.current().doubles(10_000_000_000L, -1, 1)
        .parallel()
        .collect(() -> new Average(0, 0),
            (a, d) -> a.include(d), // Average::include
            (a1, a2) -> a1.merge(a2)) // Average::merge
        .get();
    long time = System.nanoTime() - start;
    System.out.printf("Time taken was %7.3f\n", (time / 1_000_000_000.0));
  }
}
