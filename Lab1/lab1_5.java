package Labs;

import java.io.IOException;
import java.util.NoSuchElementException;
// import Assignments.StdInOut.StdOut;
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
 * @since 2020-09-03
 * @contact: taxberg@kth.se
 * 
 */

public class lab1_5 {

    /**
     * Class Queue is a LIFO queue that implements Iterable. Methods Queue<Item>(),
     * enqueue(Item item), dequeue(), isEmpty() and size() is its API methods.
     * 
     * @param <Item>
     */
    public static class Queue<Item> implements Iterable<Item> {

        /**
         * N is the number of elements in the queue.
         */
        private int N;

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
         * enqueue takes in param: data, creates a new node with that data and puts it
         * in the liked list with head as its first node.
         * 
         * @param data: the integer that will be stored in the node created
         */
        public void enqueue(Item data) {

            Node n = new Node(data, head);

            N++;
            head = n;

            printQueue();

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
            N--;

            printQueue();

            return r.data;
        }

        public void removeKthElement(int K) {

            if (K > N)
                throw new NoSuchElementException(K + "th index can not point to element in queue of length " + N);

            N--;
            Node n = head;

            if (K == 1) {

                head = n.next;
                n.next = null;
                printQueue();

                return;
            }

            int i = 1;
            Node r = n;

            while (i++ < (K - 1)) {
                n = n.next;
            }
            r = n.next;

            n.next = n.next.next;
            n = n.next;
            r.next = null;

            r = null; // Is it necessary to do this?
            n = null; // Is it necessary to do this?

            printQueue();

        }

        /**
         * printQueueRecursive prints out, recursively, every value of the nodes in the
         * queue in LIFO order.
         */
        public void printQueue() {

            Node n = head;
            StringBuilder sb = new StringBuilder();

            sb.append("[");

            if (N > 0) {

                while (n.next != null) {
                    sb.append(n.data);
                    sb.append(", ");
                    n = n.next;
                }

                sb.append(n.data);
            }
            sb.append("]");

            System.out.println(sb.toString());
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

        Queue<Integer> queue = new Queue<Integer>();

        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);
        queue.removeKthElement(1);
        queue.removeKthElement(4);
        queue.removeKthElement(1);
        queue.removeKthElement(2);
        queue.removeKthElement(1);
        queue.enqueue(5);
        queue.enqueue(4);
        queue.enqueue(3);
        queue.enqueue(2);
        queue.enqueue(1);

    }
}