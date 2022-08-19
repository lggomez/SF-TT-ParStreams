package verify;

import java.util.function.Consumer;

public class Verify {
  public static void main(String[] args) {
//    ((Consumer<String>)(var s) -> System.out.println(s))
//        .accept("Hello Java 11 World!");
    ((Consumer<String>)s -> System.out.println(s))
        .accept("Hello Java 8 World!");
    System.out.println(
        System.getProperties().getProperty("java.vm.version"));
    System.out.println(
        System.getProperties().getProperty("java.version"));
  }
}
