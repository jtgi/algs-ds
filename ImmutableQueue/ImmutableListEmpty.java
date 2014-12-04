package me.jtgi.immutable;

import java.util.NoSuchElementException;

//TODO: A nice optimization is to make this a singleton. Someday.
class ImmutableListEmpty<T> implements ImmutableList<T> {
    public T head() { throw new NoSuchElementException(); }
    public ImmutableList<T> tail() { throw new NoSuchElementException(); }

    public ImmutableList<T> add(T item) {
        return new ImmutableListNode<T>(item, new ImmutableListEmpty<T>());
    }

    public ImmutableList<T> remove(T item) { return this; }
    public boolean exists(T item) { return false; }
    public ImmutableList<T> reverse() { return this; }
    public ImmutableList<T> reverseHelper(ImmutableList<T> acc) { return acc; }
    public int size() { return 0; }
    public boolean isEmpty() { return true; }
    public String toString() { return "empty"; }
}
