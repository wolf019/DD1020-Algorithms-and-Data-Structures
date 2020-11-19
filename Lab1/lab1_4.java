package Labs;

import java.io.IOException;
// import Assignments.StdInOut.StdOut;
import java.util.Iterator;

/**
 * lab1_3.java // ID 1020 KTH
 * 
 * 
 * What problem does the code solve? Code API implements a generic iterable
 * circular FIFO-queue.
 * 
 * How is the code used? (How to execute, input and what output) - There are
 * four main methods that are available for the user.
 * 
 * 
 * @author Tom K. Axberg
 * @version 1.0
 * @since 2020-09-03
 * @contact: taxberg@kth.se
 * 
 */

public class lab1_4 {

    /**
     * Class Queue is a LIFO queue that implements Iterable. Methods Queue(),
     * InsertInFront(Item item), InsertFromBack(), removeFirst() and removeLast() is
     * the API methods.
     * 
     * @param <Item>
     */
    public static class Queue<Item> implements Iterable<Item> {

        private int N = 0;

        /**
         * Class Node is an object that contains an integer and a reference to an other
         * Node object. Attributes: next: reference to the next node data: the generic data
         * that the node contains.
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
        private Node head = null;

        /**
         * tale is a node that points to the last node, or null, in an instance of
         * LIFOqueue.
         */
        private Node tale = null;

        /**
         * InsertInFront takes in param: data, creates a new node with that data and
         * puts it in the liked list with head pointing to it.
         * 
         * @param data: the integer that will be stored in the node created
         */
        public void InsertInFront(Item data) {

            Node n = new Node(data, head);
            N++;

            if (head == null) {
                n.next = n;
                head = n;
                tale = n;
            }

            n.next = head;
            tale.next = n;
            head = n;
            printQueue();

        }

        /**
         * InsertFromBack takes in param: data, creates a new node with that data and
         * puts it in the end of the liked list.
         * 
         * @param data: the integer that will be stored in the node created
         */
        public void InsertFromBack(Item data) {

            Node n = new Node(data, head);
            N++;

            if (head == null) {
                n.next = n;
                head = n;
                tale = n;
            }

            n.next = head;
            tale.next = n;
            tale = n;
            printQueue();

        }

        /**
         * removeFirst takes the first node in the queue / liked list and returns its
         * data.
         * 
         * @return the generic value of the first node in the queue.
         */
        public Item removeFirst() {

            if (head == null) {
                System.out.println("Queue is empty. Please insert elements.");
                return null;
            }
            Node r = head;

            tale.next = r.next;
            head = r.next;
            r.next = null;
            N--;

            if (N == 0) {
                head = null;
                tale = null;
            }

            printQueue();

            return r.data;
        }

        /**
         * removeLast takes the last node in the queue / liked list and returns its
         * data.
         * 
         * @return the generic value of the first node in the queue.
         */
        public Item removeLast() {

            if (head == null) {
                System.out.println("Queue is empty. Please insert elements.");
                return null;
            }

            Node r = tale;
            int i = 0;

            while (++i < N)
                tale = tale.next;

            r.next = head;
            r.next = null;
            N--;

            if (N == 0) {
                head = null;
                tale = null;
            }

            printQueue();

            return r.data;
        }

        /**
         * printQueue prints out every value of the nodes in the queue in LIFO order in the form like followed [Item1, Item2 , ... , ItemN].
         */
        public void printQueue() {

            Node n = head;
            StringBuilder sb = new StringBuilder();

            sb.append("[");

            while (n != tale) {
                sb.append(n.data);
                sb.append(", ");
                n = n.next;
            }
            if (N != 0)
                sb.append(n.data);

            sb.append("]");

            System.out.println(sb.toString());
        }

        @Override
        public Iterator<Item> iterator() {
            return new ReverseIterator();
        }

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

            while (n != tale) {
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

        queue.InsertInFront('2');
        queue.InsertFromBack('3');
        queue.InsertInFront('1');
        queue.InsertFromBack('4');
        System.out.println(queue);
        queue.removeFirst();
        queue.removeLast();
        queue.removeFirst();
        queue.removeFirst();
        queue.removeFirst();

    }
}