package Labs;

import java.io.IOException;
import java.util.Iterator;

/**
 * lab1_2.java // ID 1020 KTH
 * 
 * 
 * What problem does the code solve? Code API implements a LIFO ADT Queue. Is to
 * show that the author know how to implement this queue ADT.
 * 
 * How is the code used? (How to execute, input and what output) - There are
 * three main methods that are available for the user. enqueue, dequeue and
 * printQueueRecursive. Enqueue adds an element in front of the first element
 * refered to by head by taking in a generic parameter. Dequeue removes the
 * first element in the queue and returns its generic value. printQueueRecursive
 * prints out all generic items recursively.
 * 
 * What it has been based upon ? Based upon FIFO queue from chapter 1.3 in
 * course book Algorithms by Robert Sedgewich and Robert Wayne.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.2
 * @since 2020-08-29
 * @contact: taxberg@kth.se
 * 
 */

public class lab1_2 {

    /**
     * Class Queue is a LIFO queue that implements Iterable. Methods Queue<Item>(),
     * enqueue(Item item), dequeue(), isEmpty() and size() is its API methods.
     * 
     * @param <Item>
     */
    public static class Queue<Item> implements Iterable<Item> {

        /**
         * Class Node is an object that contains an integer and a reference to an other
         * Node object. Attributes: next: referens to the next node data: the integer
         * that the node contains
         */

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
         * head is a node that points to the first node, or null, in an instance of
         * LIFOqueue.
         */
        public Node head = null;

        /**
         * enqeueu takes in param: data, creates a new node with that data and puts it
         * in the liked list with head as its first node.
         * 
         * @param data: the integer that will be stored in the node created
         */
        public void enqueue(Item data) {
            Node n = new Node(data, head);
            head = n;
        }

        /**
         * dequeue takes the first node in the queue / liked list and returns its data.
         * 
         * @return the generic Item value of the first node in the queue.
         */
        public Item dequeue() {
            Node r = head;
            head = r.next;
            r.next = null;
            return r.data;
        }

        /**
         * printQueueRecursive prints out, recursively, every value of the nodes in the
         * queue in LIFO order.
         */
        public void printQueueRecursive(Node n) {

            if (n.next != null) {
                System.out.print(n.data);
                n = n.next;
                printQueueRecursive(n);
            } else
                System.out.print(n.data);
        }

        @Override
        public Iterator<Item> iterator() {
            return new ReverseIterator();
        }

        /**
         * ReverseIterator is implemented in that way that its iterating in FIFO order.
         */
        public class ReverseIterator implements Iterator<Item> {

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

    public static void main(String[] args) throws IOException {

        Queue<Character> queue = new Queue<Character>();
        char a = (char) System.in.read();

        while (a != '\n') {
            queue.enqueue(a);
            a = (char) System.in.read();
        }

        for (Character c : queue)
            System.out.println(c);
        ;

        System.out.println("\n");

        System.out.println(queue);

        System.out.println("\n");

    }
}