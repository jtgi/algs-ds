package me.jtgi.immutable;

import java.util.NoSuchElementException;

/**
 * The operations I've optimized for are enqueue and dequeue.
 * As a trivial implementation will give us either O(1) dequeues or enqueues,
 * this implementation gives amortized O(1) for dequeues and enqueues.
 * It does so by keeping two immutable lists, whenever enqueue is called
 * the item is put on the `in` list, whenever enqueue is called, if it is
 * empty, the contents of `in` are reversed and set to the `out` list.
 *
 * We incur a cost during this copying, but over a sequence of operations
 * we recoup that cost giving us amortized constant time. Peek, however really
 * made things difficult. It run O(n), n = number of nodes
 * in the 'in' queue when `out` is empty.
 *
 * A further choice to eliminate this cost could be to return a tuple-like
 * class when a call is made to dequeue (x, y) where x is the item dequeued
 * and y is the new queue. But who wants to deal with some DequeueResult.
 *
 * Also some of the simplicity relies on the base Empty classes. If this hits nulls
 * at runtime its game over.
 *
 * Lastly, this was fun even if painful in Java. Recursion via polymorphism is fun.
 * Capturing your bases cases in an Empty implementation of a common interface also
 * simplifies a lot of code.
 *
 */
public class ImmutableQueue<E> {

    private final ImmutableList<E> in;
    private final ImmutableList<E> out;
    private final int size;

    public ImmutableQueue() {
        this.in = new ImmutableListEmpty<E>();
        this.out = new ImmutableListEmpty<E>();
        this.size = 0;
    }

    public ImmutableQueue(E item) {
        this.in = new ImmutableListNode<E>(item);
        this.out = new ImmutableListEmpty<E>();
        this.size = in.size() + out.size();
    }

    private ImmutableQueue(ImmutableList<E> in, ImmutableList<E> out) {
        this.in = in;
        this.out = out;
        this.size = in.size() + out.size();
    }

    /**
     *  Enqueuing the null item: Either return this or throw an exception.
     *  Choose your poison. I prefer getting stuff back I can work with.
     */
    public ImmutableQueue<E> enqueue(E item) {
        if(item == null) {
            return this;
        }

        return new ImmutableQueue<E>(in.add(item), out);
    }

    public ImmutableQueue<E> dequeue() {
        if(isEmpty())  {
            return this;
        }

        if(out.isEmpty()) {
            return new ImmutableQueue<E>(new ImmutableListEmpty<E>(), in.reverse().tail());
        }

        return new ImmutableQueue<E>(in, out.tail());
    }

    /**
     * This method is a really painful operation for a large queue.
     * It has to reverse the entire list then read the head. The work
     * done here is discarded as we can't return a queue here and we can't
     * set our out buffer because we are immutable. Sigh.
     * TODO: Make it O(1) somehow.
     *
     * Also in the empty case, I would much rather give back a Maybe/None,
     * but throwing instead.
     */
    public E peek() {
        if(out.isEmpty() && in.isEmpty()) {
            throw new NoSuchElementException("You may not peek the empty queue. Don't even look at it.");
        }

        return out.isEmpty() ? in.reverse().head() : out.head();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public String toString() {
        if(isEmpty()) {
          return "empty";
        } else {
          return peek().toString() + " <- " + dequeue().toString();
        }
    }

}

