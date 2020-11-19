package io.axbergLabs;

import java.util.Iterator;
import java.util.NoSuchElementException;

// import edu.princeton.cs.algs4.*;
/*
 * lab1_3.java // ID 1020 KTH
 * 
 * 
 * API implements generic iterable FIFO-queue based on a double linked circular list.
 * Class Queue is a FIFO queue that implements Iterable. Methods Queue(),
 * enqueue(Item item), dequeue(), isEmpty() and size() is the API methods.
 * 
 * 
 * What it has been based upon ? Based upon FIFO queue from chapter 1.3 in
 * course book Algorithms by Robert Sedgewich and Robert Wayne.s
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.2
 * @since 2020-08-29
 * @contact: taxberg@kth.se
 * 
 */

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first; // beginning of queue
    private Node<Item> last; // end of queue
    private int n; // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public boolean includes(Item n) {
        Node<Item> find = first;
        while (find != last && !find.item.equals(n)) {
            find = find.next;
        }
        if (find.item.equals(n)) {
            return true;
        }
        return false;
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Adds the item to this queue.
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null; // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (Item item : this) {
            s.append(item);
            s.append(", ");
        }
        s.delete(s.length() - 2, s.length());
        s.append("]");
        return s.toString();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Queue<Character> queue = new Queue<Character>();
        System.out.println(queue);

        for (Character c : queue) {
            StdOut.print(c);
        }

        System.out.println("\n");
    }
}