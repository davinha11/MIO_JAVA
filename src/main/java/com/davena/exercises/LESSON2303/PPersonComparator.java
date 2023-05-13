package com.davena.exercises.LESSON2303;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class PPersonComparator implements Comparator<PPerson> {
 //NON SI FA MAI QUESTA ROBA
    @Override
    public int compare(PPerson o1, PPerson o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public Comparator<PPerson> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<PPerson> thenComparing(Comparator<? super PPerson> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<PPerson> thenComparing(Function<? super PPerson, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<PPerson> thenComparing(Function<? super PPerson, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<PPerson> thenComparingInt(ToIntFunction<? super PPerson> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<PPerson> thenComparingLong(ToLongFunction<? super PPerson> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<PPerson> thenComparingDouble(ToDoubleFunction<? super PPerson> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }
}
