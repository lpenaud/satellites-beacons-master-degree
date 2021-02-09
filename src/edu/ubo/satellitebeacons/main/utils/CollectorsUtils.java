package edu.ubo.satellitebeacons.main.utils;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CollectorsUtils<T, A, R> implements Collector<T, A, R> { 
  protected static Set<Characteristics> listToSet(final Characteristics[] characteristics) {
    final var map = new EnumMap<Characteristics, Boolean>(Characteristics.class);
    for (int i = 0; i < characteristics.length; i++) {
      map.put(characteristics[i], true);
    }
    return Collections.unmodifiableSet(Collections.newSetFromMap(map));
  }

  public CollectorsUtils(final Supplier<A> supplier, final BiConsumer<A, T> accumulator, final BinaryOperator<A> combiner, final Function<A, R> finisher, final Characteristics...characteristics) {
    this.supplier = supplier;
    this.accumulator = accumulator;
    this.combiner = combiner;
    this.finisher = finisher;
    this.characteristics = listToSet(characteristics);
  }

  @Override
  public Supplier<A> supplier() {
    return supplier;
  }

  @Override
  public BiConsumer<A, T> accumulator() {
    return accumulator;
  }

  @Override
  public BinaryOperator<A> combiner() {
    return combiner;
  }

  @Override
  public Function<A, R> finisher() {
    return finisher;
  }

  @Override
  public Set<Characteristics> characteristics() {
    return characteristics;
  }

  protected final Supplier<A> supplier;
  protected final BiConsumer<A, T> accumulator;
  protected final BinaryOperator<A> combiner;
  protected final Function<A, R> finisher;
  protected final Set<Characteristics> characteristics;
}
