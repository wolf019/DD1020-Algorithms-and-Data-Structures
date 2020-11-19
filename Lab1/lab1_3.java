package Labs;

import Assignments.StdInOut.StdOut;
import java.util.Iterator;

/**
 * lab1_3.java // ID 1020 KTH
 * 
 * 
 * What problem does the code solve? Code API implements generic iterable
 * FIFO-queue based on a double linked circular list.
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
public class lab1_3 {

    /**
     * 
     * Class Queue is a FIFO queue that implements Iterable. Methods Queue(),
     * enqueue(Item item), dequeue(), isEmpty() and size() is the API methods.
     * 
     * @param <Item>
     */
    public static class Queue<Item> implements Iterable<Item> {

        /**
         * Number of elements in LIFO Queue represented by an int.
         */
        private int N = 0;

        /**
         * Class Node is an object that contains generic data and two references to two
         * other Node objects.
         * 
         * Attributes: next: the reference to the next node prev: the reference to the
         * previous data: the generic data that is stored in the node
         */

        public class Node {

            private Node next;
            private Node prev;
            private Item data;

            /**
             * Node constructor.
             * 
             * @param data: the generic type data.
             */
            public Node(Item data) {

                this.data = data;
            }

            /**
             * Node constructor.
             * 
             * @param data: the generic type data
             * @param next: the next Node in the list. I.e. the Node which this node will be
             *              put before
             * @param prev: the previous Node in the list. I.e the node which will have this
             *              Node as its next one.
             */
            public Node(Item data, Node next, Node prev) {

                this.prev = prev;
                this.next = next;
                this.data = data;
            }
        }

        /**
         * head is a node that points to the first node, or null, in an instance of
         * LIFO-queue.
         */

        private Node head = null;

        /**
         * enqueue takes in param: data, creates a new node with that data and puts it
         * in the liked list with head as its first node.
         * 
         * @param data: the generic data that will be stored in the node created
         */
        public void enqueue(Item data) {

            Node n = new Node(data);
            insertFirst(n);
            printQueue();
        }

        /**
         * dequeue takes the first node in the queue / liked list and returns its data.
         * 
         * @return the generic data of the first node in the queue. I.e data of the Node
         *         that head is referring to.
         */
        public Item dequeue() {

            Node r = head;
            r.prev.next = r.next;
            r.next.prev = r.prev;
            head = r.next;
            r.next = null;
            r.prev = null;
            N--;
            printQueue();

            return r.data;
        }

        /**
         * InsertAfter takes in two Node references. One that is the Node to be inserted
         * and one that shows where that Node shall be inserted after.
         */
        public void insertAfter(Node where, Node thisNode) {

            if (head == null) {

                thisNode.next = thisNode;
                thisNode.prev = thisNode;
                head = thisNode;
            }

            else {

                thisNode.next = where.next;
                thisNode.prev = where;
                where.next.prev = thisNode;
                where.next = thisNode;
                // head = thisNode;
            }

            N++;

        }

        public void insertFirst(Node n) {
            insertAfter(head, n);

        }

        public void insertLast(Node n) {
            insertAfter(head.prev, n);
        }

        /**
         * printQueue prints out every value of the nodes in the queue in LIFO order.
         * 
         * Code borrowed from other program. needs to be changed.
         * 
         */
        public void printQueue() {
            Node n = head;
            int i = 0;

            while (true) {

                if (n == null) {
                    System.out.println("Queue is empty.");
                    return;
                } else if (i++ < N) {
                    System.out.print(n.data);
                    n = n.prev;
                } else {
                    System.out.println();
                    return;
                }
            }
        }

        @Override
        public Iterator<Item> iterator() {
            return new ReverseIterator();
        }

        public class ReverseIterator implements Iterator<Item> {

            private int i = 0;
            private Node n = head;
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public Item next() {

                Item item = n.data;
                n = n.next;

                if (++i == N)
                    hasNext = false;

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

            while (n.next != head) {
                sb.append(n.data);
                sb.append(", ");
                n = n.next;
            }
            sb.append(n.data);
            sb.append("]");

            return sb.toString();
        }
    }

    public static void main(String[] args) {

        Queue<Character> queue = new Queue<Character>();

        queue.enqueue('X');
        queue.enqueue('t');
        queue.enqueue('r');
        queue.enqueue('a');
        queue.enqueue('f');
        queue.enqueue(' ');
        queue.enqueue('e');
        queue.enqueue('v');
        queue.enqueue('a');
        queue.enqueue('r');
        queue.enqueue('b');
        queue.dequeue();

        System.out.println(queue);

        for (Character c : queue) {
            StdOut.print(c);
        }

        System.out.println("\n");
    }
}