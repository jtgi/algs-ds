package me.jtgi.immutable;

/**
 * Created by jtgi on 11/1/14.
 */
public class ImmutableListNode<T> implements ImmutableList<T> {
    private final T head;
    private final ImmutableList<T> tail;
    private final int size;

    public ImmutableListNode(T head) {
        this(head, new ImmutableListEmpty<T>());
    }

    public ImmutableListNode(T head, ImmutableList<T> tail) {
        this.head = head;
        this.tail = tail;
        this.size = 1 + tail.size();
    }

    public T head() {
        return head;
    }

    public ImmutableList<T> tail() {
        return tail;
    }

    public int size() {
        return size;
    }

    public ImmutableList<T> add(T item) {
        return new ImmutableListNode<T>(item, this);
    }

    public ImmutableList<T> remove(T item) {
        return (head.equals(item)) ? tail().remove(item) : tail().remove(item).add(head);
    }

    public boolean exists(T item) {
        return head.equals(item) || tail().exists(item);
    }

    public ImmutableList<T> reverse() {
        return reverseHelper(new ImmutableListEmpty<T>());
    }

    public ImmutableList<T> reverseHelper(ImmutableList<T> acc) {
        return tail.reverseHelper(acc.add(head));
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public String toString() {
        return String.format("%s -> %s", head.toString(), tail.toString());
    }

}


