package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImpl implements LambdaStreamExc{


  @Override
  public Stream<String> createStrStream(String... strings) {
    return Stream.of(strings);
  }

  @Override
  public Stream<String> toUpperCase(String... strings) {
    return createStrStream(strings).map(string -> string.toUpperCase());
  }

  @Override
  public Stream<String> filter(Stream<String> stringStream, String pattern) {
    return stringStream.filter(stream -> !stream.matches(pattern));
  }

  @Override
  public IntStream createIntStream(int[] arr) {
    return IntStream.of(arr);
  }

  @Override
  public <E> List<E> toList(Stream<E> stream) {
    return stream.collect(Collectors.toList());
  }

  @Override
  public List<Integer> toList(IntStream intStream) {
    return intStream.boxed().collect(Collectors.toList());
  }

  @Override
  public IntStream createIntStream(int start, int end) {
    return IntStream.rangeClosed(start, end);
  }

  @Override
  public DoubleStream squareRootIntStream(IntStream intStream) {
    return intStream.asDoubleStream().map(DoubleStream -> Math.sqrt(DoubleStream));
  }

  @Override
  public IntStream getOdd(IntStream intStream) {
    return intStream.filter(element -> element%2 !=0 );
  }

  @Override
  public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
    return message -> System.out.println(suffix+message+suffix);
  }

  @Override
  public void printMessages(String[] messages, Consumer<String> printer) {
    Stream.of(messages).forEach(printer);
  }

  @Override
  public void printOdd(IntStream intStream, Consumer<String> printer) {
    getOdd(intStream).forEach(element -> printer.accept(Integer.toString(element)));
  }

  @Override
  public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
    return ints.flatMap(list -> list.stream().map(num -> num * num));
  }

}
