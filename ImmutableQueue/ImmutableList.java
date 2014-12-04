package me.jtgi.immutable;

/**
 * Created by jtgi on 11/1/14.
 */
interface ImmutableList<T> {
    T head();
    ImmutableList<T> tail();
    ImmutableList<T> add(T item);
    ImmutableList<T> remove(T item);
    ImmutableList<T> reverse();
    boolean exists(T item);
    ImmutableList<T> reverseHelper(ImmutableList<T> acc);
    int size();
    boolean isEmpty();
}
