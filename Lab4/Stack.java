package io.axbergLabs;

import java.util.Iterator;

/**
 * Class Queue is a LIFO queue that implements Iterable. Methods Queue<Item>(),
 * enqueue(Item item), dequeue(), isEmpty() and size() is its API methods.
 * 
 * @param <Item>
 */
public class Stack<Item> implements Iterable<Item> {

    /**
     * Class Node is an object that contains an integer and a reference to an other
     * Node object. Attributes: next: referens to the next node data: the integer
     * that the node contains
     */

    private int N;

    public class Node {

        private Node next;
        private Item data;

        public Node() {

            this.data = null;
        }

        public Node(Item data, Node next) {

            this.next = next;
            this.data = data;
        }
    }

    /**
     * head is a node that points to the first node, or null
     */
    public Node head = null;

    /**
     * enqueue takes in param: data, creates a new node with that data and puts it
     * in the liked list with head as its first node.
     * 
     * @param data: the integer that will be stored in the node created
     */
    public void push(Item data) {
        Node n = new Node(data, head);
        head = n;
        N++;
    }

    /**
     * dequeue takes the first node in the queue / liked list and returns its data.
     * 
     * @return the generic Item value of the first node in the queue.
     */
    public Item pop() {
        Node r = head;
        head = r.next;
        r.next = null;
        N--;
        return r.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return N;
    }

    @Override
    public Iterator<Item> iterator() {
        return new FIFOIterator();
    }

    /**
     * ReverseIterator is implemented in that way that its iterating in FIFO order.
     */
    public class FIFOIterator implements Iterator<Item> {

        private Node n = head;

        @Override
        public boolean hasNext() {
            return n != null;
        }

        @Override
        public Item next() {

            Item item = n.data;
            n = n.next;
            return item;
        }
    }

    /**
     * toString method to represent the list in a convenient way.
     */
    @Override
    public String toString() {

        Node n = head;

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        while (n.next != null) {
            sb.append(n.data);
            sb.append(", ");
            n = n.next;
        }
        sb.append(n.data);
        sb.append("]");

        return sb.toString();
    }



}
